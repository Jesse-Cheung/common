package common;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	public static byte[] key = {86, 89, 66, 92, 87, 30, 113, 35, 92, 34, 53, 7, 75, 41, 104, 113};
    /**
     * 加密
     * 
     * @param content 需要加密的内容
     * @return
     */
	public static byte[] encryptBytes(byte[] content) {
        try {           
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(128, new SecureRandom(key));
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
                byte[] result = cipher.doFinal(content);
                return result; // 加密
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
}
	
    public static String encryptString(String content) {
    	byte[] bytes = null;
		try {
			bytes = encryptBytes(content.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return byte2String(bytes);
    }
    
    /**解密
     * @param content  待解密内容
     * @return
     */
    public static byte[] decryptBytes(byte[] content) {
        try {
                 KeyGenerator kgen = KeyGenerator.getInstance("AES");
                 kgen.init(128, new SecureRandom(key));
                 SecretKey secretKey = kgen.generateKey();
                 byte[] enCodeFormat = secretKey.getEncoded();
                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
                byte[] result = cipher.doFinal(content);
                return result; // 加密
        } catch (Exception e) {
                e.printStackTrace();
        } 
        return null;
}
    
    public static String decryptString(String content) {
    	byte[] enBytes = string2Byte(content);
    	byte[] deBytes = decryptBytes(enBytes);
        return new String(deBytes);
    }
    
    private static String byte2String(byte[] content) {
    	StringBuffer sb = new StringBuffer();
    	for (byte b : content) {
    		sb.append(b + ",");
    	}
    	return sb.substring(0, sb.length() - 1);
    }
    
    private static byte[] string2Byte(String content) {
    	String[] array = content.split(",");
    	byte[] bytes = new byte[array.length];
    	for (int i = 0; i < array.length; i ++) {
    		bytes[i] = Byte.parseByte(array[i]);
    	}
    	return bytes;
    }
    
}
