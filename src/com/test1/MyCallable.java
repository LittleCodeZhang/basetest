package com.test1;

import java.sql.Time;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    private long time;

    public MyCallable(int time){
        this.time = time;
    }
    @Override
    public String call() throws Exception{
        Thread.sleep(time);

        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        MyCallable myCallable1 = new MyCallable(1000); //这里定义两个任务
        MyCallable myCallable2 = new MyCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<String>(myCallable1);//将callable任务封装到可供执行者调度的对象里
        FutureTask<String> futureTask2 = new FutureTask<String>(myCallable2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);//c创建线程池返回实例
        executorService.execute(futureTask1);//执行任务
        executorService.execute(futureTask2);

        while(true){

            try {
                if(futureTask1.isDone() && futureTask2.isDone()){//任务都执行完成
                    System.out.println("Done");
                    executorService.shutdown();//关闭线程池和任务
                    return ;
                }

                if(!futureTask1.isDone()){
                    System.out.println("f1 output :" +futureTask1.get());
                }

                System.out.println("wait for f2 to complete");
                String s = futureTask2.get(300L, TimeUnit.MILLISECONDS);
                if(s != null){
                    System.out.println("f2 output :"+s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
