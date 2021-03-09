package lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedTest {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        SynchronizedTest t = new SynchronizedTest();

//      new Thread(()->t.m1(), "t1").start();
//      new Thread(()->t.m2(), "t2").start();

//        new Thread(t::m1, "t1").start();
//        new Thread(t::m2, "t2").start();

        t.rem1();

    }

    /**
     * 一个同步方法可以调用另外一个同步方法（包括子类同步方法调用父类同步方法也是可以的）
     * 一个线程已经拥有某个对象的锁，再次申请相同的锁的时候仍然会得到该对象的锁
     *
     * 即已取得锁的当前线程再申请获取同一个锁是可行的
     *
     * 也就是说synchronized获得的锁是可重入的
     *
     * 下面代码m1内调用m2，是可以执行的
     */

    synchronized void rem1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rem2();
    }

    synchronized void rem2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
