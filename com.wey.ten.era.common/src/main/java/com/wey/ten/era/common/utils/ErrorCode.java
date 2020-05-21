package com.wey.ten.era.common.utils;

public enum ErrorCode {

	LOGIN_ERROR("1000", "用户名或密码错误"), PARAMETER_ILLEGAL("2001", "参数不合法"), TOKEN_INVALID("2002", "无效的Token"),
	TOKEN_SIGNATURE_INVALID("2003", "无效的签名"), TOKEN_EXPIRED("2004", "token已过期"), TOKEN_MISSION("2005", "token缺失"),
	REFRESH_TOKEN_INVALID("2006", "刷新Token无效"),

	/**
	 * 系统
	 */
	SUCCESS("00000000", "操作成功"), PARAMS_ERROR("90000003", "参数异常"), TOKEN_VALID_EXPIRED("90000001", "用户token失效,请重新登录"),
	TOKEN_FORMAT_ERROR("90000002", "token格式异常,请检查后重试"), SYSTEM_ERROR("90000004", "服务器异常，请稍后重试"),
	FILE_UPLOAD_FAIL("90000005", "文件上传失败"), UNKNOWN_ERROR("90000006", "系统繁忙，请稍后再试...."),
	/**
	 * 用户模块
	 */
	AACCOUNT_PASSWORD_ERROR("91000003", "账号或密码错误"), PASSWORD_INCINSISTENT_ERROR("91000001", "密码和确认密码不一致"),PASSWORD_CURRENT_ERROR("91000016", "当前密码与登陆密码不一致"),
	PHONE_EXISTED_ERROR("91000002", "手机号码已存在"), EMAIL_EXISTED_ERROR("91000002", "邮箱已存在"),
	CAPTCHA_ERROR("91000004", "验证码错误"), LOGIN_FAIL("91000005", "登录失败"), PASSWORD_ERROR("91000006", "用户名或密码错误"),
	BIND_FAIL_NO_PERMISSION("91000007", "操作失败，该用户已有绑定"),
	EMAIL_SEND_FAIL("91000008", "邮箱发送失败，请重新操作"),
	EMAIL_SEND_FAILURE("91000009", "验证码已失效"),
	PHONE_VERIFICATION_FAIL("91000016", "验证码输入错误"),
	IMAGE_SEND_FAILURE("91000012", "验证码已失效"),
	PHONE_NUMBER_FORMAT_INCORRECT("91000013", "手机号码格式不正确"),
	EMAIL_FORMAT_INCORRECT("91000014", "邮箱格式不正确"),
	PLEASE_INPUT_PASSWORD("91000015", "请输入密码"),

	/**
	 * 模型
	 */
	REQUEST_PARAMS_ERROR("92000000", "请求参数错误"), REQUEST_FAIL_ERROR("92000001", "请求失败，请稍后重试"),
	
	/**
	 * 订单
	 */
	ORDER_ADD_FAIL("93000000", "生成订单出错，请稍后重试"),
	ORDER_QUEST_FAIL("93000000", "查询失败，请稍后重试")
	;

	private String value;

	private String desc;

	private ErrorCode(String value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
