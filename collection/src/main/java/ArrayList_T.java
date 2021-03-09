import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArrayList_T {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);

        list.add("1");
        list.add("2");
        list.add("3");
        list.set(2,"ds");

        System.out.println(list);
        System.out.println(list.contains("ds"));
//        list.remove(1);
//        while(!list.isEmpty()){
//            list.remove(0);
//        }
        System.out.println(list);

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });


        list.forEach(test->System.out.println(test) );
    }
}
