package aqs.reentranlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {



    static class MyTask {

        private Lock lock = new ReentrantLock();

        public void execute() {
            lock.lock();

            for (int i = 0 ; i < 3 ; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            lock.unlock();
        }
    }

    static class MyThread extends Thread {

        private MyTask myTask;

        public MyThread(MyTask myTask) {
            this.myTask = myTask;
        }

        @Override
        public void run() {
            myTask.execute();
        }

    }

    /**
     * 输出结果
     * mt1 0
     * mt1 1
     * mt1 2
     * mt2 0
     * mt2 1
     * mt2 2

     */
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        MyThread mt1 = new MyThread(myTask);
        mt1.setName("mt1");
        MyThread mt2 = new MyThread(myTask);
        mt2.setName("mt2");
        mt1.start();
        mt2.start();
    }

}

