package com.jiao.research.listener;

import com.jiao.common.pojo.SolrResult;
import com.jiao.research.mapper.SolrSearchMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by jiao on 11/22/2018.
 */
public class MyMessageListener implements MessageListener {

    @Autowired
    SolrSearchMapper solrSearchMapper;
    @Autowired
    SolrServer httpSolrServer;
    @Override
    public void onMessage(Message message) {

        try {
            Thread.sleep(100);
            TextMessage textMessage = (TextMessage)message;
            long itemId = Long.parseLong(textMessage.getText());
            SolrResult solrResult = solrSearchMapper.getItemById(itemId);
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id",solrResult.getId());
            solrInputDocument.addField("item_title",solrResult.getTitle());
            solrInputDocument.addField("item_sell_point",solrResult.getSell_point());
            solrInputDocument.addField("item_price",solrResult.getPrice());
            solrInputDocument.addField("item_image",solrResult.getImage());
            solrInputDocument.addField("item_category_name",solrResult.getCatgory_name());
            httpSolrServer.add(solrInputDocument);
            httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
