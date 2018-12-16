package com.jiao.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jiao.friend.pojo.NoFriend;

/**
 * Created by jiao on 12/14/2018.
 */

@Repository
public interface NoFriendDao  extends JpaRepository<NoFriend , String>{
    /**
     * 根据userid和friendid查找nofriend记录
     */
    NoFriend findByUseridAndFriendid(String userid , String friendid);
}
