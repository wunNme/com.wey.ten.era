package com.wey.ten.era.home.entity.vo;

import com.wey.ten.era.common.utils.Page;
import com.wey.ten.era.home.entity.AdRotation;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class AdRotationVo extends Page implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 名称*/
    @ApiModelProperty(value = "名称")
    private String name;

    /** 图片*/
    @ApiModelProperty(value = "图片")
    private String imagePath;

    /** 排序*/
    @ApiModelProperty(value = "排序")
    private String orderBy;

    /** 提示信息*/
    @ApiModelProperty(value = "提示信息")
    private String resultMessage;

    /** 集合*/
    @ApiModelProperty(value = "集合")
    private List<AdRotation> list;

    public List<AdRotation> getList() {
        return list;
    }

    public void setList(List<AdRotation> list) {
        this.list = list;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
