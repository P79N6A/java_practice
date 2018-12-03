package com.sso.service.impl;

import com.jiao.common.jedis.JedisClient;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.mapper.TbUserMapper;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;
import com.jiao.pojo.TbUserExample;
import com.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    TbUserMapper tbUserMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public TaotaoResult checkData(String param, int type) {
        /**
         *  type为1时检查用户是否已经被占用
         *  type为2时检查手机号是否存在
         *  type为3时检查邮箱是否存在
         */
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if(type  == 1){
            criteria.andUsernameEqualTo(param);
        }else if(type == 2){
            criteria.andPhoneEqualTo(param);
        }else if (type == 3){
            criteria.andEmailEqualTo(param);
        }else {
                return TaotaoResult.build(400, "数据类型错误");
        }


        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if (tbUsers != null && tbUsers.size() > 0){
           return TaotaoResult.ok(false);
        }
         return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult userRegister(TbUser tbUser) {
        /***
         *   先检查数据是否为空
         *   再校验用户名密码邮箱是否占用
         *   加密password
         *   补全数据
         *   插入数据
         */
        if(StringUtils.isBlank(tbUser.getUsername()) || StringUtils.isBlank(tbUser.getPassword()) || StringUtils.isBlank(tbUser.getPhone())){
            return TaotaoResult.build(400, "数据类型错误");
        }
        TaotaoResult taotaoResult = checkData(tbUser.getUsername(), 1);
        if (!(Boolean) taotaoResult.getData()){
            return TaotaoResult.build(400, "此用户名已被占用");
        }
        taotaoResult = checkData(tbUser.getPhone(), 2);
        if (!(Boolean) taotaoResult.getData()){
            return TaotaoResult.build(400, "手机号已被占用");
        }

        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(password);

        tbUserMapper.insert(tbUser);
        return taotaoResult.ok();
    }



    @Override
    public TaotaoResult userToken(String ticket) {
        String json = jedisClient.get("SESSION:" + ticket);
        //取不到用户信息，登录已经过期，返回登录过期
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "用户登录已经过期");
        }
        // 更新过期时间
        jedisClient.expire("SESSION:" + ticket, SESSION_EXPIRE);
        TbUser tbItem = JsonUtils.jsonToPojo(json, TbUser.class);
        return TaotaoResult.ok(tbItem);
    }

    @Override
    public TaotaoResult userLogin(String username, String password) {
        /**
         *   1、判断用户和密码是否正确
         *   2、如果不正确，返回登录失败
         *   3、如果正确生成token。
         *   4、把用户信息写入redis，key：token value：用户信息
         *   5、设置Session的过期时间
         *   6、把token返回
         */
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if( tbUsers == null || tbUsers.size() == 0){
            return  TaotaoResult.build(400,"用户名或者密码错误");
        }
        TbUser tbUser = tbUsers.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())){
            return  TaotaoResult.build(400,"用户名或者密码错误");
        }

        // 生成token
        String token = UUID.randomUUID().toString();
        tbUser.setPassword(null);
        jedisClient.set( "SESSION:" + token,JsonUtils.objectToJson(tbUser));
        jedisClient.expire("SESSION:" + token,SESSION_EXPIRE);
        return TaotaoResult.ok(token);
    }



}
