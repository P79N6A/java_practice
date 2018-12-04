package com.jiao.order.pojo;

import com.jiao.pojo.TbOrder;
import com.jiao.pojo.TbOrderItem;
import com.jiao.pojo.TbOrderShipping;

import java.util.List;

public class OrderInfo extends TbOrder {
    List<TbOrderItem> orderItems;
    TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
