package com.jiao.service.impl;

import com.jiao.mapper.TbItemParamItemMapper;
import com.jiao.pojo.TbItemParamItem;
import com.jiao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 2018/11/12.
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

    @Autowired
    TbItemParamItemMapper tbItemParamItemMapper;


    public TbItemParamItem getItemParamItem(long id) {
        return tbItemParamItemMapper.selectByPrimaryKey(id);
    }
}
