package com.jiao.juc;



/**
 * Created by jiao on 12/21/2018.
 */
public class TestProductorAndConsumer {

    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
    }
}

class Clerk{
    private Integer product =0;
    /**
     * 进货
     */
    public synchronized void in(){
        if (product >= 1){
            System.out.println("仓库已满");
            try {
                this.wait();
            } catch (Exception e) {

            }
        }else{
            System.out.println(Thread.currentThread().getName()+":" + ++product);
           this.notifyAll();
        }

    }
    /**
     * 出货
     */
    public synchronized void out(){
        if (product <= 0){
            System.out.println("仓库已空");
            try {
                this.wait();
            } catch (Exception e) {
            }
        }else{
            System.out.println(Thread.currentThread().getName()+":" + --product);
            this.notifyAll();
        }

    }
}

class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.in();

        }
    }
}
class Consumer implements Runnable{
    private  Clerk clerk;
    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    public void run() {
        for (int i = 0; i<20; i++){
            clerk.out();
        }
    }
}
