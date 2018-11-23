package com.jiao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiao on 11/21/2018.
 */
public class SearchResult implements Serializable{
   private long totalPages;
   private int recourdCount;
   private List<SolrResult> itemList;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public int getRecourdCount() {
        return recourdCount;
    }

    public void setRecourdCount(int recourdCount) {
        this.recourdCount = recourdCount;
    }

    public List<SolrResult> getItemList() {
        return itemList;
    }

    public void setItemList(List<SolrResult> itemList) {
        this.itemList = itemList;
    }
}
