package com.jiao.byagent.mapper;


import com.jiao.proxy.pojo.Check;

public interface CheckMapper {
    int deleteByPrimaryKey(Integer checkId);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(Integer checkId);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);
}