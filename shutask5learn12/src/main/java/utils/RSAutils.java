package utils;


import com.sun.media.sound.AiffFileReader;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密
 * 效率没有DES高
 * 公钥可以散布到网络上
 */
public class RSAutils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";

    //创建公钥私钥
    private static Map<String, String> createKeys(int keySize) {
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such Algorithm ["+  RSA_ALGORITHM +"]");
        }

        //初始化KeyPairGenerator对象，密钥长度
        kpg.initialize(keySize);
        //生成密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        //map装载公钥和私钥
        Map<String, String> keyPairMap  = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        //返回map
        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
       //通过X509编码的key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            System.out.println("publicKey.getModulus().bitCount()"+publicKey.getModulus().bitCount());
            byte[] encrypted = cipher.doFinal(data.getBytes());
            System.out.println("Encrypted String[RSA] -> " + encrypted);

            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE,
                    data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch (Exception e){
            throw new RuntimeException("加密字符串 [ " + data +" ] 时遇到异常");
        }
    }


    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
                    publicKey.getModulus().bitLength()));
        }catch (Exception e){
            throw new RuntimeException("解密字符串 [ " + data +" ] 时遇到异常");
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            // 每个cipher初始化方法使用一个模式参数opmod，并用此模式初始化cipher对象，另外还有其他参数，包括密钥key，包含密钥的证书certificate
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE,
                    data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch (Exception e){
            throw new RuntimeException("加密字符串 [ " + data +" ] 时遇到异常");
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            System.out.println("privateKey.getModulus().bitCount()"+privateKey.getModulus().bitCount());

//            byte[] Decrypted=data.getBytes();
//            byte[] dec = cipher.doFinal(Decrypted);
//            System.out.println("Decrypted String[RSA] -> " + dec.toString());
            byte[] result = cipher.doFinal(Base64.decodeBase64(data));
            System.out.println("decrypt:"+ new String(result, "utf-8"));

            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
                    privateKey.getModulus().bitLength()));
        }catch (Exception e){
            throw new RuntimeException("解密字符串 [ " + data +" ] 时遇到异常");
        }
    }

    // rsa切割解码， ENCRYPT_MODE 加密数据， DECRYPT_MODE 解密数据
//    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
//        int maxBlock = 0;//最大块
//        if(opmode == Cipher.DECRYPT_MODE){
//            maxBlock = keySize / 8;
//            System.out.println("DECRYPT_MODE:maxblock"+maxBlock);
//        }else{
//            // PKCS1 padding adds 11 bytes to your original data increasing it from 117 bytes to 128 bytes.
//            maxBlock = keySize / 8 - 11;
//            System.out.println("ENCRYPT:maxblock"+maxBlock);
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offset = 0;
//        byte[] buff;
//        int i = 0;
//        try{
//            while(datas.length >  offset){
//                if(datas.length - offset > maxBlock){
//                    //可以调用以下的doFinal方法完成加密或解密数据
//                    buff = cipher.doFinal(datas, offset, maxBlock);
//                }else{
//                    buff = cipher.doFinal(datas, offset, datas.length - offset);
//                }
//                out.write(buff, 0, buff.length);
//                i++;
//                offset = i * maxBlock;
//            }
//        }catch (Exception e){
//            throw new RuntimeException("加解密阀值为【" + maxBlock +"]的数据发生异常"+ e);
//        }
//        byte[] resultDatas = out.toByteArray();
//        IOUtils.closeQuietly(out);
//        return resultDatas;
//    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }




    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, String> keyMap = createKeys(1024);
        // 创建公钥私钥
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥" + publicKey);
        System.out.println("私钥" + privateKey);

        String str = "你好世界";

        System.out.println("公钥——私钥解密：");
        System.out.println("明文" + str);
        System.out.println("明文大小" + str.getBytes().length);

        // 传入明文和公钥加密, 得到密文
        String encodedData = publicEncrypt(str, getPublicKey(publicKey));
        System.out.println("密文" + encodedData);

        System.out.println(getPrivateKey(privateKey));
        // 传入密文和私钥，得到明文
        String decodedData = privateDecrypt(encodedData, getPrivateKey(privateKey));
        System.out.println("解密后文字" + decodedData);



    }
}
