package com.test4.thridrealization;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Resource {
    private BlockingQueue resourceQueue = new LinkedBlockingDeque();

    public void add(){
        try {
            resourceQueue.put(1);
            System.out.println("生产者："+Thread.currentThread().getName()+"生产了一个资源，"
                    +"目前有" + resourceQueue.size()+"个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remove(){
        try {
            resourceQueue.take();
            System.out.println("消费者："+Thread.currentThread().getName()+"消费了一个资源，"
                    +"目前有" + resourceQueue.size()+"个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
