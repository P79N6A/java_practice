package com.jiao.ts_recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jiao.ts_recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * 推荐职位查询
     */
   List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);
    /**
     * 最新职位查询
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);

}
