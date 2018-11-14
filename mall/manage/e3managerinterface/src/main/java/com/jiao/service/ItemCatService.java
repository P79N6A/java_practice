package com.jiao.service;

import com.jiao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by jiao on 2018/11/8.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long id);
}
