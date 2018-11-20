package com.jiao.research.dao;


import com.jiao.common.pojo.SolrResult;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.research.GetSolrResult;
import com.jiao.research.mapper.SolrSearchMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiao on 11/20/2018.
 */
@Service
public class GetSolrResultImpl implements GetSolrResult {

    @Autowired
    SolrSearchMapper solrSearchMapper;
  
    @Autowired
    SolrServer httpSolrServer;

    /**
    * @Description: 索引库维护
    * @Param: []
    * @return: com.jiao.common.utils.TaotaoResult
    * @Author: Mr.Jiao
    * @Date: 11/20/2018
    */
    @Override
    public TaotaoResult getSolrResult() {
        try {

            List<SolrResult> SolrResults = solrSearchMapper.getItemInfo();
            for(SolrResult solrResult    : SolrResults ){
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                solrInputDocument.addField("id",solrResult.getId());
                solrInputDocument.addField("item_title",solrResult.getTitle());
                solrInputDocument.addField("item_sell_point",solrResult.getSell_point());
                solrInputDocument.addField("item_price",solrResult.getPrice());
                solrInputDocument.addField("item_image",solrResult.getImage());
                solrInputDocument.addField("item_category_name",solrResult.getCatgory_name());
                httpSolrServer.add(solrInputDocument);
            }
            httpSolrServer.commit();
            return TaotaoResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,"服务器发生内部错误");
        }



    }
}
