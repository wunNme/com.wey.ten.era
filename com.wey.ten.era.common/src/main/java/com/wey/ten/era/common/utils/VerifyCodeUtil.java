package com.wey.ten.era.common.utils;

import java.util.Random;

public class VerifyCodeUtil {
	/**
	 * 生成N位数字和字母混合的验证码
	 * 
	 * @param num 验证码位数
	 * @return code 生成的验证码字符串
	 */
	public String produceNumAndChar(int num) {
		Random random = new Random();
		String code = "";
		String ch = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
		String n = "123456789";
		for (int i = 0; i < num; i++) {
			int flag = random.nextInt(2);
			if (flag == 0) {// 数字
				code += n.charAt(random.nextInt(n.length()));
			} else {// 字母
				code += ch.charAt(random.nextInt(ch.length()));
			}
		}
		return code;
	}
}
