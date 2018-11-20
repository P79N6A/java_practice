package com.jiao.research.mapper;

import com.jiao.common.pojo.SolrResult;

import java.util.List;

/**
 * Created by jiao on 11/20/2018.
 */
public interface SolrSearchMapper {
    List<SolrResult> getItemInfo();
}
