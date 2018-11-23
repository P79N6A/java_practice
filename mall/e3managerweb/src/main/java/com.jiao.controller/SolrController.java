package com.jiao.controller;

import com.jiao.common.utils.TaotaoResult;
import com.jiao.research.GetSolrResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiao on 11/20/2018.
 */
@Controller
public class SolrController {

    @Autowired
    GetSolrResult getSolrResult;

    /**
     * @Description: 商品列表分页查询
     * @Param:
     * @return:
     * @Author: Mr.Jiao
     * @Date: 2018/11/8
     */
    @ResponseBody
    @RequestMapping("/index/item/import")
    public TaotaoResult getItemList(){
       return getSolrResult.getSolrResult();
    }
}
