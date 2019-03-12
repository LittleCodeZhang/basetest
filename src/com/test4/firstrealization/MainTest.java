package com.test4.firstrealization;

public class MainTest {
    public static void main(String[] args) {
        Resource resource = new Resource();

        ProducerThread producerThread1 = new ProducerThread(resource);
        ProducerThread producerThread2 = new ProducerThread(resource);
        ProducerThread producerThread3 = new ProducerThread(resource);

        ConsumerThread consumerThread1 = new ConsumerThread(resource);
        ConsumerThread consumerThread2 = new ConsumerThread(resource);


        producerThread1.start();
        producerThread2.start();
        producerThread3.start();

        consumerThread1.start();
        consumerThread2.start();
    }
}
