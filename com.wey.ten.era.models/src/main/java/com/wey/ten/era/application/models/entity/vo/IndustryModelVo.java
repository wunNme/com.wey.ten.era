package com.wey.ten.era.application.models.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wey.ten.era.common.utils.Page;

public class IndustryModelVo extends Page implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 模型名称
	 */
	@ApiModelProperty(value="模型名称")
	private String modelName;

	/**
	 * 模型类型，对应模型分类表
	 */
	@ApiModelProperty(value="模型类型，对应模型分类表")
	private Integer modelType;
	
	/**
	 * userId
	 */
	@ApiModelProperty(value="所属用户")
	private Integer userId;
	
	/**
	 * 模型 版本号
	 */
	@ApiModelProperty(value="模型 版本号")
	private String modelVersion;

	/**
	 * 所属行业
	 */
	@ApiModelProperty(value="所属行业")
	private Integer industry;

	/**
	 * 是否收费 1：收费  2：免费
	 */
	@ApiModelProperty(value="是否收费 1：收费  2：免费")
	private Integer isToll;

	/**
	 * 模型状态 0：正常  1：禁用
	 */
	@ApiModelProperty(value="模型状态 0：正常  1：禁用")
	private Integer modelStatus;

	/**
	 * 使用状态 1：上架  2：下架
	 */
	@ApiModelProperty(value="使用状态 1：上架  2：下架")
	private Integer useStatus;
	
	@ApiModelProperty(value="企业类型",hidden=true)
	private String enterprisType;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getModelType() {
		return modelType;
	}

	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getIsToll() {
		return isToll;
	}

	public void setIsToll(Integer isToll) {
		this.isToll = isToll;
	}

	public Integer getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(Integer modelStatus) {
		this.modelStatus = modelStatus;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEnterprisType() {
		return enterprisType;
	}

	public void setEnterprisType(String enterprisType) {
		this.enterprisType = enterprisType;
	}
	
}
