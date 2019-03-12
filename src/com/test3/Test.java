package com.test3;

public class Test {
    /*
        synchronized代码块和修饰静态方法，如下，表示在这个类的对象在运行test1方法时，
        其他被synchronize修饰的方法或代码块不能运行，需要等到test1释放锁后才能运行；
        我的理解：这个锁是对象锁，
    */
    public void test1(){
        synchronized (this){
        }
    }
    public synchronized void test2(){
    }
    /*
        内类的这个this只与内类有关，与外类无关，但是通过这个也可以使得内类的非静态方法与外类的
        test1、test2同步，也就是说test1、test2、test3这三个方法同一时间只能运行一个
    */
    class innerClass{
        public void test3(){
            synchronized (Test.this){
            }
        }
    }

    /*
        synchronize修饰静态方法，由于在调用静态方法的时候，类的对象可能还没有创建，所以不能使用this
        同步静态方法，只能通过Class对象来同步，如下面两个方法：
            1、类.class   2、实例.getClass() （这两种方式获得的其实是同一个Class对象）
        可以看出这是类锁
    */
    public static void test4(){
        synchronized (Test.class){
        }
    }
    public static void test5(){
        Test test = new Test();
        synchronized (test.getClass()){
        }
    }
    public static synchronized void test6(){
    }
    /*
        总结：
            1、静态方法属于类，同步静态方法的锁是加在类对象上的；非静态方法属于对象，同步非静态方法的锁
               是加在这个对象上的。
            2、非静态方法通过this进行加锁；静态方法通过 类.class 或 实例.getClass(); 加锁的
    */
}
