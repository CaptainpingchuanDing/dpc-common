package reference;

public class T01_NormalReference {

    public static void main(String[] args) throws Exception{
        M m = new M();
        m = null;
        System.gc();
        System.in.read();
    }
}
