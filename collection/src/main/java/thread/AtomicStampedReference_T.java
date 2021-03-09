package thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReference_T {
    private static void test() {
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference("ds", 0);
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.getReference());
        atomicStampedReference.compareAndSet("ds", "new", 0, 1);
        System.out.println(atomicStampedReference.getReference());

    }

    public static void main(String[] args) {
        test();
    }
}
