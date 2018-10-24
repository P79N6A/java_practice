package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.byagent.pojo.MgrVO;
import com.jiao.proxy.pojo.Mgr;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jiao on 2018/10/22.
 */
public interface MgrDaoMapper extends Mapper<Mgr> {
    /**
    * @Description: 根据用户名密码查询经理
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    MgrVO findByNameAndPass(@Param("name") String name , @Param("pass") String pass)throws Throwable;

    /**
    * @Description: 根据用户名查询经理
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    MgrVO findByName(String name) throws Throwable;


}
