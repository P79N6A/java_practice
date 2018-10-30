package com.jiao.byagent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jiao on 2018/10/20.
 */
@Controller
public class TestConroller {

    @RequestMapping(value = "index1", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "知识林-jiao-jiao");
        return "hello";
    }

}
