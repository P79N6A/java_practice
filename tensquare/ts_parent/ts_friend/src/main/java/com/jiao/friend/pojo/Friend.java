package com.jiao.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jiao on 12/14/2018.
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable{
    /**
     *   `userid` varchar(20) NOT NULL COMMENT '用户ID',
     *   `friendid` varchar(20) NOT NULL COMMENT '好友ID',
     *   `islike` varchar(1) DEFAULT NULL COMMENT '是否互相喜欢',
     */
    @Id
    private String userid;

    @Id
    private String friendid;

    private String islike;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
