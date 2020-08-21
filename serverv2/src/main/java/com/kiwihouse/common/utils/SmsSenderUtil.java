package com.kiwihouse.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class SmsSenderUtil {
	
	/**
	 * MD5加密KEY
	 */
	private final static String SMmsEncryptKey = "SMmsEncryptKey";
	
	public static boolean isNotEmpty(String s) {
		if(s == null || s.isEmpty())
			return false;
		return true;
	}
	
	public static long getCurrentTime() {
		return System.currentTimeMillis()/1000;
	}
	
	public static long getRandom() {
		return (new Random(getCurrentTime())).nextInt(900000) + 100000;
	}
	
	public static String sha256(String rawString) {
		return DigestUtils.sha256Hex(rawString);
	}
	
	static String md5(String password) {
		String encodeStr = DigestUtils.md5Hex(password + SMmsEncryptKey);
		return encodeStr.toUpperCase();
	}
	
	public static String calcMoReportSign(String accountId, String password, long random, long timestamp) {
		StringBuilder builder = new StringBuilder("AccountId=")
				.append(accountId)
				.append("&Password=")
				.append(md5(password))
				.append("&Random=")
				.append(random)
				.append("&Timestamp=")
				.append(timestamp);
		
		return sha256(builder.toString());
	}
	
	public static String calcSendSmsSign(String accountId, String mobile, String password, long random, long timestamp) {
		StringBuilder builder = new StringBuilder("AccountId=")
                .append(accountId)
                .append("&PhoneNos=")
                .append(mobile)
                .append("&Password=")
                .append(md5(password))
                .append("&Random=")
                .append(random)
                .append("&Timestamp=")
                .append(timestamp);
		
		return sha256(builder.toString());
	}
	
	public static String calcMutilSmsSign(String account, String password, long random, long timestamp)
    {
        StringBuilder builder = new StringBuilder("AccountId=")
            .append(account)
            .append("&Password=")
            .append(md5(password))
            .append("&Random=")
            .append(random)
            .append("&Timestamp=")
            .append(timestamp);

        return sha256(builder.toString());
    }
	
	public static String calcSendTemplateSmsSign(String accountId, String mobile, String password, int tempCode, long random, long timestamp)
    {
        StringBuilder builder = new StringBuilder("AccountId=")
            .append(accountId)
            .append("&PhoneNos=")
            .append(mobile)
            .append("&Password=")
            .append(md5(password))
            .append("&Random=")
            .append(random)
            .append("&TempCode=")
            .append(tempCode)
            .append("&Timestamp=")
            .append(timestamp);
        return sha256(builder.toString());
    }
}
