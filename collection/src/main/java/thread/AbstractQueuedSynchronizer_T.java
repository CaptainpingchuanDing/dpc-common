package thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizer_T {
    public static void main(String[] args) {
//        AbstractQueuedSynchronizer abstractQueuedSynchronizer =

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }
}
