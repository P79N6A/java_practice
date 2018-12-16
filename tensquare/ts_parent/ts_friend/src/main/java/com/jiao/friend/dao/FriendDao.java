package com.jiao.friend.dao;

import com.jiao.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by jiao on 12/14/2018.
 */
@Repository
public interface FriendDao  extends JpaRepository<Friend,String>{

    /**
     * 根據userid和friendid查找friend記錄
     * @param userid
     * @param friendid
     * @return
     */
    Friend findByUseridAndFriendid(String userid,String friendid);

    /**
     * 根據userid和friendid更新friend記錄
     */
    @Modifying
    @Query(value = "UPDATE tb_friend  f SET f.islike = ?  WHERE f.userid = ? AND f.friendid = ?",nativeQuery = true)
    void updateIslike(String islike,String userid ,String friendid);
}
