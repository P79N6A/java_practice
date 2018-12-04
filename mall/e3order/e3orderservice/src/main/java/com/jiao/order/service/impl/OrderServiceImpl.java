package com.jiao.order.service.impl;

import com.jiao.common.jedis.JedisClient;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.mapper.TbOrderItemMapper;
import com.jiao.mapper.TbOrderMapper;
import com.jiao.mapper.TbOrderShippingMapper;
import com.jiao.order.pojo.OrderInfo;
import com.jiao.order.service.OrderService;
import com.jiao.pojo.TbOrderItem;
import com.jiao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;

    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;

    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN_KEY;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        /**
         *  生成订单id  生成id，给定一个初始值，使用redis生成 
         *  补全3个表的数据 分别插入
         */
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)){
            jedisClient.set(ORDER_ID_GEN_KEY,ORDER_ID_START);
        }
        String orderId = jedisClient.incr("REDIS_INC_KEY").toString();
        /**
         */
        orderInfo.setOrderId(orderId);
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        tbOrderMapper.insert(orderInfo);
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem t :
                orderItems ) {
            //生成明细id
            String odId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
            //补全pojo的属性
            t.setId(odId);
           t.setOrderId(orderId);
            tbOrderItemMapper.insert(t);
        }
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShipping.setOrderId(orderId);
        tbOrderShippingMapper.insert(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
