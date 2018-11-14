package com.jiao.content.service;

import com.jiao.common.pojo.EasyUITreeNode;
import com.jiao.common.utils.TaotaoResult;

import java.util.List;

/**
 * Created by jiao on 2018/11/13.
 */
public interface ContentCatgoryService {
    List<EasyUITreeNode> getContentCatgoryList(long id);

    TaotaoResult addContentCatgory(long parentId, String name);
}
