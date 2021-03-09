package thread;

public class RunnableTest implements Runnable {

    @Override
    public void run() {

        System.out.println("RunnableTest running...");
        System.out.println("thread name: "+ Thread.currentThread().getName());
    }

}
