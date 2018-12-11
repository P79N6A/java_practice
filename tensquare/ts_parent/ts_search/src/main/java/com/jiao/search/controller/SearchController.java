package com.jiao.search.controller;

import com.jiao.search.pojo.SearchArticle;
import com.jiao.search.service.SearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiao on 12/11/2018.
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 增加
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody SearchArticle searchArticle){
        searchService.save(searchArticle);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    /**
     *  /article/search/{keyword}/{page}/{size}
     文章分页
     */
    @RequestMapping(value = "/search/{keyword}/{page}/{size}",method = RequestMethod.GET)
    public Result findByContentAndTitleLike(@PathVariable  String keyword,@PathVariable int page ,@PathVariable int size){


        Page<SearchArticle> pages = searchService.findByContentAndTitleLike(keyword, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }

}
