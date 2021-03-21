package aqs;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    Condition condition =  new Condition() {
        @Override
        public void await() throws InterruptedException {

        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

        }

        @Override
        public void signalAll() {

        }
    };
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

    }
}
