import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static List<List<Object>> readCsv(InputStream inStream) throws IOException {
        List<List<Object>> allValueList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
        //换成你的文件名
        reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
        String line = null;
        int num = 0;// 行数
        while ((line = reader.readLine()) != null) {
            num++;
            String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
            List<Object> list = new ArrayList<>();
            for (String item : items) {
                list.add(item);
            }
            allValueList.add(list);
        }

        return allValueList;
    }


}
