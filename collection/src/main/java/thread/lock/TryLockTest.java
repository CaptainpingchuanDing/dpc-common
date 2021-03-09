package thread.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by haicheng.lhc on 17/05/2017.
 *
 * @author haicheng.lhc
 * @date 2017/05/17
 */
public class TryLockTest extends Thread {

    public static ReentrantLock lock = new ReentrantLock();

    public TryLockTest(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println(this.getName() + " get lock failed");
            }
        } catch (Exception e) {
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println("lock.isHeldByCurrentThread: " + this.getName());
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TryLockTest t1 = new TryLockTest("TryLockTest1");
        TryLockTest t2 = new TryLockTest("TryLockTest2");

        t1.start();
        t2.start();
    }

}
