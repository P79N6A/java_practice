package com.jiao.alipay.service.impl;

import com.jiao.alipay.config.AliPayConfig;
import com.jiao.alipay.service.TestService;
import com.jiao.alipay.utils.AliPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 1/4/2019.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private AliPayConfig aliPayConfig;


    public String barcodePay(String orderNo, String payCode, String storeId) {
        String title ="订单标题";
        String totalAmount = "0.01";
        return AliPayUtils.barcodePay(aliPayConfig,orderNo , payCode ,title , storeId , totalAmount,"3600000");
    }



    public String queryOrder(String orderNo) {
        return null;
    }

    public String refundOrder(String orderNo, String money) {
        return null;
    }

    public String closeOrder(String orderNo) {
        return null;
    }
}
