package com.jiao.byagent.service;

import com.jiao.byagent.pojo.AttendVo;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Pay;
import com.jiao.proxy.pojo.Type;

import java.util.List;

/**
 * Created by jiao on 2018/10/23.
 */
public interface EmpManage {

    //登陆失败
    public static final int LOGIN_FAIL = 0;
    //以普通员工登陆
    public static final int LOGIN_EMP = 1;
    //以经理登陆
    public static final int LOGIN_MGR = 2;

    //不能打卡
    public static final int NO_PUNCH = 0;
    //只能上班打卡
    public static final int COME_PUNCH = 1;
    //只能下班打卡
    public static final int LEAVE_PUNCH = 2;
    //既可以上班，也可以下班打卡
    public static final int BOTH_PUNCH = 3;

    //打卡失败
    public static final int PUNCH_FAIL = 0;
    //已经打卡
    public static final int PUNCHED = 1;
    //打卡成功
    public static final int PUNCH_SUCC = 2;

    //以上午11点为上午时间
    public static final int AM_LIMIT = 11;


    //以上午9点之前为正常上班
    public static final int COME_LIMIT = 9;
    //以上午9~11点之间算迟到
    public static final int LATE_LIMIT = 11;
    //以下午18点之后算正常下班
    public static final int LEAVE_LIMIT = 18;
    //以上午16~18点之间算早退
    public static final int EARLY_LIMIT = 16;




    /**
     * 验证登陆
     * @param user 登陆用的用户名
     * @param pass 登陆用的密码
     * @return 登陆后的身份确认:0为登陆失败，1为登陆emp 2为登陆mgr
     */
    int validLogin(String user , String pass) throws Throwable;

    /**
     * 自动打卡，周一到周五，早上7：00为每个员工插入旷工记录
     */
    void autoPunch() throws Throwable;

    /**
     * 自动结算工资，每月1号，结算上个月工资
     */
    void autoPay() throws Throwable;


    /**
     * 验证某个员工是否可打卡
     * @param user 员工名
     * @param dutyDay 日期
     * @return 可打卡的类别
     */
    int validPunch(String user , String dutyDay) throws Throwable;

    /**
     * 打卡
     * @param user 员工名
     * @param dutyDay 打卡日期
     * @param isCome 是否是上班打卡
     * @return 打卡结果
     */
     int punch(String user , String dutyDay , boolean isCome) throws Throwable;

    /**
     * 根据员工浏览自己的工资
     * @param empName 员工名
     * @return 该员工的工资列表
     */
    List<Pay> empSalary(String empName) throws Throwable;

    /**
     * 员工查看自己的最近三天非正常打卡
     * @param empName 员工名
     * @return 该员工的最近三天的非正常打卡
     */
    List<AttendVo> unAttend(String empName) throws Throwable;

    /**
     * 返回全部的出勤类别
     * @return 全部的出勤类别
     */
    List<Type> getAllType() throws Throwable;

    /**
     * 添加申请
     * @param attId 申请的出勤ID
     * @param typeId 申请的类型ID
     * @param reason 申请的理由
     * @return 添加的结果
     */
    boolean addApplication(int attId , int typeId , String reason) throws Throwable;

    /**
    * @Description: 根据id查员工
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    Emp findEmpByEmpId(int id) throws Throwable;
}
