package com.test4.firstrealization;

public class Resource {
    //当前资源个数
    private int num = 0;
    //最大资源个数
    private int size = 10;

    //取走元素
    public synchronized void remove(){
        if(num > 0){
            num --;
            System.out.println("消费者："+Thread.currentThread().getName()
                    +"消费一个资源，当前还剩下"+num+"个资源");
            notifyAll();
        }else{
            try {
                wait();
                System.out.println("当前消费者："+Thread.currentThread().getName()+"进入线程等待。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //添加元素
    public synchronized void add(){
        if(num < size){
            num++;
            System.out.println("生产者："+Thread.currentThread().getName()
                    +"生产一个资源，当前存在"+num+"个资源");
            notifyAll();
        }else{
            try {
                wait();
                System.out.println("生产者："+Thread.currentThread().getName()+"进入线程等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
