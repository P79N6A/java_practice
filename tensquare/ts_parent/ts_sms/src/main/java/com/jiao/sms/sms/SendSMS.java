package com.jiao.sms.sms;

import com.aliyuncs.exceptions.ClientException;
import com.jiao.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jiao on 12/12/2018.
 */
@Component
@RabbitListener(queues = "sms")
public class SendSMS {
    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @RabbitHandler
    public void sendSMS(Map<String,String> map){
        String mobile = map.get("mobile");
        String code = map.get("code");
        try {
            smsUtil.sendSms(mobile,template_code,sign_name,"{\"code\":\""+code+"\"}");  //模板中以${code}取值
        }catch (ClientException e){
            e.printStackTrace();
        }


    }

}


