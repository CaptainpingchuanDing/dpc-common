package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInteger_T {


    public static void test(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        System.out.println(atomicInteger.get());
        atomicInteger.incrementAndGet();
        System.out.print(atomicInteger.get());
    }

    public static void main(String[] args) {
        test();
    }
}
