package com.wey.ten.era.application.models.entity;

import java.util.Date;

public class Evaluation {

	/**
	 * 主鍵
	 */
	private Integer id;

	/**
	 * 外键，官员模型表ID
	 */
	private Integer industryModelId;
	
	/**
	 * 评价星级
	 */
	private Integer star;
	
	/**
	 * 所属 用户
	 */
	private Integer userId;

	/**
	 * 所属 用户 名称
	 */
	private String userName;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 地址
	 */
	private String adress;
	
	/**
	 * 评价内容
	 */
	private String contentDesc;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
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
