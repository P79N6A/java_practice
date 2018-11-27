package com.jiao.service.impl;


import com.jiao.common.jedis.JedisClient;
import com.jiao.common.utils.JsonUtils;
import com.jiao.mapper.TbItemDescMapper;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbItemDesc;
import com.jiao.service.ItemDesc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 2018/11/12.
 */
@Service
public class ItemDescImpl implements ItemDesc{
    @Autowired
    TbItemDescMapper tbItemDescMapper;
    // 注入redis

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_PRE}")
    private String REDIS_ITEM_PRE;

    @Value("${ITEM_CACHE_EXPIRE}")
    private int ITEM_CACHE_EXPIRE;

    public TbItemDesc getItemDesc(long id) {
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + id + ":DESC");
            if(StringUtils.isNotBlank(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        //查询缓存
        try {
            jedisClient.set(REDIS_ITEM_PRE + ":" + id + ":DESC", JsonUtils.objectToJson(tbItemDesc));
            jedisClient.expire(REDIS_ITEM_PRE + ":" + id + ":DESC",ITEM_CACHE_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tbItemDesc;

    }
}
