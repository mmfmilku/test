package util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

    static Base64.Encoder encoder = Base64.getEncoder();

    static Base64.Decoder decoder = Base64.getDecoder();

    public static String encode2Str(byte[] src) {
        String res = "";
        try {
            res = new String(encode(src), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static byte[] encode(byte[] src) {
        return encoder.encode(src);
    }

    public static byte[] decode(byte[] src) {
        return decoder.decode(src);
    }

}
