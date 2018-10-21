package com.jiao.proxy;

import com.jiao.proxy.Mgr;

public interface MgrMapper {
    int deleteByPrimaryKey(Integer mgrId);

    int insert(Mgr record);

    int insertSelective(Mgr record);

    Mgr selectByPrimaryKey(Integer mgrId);

    int updateByPrimaryKeySelective(Mgr record);

    int updateByPrimaryKey(Mgr record);
}