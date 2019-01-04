package com.jiao.alipay.service;

/**
 * Created by houcunlu on 2017/10/19.
 */
public interface TestService {


    /**
     * 当面付 ： 条码支付
     * @param
     * @return
     */
    String barcodePay(String orderNo, String payCode, String storeId);

    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    String queryOrder(String orderNo);

    /**
     * 退款
     * @param orderNo
     * @param money
     * @return
     */
    String refundOrder(String orderNo, String money);

    /**
     * 关闭订单
     * @param orderNo
     * @return
     */
    String closeOrder(String orderNo);


}
