package com.jiao.order.interceptor;

import com.jiao.cart.service.CartService;
import com.jiao.common.utils.CookieUtils;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;
import com.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        /**
         *  检查用户是否登录
         *  未登录跳到登录页面  登陆后 跳回该页面
         *  如果登录  检查token是否过期  如果过期  跳到登录页面  登录后 跳回该页面
         *  如果没有过期,检查cookie中是否有购物车商品，有则合并购物车数据
         *  最后跳到放行到结算页面
         */
        // 从cookie中取出token
        String token = CookieUtils.getCookieValue(httpServletRequest, "token");
        if (StringUtils.isBlank(token)){
            httpServletResponse.sendRedirect("http://vmserver.jiao.com:8010/page/login?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        // 存在token  则检查是否过期
        TaotaoResult taotaoResult = registerService.userToken(token);
        if (taotaoResult.getStatus() != 200){
            httpServletResponse.sendRedirect("http://vmserver.jiao.com:8010/page/login?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        TbUser user = (TbUser) taotaoResult.getData();
        httpServletRequest.setAttribute("user", user);
        //判断cookie中是否有购物车数据，如果有就合并到服务端。
        String jsonCartList = CookieUtils.getCookieValue(httpServletRequest, "cart", true);
        if (StringUtils.isNoneBlank(jsonCartList)) {
            //合并购物车
            cartService.mergeCartToServer(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
