package com.test4.firstrealization;

public class ProducerThread extends Thread{

    private Resource resource;

    public ProducerThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}
