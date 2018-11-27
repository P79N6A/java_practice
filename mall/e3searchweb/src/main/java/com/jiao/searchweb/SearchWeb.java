package com.jiao.searchweb;

import com.jiao.common.pojo.SearchResult;
import com.jiao.research.SearchItems;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jiao on 11/21/2018.
 */
@Controller
public class SearchWeb {

    @Autowired
    SearchItems searchItems;

    @RequestMapping("/search")
    public String search(String keyword , @RequestParam(defaultValue = "1") int page , Model model)throws Exception{
        keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        SearchResult searchItems = this.searchItems.getSearchItems(keyword, page, 10);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",searchItems.getTotalPages());
        model.addAttribute("recourdCount",searchItems.getRecourdCount());
        model.addAttribute("itemList",searchItems.getItemList());
        model.addAttribute("page",page);
        return "search";
    }
}
