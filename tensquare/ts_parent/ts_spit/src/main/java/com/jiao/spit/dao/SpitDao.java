package com.jiao.spit.dao;

import com.jiao.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiao on 12/10/2018.
 */
@Repository
public interface SpitDao extends MongoRepository<Spit, String>{
    /**
     * 根据上级ID查询吐槽数据（分页）
     */
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
