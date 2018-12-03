package com.jiao.item;


import com.jiao.pojo.Item;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbItemDesc;
import com.jiao.service.ItemDesc;
import com.jiao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
public class ItemInfoController {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemDesc itemDesc;


    // http://localhost:8086/item/154296316849911.html
    @RequestMapping("/item/{itemId}")
    public String itemDetails(@PathVariable long itemId, Model model){
        TbItem itemById = itemService.getItemById(itemId);
        Item item = new Item(itemById);
        TbItemDesc itemDesc = this.itemDesc.getItemDesc(itemId);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }





}
