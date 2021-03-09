package pers.dingpingchuan.commons.heavy.utils.http;

import com.alibaba.fastjson.JSON;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HttpUtils {

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private static final OkHttpClient client = new OkHttpClient();

    // http://alpha-sales-proxy.jd.com/writing/predict?sku=45276783489


    public static void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://alpha-sales-proxy.jd.com/writing/predict?sku=45276783489")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("response:{}" + response.toString());
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        System.out.println(response.body().string());
    }

    public static String doGet(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

//        Headers responseHeaders = response.headers();
//        for (int i = 0; i < responseHeaders.size(); i++) {
//            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//        }
        return response.body().string();
    }

    public static void main(String[] args) throws Exception {
//        String url = "http://alpha-sales-proxy.jd.com/writing/predict?sku=45276783489";
//       System.out.println(doGet(url));
//
//        String filePath = "/" + getConTextPath() + "/src/main/resources/file/http.txt";
//
//
//        List<String> testList = readFileContent(filePath);
//        System.out.println(testList.size());
//        System.out.println(testList);

        Set<String> set = new HashSet<>();
        set.add("ew2");
        set.add("err");
        System.out.println(JSON.toJSONString(set));
    }
}
