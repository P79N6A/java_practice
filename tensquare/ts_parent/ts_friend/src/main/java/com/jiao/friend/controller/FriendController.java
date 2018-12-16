package com.jiao.friend.controller;

import com.jiao.friend.client.UserClient;
import com.jiao.friend.service.FriendService;
import com.netflix.discovery.converters.Auto;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiao on 12/14/2018.
 */
@RestController
@RequestMapping("/friend")
@CrossOrigin
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;




    /**
     * /friend/like/{friendid}/{type}
     * 添加好友或非好友
     */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid , @PathVariable int type){
        /**
         *  检查request中有没有claim对象
         *  没有  返回没有权限
         *  有则 判断type的情况
         *  type =1 时 调用喜欢service方法  返回值0 重复添加  返回值1 添加成功
         *  type =2 时 调用不喜欢service方法  返回值0 重复不喜欢  返回值1 操作成功
         *  其他type情况  输入错误
         */
        Claims role_user = (Claims) request.getAttribute("role_user");
        if (role_user == null){
            return new Result(false, StatusCode.ERROR,"没有权限");
        }
        String userid = role_user.getId();
        if (type == 1){
            int result = friendService.addFriend(userid,friendid);
            if (result == 0){
                return new Result(false, StatusCode.ERROR,"重复添加");
            }
            if (result == 1){
                userClient.updateFans(friendid, "1");
                userClient.updateFollow(userid, "1");

                return new Result(true, StatusCode.OK,"添加成功");
            }
        }
        if (type == 2){
            /**
             * 添加非好友
             *
             */
            int result = friendService.addNOFriend(userid,friendid);
            if (result == 0){
                return new Result(false, StatusCode.ERROR,"重复添加");
            }
            if (result == 1){
                return new Result(true, StatusCode.OK,"添加成功");
            }
        }
        return new Result(false,StatusCode.ERROR,"参数错误");
    }
    /**
     * /friend/{friendid}
     * 删除好友
     */
    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable("friendid") String friendid){
        /**
         *
         */
        Claims role_user = (Claims) request.getAttribute("role_user");
        if (role_user == null){
            return new Result(false, StatusCode.ERROR,"没有权限");
        }
        String userid = role_user.getId();
        int i = friendService.deleteFriend(userid, friendid);
        if (i == 0){
            return new Result(false,StatusCode.ERROR,"参数错误");
        }
        userClient.updateFans(friendid,"-1");
        userClient.updateFollow(userid,"-1");
        return new Result(true,StatusCode.OK,"删除成功");

    }
}
