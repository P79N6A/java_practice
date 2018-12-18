package com.jiao.ts_qa.client.impl;

import com.jiao.ts_qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * Created by jiao on 12/18/2018.
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR ,"网络异常");
    }
}
