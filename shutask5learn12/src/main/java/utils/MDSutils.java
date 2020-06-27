package utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 单向加密:
 * 经常用来校验，比如加密原字符串和另一个字符串，对比加密后的密文是否相同，但看不到原字符串
 */
public class MDSutils {

    /**
     *  java 自带
     */
    public static String stringToMDS(String plainText){
        byte[] secretBytes = null;
        try{
            //            java自带类MessageDigest
            // MessageDigest.getInstance()源代码显示参数是byte【】所以要用getBytes
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("加密异常");
        }

        String md5code = new BigInteger(1, secretBytes).toString(16);
        for(int i = 0; i < 32 - md5code.length(); i++){
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        System.out.println(stringToMDS("感谢！"));
        // spring 自带工具包 DigestUtils
        System.out.println(DigestUtils.md5DigestAsHex("感谢！".getBytes()));
    }


}
