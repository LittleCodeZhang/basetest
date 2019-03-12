package com.test2;

public class MyThread extends Thread {
    int id;
    public MyThread(int value){
        id = value;
    }
    @Override
    public void run() {
        for (int i = 0 ; i<3 ; i++){
            System.out.println("Thread--"+id+" : "+ ThreadLocalDemo.threadLocal.get());
            try {
                Thread.sleep((int)(100*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
