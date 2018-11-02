package com.jiao.byagent.controller;

import com.jiao.byagent.pojo.AppVo;
import com.jiao.byagent.pojo.PayVO;
import com.jiao.byagent.service.EmpManage;
import com.jiao.byagent.service.MgrManage;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.jiao.byagent.service.EmpManage.*;

/**
 * Created by jiao on 2018/10/29.
 */
@RequestMapping("/mgr")
@Controller
public class MgrController {
    @Autowired
    EmpManage empImpl;

    @Autowired
    MgrManage mgrImpl;

    /**
     * @Description: 员工打卡，查看是否可以打卡
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/30
     */
    @RequestMapping("/employeePunch")
    public String employeePunch(HttpServletRequest request, ModelMap modelMap) throws Throwable {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute(WebConstant.USER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dutyday = simpleDateFormat.format(new Date());
        int i = empImpl.validPunch(user, dutyday);
        modelMap.addAttribute("result",i);
        return "mgr/punch";
    }

    /**
     * @Description: 上班打卡
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/30
     */
    @RequestMapping(value = "/employeeCome",method = RequestMethod.POST)
    public  String employeeCome(HttpServletRequest request, ModelMap modelMap) throws Throwable {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute(WebConstant.USER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dutyday = simpleDateFormat.format(new Date());
        int punch = empImpl.punch(user, dutyday, true);
        String tip = null;
        if(punch == PUNCH_SUCC){
            tip = "打卡成功";
        }
        if(punch == PUNCH_FAIL){
            tip = "打卡失败";
        }
        if(punch == PUNCHED){
            tip = "重复打卡";
        }
        modelMap.addAttribute("tip",tip);
        return "mgr/punch";
    }


    /**
     * @Description: 下班打卡
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/30
     */
    @RequestMapping(value = "/employeeLeave",method = RequestMethod.POST)
    public  String employeeLeave(HttpServletRequest request, ModelMap modelMap) throws Throwable {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute(WebConstant.USER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dutyday = simpleDateFormat.format(new Date());
        int punch = empImpl.punch(user, dutyday, false);
        String tip = null;
        if(punch == PUNCH_SUCC){
            tip = "打卡成功";
        }
        if(punch == PUNCH_FAIL){
            tip = "打卡失败";
        }
        if(punch == PUNCHED){
            tip = "重复打卡";
        }
        modelMap.addAttribute("tip",tip);
        return "mgr/punch";
    }


    /**
     * @Description: 查看历史工资
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/30
     */
    @RequestMapping(value = "/empsalary")
    public ModelAndView addApp(HttpServletRequest request) throws Throwable {
        String user = (String) WebUtils.getSessionAttribute(request, WebConstant.USER);
        List<Pay> pays = empImpl.empSalary(user);
        ModelAndView modelAndView = new ModelAndView("mgr/salary");
        modelAndView.addObject("pays",pays);
        return modelAndView;
    }

    /**
     * @Description: 进入签核申请页面
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/30
     */
    @RequestMapping(value = "/reapp")
    public String reapp(HttpServletRequest request,ModelMap modelMap) throws Throwable {
        String user = (String)WebUtils.getSessionAttribute(request, WebConstant.USER);
        int id = mgrImpl.findId(user);
        List<AppVo> apps = mgrImpl.findByMgrId(id);
        modelMap.addAttribute("apps",apps);
        return "mgr/reapp";
    }

    /**
    * @Description: 经理处理考勤申请
    * @Param: [request, modelMap]
    * @return: java.lang.String
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    @RequestMapping(value = "/doapp")
    public String doapp(HttpServletRequest request,int result ,int id) throws Throwable {
     boolean res = result == 1? true : false;
     String user = (String) WebUtils.getRequiredSessionAttribute(request,WebConstant.USER);
     mgrImpl.check(id,user,res);
        return "redirect:/mgr/reapp";
    }

    /**
    * @Description:  查看部门所有员工
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    @RequestMapping("/manage")
    public String getAllEmps(HttpServletRequest request,ModelMap modelMap) throws Throwable {
        String user = (String) WebUtils.getRequiredSessionAttribute(request,WebConstant.USER);
        List<Emp> emps = mgrImpl.getEmpsByMgr(user);
        modelMap.addAttribute("emps",emps);
        return "mgr/manage";
    }

    /**
    * @Description: 新增和编辑员工
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    @RequestMapping("/addemp")
    public String addemp(HttpServletRequest request ,ModelMap modelMap , @RequestParam(value = "id",required = false) Integer id) throws Throwable {
        String user = (String) WebUtils.getRequiredSessionAttribute(request,WebConstant.USER);
        Emp emp;
        if (id ==null){
            emp = new Emp();
        }
        else {
            emp = empImpl.findEmpByEmpId(id);
        }
        modelMap.addAttribute("emp",emp);
        return "mgr/add";
    }

    /**
     * @Description: 新增和编辑员工
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/10/31
     */
    @RequestMapping(value = "/editEmp",method = RequestMethod.POST)
    public String addemp(HttpServletRequest request ,ModelMap modelMap , Emp emp) throws Throwable {
        String user = (String)WebUtils.getSessionAttribute(request,WebConstant.USER);
        mgrImpl.addEmp(emp.getEmpName(),emp.getEmpPass(),emp.getEmpSalary(),user);
        modelMap.addAttribute("msg","添加成功");
        return "redirect:/mgr/manage";
    }

    /**
    * @Description: 查看部门上月发薪
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/31
    */
    @RequestMapping("presalary")
    public String presalary(HttpServletRequest request ,ModelMap modelMap) throws Throwable {
        String user = (String)WebUtils.getSessionAttribute(request,WebConstant.USER);
        List<PayVO> pays = mgrImpl.getSalaryByMgr(user);
        modelMap.addAttribute("pays",pays);
        return "mgr/presalary";

    }
    /**
    * @Description: 删除员工
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/11/1
    */
    @PostMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") int id) throws Throwable {
          mgrImpl.deleteById(id);
        return "redirect:/mgr/manage";
    }
}
