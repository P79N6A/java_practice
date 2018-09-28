package com.jiao.server.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by jiao on 2018/9/28.
 */
@WebService
public class WebServiceTest {

    public String getName(String name){
        return name;
    }



    public static void main(String[] args){
        Endpoint.publish("http://127.0.0.1:8080/",new WebServiceTest());
        System.out.println("发布成功");
    }

}
