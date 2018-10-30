package com.jiao.byagent.controller;

import com.jiao.byagent.pojo.AttendVo;
import com.jiao.byagent.service.EmpManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.jiao.byagent.service.EmpManage.*;

/**
 * Created by jiao on 2018/10/25.
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpManage empImpl;
    
    @Autowired
    EmpManage mgrImpl;

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
        return "/emp/punch";
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
        return "/emp/punch";
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
        return "/emp/punch";
    }
    /**
    * @Description: 查看三天的打卡异动
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/10/30
    */
    @RequestMapping("/unAttendList")
    public String unAttendList(HttpServletRequest request , ModelMap modelMap) throws Throwable {
        String username = (String)WebUtils.getSessionAttribute(request, WebConstant.USER);
        List<AttendVo> attends = empImpl.unAttend(username);
          modelMap.addAttribute("attends",attends);
        return "/emp/attend";
    }

}
