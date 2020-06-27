package utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * 对称加密
 * DES加密 解密算法
 **/

public class DESutils {
    private final static String DES = "DES";
    private final static String ENCODE = "utf-8";

    //就算打超过8位，也只用到前8位来加密，前8位一样则密文一样
    private final static String defaultKey = "11121112ABCSqqq";

    public static void main(String[] args) throws Exception{
        String data = "测试s"; //比如密码 1234GEFv
        System.out.println(encrypt(data, defaultKey));
        //可以自己设立规则，在密文中每隔两位加一个乱码，需要去掉才能解密
        System.out.println(decrypt("VqTJKQbZ6+g=", defaultKey ));
    }
    /**
     * 加密
     *
     **/
    public static String encrypt(String data, String key) throws Exception{
        byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
        String str  = new BASE64Encoder().encode(bt);
        return str;
    }

    /**
     * 解密
     *
     **/
    public static String decrypt(String data, String key) throws IOException, Exception{
        if(data == null){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes(ENCODE));
        return new String(bt, ENCODE);

    }

    /**
     * 加密
     *
     **/
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        //生成一个可信任的随机数
        SecureRandom sr  = new SecureRandom();

        //从原始数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        //创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // cipher 对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        //用密钥初始化cipher对象
        cipher.init(cipher.ENCRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);

    }

    /**
     * 解密
     *
     **/
    public static byte[] decrypt(byte[] data, byte[] key) throws IOException, Exception{
        //生成一个可信任的随机数
        SecureRandom sr  = new SecureRandom();

        //从原始数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        //创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // cipher 对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        //用密钥初始化cipher对象
        cipher.init(cipher.DECRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);

    }
}
