package lock;

public class DeadlockTest {
    Object a = new Object();
    Object b = new Object();

    public void m1() {
        synchronized (a) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println("success1");
            }
        }
    }

    public void m2() {
        synchronized (b) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("success2");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockTest t = new DeadlockTest();
        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
