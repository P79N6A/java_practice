package com.jiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.utils.IDUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.mapper.TbItemDescMapper;
import com.jiao.mapper.TbItemMapper;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbItemDesc;
import com.jiao.pojo.TbItemExample;
import com.jiao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jiao on 2018/11/6.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public TbItem getItemById(long id) {

        return itemMapper.selectByPrimaryKey(id);
    }



    public DataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setRows(tbItems);
        return dataGridResult;
    }


    public TaotaoResult addItem(TbItem tbItem, String desc){
        // 生成id
        long l = IDUtils.genItemId();
        tbItem.setId(l);
        // 商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte)1);
        // 创建时间
        tbItem.setCreated(new Date());

        // 更新时间
        tbItem.setUpdated(new Date());
        // 保存tbItem
        itemMapper.insert(tbItem);
        // 保存TbItemDesc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(l);
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult update(TbItem tbItem, String desc) {
//        // 获取原来的tbitem
//        TbItem tbItem1 = itemMapper.selectByPrimaryKey(tbItem.getId());
//        // 更新tbitem
//        tbItem.setStatus(tbItem1.getStatus());
//        tbItem.setCreated(tbItem1.getCreated());
//        tbItem.setUpdated(new Date());
//        itemMapper.updateByPrimaryKey(tbItem);
        // 使用沒有Selective的更新或者插入方法，当pojo的属性为null时，原字段也会更新为null
        // 使用带有Selective的更新或者插入方法，当pojo的属性为null事，原字段不会更新

        // 修改update时间
        tbItem.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(tbItem);

//        // 找到ItemDesc
//        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(tbItem.getId());
//        tbItemDesc.setItemDesc(desc);
//        tbItemDesc.setUpdated(new Date());
//        // 保存itemdesc
//        tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
//        tbItemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(tbItem.getId());
        itemDesc.setItemDesc(desc);
        tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
        return TaotaoResult.ok();
    }

}

