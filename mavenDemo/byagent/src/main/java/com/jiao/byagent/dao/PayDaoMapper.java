package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.byagent.pojo.PayVO;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Pay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
public interface PayDaoMapper extends Mapper<Pay> {
    /**
    * @Description: 根据员工查月结薪水
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    List<Pay> findByEmp (Emp emp) throws Throwable;


    /**
    * @Description: 根据员工和发薪水月查看薪水
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    PayVO findByEmpAndMonth(@Param("emp") Emp emp , @Param("month") String month) throws  Throwable;



}
