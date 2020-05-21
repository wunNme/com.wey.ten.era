package com.wey.ten.era.order.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wey.ten.era.common.utils.Page;

/**
 * 购物车对象 shopping_cart_info
 * 
 * @author yangws
 * @date 2020-05-18
 */
public class ShoppingCartInfoVo extends Page implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 应用分类 */
    @ApiModelProperty(value = "应用分类")
    private Integer applicationClassification;

    public void setApplicationClassification(Integer applicationClassification) 
    {
        this.applicationClassification = applicationClassification;
    }

    public Integer getApplicationClassification() 
    {
        return applicationClassification;
    }

}
