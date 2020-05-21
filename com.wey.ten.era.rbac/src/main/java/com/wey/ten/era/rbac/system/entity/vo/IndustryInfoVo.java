package com.wey.ten.era.rbac.system.entity.vo;


public class IndustryInfoVo {

    private static final long serialVersionUID = 1L;

    /** 行业Id */
    private Integer industryId;

    /** 行业名称 */
    private String industryType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getIndustryType() {
        return industryType;
    }
}
