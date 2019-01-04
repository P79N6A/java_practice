package com.jiao.supertest;

/**
 * Created by jiao on 12/24/2018.
 */
public class B extends A {
    private String name;

    public B(String name) {
          this.name = name;
//        this.setName(name);
//          super.setName("B");
    }



    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void run(){
        System.out.println(getName());
        System.out.println(this.name);
    }


    public static void main(String[] args){
        B b = new B("A");
        b.run();
    }
}
