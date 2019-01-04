package com.jiao.alipay.pojo;

/**
 * Created by jiao on 1/4/2019.
 */
public class Order {
    // 订单id
    private String orderNo;
    // 条形码id
    private String paycode;
    // 商户id
    private String storeId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", paycode='" + paycode + '\'' +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
