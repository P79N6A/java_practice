package com.jiao.t1;

/**
 * Created by jiao on 12/27/2018.
 */
public class PrintlnA {

    public static void main(String[] args){
        System.out.println(new PrintlnA().test());

    }
    public int test(){
        int a = 0;
        try {
            a = a+2;
          return a ;
        }catch (Exception e ){}
        finally {
            return a++;
        }
    }

}
