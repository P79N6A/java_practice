package com.jiao.alipay.controller;

import com.jiao.alipay.pojo.Order;
import com.jiao.alipay.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiao on 1/4/2019.
 */
@RestController
public class AlipayController {

    @Autowired
    private  TestServiceImpl testServiceImpl;

    @RequestMapping( value = "/barcodePay" , method = RequestMethod.POST)
    public String barcodePay(@RequestBody Order order){
         String orderNo = System.currentTimeMillis()+"";
         return testServiceImpl.barcodePay(orderNo,order.getPaycode(),order.getStoreId());
    }

}
