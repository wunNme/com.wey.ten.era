package com.wey.ten.era.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

	/***
	 * 加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encode(String encodeText) {
		String result = null;
		try {
			final Base64.Encoder encoder = Base64.getEncoder();
			byte[] bytes = encodeText.getBytes("UTF-8");
			result = encoder.encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解码
	 * 
	 * @param base64
	 * @return
	 */
	public static String decode(String base64) {
		String result = null;
		try {
			final Base64.Decoder decoder = Base64.getDecoder();
			result = new String(decoder.decode(base64), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// 第二种
		Base64.Encoder encoder = Base64.getEncoder();
		Base64.Decoder decoder = Base64.getDecoder();
		String pwd = "123456";
		byte[] byData = pwd.getBytes("UTF-8");
		String pwdEn = encoder.encodeToString(byData);
		System.out.println("加密：" + pwdEn);
		String pwdDe = new String(decoder.decode(pwdEn), "UTF-8");
		System.out.println("解密：" + pwdDe);
	}
}
