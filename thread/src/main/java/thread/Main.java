package thread;


import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws Exception {
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();

        CallableTest callableTest = new CallableTest();
        FutureTask  futureTask = new FutureTask(callableTest);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        System.out.println("future result: "+futureTask.get());

    }
}

