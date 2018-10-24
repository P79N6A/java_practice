package com.jiao.byagent.dao;

import com.jiao.byagent.pojo.MgrEmp;
import com.jiao.byagent.pojo.MgrEmp3;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
@Mapper
public interface MgrEmpMapper {
    List<MgrEmp3> findById4();

    List<MgrEmp> findById5();
}
