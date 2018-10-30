package com.jiao.byagent.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiao on 2018/10/26.
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /** 
    * @Description: action方法执行之前 
    * @Param: [httpServletRequest, httpServletResponse, o] 
    * @return: boolean 
    * @Author: Mr.Jiao
    * @Date: 2018/10/26 
    */ 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
            o) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            request.getSession().setAttribute("msg1","测试session");
            request.getRequestDispatcher("/shouye").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }
    }

    /** 
    * @Description:  action方法执行完成  生成视图之前
    * @Param: [httpServletRequest, httpServletResponse, o, modelAndView] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/26 
    */ 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    /** 
    * @Description: 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源 
    * @Param: [httpServletRequest, httpServletResponse, o, e] 
    * @return: void 
    * @Author: Mr.Jiao
    * @Date: 2018/10/26 
    */ 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
