package util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class Encrypt {

    static final String KEY = "1234abcd";

    static final String ENCODE = "UTF-8";

    static final String DES = "DES";

    public static byte[] encrypt(byte[] data) {
        return calculate(Cipher.ENCRYPT_MODE, data);
    }

    public static byte[] decrypt(byte[] data) {
        return calculate(Cipher.DECRYPT_MODE, data);
    }

    private static byte[] calculate(int ENCRYPT_MODE, byte[] data) {
        byte[] res = new byte[0];
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(KEY.getBytes(ENCODE));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(DES);

            cipher.init(ENCRYPT_MODE, secretKey, sr);

            res = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
