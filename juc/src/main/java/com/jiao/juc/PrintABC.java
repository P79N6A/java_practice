package com.jiao.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jiao on 12/21/2018.
 */
public class PrintABC {
    public static void main(String[] args){
       final A a = new A();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<10;  i++ ){
                    a.printA();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<10 ;   i++ ){
                a.printB();}
            }
        },"B").start();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<10  ;   i++ ){
                a.printC();}
            }
        },"C").start();
    }
}


class A {

        private int num = 1;
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();


    public void printA(){
        lock.lock();
        try {
            if(  num !=1 ){
                conditionA.await();
            }
                System.out.println(Thread.currentThread().getName());
                num = 2;
                conditionB.signal();

        }catch (Exception e){e.printStackTrace();}finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            if(num!=2 ){
                conditionB.await();
            }
                System.out.println(Thread.currentThread().getName());
                num = 3;
                conditionC.signal();

        }catch (Exception e){e.printStackTrace();}finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            if(num != 3 ){
                conditionC.await();
            }
                System.out.println(Thread.currentThread().getName());
                num = 1;
                conditionA.signal();
        }catch (Exception e){e.printStackTrace();}finally {
            lock.unlock();
        }
    }
}
