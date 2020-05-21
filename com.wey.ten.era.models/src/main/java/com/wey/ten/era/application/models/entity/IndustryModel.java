package com.wey.ten.era.application.models.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

public class IndustryModel {//extends Page
	
	@ApiModelProperty(value="模型ID")
	private Integer id;
	
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
	
	@ApiModelProperty(value="模型分类名称",hidden=true)
	@TableField(exist=false)
	private String classificationName;
	
	/**
	 * 所属行业
	 */
	@ApiModelProperty(value="行业名称")
	@TableField(exist=false)
	private String industryName;
	
	/**
	 * 公司名称
	 */
	@ApiModelProperty(value="企业名称")
	@TableField(exist=false)
	private String enterprisName;
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
	 * 产品介绍
	 */
	@ApiModelProperty(value="产品介绍")
	private String productIntroduction;

	/**
	 * 价格说明
	 */
	@ApiModelProperty(value="价格说明")
	private String priceExplanation;

	/**
	 * 使用说明
	 */
	@ApiModelProperty(value="使用说明")
	private String touseExplanation;

	/**
	 * 是否收费 1：收费  2：免费
	 */
	@ApiModelProperty(value="是否收费 1：收费  2：免费")
	private Integer isToll;

	/**
	 * 价格
	 */
	@ApiModelProperty(value="价格")
	private Double price;

	/**
	 * 价格类型1：按日 2：按月3：按年  4：终身 5：次数
	 */
	@ApiModelProperty(value="价格类型1：按日 2：按月3：按年  4：终身 5：次数")
	private Integer priceType;

	/**
	 * 使用次数，当价格类型为  5时使用
	 */
	@ApiModelProperty(value="使用次数，当价格类型为  5时使用")
	private Integer useNumber;

	/**
	 * 模型log地址：图片地址
	 */
	@ApiModelProperty(value="模型log地址：图片地址")
	private String modelLogUrl;

	/**
	 * 浏览量
	 */
	@ApiModelProperty(value="使用说明",hidden=true)
	private Long pageviews;

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

	/**
	 * 是否 删除  0：正常 1：删除
	 */
	@ApiModelProperty(value="是否 删除  0：正常 1：删除")
	private Integer del;

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

	/**
	 * 当前数据版本
	 */
	@ApiModelProperty(required=false,hidden=true)
	private int currentVersion;

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

	public String getProductIntroduction() {
		return productIntroduction;
	}

	public void setProductIntroduction(String productIntroduction) {
		this.productIntroduction = productIntroduction;
	}

	public String getPriceExplanation() {
		return priceExplanation;
	}

	public void setPriceExplanation(String priceExplanation) {
		this.priceExplanation = priceExplanation;
	}

	public String getTouseExplanation() {
		return touseExplanation;
	}

	public void setTouseExplanation(String touseExplanation) {
		this.touseExplanation = touseExplanation;
	}

	public Integer getIsToll() {
		return isToll;
	}

	public void setIsToll(Integer isToll) {
		this.isToll = isToll;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public Integer getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}

	public String getModelLogUrl() {
		return modelLogUrl;
	}

	public void setModelLogUrl(String modelLogUrl) {
		this.modelLogUrl = modelLogUrl;
	}

	public Long getPageviews() {
		return pageviews;
	}

	public void setPageviews(Long pageviews) {
		this.pageviews = pageviews;
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

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
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

	public int getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(int currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getEnterprisName() {
		return enterprisName;
	}

	public void setEnterprisName(String enterprisName) {
		this.enterprisName = enterprisName;
	}
	
}
