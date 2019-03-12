package com.test2;

public class ThreadLocalDemo {
    public static ThreadLocal<Integer>threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
        @Override
        public Integer get() {
            super.set(super.get()+1);
            return super.get();
        }

        @Override
        public void set(Integer value) {
            super.set(value);
        }

        @Override
        public void remove() {
            super.remove();
        }
    };

    public static void main(String[] args) {
        for(int i = 0 ; i<3 ;i++){
            new MyThread(i).start();
        }
    }
}
