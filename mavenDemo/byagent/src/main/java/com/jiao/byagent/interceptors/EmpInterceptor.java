package com.jiao.byagent.interceptors;

import com.jiao.byagent.controller.WebConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jiao on 2018/10/29.
 */

public class EmpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Object attribute = session.getAttribute(WebConstant.LEVEL);
        if (attribute != null && ((attribute.equals(WebConstant.MGR_LEVEL))|| (attribute.equals(WebConstant.EMP_LEVEL)))){

            return true;
        }else {
            httpServletRequest.setAttribute("msg","没有权限请访问");
            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
