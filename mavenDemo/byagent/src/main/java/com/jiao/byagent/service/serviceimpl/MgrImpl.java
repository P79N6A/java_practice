package com.jiao.byagent.service.serviceimpl;

import com.jiao.byagent.dao.*;
import com.jiao.byagent.pojo.MgrVO;
import com.jiao.byagent.pojo.PayVO;
import com.jiao.byagent.service.MgrManage;
import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Check;
import com.jiao.proxy.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Emp emp = new Emp();
        emp.setEmpName(user);
        emp.setEmpPass(pass);
        emp.setEmpSalary(salary);
        emp.setMgrId(byName.getEmpId());
        int insert = empDapMapper.insert(emp);
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
            pays.add(byEmpAndMonth);
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
        Check check = new Check();
        check.setCheckResult(result);
        check.setAppId(appid);
        Emp byName = empDapMapper.findByName(mgrName);
        check.setMgrId(byName.getEmpId());
        checkDaoMapper.insert(check);
    }
}
