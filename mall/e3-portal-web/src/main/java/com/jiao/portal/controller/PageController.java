package com.jiao.portal.controller;

import com.jiao.content.service.ContentService;
import com.jiao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jiao on 2018/11/13.
 */
@Controller
public class PageController {
    @Value("${QUERY_CONTENT_BYiD}")
    private long categoryId;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        // 根据categoryId查询分类类容
        List<TbContent> tbContents = contentService.getContentByCatId(categoryId);
        modelMap.addAttribute("ad1List",tbContents);
        return "index";
    }

}
