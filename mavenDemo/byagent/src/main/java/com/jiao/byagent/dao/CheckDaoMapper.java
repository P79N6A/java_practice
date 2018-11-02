package com.jiao.byagent.dao;

import com.jiao.byagent.base.Mapper;
import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Check;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
public interface CheckDaoMapper extends Mapper<Check> {
    /**
    * @Description: 根据经理查询没有批复的申请
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/25
    */
  List<App> findUncheckedAppByMgr(String name) throws Throwable;



}
