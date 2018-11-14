package com.jiao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.content.service.ContentService;
import com.jiao.mapper.TbContentMapper;
import com.jiao.pojo.TbContent;
import com.jiao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jiao on 2018/11/14.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    /**
    * @Description:  tbContent表的分页查询
    * @Param: [page, rows]
    * @return: com.jiao.common.pojo.DataGridResult
    * @Author: Mr.Jiao
    * @Date: 2018/11/14
    */
//    categoryId: 90
//    page: 1
//    rows: 20
    @Override
    public DataGridResult getContentList(long categoryId ,int page, int rows) {
        PageHelper.startPage(page,rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setTotal(tbContentPageInfo.getTotal());
        dataGridResult.setRows(tbContents);
        return dataGridResult;
    }
    /**
     tbContent = {TbContent@7779}
     id = null
     categoryId = {Long@7804} "89"
     title = "3434"
     subTitle = "434"
     titleDesc = "23423"
     url = "43432"
     pic = "http://192.168.8.132:8888/group1/M00/00/00/wKgIhFvr5EmADRy2AAAQjLR_hEw999.jpg"
     pic2 = "http://192.168.8.132:8888/group1/M00/00/00/wKgIhFvr5E-AOMtRAAAQjLR_hEw996.jpg"
     created = null
     updated = null
     content = "43243"
     */

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);
        return TaotaoResult.ok();
    }

    /**
    * @Description: 根据分类categoryId查找内容列表
    * @Param: [categoryId]
    * @return: java.util.List<com.jiao.pojo.TbContent>
    * @Author: Mr.Jiao
    * @Date: 2018/11/14
    */
    @Override
    public List<TbContent> getContentByCatId(long categoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        return tbContents;
    }
}
