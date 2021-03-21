package aqs;

public class InterruptTest {

    public static void main(String args[]) throws InterruptedException {
//        Thread thread = new Thread(new NonBlockedTest());
//        Thread thread = new Thread(new NonBlockedTest1());
        Thread thread = new Thread(new NonBlockedTest2());
        thread.start();
        Thread.sleep(50);
        System.out.println("接下来中断线程");
        thread.interrupt();
    }

    /**
     * 没有阻塞操作的线程
     *
     */
    private static class NonBlockedTest implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("线程执行中...");
            }
        }
    }


    /**
     * 没有阻塞操作的线程
     *
     */
    private static class NonBlockedTest1 implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println("线程执行中...");
            }
        }
    }


    /**
     * 有阻塞操作的线程
     *
     */
    private static class NonBlockedTest2 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("线程开始阻塞调用");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedExceptioon");
            }
            System.out.println("Exit run()");
        }
    }

}
