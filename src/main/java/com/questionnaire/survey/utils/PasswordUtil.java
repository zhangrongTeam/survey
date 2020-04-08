package com.questionnaire.survey.utils;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 密码加密工具
 * @author autoCode
 * @version 2017-09-25 08:52
 */
public final class PasswordUtil {

	public static final int SALT_SIZE = 8;
	
	public static final int HASH_INTERATIONS = 1024;

    private PasswordUtil() {
        throw new IllegalStateException();
    }

    /**
     * 使用SHA-256进行密码加密
     * @param plainPassword 密码原文
     */
    public static String encrypt(String plainPassword) {
        return sha256Hex(plainPassword);
    }

    public static boolean validatePassword(String plainPassword, String cipherPassword) {
        return isNotBlank(plainPassword) && encrypt(plainPassword).equals(cipherPassword);

    }
    
    
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword2(String plainPassword, String password) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	
	public static String getSixCode(){
	    int intFlag = (int)(Math.random() * 1000000);
	    String flag = String.valueOf(intFlag);
	    if (flag.length() == 6 && flag.substring(0, 1).equals("9")){
	    	System.out.println(intFlag);
	    }else{
	        intFlag = intFlag + 100000;
	        System.out.println(intFlag);
	    }
	    return intFlag+"";
	}
	
	public static void main(String[] args) {
		String entryptPassword = entryptPassword("123456");
		System.out.println(entryptPassword);
	}
	
	
	
	

}
