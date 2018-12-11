package com.jiao.search.dao;

import com.jiao.search.pojo.SearchArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiao on 12/11/2018.
 */
@Repository
public interface SearchDao extends ElasticsearchRepository<SearchArticle,String>{
    /**
     *  /article/search/{keyword}/{page}/{size}
     文章分页
     */
    Page<SearchArticle> findByContentOrTitleLike(String title ,String content, Pageable pageable);

}
