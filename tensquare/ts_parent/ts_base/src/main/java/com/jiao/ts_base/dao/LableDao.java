package com.jiao.ts_base.dao;

import com.jiao.ts_base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by jiao on 12/6/2018.
 */
public interface LableDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {
}
