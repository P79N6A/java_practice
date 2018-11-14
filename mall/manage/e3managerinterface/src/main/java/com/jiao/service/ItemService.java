package com.jiao.service;

import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;

/**
 * Created by jiao on 2018/11/6.
 */
public interface ItemService {

    public TbItem getItemById(long id);

    public DataGridResult getItemList(int page,int rows);

    public TaotaoResult addItem(TbItem tbItem, String desc);

    public TaotaoResult update(TbItem tbItem , String desc);
}
