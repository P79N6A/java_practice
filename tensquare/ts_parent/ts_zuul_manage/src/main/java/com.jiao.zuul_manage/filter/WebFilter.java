package com.jiao.zuul_manage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by jiao on 12/18/2018.
 */
@Component
public class WebFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**
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
         */
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        if ( request.getRequestURI().indexOf("/admin/login")>0){
            System.out.println("登陆页面");
            return null;
        }
        if (StringUtils.isNotBlank(token) && token.startsWith("JIao ")){
            String auth = token.substring(5);
            Claims claims ;
            try {
                claims = jwtUtil.parseJWT(auth);
                if (claims != null){
                    String roles = (String) claims.get("roles");
                    if (roles.equals("admin")){
                        currentContext.addZuulRequestHeader("token",token);
                        System.out.println("token验证通过，添加了头信息：" + token);
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 终止运行
                currentContext.setSendZuulResponse(false);
                return null;
            }

        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody("无权访问");
        currentContext.setResponseStatusCode(401);
        currentContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
