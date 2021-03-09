package CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASStudy {

    private void  test(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.decrementAndGet();
    }
}
