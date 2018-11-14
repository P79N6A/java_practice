package com.jiao.controller;

import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.pojo.EasyUITreeNode;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.content.service.ContentCatgoryService;
import com.jiao.content.service.ContentService;
import com.jiao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jiao on 2018/11/13.
 */
@Controller
public class ContentCatController {
    @Autowired
    ContentCatgoryService contentCatgoryService;

    @Autowired
    ContentService contentService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCats(@RequestParam(name="id",defaultValue = "0") long id){
        return contentCatgoryService.getContentCatgoryList(id);
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult getContentCats(long parentId,String name){
        return contentCatgoryService.addContentCatgory(parentId,name);
    }
    /** 
    * @Description: 内容管理分页查询
    * @Param:  
    * @return:  
    * @Author: Mr.Jiao
    * @Date: 2018/11/14 
    */
    // long categoryId ,int page, int rows
    @RequestMapping("/content/query/list")
    @ResponseBody
    public DataGridResult queryContentList(long categoryId ,int page, int rows){
        return contentService.getContentList( categoryId , page,  rows);
    }
    
    /** 
    * @Description: 新增内容
    * @Param:  
    * @return:  
    * @Author: Mr.Jiao
    * @Date: 2018/11/14 
    */
    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult saveContent(TbContent tbContent){
        return contentService.addContent(tbContent);

    }


}
