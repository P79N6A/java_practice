package com.jiao.alipay;

import com.jiao.alipay.config.AliPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jiao on 1/4/2019.
 */
@SpringBootApplication
@PropertySource(value = {"classpath:alipay.properties"})
public class AlipayApplication {

    @Value("${APP_ID}")
    private String app_id;

    @Value("${APP_PRIVATE_KEY}")
    private String app_private_key;

    @Value("${ALIPAY_PUBLIC_KEY}")
    private String appid_public_key;

    @Value("${SIGN_TYPE}")
    private String sign_type;

    public static void main(String[] args) {
        SpringApplication.run(AlipayApplication.class,args);
    }

    @Bean
    public AliPayConfig aliPayConfig(){
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAlipayPublicKey(appid_public_key);
        aliPayConfig.setAppId(app_id);
        aliPayConfig.setAppPrivateKey(app_private_key);
        aliPayConfig.setSignType(sign_type);
        return aliPayConfig;
    }
}
