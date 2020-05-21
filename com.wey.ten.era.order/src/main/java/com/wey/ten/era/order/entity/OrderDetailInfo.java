package com.wey.ten.era.order.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 订单对象 order_detail_info
 * 
 * @author yangws
 * @date 2020-05-18
 */
public class OrderDetailInfo implements Serializable {
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

	/** 采购商ID */
	@ApiModelProperty(value = "采购商ID")
	private Integer buyerUserId;

	/** 采购商企业名称 */
	@ApiModelProperty(value = "采购商企业名称")
	private String buyerEnterprisName;

	/** 采购用户名称 */
	@ApiModelProperty(value = "采购用户名称")
	private String buyerUserName;

	/** 服务商ID */
	@ApiModelProperty(value = "服务商ID")
	private Integer serviceProviderUserId;

	/** 服务商企业名称 */
	@ApiModelProperty(value = "服务商企业名称")
	private String serviceProviderEnterprisName;

	/** 服务商用户名称 */
	@ApiModelProperty(value = "服务商用户名称")
	private String serviceProviderUserName;

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

	public void setBuyerUserId(Integer buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public Integer getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerEnterprisName(String buyerEnterprisName) {
		this.buyerEnterprisName = buyerEnterprisName;
	}

	public String getBuyerEnterprisName() {
		return buyerEnterprisName;
	}

	public void setBuyerUserName(String buyerUserName) {
		this.buyerUserName = buyerUserName;
	}

	public String getBuyerUserName() {
		return buyerUserName;
	}

	public void setServiceProviderUserId(Integer serviceProviderUserId) {
		this.serviceProviderUserId = serviceProviderUserId;
	}

	public Integer getServiceProviderUserId() {
		return serviceProviderUserId;
	}

	public void setServiceProviderEnterprisName(
			String serviceProviderEnterprisName) {
		this.serviceProviderEnterprisName = serviceProviderEnterprisName;
	}

	public String getServiceProviderEnterprisName() {
		return serviceProviderEnterprisName;
	}

	public void setServiceProviderUserName(String serviceProviderUserName) {
		this.serviceProviderUserName = serviceProviderUserName;
	}

	public String getServiceProviderUserName() {
		return serviceProviderUserName;
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
				.append("buyerUserId", getBuyerUserId())
				.append("buyerEnterprisName", getBuyerEnterprisName())
				.append("buyerUserName", getBuyerUserName())
				.append("serviceProviderUserId", getServiceProviderUserId())
				.append("serviceProviderEnterprisName",
						getServiceProviderEnterprisName())
				.append("serviceProviderUserName", getServiceProviderUserName())
				.append("softwareId", getSoftwareId())
				.append("isToll", getIsToll()).append("price", getPrice())
				.append("priceType", getPriceType())
				.append("useNumber", getUseNumber())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).toString();
	}
}
