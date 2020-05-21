package com.wey.ten.era.application.models.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wey.ten.era.common.utils.Page;

public class OnlineTestVo extends Page implements Serializable{
	
	private static final long serialVersionUID = 1L;


	/**
	 * 外键，官员模型表ID
	 */
	@ApiModelProperty(value="外键，官员模型表ID")
	private Integer industryModelId;
	
	@ApiModelProperty(value="外键，官员模型表ID")
	private Integer groupId;
	
	/**
	 * 在线模型名称
	 */
	@ApiModelProperty(value="在线模型名称")
	private String onlineModelName;
	
	/**
	 * 接口名称
	 */
	@ApiModelProperty(value="接口名称")
	private String interfaceName;


	/**
	 * 请求方式
	 */
	@ApiModelProperty(value="请求方式 格式：get/post")
	private String requestType;


	public Integer getIndustryModelId() {
		return industryModelId;
	}


	public void setIndustryModelId(Integer industryModelId) {
		this.industryModelId = industryModelId;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getOnlineModelName() {
		return onlineModelName;
	}


	public void setOnlineModelName(String onlineModelName) {
		this.onlineModelName = onlineModelName;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}


	public String getRequestType() {
		return requestType;
	}


	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	
}
