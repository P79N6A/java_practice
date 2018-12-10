package com.jiao.spit.controller;

import com.jiao.spit.pojo.Spit;
import com.jiao.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiao on 12/10/2018.
 */
@RestController
@CrossOrigin
@RequestMapping(("/spit"))
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 增加吐槽
      */
    @RequestMapping(method = RequestMethod.POST)
    public Result addSpit(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    /**
     * 全部列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result spit(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }
    /**
     * 根据ID查询吐槽
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    /**
     *

     修改吐槽
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    /**
     *
     根据ID删除吐槽
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     *
     吐槽点赞
     */
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        /**
         * 防止重复点赞
         * 进入方法，查询redis有没有key
         * 没有 则点赞  把状态插入redis
         * 有 则返回重复提交
         */
        String userid = "111";
        String o = (String) redisTemplate.opsForValue().get("thumbup:" + userid + ":" + spitId);
        if (StringUtils.isNotBlank(o)){
            return new Result(false, StatusCode.ERROR,"重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup:" + userid + ":" + spitId,"123");
        return new Result(true, StatusCode.OK,"点赞成功");
    }
    /**
     *根据上级ID查询吐槽数据（分页）
     */
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid , @PathVariable int page ,@PathVariable int size){
        Page<Spit> spits = spitService.commment(parentid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(spits.getTotalElements(),spits.getContent()));
    }
}
