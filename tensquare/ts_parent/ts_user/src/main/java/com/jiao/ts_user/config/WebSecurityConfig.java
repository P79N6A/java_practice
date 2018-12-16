package com.jiao.ts_user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by jiao on 12/13/2018.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *  引入spring security依赖后，所有的地址都会被spring security控制，我们只需要用到BCrypt加密解密部分，
         *  所以放开所有地址
         *  authorize： 权限的意思
         *  permitAll： 全部允许
         *  authenticated：验证，即登录
         *  and().csrf().disable()：配置防止csrf攻击失效，是利用cookie不安全的漏洞攻击，这里放开。
         */
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
