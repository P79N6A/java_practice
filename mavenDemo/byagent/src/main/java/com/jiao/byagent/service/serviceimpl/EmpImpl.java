package com.jiao.byagent.service.serviceimpl;

import com.jiao.byagent.dao.*;
import com.jiao.byagent.pojo.AttendVo;
import com.jiao.byagent.service.EmpManage;
import com.jiao.proxy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    public void autoPay() throws Throwable{
        List<Emp> emps = empDapMapper.selectAll();
        //获取上个月时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -15);
        SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());

        for(Emp emp :emps){
            List<AttendVo> attendsByEmp = attendDaoMapper.findAttendsByEmp(emp, payMonth);
            Double empSalary = emp.getEmpSalary();
            for (AttendVo attendVo :attendsByEmp){
                empSalary+= attendVo.getType().getAmerceAmount();
            }
            Pay pay = new Pay();
            pay.setEmpId(emp.getEmpId());
            pay.setPayAmount(empSalary);
            pay.setPayMonth(payMonth);
            payDaoMapper.insert(pay);
        }
    }
    
    /** 
    * @Description:  验证某个员工是否可打卡
    * @Param: [user, dutyDay] 
    * @return: int 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */
    @Override
    public int validPunch(String user, String dutyDay) throws Throwable {
        Emp byName = empDapMapper.findByName(user);
        List<Attend> byEmpAndDutyday = attendDaoMapper.findByEmpAndDutyday(byName, dutyDay);
        Date date = new Date();
        int hours = date.getHours();
        int length = byEmpAndDutyday.size();
        if (length == 1){
            if (!byEmpAndDutyday.get(0).getIsCome()){
                if (hours < 9){
                    return COME_PUNCH;
                }else if ( hours>9 && hours <17){
                    return BOTH_PUNCH;
                }else {
                    return NO_PUNCH;
                }
            }else{
                if ( hours< 20){
                    return LEAVE_PUNCH;
                }
                return NO_PUNCH;
            }
        }else {
            return NO_PUNCH;
        }
    }

    /** 
    * @Description:  打卡
    * @Param: [user, dutyDay, isCome] 
    * @return: int 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public int punch(String user, String dutyDay, boolean isCome) throws Throwable {
        Emp emp = empDapMapper.findByName(user);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        int hour = date.getHours();
        String punchTime = simpleDateFormat.format(date);
        List<Attend> byEmpAndDutyday = attendDaoMapper.findByEmpAndDutyday(emp, dutyDay);
        Attend attend;
        if (byEmpAndDutyday.size()==1 && !byEmpAndDutyday.get(0).getIsCome()){
            if ( hour<9){
                attend = byEmpAndDutyday.get(0);
                attend.setPunchTime(date);
                attend.setIsCome(isCome);
                attend.setTypeId(1);

            }else{
                attend = byEmpAndDutyday.get(0);
                attend.setPunchTime(date);
                attend.setIsCome(isCome);
                attend.setTypeId(4);
            }
            int i = attendDaoMapper.updateByPrimaryKey(attend);
            if(i>0)
                return PUNCH_SUCC;
            else
                return  PUNCH_FAIL;
        }else if(byEmpAndDutyday.size()==1 && byEmpAndDutyday.get(0).getIsCome()){
            if (isCome == true){
                return PUNCHED;
            }else{
                attend = new Attend();
                if (  hour <18){
                    attend.setPunchTime(date);
                    attend.setIsCome(isCome);
                    attend.setTypeId(5);
                    attend.setEmpId(emp.getEmpId());

                }else{
                    attend.setPunchTime(date);
                    attend.setIsCome(isCome);
                    attend.setTypeId(1);
                    attend.setEmpId(emp.getEmpId());
                }
                attend.setDutyDay(dutyDay);
                int insert = attendDaoMapper.insert(attend);
                if(insert>0)
                    return PUNCH_SUCC;
            }

        }else if (byEmpAndDutyday.size()==2){
            return PUNCHED;
        }

        return  PUNCH_FAIL;
    }

    
    /** 
    * @Description:  根据员工浏览自己的工资
    * @Param: [empName] 
    * @return: java.util.List<com.jiao.proxy.pojo.Pay> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<Pay> empSalary(String empName) throws Throwable {
        Emp emp = empDapMapper.findByName(empName);
        List<Pay> byEmp = payDaoMapper.findByEmp(emp);
        return byEmp;
    }

    /** 
    * @Description:  员工查看自己的最近三天非正常打卡
    * @Param: [empName] 
    * @return: java.util.List<com.jiao.proxy.pojo.Attend> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<AttendVo> unAttend(String empName) throws Throwable {
        Emp emp = empDapMapper.findByName(empName);
        Type type = new Type();
        type.setTypeId(1);
        List<AttendVo> byEmpUnAttend = attendDaoMapper.findByEmpUnAttend(emp,type);
        return  byEmpUnAttend;
    }
    
    /** 
    * @Description:  返回全部的出勤类别
    * @Param: [] 
    * @return: java.util.List<com.jiao.proxy.pojo.Type> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<Type> getAllType() {
        List<Type> types = typeDaoMapper.selectAll();
        return types;     
    }

    /** 
    * @Description:  添加申请
    * @Param: [attId, typeId, reason] 
    * @return: boolean 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public boolean addApplication(int attId, int typeId, String reason) {
        App app = new App();
        app.setAttendId(attId);
        app.setTypeId(typeId);
        app.setAppReason(reason);
        int insert = appDaoMapper.insert(app);
        if (insert>0)
            return true;
        else
            return false;
    }
}
