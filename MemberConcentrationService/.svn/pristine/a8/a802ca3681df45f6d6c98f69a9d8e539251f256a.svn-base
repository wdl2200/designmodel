package com.cmts.xm.utils.code;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;

/**
 * User:LD
 */
public class AESUtil {
    public static final String GEN_PASSWORLD = "crazycrazycrazy1";//客户端解密，服务器端加密使用
    //public static final String GEN_PASSWORLD_JIE = "";//客户端加密，服务器端解密使用
    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
           // Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] byteContent = fromHexString(content);
            byte[] result = cipher.doFinal(byteContent);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        try {
            for (int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Android加密后要截取多余的字符，根据原始长度，去掉后面多余的0
     *
     * @param data
     * @param oriLen 原本长度
     * @return
     */
    public static byte[] trip(byte[] data, int oriLen) {
        if(data.length == oriLen) {
            return data;
        } else {
            byte[] nData = new byte[oriLen];
            for (int i = 0; i < oriLen; i++) {
                nData[i] = data[i];
            }
            return nData;
        }
    }

    /**
     * 解密Android
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decryptAndroidAndIos(String content, String password, int length) {
        String s = decrypt(content, password);
        if (StringUtils.isNotBlank(s)) {
            try {
                return new String(trip(s.getBytes(), length),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
    
    public static void main(String[] args){
    	String phone = AESUtil.encrypt("13401158231", "crazycrazycrazy1");
    	System.out.println(phone);
    	String phone1 = AESUtil.decrypt("AEC750253A2F61EDCD631C4A43B0B0AE", "crazycrazycrazy1");
    	System.out.println(phone1);
    	String phone2 = AESUtil.decrypt("07CF65A3DBC6552D2BD4F1196116BC83", "crazycrazycrazy1");
    	System.out.println(phone2);
    	
    	String phone4 = AESUtil.encrypt("15545016800", "crazycrazycrazy1");
    	System.out.println(phone4);
    	System.out.println(AESUtil.decryptAndroidAndIos("8205C402F364B5A55A1215FF3B55D1AE", "crazycrazycrazy1", 11));
    }
}
