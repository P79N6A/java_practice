package com.jiao.research;


import com.jiao.common.pojo.SearchResult;

/**
 * Created by jiao on 11/21/2018.
 */
public interface SearchItems {

    SearchResult getSearchItems(String keyword, int page, int rows) throws Exception;
}
