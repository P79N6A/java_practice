package com.jiao.ts_base.controller;

import com.jiao.ts_base.pojo.Label;
import com.jiao.ts_base.pojo.PageLabel;
import com.jiao.ts_base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiao on 12/6/2018.
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 标签全部列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> lists = labelService.findAll();
        Result result = new Result(true, StatusCode.OK, "查询成功");
        result.setData(lists);
        return result;
    }
    /**
     * 增加标签
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    /**
     * 修改标签
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label ,@PathVariable String labelId){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    /**
     * 删除标签
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "/{labelId}")
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        System.out.println("第3个");
        Label label = labelService.findById(labelId);
        Result result = new Result(true, StatusCode.OK, "根据ID查询成功");
        result.setData(label);
        return result;
     }
    /**
     * 推荐标签列表
     */
    @RequestMapping(method = RequestMethod.GET,value = "/toplist")
    public Result findTopList(){
        return new Result(true, StatusCode.OK,"查询推荐列表成功");
    }

    /**
     * 有效标签列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result findList(){
        return new Result(true, StatusCode.OK,"查询有效标签列表成功");
    }
    /**
     * 标签分页
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findPage(@PathVariable int page , @PathVariable int size, @RequestBody PageLabel pageLabel){
        Page<Label> pages = labelService.findPage(pageLabel, page, size);
        return new Result(true, StatusCode.OK,"分页查询成功",new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }
    /**
     * 标签分页
     */
    @RequestMapping(value = "/search" , method = RequestMethod.POST)
    public Result fingPageNoCondition(@RequestBody PageLabel pageLabel){
        List<Label> list = labelService.findSearch(pageLabel);
        return new Result(true, StatusCode.OK,"条件查询成功",list);
    }

}
