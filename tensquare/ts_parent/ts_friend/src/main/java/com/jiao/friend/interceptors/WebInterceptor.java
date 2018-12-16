package com.jiao.friend.interceptors;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiao on 12/13/2018.
 */
@Component
public class WebInterceptor implements HandlerInterceptor{


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 从request中获取token
         * 如果有  从token中获取claims
         * 如果获取到了claims  从claims中获取roles的信息
         * 则将roles信息放到request中
         * 最后放行
         */
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token) && token.startsWith("JIao ")){
            token = token.substring(5);
            Claims claims ;
            try {
                claims = jwtUtil.parseJWT(token);
                if (claims != null){
                    String roles = (String) claims.get("roles");
                    if (roles.equals("admin")){
                        request.setAttribute("role_admin",claims);
                    }
                    if (roles.equals("user")){
                        request.setAttribute("role_user",claims);
                    }
                }
            } catch (Exception e) {
               e.printStackTrace();
         }
        }
        return true;
    }
}
