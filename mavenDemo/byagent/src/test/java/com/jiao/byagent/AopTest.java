package com.jiao.byagent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by jiao on 2018/10/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {

    public void test(String str){
        System.out.println(str);
    }

    @Test
    public void test1() throws Throwable{
        Class clazz = Class.forName("com.jiao.byagent.AopTest");
        Constructor con = clazz.getConstructor();
        AopTest aopTest = (AopTest)con.newInstance();
        Method method = clazz.getMethod("test",String.class);
        method.invoke(aopTest,"haha");
    }
}
