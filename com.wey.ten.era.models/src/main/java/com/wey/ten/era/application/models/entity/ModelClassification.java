package com.wey.ten.era.application.models.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ModelClassification {

	/**
	 * 分类ID主键
	 */
	@ApiModelProperty(value="分类ID")
	private Integer classificationId;

	/**
	 * 分类名称
	 */
	@ApiModelProperty(value="分类名称")
	private String classificationName;

	/**
	 * 状态 1：正常 2：禁用
	 */
	@ApiModelProperty(hidden=true)
	private Integer classificationStatus;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden=true)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(hidden=true)
	private Date updateTime;

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public Integer getClassificationStatus() {
		return classificationStatus;
	}

	public void setClassificationStatus(Integer classificationStatus) {
		this.classificationStatus = classificationStatus;
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

}
