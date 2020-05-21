package com.wey.ten.era.order.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 购物车对象 shopping_cart_info
 * 
 * @author yangws
 * @date 2020-05-18
 */
public class ShoppingCartInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 订单详情ID 主键 */
    @ApiModelProperty(value = "订单详情ID 主键")
    private Integer id;

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

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setApplicationClassification(Integer applicationClassification) 
    {
        this.applicationClassification = applicationClassification;
    }

    public Integer getApplicationClassification() 
    {
        return applicationClassification;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setServiceProviderUserId(Integer serviceProviderUserId) 
    {
        this.serviceProviderUserId = serviceProviderUserId;
    }

    public Integer getServiceProviderUserId() 
    {
        return serviceProviderUserId;
    }
    public void setSoftwareId(Integer softwareId) 
    {
        this.softwareId = softwareId;
    }

    public Integer getSoftwareId() 
    {
        return softwareId;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applicationClassification", getApplicationClassification())
            .append("userId", getUserId())
            .append("serviceProviderUserId", getServiceProviderUserId())
            .append("softwareId", getSoftwareId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
