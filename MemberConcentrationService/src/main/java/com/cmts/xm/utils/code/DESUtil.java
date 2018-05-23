/* ========================================================
 * 哈尔滨富利通科技有限公司研发二部
 * 日 期：2016-1-14
 * 功能：
 * 作 者：苏少轩
 * 版 本：1.0
 * =========================================================
 */
package com.cmts.xm.utils.code;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DESUtil {
	// 密钥
	private static Key key;
	// KEY种子
	private static String KEY_STR = "mtxcmts";
	// 常量
	public static final String UTF_8 = "UTF-8";
	public static final String DES = "DES";

	// 静态初始化
	static {
		try {
			// KEY 生成器
			KeyGenerator generator = KeyGenerator.getInstance(DES);
			// 初始化,安全随机算子
			generator.init(new SecureRandom(KEY_STR.getBytes(UTF_8)));
			// 生成密钥
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对源字符串加密,返回 BASE64编码后的加密字符串
	 * 
	 * @param source
	 *            源字符串,明文
	 * @return 密文字符串
	 */
	public static String encode(String source) {
		try {
			// 根据编码格式获取字节数组
			byte[] sourceBytes = source.getBytes(UTF_8);
			// DES 加密模式
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 加密后的字节数组
			byte[] encryptSourceBytes = cipher.doFinal(sourceBytes);
			// Base64编码器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			return base64Encoder.encode(encryptSourceBytes);
		} catch (Exception e) {
			// throw 也算是一种 return 路径
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对本工具类 encode() 方法加密后的字符串进行解码/解密
	 * 
	 * @param encrypted
	 *            被加密过的字符串,即密文
	 * @return 明文字符串
	 */
	public static String decode(String encrypted) {
		// Base64解码器
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			// 先进行base64解码
			byte[] cryptedBytes = base64Decoder.decodeBuffer(encrypted);
			// DES 解密模式
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 解码后的字节数组
			byte[] decryptStrBytes = cipher.doFinal(cryptedBytes);
			// 采用给定编码格式将字节数组变成字符串
			return new String(decryptStrBytes, UTF_8);
		} catch (Exception e) {
			// 这种形式确实适合处理工具类
			throw new RuntimeException(e);
		}
	}

	public static byte[] txt2String(String filePath) throws Exception {
		 FileInputStream in= new FileInputStream(filePath);  
         
	        ByteArrayOutputStream out=new ByteArrayOutputStream(1024);  
	          
	        System.out.println("bytes available:"+in.available());  
	          
	        byte[] temp=new byte[1024];  
	          
	        int size=0;  
	          
	        while((size=in.read(temp))!=-1)  
	        {  
	            out.write(temp,0,size);  
	        }  
	          
	        in.close();  
	        
	        byte[] bytes=out.toByteArray();  
	        System.out.println("bytes size got is:"+bytes.length);  
	          
	        return bytes;  
	}

	// 单元测试
	public static void main(String[] args) throws Exception {
		// 需要加密的字符串
		// String ttt = "mtx3cmts4mtx3";
		// // 加密
		// String encrypted = DESUtil.encode(ttt);
		// // 解密
		// String decrypted = DESUtil.decode(encrypted);
		// // 输出结果;
		// System.out.println("ttt: " + ttt);
		// System.out.println("encrypted: " + encrypted);
		// System.out.println("decrypted: " + decrypted);
		// System.out.println("ttt.equals(decrypted): " +
		// ttt.equals(decrypted));
//		File file = new File("E:/10C/123123.cmtskey");
		byte[] aa = txt2String("E:/10C/1111.cmtskey");
		String a  = new String(aa,UTF_8);
		System.out.println(getFromBase64(decrypt(a, "12345678")));
		
//		BASE64Decoder base64Decoder = new BASE64Decoder();
//		byte[] b = base64Decoder.de
//		
//		String strs = new BASE64Encoder().encode(bt);
//		System.out.println(a);
////		a = DESUtil.decode(b);
//		System.out.println(new String(base64Decoder.decodeBuffer(a),UTF_8));
	}
	
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException,
	        Exception {
	    if (data == null)
	        return null;
	    BASE64Decoder decoder = new BASE64Decoder();
	    byte[] buf = decoder.decodeBuffer(data);
	    byte[] bt = decrypt(buf,key.getBytes());
	    return new String(bt);
	}
	
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
	    // 生成一个可信任的随机数源
	    SecureRandom sr = new SecureRandom();

	    // 从原始密钥数据创建DESKeySpec对象
	    DESKeySpec dks = new DESKeySpec(key);

	    // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    SecretKey securekey = keyFactory.generateSecret(dks);

	    // Cipher对象实际完成解密操作
	    Cipher cipher = Cipher.getInstance("DES");

	    // 用密钥初始化Cipher对象
	    cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

	    return cipher.doFinal(data);
	}
	
	// Base64解密
		public static String getFromBase64(String s) {
			byte[] b = null;
			String result = null;
			if (s != null) {
				BASE64Decoder decoder = new BASE64Decoder();
				try {
					b = decoder.decodeBuffer(s);
					result = new String(b, "utf-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
}
