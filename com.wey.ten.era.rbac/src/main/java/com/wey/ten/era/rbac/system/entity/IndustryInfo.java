package com.wey.ten.era.rbac.system.entity;

import java.util.Date;

public class IndustryInfo {

    private static final long serialVersionUID = 1L;

    /** 行业Id */
    private Integer industryId;

    /** 行业名称 */
    private String industryType;

    /** 行业创建时间 */
    private Date createTime;

    /** 行业修改时间 */
    private Date updateTime;

    /** 行业提交时间 */
    private Date submissionTime;

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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getIndustryType() {
        return industryType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }
}
