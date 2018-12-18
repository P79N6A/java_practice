package com.jiao.zuul_front.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiao on 12/18/2018.
 */
@Component
public class WebFilter extends ZuulFilter{
    // 什么时候拦截
    @Override
    public String filterType() {
    // 前置过滤器 可以在请求被路由之前调用
        return "pre";
    }

    @Override
    public int filterOrder() {
    // 多个过滤器之间的优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
    // 过滤器是否生效

        return true;
    }
    // 过滤器的方法
    @Override
    public Object run() throws ZuulException {
        // 如果有token 直接转发
        System.out.println("zuul过滤器。。。");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");
        if (token!=null && !"".equals(token)){
            currentContext.addZuulRequestHeader("token",token);
        }
        return null;
    }
}
