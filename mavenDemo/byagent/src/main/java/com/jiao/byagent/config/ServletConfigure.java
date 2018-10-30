package com.jiao.byagent.config;

import com.jiao.byagent.controller.AuthImg;
import com.jiao.byagent.interceptors.EmpInterceptor;
import com.jiao.byagent.interceptors.MgrInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiao on 2018/10/26.
 */

@Configuration
public class ServletConfigure {

    /**
     * 代码注册servlet(不需要@ServletComponentScan注解)
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new AuthImg(), "/servlet/auth");// ServletName默认值为首字母小写，即myServlet1
    }
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/signin/index");
                registry.addViewController("/login").setViewName("/signin/index");
//                registry.addViewController("/index.html").setViewName("login");
//                registry.addViewController("/main.html").setViewName("dashboard");
                registry.addViewController("/emp/punch").setViewName("/emp/punch");
                registry.addViewController("/emp/attend").setViewName("/emp/attend");
                registry.addViewController("/emp/salary").setViewName("/emp/salary");
                registry.addViewController("/mgr/reapp").setViewName("/mgr/reapp");
                registry.addViewController("/mgr/manage").setViewName("/mgr/manage");
                registry.addViewController("/mgr/add").setViewName("/mgr/add");
                registry.addViewController("/mgr/presalary").setViewName("/mgr/presalary");
                registry.addViewController("/mgr/punch").setViewName("/mgr/punch");
                registry.addViewController("/mgr/salary").setViewName("/mgr/salary");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//              .excludePathPatterns("/index.html","/","/user/login","/login","/signin/index.html","/signin/index","/shouye");
   registry.addInterceptor(new EmpInterceptor()).addPathPatterns("/emp/**");
   registry.addInterceptor(new MgrInterceptor()).addPathPatterns("/mgr/**");
            }
        };
        return adapter;
    }



}
