package es;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class ESUserNameAndPassword {

    private static String basicAuthHeaderValue(String username, String passwd) {
        CharBuffer chars = CharBuffer.allocate(username.length() + passwd.length() + 1);
        byte[] charBytes = null;
        try {
            chars.put(username).put(':').put(passwd);
            charBytes = toUtf8Bytes(chars.array());

            String basicToken = Base64.getEncoder().encodeToString(charBytes);
            return "Basic " + basicToken;
        } finally {
            Arrays.fill(chars.array(), (char) 0);
            if (charBytes != null) {
                Arrays.fill(charBytes, (byte) 0);
            }
        }
    }

    private static byte[] toUtf8Bytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public static void main(String[] args) {
        String d = basicAuthHeaderValue("yanxi","yXMsF8lWZq0iYto4");
        System.out.println(d);
    }

    /**
     * curl -XPOST -H "Content-Type: application/json" -H "Authorization: Basic eWFueGk6eVhNc0Y4bFdacTBpWXRvNA==" http://es-nlb-es-bl0xp9vo3j.jvessel-open-hb.jdcloud.com:9200/open-knowledge-klg-doc/_search?pretty -d '{
     *     "query":{
     *         "match_all":{
     *
     *         }
     *     }
     * }’
     */
}
