package com.jiao.byagent.schedule;

import com.jiao.byagent.service.EmpManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jiao on 2018/11/1.
 */
@Component
public class Jobs {
    @Autowired
    EmpManage empImpl;

    @Scheduled(cron="0 0 0 1 * ? ")
    public void autoPunch() throws Throwable {
        empImpl.autoPunch();
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void autoPay() throws Throwable {
       empImpl.autoPay();
    }

}
