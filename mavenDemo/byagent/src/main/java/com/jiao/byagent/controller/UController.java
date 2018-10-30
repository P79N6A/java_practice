package com.jiao.byagent.controller;

import com.jiao.byagent.service.EmpManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.jiao.byagent.service.EmpManage.LOGIN_EMP;
import static com.jiao.byagent.service.EmpManage.LOGIN_MGR;

/**
 * Created by jiao on 2018/10/29.
 */
@Controller
public class UController {
    @Autowired
    EmpManage empImpl;

    @Autowired
    EmpManage mgrImpl;


    @PostMapping("/validLogin")
    public String login(HttpServletRequest request, @RequestParam("name") String name , @RequestParam("pass") String pass , @RequestParam("code") String code ) throws Throwable {
        // 查找数据库
        int i = empImpl.validLogin(name, pass);
        HttpSession session = request.getSession();
        String rand = (String) session.getAttribute("rand");
        if(code.equalsIgnoreCase(rand) ){
            if (i == LOGIN_EMP){
                session.setAttribute(WebConstant.LEVEL,WebConstant.EMP_LEVEL);
                session.setAttribute(WebConstant.USER,name);
                return "emp/index";
            }
            else if(i==LOGIN_MGR){
                session.setAttribute(WebConstant.LEVEL,WebConstant.MGR_LEVEL);
                session.setAttribute(WebConstant.USER,name);
                return "mgr/index";
            }
            else{
                request.setAttribute("msg","用户名或者密码错误");
                return "signin/index";
            }
        }else {
            request.setAttribute("msg","验证码错误");
            return "signin/index";
        }

    }


    @RequestMapping("/logout")
    public String login(HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        session.invalidate();
        return "signin/logout";
    }
}
