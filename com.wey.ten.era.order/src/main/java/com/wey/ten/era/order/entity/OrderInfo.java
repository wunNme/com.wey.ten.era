package com.wey.ten.era.order.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 订单对象 order_info
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;

	/** 订单编号 */
	@ApiModelProperty(value = "订单编号")
	private String orderId;

	/** 采购商ID */
	@ApiModelProperty(value = "采购商ID")
	private Integer buyerUserId;

	/** 订单状态 1：待支付 2：已支付 */
	@ApiModelProperty(value = "订单状态  1：待支付  2：已支付 3:已取消")
	private Integer orderStatus;

	/** 订单总金额 */
	@ApiModelProperty(value = "订单总金额")
	private Double orderTotalAmount;
	
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
	
	@ApiModelProperty(required = false, hidden = true)
	@TableField(exist=false)
	private Object orderDetail;
	
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

	public void setBuyerUserId(Integer buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public Integer getBuyerUserId() {
		return buyerUserId;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public Double getOrderTotalAmount() {
		return orderTotalAmount;
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

	public Object getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Object orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId()).append("orderId", getOrderId())
				.append("buyerUserId", getBuyerUserId())
				.append("orderStatus", getOrderStatus())
				.append("orderTotalAmount", getOrderTotalAmount())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).toString();
	}
}
