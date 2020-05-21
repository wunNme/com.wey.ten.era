package com.wey.ten.era.home.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AdRotation implements Serializable {
    private static final long serialVersionUID = 1L;


    /** id */
    @ApiModelProperty(value = "id")
    private int id;

    /** 图片 */
    @ApiModelProperty(value = "图片链接")
    private String imagePath;

    /** 名称*/
    @ApiModelProperty(value = "名称")
    private String name;

    /** 排序*/
    @ApiModelProperty(value = "排序")
    private String orderBy;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间",hidden = false)
    private int createTime;

    /** 修改时间 */
    @ApiModelProperty(value = "修改时间",hidden = false)
    private int updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
