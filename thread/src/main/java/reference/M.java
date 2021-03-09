package reference;

public class M {

    @Override
    public void finalize() throws Throwable{// 对象被回收的时候，该方法会被调用
        System.out.println("finalize");
    }
}
