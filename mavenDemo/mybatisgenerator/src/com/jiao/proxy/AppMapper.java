package com.jiao.proxy;

import com.jiao.proxy.App;

public interface AppMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}