package com.jiao.byagent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiao on 2018/10/19.
 */
@Controller
public class Hello {

    @RequestMapping("/hello")
    public String get(){
        return "index";
    }
}
