package aqs.semaphore;

public class TaskDemo implements Runnable {
    private String id;

    TaskDemo(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {

            System.out.println("Thread " + id + " is working");
            Thread.sleep(1000);
            System.out.println("Thread " + id + " is over");
        } catch (InterruptedException e) {
        }
    }
}
