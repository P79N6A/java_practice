package com.jiao.ts_qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiao on 12/14/2018.
 */
@FeignClient("ts-base")
public interface LabelClient {

     @RequestMapping(value = "/label/{labelId}",method = RequestMethod.GET)
     Result findById(@PathVariable("labelId") String labelId);
}
