package com.jiao.byagent.dao;


import com.jiao.byagent.pojo.MgrEmp;
import com.jiao.proxy.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jiao on 2018/10/22.
 */
@Mapper
public interface MgrDaosMapper {
    @Select("SELECT * FROM type_table WHERE type_id = #{id}")
    Type findById2(int id);

    @Select("SELECT * FROM emp_table as emp,mgr_table as mgr WHERE emp.mgr_id = mgr.mgr_id")
    @Results({
        @Result(id=true,column = "emp_id" ,property = "emp.mgrId")
    })
    List<MgrEmp> findById3();


}
