package com.jiao.super1;

/**
 * Created by jiao on 12/27/2018.
 */
public class B {
    private C c = new C();

    public void test(){
        c.a();
    }



    static class C extends A {

        @Override
        protected void b() {
            System.out.println("B -> b");
        }
    }


    public static void main(String[] args){
        B b = new B();
        b.test();
         A a = new A();
        a.a();
    }
}
