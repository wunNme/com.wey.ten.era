package com.wey.ten.era.application.models.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ModelGroup {

	/**
	 * 主鍵
	 */
	@ApiModelProperty(value="分组ID，主键")
	private Integer id;

	/**
	 * 外键，官员模型表ID
	 */
	@ApiModelProperty(value="模型ID")
	private Integer industryModelId;

	/**
	 * 组名称
	 */
	@ApiModelProperty(value="模型名称")
	private String groupName;
	
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
