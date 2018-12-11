package com.jiao.search.service;

import com.jiao.search.dao.SearchDao;
import com.jiao.search.pojo.SearchArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * Created by jiao on 12/11/2018.
 */
@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private IdWorker idWorker;
    /**
     *  增加
     */
    public void save(SearchArticle searchArticle){
        searchArticle.setId(idWorker.nextId()+"'");
        searchDao.save(searchArticle);
    }

    /**
     *  /article/search/{keyword}/{page}/{size}
     文章分页
     */
   public Page<SearchArticle> findByContentAndTitleLike(String keyword,int page ,int size){
       Pageable pageable = PageRequest.of(page,size);
       Page<SearchArticle> pages = searchDao.findByContentOrTitleLike(keyword, keyword, pageable);
       return pages;
   }
    /**
     * 根据id删除
     */
    /**
     * 根据id查找
     */
    /**
     * 查找全部
     */
    /**
     * 修改
     */
}
