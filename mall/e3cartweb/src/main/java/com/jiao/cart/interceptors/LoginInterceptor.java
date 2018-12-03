package com.jiao.cart.interceptors;

import com.jiao.common.utils.CookieUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbUser;
import com.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @Autowired
    private RegisterService registerService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 检查是否有token 没有直接放行
        String token = CookieUtils.getCookieValue(httpServletRequest, TOKEN_KEY);
        if(StringUtils.isBlank(token)){
            return true;
        }
        // 检查token是否过期 过期直接放行
        // 根据token从redis中获取user信息
        TaotaoResult taotaoResult = registerService.userToken(token);
        if ( taotaoResult.getStatus() != 200 ) {
            return true;
        }
        TbUser data = (TbUser) taotaoResult.getData();
        httpServletRequest.setAttribute("user",data);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
