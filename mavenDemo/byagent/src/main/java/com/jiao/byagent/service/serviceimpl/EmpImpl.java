package com.jiao.byagent.service.serviceimpl;

import com.jiao.byagent.dao.*;
import com.jiao.byagent.service.EmpManage;
import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Pay;
import com.jiao.proxy.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * Created by jiao on 2018/10/23.
 */
@Service
public class EmpImpl implements EmpManage {
        @Autowired
        AppDaoMapper appDaoMapper;
        @Autowired
        AttendDaoMapper attendDaoMapper;
        @Autowired
        CheckDaoMapper checkDaoMapper;
        @Autowired
        EmpDapMapper empDapMapper;
        @Autowired
        MgrDaoMapper mgrDaoMapper;
        @Autowired
        PayDaoMapper payDaoMapper;
        @Autowired
        TypeDaoMapper typeDaoMapper;
    @Override
    public int validLogin(String user, String pass) throws Throwable{
        Emp emp = empDapMapper.findByNameAndPass(user, pass);

        if(emp == null){
            return LOGIN_FAIL;
        }
        else if(mgrDaoMapper.selectByPrimaryKey(emp.getEmpId())!=null){
            return LOGIN_MGR;
        }else {
            return LOGIN_EMP;
        }

    }

    /** 
    * @Description: 每天7点为每个员工插入一条旷工记录
    * @Param: [] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/23 
    */ 
    @Override
    public void autoPunch() {
        List<Emp> emps = empDapMapper.selectAll();
        Attend attend = new Attend();
        Condition condition = new Condition(Type.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("typeName","旷工");
        condition.and(criteria);
        Type type = typeDaoMapper.selectByCondition(condition).get(0);
        attend.setTypeId(type.getTypeId());
        attend.setIsCome(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dutyday = simpleDateFormat.format(new Date());
        attend.setDutyDay(dutyday);
        attend.setPunchTime(new Date());
        emps.forEach(t->{
            attend.setEmpId(t.getEmpId());
            attendDaoMapper.insert(attend);
        });
    }

    /** 
    * @Description:  自动结算工资，每月1号，结算上个月工资
    * @Param: [] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/23 
    */ 
    @Override
    public void autoPay() {
        List<Emp> emps = empDapMapper.selectAll();
        emps.forEach(t->{

        });

    }

    @Override
    public int validPunch(String user, String dutyDay) {
        return 0;
    }

    @Override
    public int punch(String user, String dutyDay, boolean isCome) {
        return 0;
    }

    @Override
    public List<Pay> empSalary(String empName) {
        return null;
    }

    @Override
    public List<Attend> unAttend(String empName) {
        return null;
    }

    @Override
    public List<Type> getAllType() {
        return null;
    }

    @Override
    public boolean addApplication(int attId, int typeId, String reason) {
        return false;
    }
}
