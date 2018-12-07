package com.jiao.ts_qa.dao;

import com.jiao.ts_qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问答列表
     */
    @Query(value = "SELECT * from tb_pl , tb_problem WHERE problemid = id AND labelid = ? ORDER BY createtime DESC" ,nativeQuery = true)
    public Page<Problem> newlist(String labelid , Pageable pageable);
    /**
     * 热门问答列表
     */
    @Query(value = "SELECT * from tb_pl , tb_problem WHERE problemid = id AND labelid = ? ORDER BY reply DESC" , nativeQuery = true)
    public Page<Problem> hotlist(String labelid , Pageable pageable);
    /**
     * 等待回答列表
     */
    @Query(value = "SELECT * from tb_pl , tb_problem WHERE problemid = id AND labelid = ? AND reply = 0 ORDER BY " +
            "createtime DESC" , nativeQuery = true)
    public Page<Problem> waitlist(String labelid , Pageable pageable);
}
