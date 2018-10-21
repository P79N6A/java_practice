package com.jiao.byagent.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by jiao on 2018/10/21.
 */
public interface Mapper<T> extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {

}