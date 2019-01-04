package com.jiao.staticTest;

/**
 * Created by jiao on 12/26/2018.
 */
public class StaticTest {
    private static int c = 1;
    private int d = 1;


    public static void a(){
        System.out.println("1");
    }

    public  void b(){
        System.out.println("1");
    }
    public static void main(String[] args){
        a();
//        b();
        System.out.println(c);
//        System.out.println(d);
    }
}
