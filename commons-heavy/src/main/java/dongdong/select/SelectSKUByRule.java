package dongdong.select;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectSKUByRule {


    static String sku_key_word_1 = "item.jd.com/";
    static String getSku_key_word_1_end = ".html";
    static String sku_key_word_2 = "商品编号：";

    public static void main(String[] args) {
        String inputPath = "";
        String outputPath = "";
        if (args.length == 2) {
            inputPath = args[0];
            outputPath = args[1];
        } else {
            System.out.println("please give two parameters: inputPath and outputPath");
        }
        System.out.println("inputPath: " + inputPath);
        System.out.println("outputPath: " + outputPath);

        outputWithSKUAndSessionId(outputPath, inputPath);
        System.out.println("select sku and date and sessionId finished!");

//        System.out.println(isNumeric("33"));
//        String test = "顾客 13945910-10992054 加入咨询 商品编号：100000043280";
//        String test1 = "http://item.jd.com/7553218.html";
//        String test2 = "洗衣机换代就选海尔直驱 更多活动请查看：https://sale.jd.com/mall/TcPqUuspYO40Fb.html直驱爆款抢购【EG10014BD59GU1JD】：https://item.jd.com/5217075.html1>         、10kg大容量斐雪派克直驱变频滚筒（夜晚洗衣也安静），2、自带双喷淋（去除观察窗及窗垫泡沫残留），3、智能APP，智能添加洗衣液（双智能智慧洗护）【海尔洗衣机专业人员免费上门安装服务：微信号haier-fw，售后电";
//        System.out.println("--"+findSKU(test2)+"--");
    }

    static void outputWithSKUAndSessionId(String outputPath, String inputPath) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)));
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)));
            String line;
            String sid = "tag", mallId, brand, date,sku="", customer = "", waiter = "", content="";
            String outputFirstLine = "";
            boolean sku_flag = false;
            List<String> conservation = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split("\t");
                if (elements.length < 7) {
                    System.out.println("Missing field, line: " + line);
                    continue;
                }
                String timestamp = elements[5];

                if ("tag".equals(sid)) {
                    mallId = elements[0];
                    brand = elements[1];
                    waiter = elements[2];
                    customer = elements[3];
                    sid = elements[4];
                    date = timeStamp2Date(timestamp);
                    outputFirstLine = "##" + mallId + "\t" + brand + "\t" + waiter + "\t" + customer + "\t" + sid + "\t" + date;

                } else if (!sid.equals(elements[4])) {
                    writer.write(outputFirstLine+"\n");
                    for (String text : conservation) {
                        writer.write(text);
                        writer.flush();
                    }
                    writer.write("\n");

                    conservation.clear();
                    mallId = elements[0];
                    brand = elements[1];
                    waiter = elements[2];
                    customer = elements[3];
                    sid = elements[4];
                    date = timeStamp2Date(timestamp);
                    outputFirstLine = "##" + mallId + "\t" + brand + "\t" + waiter + "\t" + customer + "\t" + sid + "\t" + date ;
                }
                String waiterSend = elements[6];

                sku = findSKU(content);
                if(!"".equals(sku)){
                    outputFirstLine += "\t" + sku;
                }
                String text;
                if (waiterSend.equals("0")) {
                    text = timestamp + "\t" + customer + " :" + content + "\n";
                } else {
                    text = timestamp + "\t" + waiter + " :" + content + "\n";
                }
                conservation.add(text);
            }
            writer.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("met error:" + e);
        }

    }

    static String findSKU(String text) {
        String result = "";
        if (text == null || "".equals(text)) {
            return "";
        } else {
            if (text.contains("item.jd.com/")) {
                String[] elements = text.split(sku_key_word_1);
                if (elements.length > 1) {
                    String[] tempElement = elements[1].split(getSku_key_word_1_end);
                    if(tempElement.length>=1){
                        result = elements[1].split(getSku_key_word_1_end)[0];
                    }
                }
            } else if (text.contains("商品编号：")) {
                String[] elements = text.split(sku_key_word_2);
                if (elements.length > 1) {
                    result = elements[1];
                } else if (elements.length == 1) {
                    result = elements[0];
                }
            }
        }
        return result;
    }

    public static String timeStamp2Date(String millisecond) {
        if (millisecond == null) {
            return "";
        }
        String format = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millisecond)));
    }

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

}
