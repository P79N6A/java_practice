package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
public interface AttendDaoMapper extends Mapper<Attend> {
    /**
    * @Description: 根据员工、日期查询员工的打卡记录
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
     List<Attend> findByEmpAndDutyday(@Param("emp") Emp emp,@Param("dutyday") String dutyday) throws Throwable;

    /**
    * @Description: 根据员工、日期、上下班查询该员工的打卡记录集合
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
      List<Attend> fingByEmpAndDutydayAndCome(@Param("emp") Emp emp ,@Param("dutyday") String dutyday,@Param("isCome") boolean isCome) throws Throwable;

      /**
      * @Description: 查询员工前3天的非正常打卡
      * @Param:
      * @return:
      * @Author: Mr.Jiao
      * @Date: 2018/10/22
      */
      List<Attend> findByEmpUnAttend(@Param("emp") Emp emp , @Param("type") Type type) throws Throwable;


      /**
      * @Description: 查询员工上月的考勤绩效
      * @Param:
      * @return:
      * @Author: Mr.Jiao
      * @Date: 2018/10/23
      */

       int findPerfbByEmp(Emp emp )throws Throwable;
}
