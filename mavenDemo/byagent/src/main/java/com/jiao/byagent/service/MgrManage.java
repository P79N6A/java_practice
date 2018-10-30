package com.jiao.byagent.service;

import com.jiao.byagent.pojo.PayVO;
import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Emp;

import java.util.List;

/**
 * Created by jiao on 2018/10/23.
 */
public interface MgrManage {
    /**
     * 新增员工
     * @param user 新增的员工名
     * @param pass 员工的初始密码
     * @param salary 员工的薪水
     */
    void addEmp(String user , String pass , double salary ,String mgr)
            throws Throwable;


    /**
     * 根据经理返回所有的部门上个月工资
     * @param mgr 新增的员工名
     * @return 部门上个月工资
     */
    List<PayVO> getSalaryByMgr(String mgr)throws Throwable;

    /**
     * 根据经理返回该部门的全部员工
     * @param mgr 经理名
     * @return 经理的全部下属
     */
    List<Emp> getEmpsByMgr(String mgr)throws Throwable;

    /**
     * 根据经理返回该部门的没有批复的申请
     * @param mgr 经理名
     * @return 该部门的全部申请
     */
    List<App> getAppsByMgr(String mgr)throws Throwable;

    /**
     * 处理申请
     * @param appid 申请ID
     * @param mgrName 经理名字
     * @param result 是否通过
     */
    void check(int appid, String mgrName, boolean result) throws Throwable;
}
