package com.jiao.ts_qa.client;

import com.jiao.ts_qa.client.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiao on 12/14/2018.
 */
@FeignClient(value = "ts-base",fallback = LabelClientImpl.class)
public interface LabelClient {

     @RequestMapping(value = "/label/{labelId}",method = RequestMethod.GET)
     Result findById(@PathVariable("labelId") String labelId);
}
