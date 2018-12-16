package com.jiao.ts_user.controller;

import com.jiao.ts_user.pojo.User;
import com.jiao.ts_user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * 根据id更新粉丝数
	 */
	@RequestMapping(value = "/follow/myfans/{userid}/{num}",method = RequestMethod.PUT)
	public Result updateFans(@PathVariable String userid,@PathVariable String num){
		userService.updateFans(userid,num);
		return new Result(true,StatusCode.OK,"更新成功");
	}

	/**
	 * 根据id更新关注数
	 */
	@RequestMapping(value = "/follow/myfollow/{userid}/{num}",method = RequestMethod.PUT)
	public Result updateFollow(@PathVariable String userid, @PathVariable String num){
		userService.updateFollow(userid,num);
		return new Result(true,StatusCode.OK,"更新成功");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){

		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		/**
		 *  从请求头中获取token
		 *  token 不存在  返回 权限不足
		 *  token 存在   判断token是不是以JIao开头
		 *  不是 则返回 权限不足
		 *  是 则 解析token
		 *  解析成功  判断是否是admin角色   是  则允许删除
		 *  不是则返回权限不足
		 *  解析不成功  则返回权限不足
		 */
		Claims role_admin = (Claims) request.getAttribute("role_admin");
		if (role_admin != null){
			userService.deleteById(id);
			return new Result(true,StatusCode.OK,"删除成功");
		}
		return new Result(false,StatusCode.ERROR,"权限不足");
	}

	/**
	 * /user/sendsms/{mobile}
	 * 发送手机验证码
	 */
	@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
	public Result sendsms(@PathVariable String mobile){
		/**
		 *  生成6位随机数字 lang3
		 *  控制台打印一份
		 *  redis存一份
		 *  sms队列发送一份 包括手机号  和  验证码
		 *  返回成功
		 */
		String code = RandomStringUtils.randomNumeric(6);
		System.out.println(code);
		redisTemplate.opsForValue().set("code:"+mobile,code,1, TimeUnit.HOURS);
		Map<String,String> map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("code",code);
//		rabbitTemplate.convertAndSend("sms",map);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	/**
	 * 注册
	 * /user/register/{code}
	 */
	@RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
	public Result register(@PathVariable String code , @RequestBody User user){
		/**
		 * 检查redis中的code存不存在
		 * 不存在 返回 请先获取验证码
		 * 存在 判断相不相等
		 * 不相等  返回验证码错误
		 * 相等 保存注册信息
		 */
		String redisCode = (String) redisTemplate.opsForValue().get("code:" + user.getMobile());
		if (StringUtils.isBlank(redisCode)){
			return new Result(false,StatusCode.ERROR,"请先获取验证码");
		}
		if (!StringUtils.equals(code,redisCode)){
			return new Result(false,StatusCode.ERROR,"验证码错误");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userService.add(user);
		return new Result(true,StatusCode.OK,"注册成功");
	}
	/**
	 * /user/login
	 * 登陆
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/login")
	public Result login(@RequestBody User user , HttpServletResponse response){

		User loginUser = userService.login(user);

		if (loginUser == null){
			return new Result(false,StatusCode.LOGINERROR,"登陆失败");
		}
		String token = "JIao " + jwtUtil.createJWT(loginUser.getId(), loginUser.getMobile(), "user");
		Map<String,Object> map = new HashMap();
		map.put("token",token);
		map.put("roles","user");
		response.setHeader("token",token);
		return new Result(true,StatusCode.OK,"登录成功" , map);
	}
}
