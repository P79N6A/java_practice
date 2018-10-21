package com.jiao.byagent.mapper;


import com.jiao.proxy.pojo.Attend;

public interface AttendMapper {
    int deleteByPrimaryKey(Integer attendId);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Integer attendId);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);
}