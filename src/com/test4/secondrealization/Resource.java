package com.test4.secondrealization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Resource {

    private int num;
    private int size = 10;

    private Condition producerCondition;
    private Condition consumerCondition;
    private Lock lock;

    public Resource(Lock lock,Condition producerCondition,Condition consumerCondition){
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    public void add(){
        lock.lock();
        try {
            if( num < size ){
                num ++;
                System.out.println("线程："+Thread.currentThread().getName()+"生产了一个资源，当前还有"+num+"个资源");
                consumerCondition.signalAll();
            }else{
                producerCondition.await();
                System.out.println("线程："+Thread.currentThread().getName()+"进入等待");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void remove(){
        lock.lock();
        try {
            if( num > 0) {
                num --;
                System.out.println("线程："+Thread.currentThread().getName()+"消费了一个资源，当前还有"+num+"个资源");
                producerCondition.signalAll();
            }else{
                consumerCondition.await();
                System.out.println("线程："+Thread.currentThread().getName()+"进入等待");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
