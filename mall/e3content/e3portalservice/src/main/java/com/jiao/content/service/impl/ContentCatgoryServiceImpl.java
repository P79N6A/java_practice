package com.jiao.content.service.impl;

import com.jiao.common.pojo.EasyUITreeNode;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.content.service.ContentCatgoryService;
import com.jiao.mapper.TbContentCategoryMapper;
import com.jiao.pojo.TbContentCategory;
import com.jiao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by jiao on 2018/11/13.
 */
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatgoryList(long id) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategories){
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbContentCategory.getId());
            easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            easyUITreeNode.setText(tbContentCategory.getName());
            easyUITreeNodes.add(easyUITreeNode);
        }
        return easyUITreeNodes;
    }

    @Override
    public TaotaoResult addContentCatgory(long parentId, String name) {
//  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
//  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
//  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
//  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
//  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
//  `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
//  `created` datetime DEFAULT NULL COMMENT '创建时间',
//  `updated` datetime DEFAULT NULL COMMENT '创建时间',
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setParentId(parentId);
        // 修改父节点的isparent
        TbContentCategory tbContentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!tbContentCategory1.getIsParent()){
            tbContentCategory1.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);
        }
        tbContentCategoryMapper.insert(tbContentCategory);
        return TaotaoResult.ok(tbContentCategory);
    }
}
