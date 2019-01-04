package com.jiao.juc;

/**
 * Created by jiao on 12/26/2018.
 */
public class TestThis {


    public static void main(String[] args){
     new Thread(new Runnable() {
         public void run() {
             System.out.println(this instanceof Runnable);
//             System.out.println(this instanceof Thread);
             // this是runnable的实例
         }
     }).start();
    }
}
