package aqs.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPool {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new TaskDemo("a"));
        executorService.submit(new TaskDemo("b"));
        executorService.submit(new TaskDemo("c"));
        executorService.submit(new TaskDemo("d"));
        executorService.submit(new TaskDemo("e"));
        executorService.submit(new TaskDemo("f"));
        executorService.submit(new TaskDemo("g"));

    }
}
