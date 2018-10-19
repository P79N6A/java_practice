package com.jiao.byagent.config;

import com.jiao.proxy.aop.Aop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiao on 2018/10/19.
 */
@Configuration
public class Config {
    @Bean
    public Aop getAop(){
        Aop aop = new Aop();
        return aop;
    }
}
