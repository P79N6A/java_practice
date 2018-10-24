package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Mgr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
public interface EmpDapMapper extends Mapper<Emp> {
    /** 
    * @Description: 根据用户名密码查询员工 
    * @Param:  
    * @return:  
    * @Author: Mr.Jiao
    * @Date: 2018/10/22 
    */
    Emp findByNameAndPass(@Param("name") String name,@Param("pass") String pass) throws Throwable;
    
    
    /** 
    * @Description: 根据用户名查询员工
    * @Param:  
    * @return:  
    * @Author: Mr.Jiao
    * @Date: 2018/10/22 
    */
    Emp findByName(String name) throws Throwable;

    /**
    * @Description: 根据经理查询员工
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    List<Emp> findByMgr(Mgr mgr) throws Throwable;
}
