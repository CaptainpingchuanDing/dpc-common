package aqs.reentranlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockWithCondition {
    static class MyTask {

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void executeWait() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " await start");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " await end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void executeSignal() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " signal start");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " signal end");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final MyTask myTask = new MyTask();
        Thread t1 = new Thread(() -> myTask.executeWait());
        t1.setName("t1");

        Thread t2 = new Thread(() -> myTask.executeSignal());
        t2.setName("t2");

        t1.start();
        t2.start();
    }
}
