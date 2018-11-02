package com.jiao.byagent.service.serviceimpl;


import com.jiao.byagent.dao.*;
import com.jiao.byagent.pojo.AppVo;
import com.jiao.byagent.pojo.MgrVO;
import com.jiao.byagent.pojo.PayVO;
import com.jiao.byagent.service.MgrManage;
import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Check;
import com.jiao.proxy.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by jiao on 2018/10/25.
 */
@Service
public class MgrImpl implements MgrManage {
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

    /** 
    * @Description:  新增员工
    * @Param: [user, pass, salary, mgr] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public void addEmp(String user, String pass, double salary, String mgr) throws Throwable {
        Emp byName = empDapMapper.findByName(mgr);
        Emp byName1 = empDapMapper.findByName(user);
        Emp emp = new Emp();
        if(!ObjectUtils.isEmpty(byName1)){
            emp.setEmpId(byName1.getEmpId());
        }
        emp.setEmpName(user);
        emp.setEmpPass(pass);
        emp.setEmpSalary(salary);
        emp.setMgrId(byName.getEmpId());
        emp.setEmpStatus(true);
        if(!ObjectUtils.isEmpty(byName1)){
            empDapMapper.updateByPrimaryKey(emp);
        }else {
            empDapMapper.insert(emp);
        }

    }
    /** 
    * @Description:  根据经理返回所有的部门上个月工资
    * @Param: [mgr] 
    * @return: java.util.List<com.jiao.proxy.pojo.Pay> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<PayVO> getSalaryByMgr(String mgr) throws Throwable {
        List<PayVO> pays = new ArrayList<>();
        MgrVO byName1 = mgrDaoMapper.findByName(mgr);
        List<Emp> byMgr = empDapMapper.findByMgr(byName1.getMgr());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String payMonth = simpleDateFormat.format(new Date());
        byMgr.forEach(t->{
            PayVO byEmpAndMonth = null;
            try {
                byEmpAndMonth = payDaoMapper.findByEmpAndMonth(t, payMonth);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            if(byEmpAndMonth!=null){
                pays.add(byEmpAndMonth);
            }

        });
        return pays;
    }
    /** 
    * @Description:  根据经理返回该部门的全部员工
    * @Param: [mgr] 
    * @return: java.util.List<com.jiao.proxy.pojo.Emp> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<Emp> getEmpsByMgr(String mgr) throws Throwable {
        MgrVO byName1 = mgrDaoMapper.findByName(mgr);
        List<Emp> byMgr = empDapMapper.findByMgr(byName1.getMgr());
        return byMgr;
    }
    /** 
    * @Description:  根据经理返回该部门的没有批复的申请
    * @Param: [mgr] 
    * @return: java.util.List<com.jiao.proxy.pojo.App> 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public List<App> getAppsByMgr(String mgr) throws Throwable {
        List<App> uncheckedAppByMgr = checkDaoMapper.findUncheckedAppByMgr(mgr);
        return uncheckedAppByMgr;
    }
    /** 
    * @Description: 处理申请
    * @Param: [appid, mgrName, result] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/25 
    */ 
    @Override
    public void check(int appid, String mgrName, boolean result) throws Throwable {
        Check check;
        Condition condition = new Condition(Check.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("appId",appid);
        condition.and(criteria);
        List<Check> checks = checkDaoMapper.selectByCondition(condition);
        Emp byName = empDapMapper.findByName(mgrName);
        App app = appDaoMapper.selectByPrimaryKey(appid);
        Attend byId = attendDaoMapper.findById(app.getAttendId());

        app.setAppResult(result);
        if (checks.size()>0){
            check = checks.get(0);
            check.setCheckResult(result);
         if (result)
             byId.setTypeId(app.getTypeId());
            checkDaoMapper.updateByPrimaryKey(check);
        }
        else{
            check = new Check();
            check.setMgrId(byName.getEmpId());
            check.setAppId(appid);
            check.setCheckResult(result);
            if (result)
                byId.setTypeId(app.getTypeId());
            checkDaoMapper.insert(check);
        }
        appDaoMapper.updateByPrimaryKey(app);
        attendDaoMapper.updateByPrimaryKey(byId);
    }

    /**
    * @Description: 根据经理名查经理id
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    @Override
    public int findId(String mgr) throws Throwable {
        int id = mgrDaoMapper.findIdByName(mgr);
        return id;
    }

    @Override
    public List<AppVo> findByMgrId(int id) throws Throwable {
        List<AppVo> byMgr = appDaoMapper.findByMgr(id);
        return byMgr;
    }

    @Override
    public Check findCheckByid(int appid) throws Throwable {
        Condition condition = new Condition(Check.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("app_id",appid);
        condition.and(criteria);
        List<Check> checks = checkDaoMapper.selectByCondition(condition);
        if (checks.size()>0)
            return checks.get(0);
        else
            return  new Check();
    }

    @Override
    public void deleteById(int id) throws Throwable {

        Emp emp = empDapMapper.selectByPrimaryKey(id);
        emp.setEmpStatus(false);
        empDapMapper.updateByPrimaryKey(emp);
    }

}
