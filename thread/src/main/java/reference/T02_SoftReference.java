package reference;

import java.lang.ref.SoftReference;

public class T02_SoftReference {

    // 设置最大堆内存 20M
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        byte[] other = new byte[1024 * 1024 * 15];// 当再分配15M 堆内存的时候，空间不够，jvm会把软引用的干掉
        System.out.println(m.get());

    }
}
