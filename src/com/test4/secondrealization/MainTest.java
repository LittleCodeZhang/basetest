package com.test4.secondrealization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition producerCondition1 = lock.newCondition();

        Condition consumerCondition1 = lock.newCondition();

        Resource resource = new Resource(lock,producerCondition1,consumerCondition1);

        ProducerThread producerThread1 = new ProducerThread(resource);
        ProducerThread producerThread2 = new ProducerThread(resource);

        ConsumerThread consumerThread1 = new ConsumerThread(resource);

        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
    }
}
