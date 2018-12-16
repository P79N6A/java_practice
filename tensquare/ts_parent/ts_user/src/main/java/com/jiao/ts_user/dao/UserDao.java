package com.jiao.ts_user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jiao.ts_user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     * 根据手机查找用户
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 更新用户的粉丝数
     * @param num
     * @param userid
     */
    @Modifying
    @Query(value = "UPDATE tb_user SET fanscount = fanscount + ?  WHERE id= ? ",nativeQuery = true)
    void updateFans( String num , String userid);

    /**
     * 更新用户的关注数
     * @param num
     * @param userid
     */
    @Modifying
    @Query(value = "UPDATE tb_user SET followcount = followcount + ? WHERE id= ?",nativeQuery = true)
    void updateFollow( String num , String userid);
}
