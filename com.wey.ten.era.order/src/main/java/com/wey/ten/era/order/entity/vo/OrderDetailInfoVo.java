package com.wey.ten.era.order.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wey.ten.era.common.utils.Page;

/**
 * 订单对象 order_detail_info
 * 
 * @author yangws
 * @date 2020-05-18
 */
public class OrderDetailInfoVo extends Page implements Serializable{
	private static final long serialVersionUID = 1L;


	/** 订单编号 */
	@ApiModelProperty(value = "订单编号")
	private String orderId;

	/** 应用分类 */
	@ApiModelProperty(value = "应用分类")
	private Integer applicationClassification;

	/** 采购商ID */
	@ApiModelProperty(value = "采购商ID")
	private Integer buyerUserId;

	/** 服务商企业名称 */
	@ApiModelProperty(value = "服务商企业名称")
	private String serviceProviderEnterprisName;

	/** 是否收费 1：收费 2：免费 */
	@ApiModelProperty(value = "是否收费 1：收费  2：免费")
	private Integer isToll;

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

	public void setServiceProviderEnterprisName(
			String serviceProviderEnterprisName) {
		this.serviceProviderEnterprisName = serviceProviderEnterprisName;
	}

	public String getServiceProviderEnterprisName() {
		return serviceProviderEnterprisName;
	}

	public void setIsToll(Integer isToll) {
		this.isToll = isToll;
	}

	public Integer getIsToll() {
		return isToll;
	}

}
