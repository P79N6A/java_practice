package com.jiao.service.impl;

import com.jiao.mapper.TbItemDescMapper;
import com.jiao.pojo.TbItemDesc;
import com.jiao.service.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 2018/11/12.
 */
@Service
public class ItemDescImpl implements ItemDesc{
    @Autowired
    TbItemDescMapper tbItemDescMapper;


    public TbItemDesc getItemDesc(long id) {
        return tbItemDescMapper.selectByPrimaryKey(id);
    }
}
