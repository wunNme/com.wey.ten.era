package com.wey.ten.era.common.utils;

public class ResultObject {

	/**
	 * 返回碼
	 */
	private String errorCode;
	/**
	 * 返回碼描述
	 */
	private String errorMsg;
	/**
	 * 返回結果
	 */
	private Object data;

	public ResultObject() {
	}

	public ResultObject(ErrorCode errorCode,Object data){
		this.errorCode=errorCode.getValue();
		this.errorMsg=errorCode.getDesc();
		this.data = data;
	}
	

	public ResultObject(ErrorCode errorCode) {
		this.errorCode = errorCode.getValue();
		this.errorMsg = errorCode.getDesc();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultObject success(Object data) {
		ResultObject resultObject = new ResultObject();
		resultObject.setErrorCode(ErrorCode.SUCCESS.getValue());
		resultObject.setErrorMsg(ErrorCode.SUCCESS.getValue());
		resultObject.setData(data);
		return resultObject;
	}

	public static ResultObject fail(String msg) {
		ResultObject resultObject = new ResultObject();
		resultObject.setErrorCode(ErrorCode.PARAMS_ERROR.getValue());
		resultObject.setErrorMsg(msg);
		return resultObject;
	}
	
	public static ResultObject fail(String msg,Object data) {
		ResultObject resultObject = new ResultObject();
		resultObject.setErrorCode(ErrorCode.PARAMS_ERROR.getValue());
		resultObject.setErrorMsg(msg);
		resultObject.setData(data);
		return resultObject;
	}
}
