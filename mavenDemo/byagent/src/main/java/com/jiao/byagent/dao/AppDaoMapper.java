package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.byagent.pojo.AppVo;
import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Emp;

import java.util.List;

/**
 * Created by jiao on 2018/10/21.
 */
public interface AppDaoMapper extends Mapper<App> {
    /**
    * @Description: 根据员工查询未处理的异动申请
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/22
    */
    List<App> findByEmp(Emp emp) throws Throwable;


    /**
    * @Description: 根据经理查询所有的申请
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    List<AppVo> findByMgr(int mgr_id) throws Throwable;



}
