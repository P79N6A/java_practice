package com.jiao.super1;

/**
 * Created by jiao on 12/27/2018.
 */
public class A {

    public void a(){
        System.out.println("A -> a");
        b();
    }

    protected void b() {
        System.out.println("A -> b");
    }



}
