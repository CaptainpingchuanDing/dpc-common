package thread.lock;

public class MonitorTest {

    public static void main(String[] args) {
        Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *  执行之后 报错  Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
     *  因为object是主线程， 子线程没有控制权，所以会报错
     */
}
