package com.jiao.jedis;

import com.jiao.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Created by jiao on 11/15/2018.
 */
public class JedisTest {

    @Test
    public void testJedis()throws Exception{
        //  创建jedis对象连接redis'sport&host
        Jedis jedis = new Jedis("192.168.8.132", 6379);

        //  使用jedis操作redis
        jedis.set("a","123");
        jedis.set("b","1234");
        jedis.set("c","012");

        //  关闭jedis
        jedis.close();
    }
    /**
    * @Description: 配置接口，调用测试redis单机版
    * @Param: []
    * @return: void
    * @Author: Mr.Jiao
    * @Date: 11/15/2018
    */
    @Test
    public void testJedis2()throws Exception{
       //  初始化spring容器
       //  获取jedisClientPool对象
        // 操作redis
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient bean = ctx.getBean(JedisClient.class);
        bean.set("jiao1","jianjun");
    }
}
