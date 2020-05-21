package com.wey.ten.era.order.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 我的购买记录对象 user_purchase_history
 * 
 * @author yangws
 * @date 2020-05-18
 */
public class UserPurchaseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 订单详情ID 主键 */
	@ApiModelProperty(value = "订单详情ID 主键")
	private Integer id;

	/** 订单编号 */
	@ApiModelProperty(value = "订单编号")
	private String orderId;

	/** 应用分类 */
	@ApiModelProperty(value = "应用分类")
	private Integer applicationClassification;

	/** 用户ID */
	@ApiModelProperty(value = "用户ID")
	private Integer userId;

	/** 服务商ID */
	@ApiModelProperty(value = "服务商ID")
	private Integer serviceProviderUserId;

	/** 产品id */
	@ApiModelProperty(value = "产品id")
	private Integer softwareId;

	/** 是否收费 1：收费 2：免费 */
	@ApiModelProperty(value = "是否收费 1：收费  2：免费")
	private Integer isToll;

	/** 产品总价格 */
	@ApiModelProperty(value = "产品总价格")
	private Double price;

	/** 价格类型1：按日 2：按月3：按年 4：终身 5：次数 */
	@ApiModelProperty(value = "价格类型1：按日 2：按月3：按年  4：终身 5：次数")
	private Integer priceType;

	/** 有效期，当按次数，该值不用设置 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "有效期，当按次数，该值不用设置")
	private Date expirationDate;

	/** 是否有效 1：有效 2无效 */
	@ApiModelProperty(value = "是否有效 1：有效   2无效")
	private Integer isEffective;

	/** 使用次数，当价格类型为 5时使用 */
	@ApiModelProperty(value = "使用次数，当价格类型为  5时使用")
	private Integer useNumber;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(required = false, hidden = true)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(required = false, hidden = true)
	private Date updateTime;
	/**
	 * 产品信息对应
	 */
	@TableField(exist=false)
	private Object productObject;
	
	/**
	 * 用户信息
	 */
	@TableField(exist=false)
	private Object userInfo;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setApplicationClassification(Integer applicationClassification) {
		this.applicationClassification = applicationClassification;
	}

	public Integer getApplicationClassification() {
		return applicationClassification;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setServiceProviderUserId(Integer serviceProviderUserId) {
		this.serviceProviderUserId = serviceProviderUserId;
	}

	public Integer getServiceProviderUserId() {
		return serviceProviderUserId;
	}

	public void setSoftwareId(Integer softwareId) {
		this.softwareId = softwareId;
	}

	public Integer getSoftwareId() {
		return softwareId;
	}

	public void setIsToll(Integer isToll) {
		this.isToll = isToll;
	}

	public Integer getIsToll() {
		return isToll;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}

	public Integer getIsEffective() {
		return isEffective;
	}

	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}

	public Integer getUseNumber() {
		return useNumber;
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


	public Object getProductObject() {
		return productObject;
	}

	public void setProductObject(Object productObject) {
		this.productObject = productObject;
	}

	public Object getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("orderId", getOrderId())
				.append("applicationClassification",
						getApplicationClassification())
				.append("userId", getUserId())
				.append("serviceProviderUserId", getServiceProviderUserId())
				.append("softwareId", getSoftwareId())
				.append("isToll", getIsToll()).append("price", getPrice())
				.append("priceType", getPriceType())
				.append("expirationDate", getExpirationDate())
				.append("isEffective", getIsEffective())
				.append("useNumber", getUseNumber())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).toString();
	}
}
