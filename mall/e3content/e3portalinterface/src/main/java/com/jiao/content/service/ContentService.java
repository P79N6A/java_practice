package com.jiao.content.service;

import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbContent;

import java.util.List;

/**
 * Created by jiao on 2018/11/14.
 */
public interface ContentService {
    DataGridResult getContentList(long categoryId ,int page, int rows);

    TaotaoResult addContent(TbContent tbContent);

    List<TbContent> getContentByCatId(long categoryId);
}
