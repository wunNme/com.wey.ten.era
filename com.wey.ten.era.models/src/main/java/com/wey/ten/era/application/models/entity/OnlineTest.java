package com.wey.ten.era.application.models.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OnlineTest {//extends Page{
	/**
	 * 主鍵
	 */
	@ApiModelProperty(value="主鍵")
	private Integer id;

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
	 * 接口说明
	 */
	@ApiModelProperty(value="接口说明")
	private String interfaceExplanaion;

	/**
	 * 接口访问地址
	 */
	@ApiModelProperty(value="接口访问地址")
	private String accessAdress;

	/**
	 * 请求方式
	 */
	@ApiModelProperty(value="请求方式 格式：get/post")
	private String requestType;

	/**
	 * 协议
	 */
	@ApiModelProperty(value="协议")
	private String protocal;

	/**
	 * 编码格式
	 */
	@ApiModelProperty(value="编码格式")
	private String encodingFormat;
	
	/**
	 * 返回格式
	 */
	@ApiModelProperty(value="返回格式")
	private String resultFormat;
	
	/**
	 * 请求参数
	 */
	@ApiModelProperty(value="请求参数 格式：[{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'},{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'}]")
	private String requestParams;
	
	/**
	 * 返回数据结构
	 */
	@ApiModelProperty(value="返回数据结构 格式：[{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'},{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'}]")
	private String resultDataStructure;
	
	/**
	 * 返回码
	 */
	@ApiModelProperty(value="返回码 格式:[{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'},{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'}]")
	private String resultErrorStructure;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,hidden=true)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(required=false,hidden=true)
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndustryModelId() {
		return industryModelId;
	}

	public void setIndustryModelId(Integer industryModelId) {
		this.industryModelId = industryModelId;
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

	public String getInterfaceExplanaion() {
		return interfaceExplanaion;
	}

	public void setInterfaceExplanaion(String interfaceExplanaion) {
		this.interfaceExplanaion = interfaceExplanaion;
	}

	public String getAccessAdress() {
		return accessAdress;
	}

	public void setAccessAdress(String accessAdress) {
		this.accessAdress = accessAdress;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getProtocal() {
		return protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public String getEncodingFormat() {
		return encodingFormat;
	}

	public void setEncodingFormat(String encodingFormat) {
		this.encodingFormat = encodingFormat;
	}

	public String getResultFormat() {
		return resultFormat;
	}

	public void setResultFormat(String resultFormat) {
		this.resultFormat = resultFormat;
	}

	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}

	public String getResultDataStructure() {
		return resultDataStructure;
	}

	public void setResultDataStructure(String resultDataStructure) {
		this.resultDataStructure = resultDataStructure;
	}

	public String getResultErrorStructure() {
		return resultErrorStructure;
	}

	public void setResultErrorStructure(String resultErrorStructure) {
		this.resultErrorStructure = resultErrorStructure;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
