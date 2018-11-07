package com.jiao.service.impl;

import com.jiao.mapper.TbItemMapper;
import com.jiao.pojo.TbItem;
import com.jiao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 2018/11/6.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
