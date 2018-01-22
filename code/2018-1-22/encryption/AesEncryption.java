package cf.android666.myapplication.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jixiaoyong on 2018/1/22.
 */

public class AesEncryption {

    private static final String AES = "AES";
    private static final String PASSWPRD = "123456";


    public static SecretKeySpec initKey(){
        SecretKeySpec key = null;

        try {

            KeyGenerator kg = KeyGenerator.getInstance(AES);
            kg.init(128,new SecureRandom(PASSWPRD.getBytes()));
            SecretKey securityKey = kg.generateKey();
            byte[] encodedKey = securityKey.getEncoded();
            key = new SecretKeySpec(encodedKey, AES);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return key;
    }

    public static byte[] encrypt(String source, SecretKeySpec key){
        byte[] s = null;

        try {

            Cipher cipher = Cipher.getInstance(AES);
            byte[] byteContent = source.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return s;
    }

    public static byte[] decrypt(byte[] source,SecretKeySpec key){
        byte[] result = null;

        try {

            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(source);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xff);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stringBuffer.append(hex.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr){
        if (hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    public static void main(String[] args) {
        String content = "test";
        String password = "12345678";

        SecretKeySpec keySpec = initKey();
//加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content,keySpec);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + encryptResultStr);
//解密
        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
        byte[] decryptResult = decrypt(decryptFrom,keySpec);
        System.out.println("解密后：" + new String(decryptResult));
    }
}
