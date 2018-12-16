package com.jiao.friend.service;

import com.jiao.friend.dao.FriendDao;
import com.jiao.friend.dao.NoFriendDao;
import com.jiao.friend.pojo.Friend;
import com.jiao.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiao on 12/14/2018.
 */
@Transactional
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;
//    @Autowired
//    private UserClient userClient;




    /**
     *  根据userid和friendid查找数据
     *  能查到 说明已经是好友关系 发返回重复添加
     *  否则新建一条friend记录
     *  再根据friend 和 userID 查找数据库
     *  有则更新 双方是否喜欢为1
     *  否则 直接将friend插入数据库中
     *  在将userid的user关注数+1
     *  friendid的user粉丝数+1
     */
    public Integer addFriend(String userid, String friendid) {
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null){
            return 0;
        }
        friend = friendDao.findByUseridAndFriendid(friendid, userid);
        Friend newfriend = new Friend();
        newfriend.setFriendid(friendid);
        newfriend.setUserid(userid);
        if (friend == null){
            newfriend.setIslike("0");
            friendDao.save(newfriend);
        }else {
            newfriend.setIslike("1");
           friendDao.save(newfriend);
           friendDao.updateIslike("1",friend.getUserid(),friend.getFriendid());
        }
        return 1;

    }

    public int addNOFriend(String userid, String friendid) {
        /**
         * 添加非好友
         * 根据userid和friendid查询nofriend表  找到数据  返回0 不能重复添加非好友
         * 否则 在nofriend表中添加一条数据   返回1  显示添加成功
         */
        NoFriend nofriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if(nofriend != null){
            return 0;
        }
        nofriend =  new NoFriend();
        nofriend.setFriendid(friendid);
        nofriend.setUserid(userid);
        noFriendDao.save(nofriend);
        return 1;
    }


    public int deleteFriend(String userid, String friendid) {
        /**
         * 删除好友
         * 先找到记录 没有找到 直接返回
         * 找到 直接删除
         * 更新userid的关注数
         * 更新friendid的粉丝数
         * 将friendid到userid的记录如果存在  将islike改成0
         * 并在nofriend中插入一条userid道friendid的记录
         */
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        Friend newfriend = friendDao.findByUseridAndFriendid(friendid, userid);
        if(friend == null){
            return 0;
        }
        if(newfriend != null ){
            friendDao.updateIslike("0",newfriend.getUserid(),newfriend.getFriendid());
        }
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        friendDao.delete(friend);
        return 1;
    }
}
