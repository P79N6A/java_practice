package com.jiao.controller;

import com.jiao.common.pojo.EasyUITreeNode;
import com.jiao.common.utils.FastDFSClient;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbItemDesc;
import com.jiao.pojo.TbItemParamItem;
import com.jiao.service.ItemCatService;
import com.jiao.service.ItemDesc;
import com.jiao.service.ItemParamItemService;
import com.jiao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiao on 2018/11/6.
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private ItemDesc itemDesc;

    @Autowired
    private ItemParamItemService itemParamItemService;

    // 绑定properties中配置的属性
    @Value("${IMAGE_SERVER_URL}")
    String IMAGE_SERVER_URL;



    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping(value = "/item/cat/list",method = RequestMethod.POST)
    @ResponseBody
    private List<EasyUITreeNode> getCatList(@RequestParam(name = "id",defaultValue = "0") long id) {
        List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(id);
        return itemCatList;
    }

    /** 
    * @Description: 图片上传 
    * @Param: [uploadFile] 
    * @return: java.lang.String 
    * @Author: Mr.Jiao
    * @Date: 2018/11/12 
    */ 
    @RequestMapping("/pic/upload")
    @ResponseBody
    private String uploadFile(MultipartFile uploadFile){
        try {
            // 使用fastdfs上传工具类
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            // 获取文件原扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extname = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            String s = fastDFSClient.uploadFile(uploadFile.getBytes(), extname);
            // 拼接图片地址
            String url =  IMAGE_SERVER_URL+s;
            // 返回数据
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            String s1 = JsonUtils.objectToJson(result);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("error",0);
            map.put("url","上传失败");
            String s1 = JsonUtils.objectToJson(map);
            return s1;
        }
    }
    
    /** 
    * @Description: 增加商品
    * @Param:  
    * @return:  
    * @Author: Mr.Jiao
    * @Date: 2018/11/12 
    */
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem, String desc){
        return  itemService.addItem(tbItem,desc);
    }

    /**
    * @Description: 加载商品描述
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/11/12
    */
    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable long id){
        TbItemDesc itemDesc = this.itemDesc.getItemDesc(id);
        Map map = new HashMap();
        map.put("itemDesc",itemDesc.getItemDesc());
        TaotaoResult ok = TaotaoResult.ok(map);
        return ok;
    }

    /**
    * @Description: 加载商品规格
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/11/13
    */
    @RequestMapping("/rest/item/param/item/query/{id}")
    @ResponseBody
    public TaotaoResult getItemParamItem(@PathVariable long id){
        TbItemParamItem itemParamItem = itemParamItemService.getItemParamItem(id);
        Map map = new HashMap();
        if(itemParamItem != null){
        map.put("id",itemParamItem.getId());
        map.put("paramData",itemParamItem.getParamData());}
        TaotaoResult ok = TaotaoResult.ok(map);
        return ok;
    }

    /**
    * @Description: 编辑商品提交
    * @Param:
    * @return:
    * @Author: Mr.Jiao
    * @Date: 2018/11/13
    */
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc){
      return itemService.update(item,desc);
    }
}


