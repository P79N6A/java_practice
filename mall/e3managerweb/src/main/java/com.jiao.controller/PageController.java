package com.jiao.controller;

import com.jiao.common.pojo.DataGridResult;
import com.jiao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiao on 2018/11/8.
 */
@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/rest/page/{page}")
    public String showRestPage(@PathVariable String page){
        return page;
    }

    /**
    * @Description: 商品列表分页查询
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/11/8
    */
    @ResponseBody
    @RequestMapping("/item/list")
    public DataGridResult getItemList(int page ,int rows){
        DataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }

}
