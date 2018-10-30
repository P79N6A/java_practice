package com.jiao.byagent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiao on 2018/10/25.
 */
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc 不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
        public void addViewControllers(ViewControllerRegistry registry) {
    // super.addViewControllers(registry);
    //浏览器发送 /atguigu 请求来到 success
//            registry.addViewController("/atguigu").setViewName("success");
        }
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
//    @Bean //将组件注册在容器
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
//        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/login").setViewName("/signin/index.html");
////                registry.addViewController("/index.html").setViewName("login");
//            }
//        };
//        return adapter;
//    }




}