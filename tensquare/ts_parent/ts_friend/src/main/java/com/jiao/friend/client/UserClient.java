package com.jiao.friend.client;


import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiao on 12/14/2018.
 */
@FeignClient("ts-user")
public interface UserClient {
    /**
     * 更新粉丝数
     */
    @RequestMapping(value = "/user/follow/myfans/{userid}/{num}",method = RequestMethod.PUT)
    public Result updateFans(@PathVariable("userid") String userid,@PathVariable("num") String num);

    /**
     * 更新关注数
     */
    @RequestMapping(value = "/user/follow/myfollow/{userid}/{num}",method = RequestMethod.PUT)
    public Result updateFollow(@PathVariable("userid") String userid, @PathVariable("num") String num);
}
