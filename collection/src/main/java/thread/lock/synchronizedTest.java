package thread.lock;

public class synchronizedTest {

    private final Object flag = new Object();

    public static void main(String[] args) {
        synchronizedTest threadTest = new synchronizedTest();
        ThreadA threadA = threadTest.new ThreadA();
        threadA.start();
        ThreadB threadB = threadTest.new ThreadB();
        threadB.start();
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (synchronizedTest.class) {
                for (int i = 1; i <= 100; i += 2) {
                    synchronizedTest.class.notify();
                    System.out.println(i); // 奇数
                    try {
                        synchronizedTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (synchronizedTest.class) {
                for (int i = 2; i <= 100; i += 2) {
                    synchronizedTest.class.notify();
                    System.out.println(i); // 偶数
                    if (i == 100) {
                        // 当输出了最后一个数字的时候，不能再wait了
                        break;
                    }
                    try {
                        synchronizedTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
