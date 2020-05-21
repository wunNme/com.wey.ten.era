package com.wey.ten.era.order.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wey.ten.era.common.utils.Page;

/**
 * 订单对象 order_info
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public class OrderInfoVo extends Page implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 订单编号 */
	@ApiModelProperty(value = "订单编号")
	private String orderId;

	/** 订单状态 1：待支付 2：已支付 */
	@ApiModelProperty(value = "订单状态  1：待支付  2：已支付")
	private Integer orderStatus;

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

}
