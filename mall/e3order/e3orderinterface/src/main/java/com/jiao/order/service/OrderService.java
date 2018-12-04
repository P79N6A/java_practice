package com.jiao.order.service;

import com.jiao.common.utils.TaotaoResult;
import com.jiao.order.pojo.OrderInfo;

public interface OrderService {
    /**
     *  生成订单
     *  使用redis的自增id生成订单id
     *  保存3个订单相关的表
     */
    TaotaoResult createOrder(OrderInfo orderInfo);
}
