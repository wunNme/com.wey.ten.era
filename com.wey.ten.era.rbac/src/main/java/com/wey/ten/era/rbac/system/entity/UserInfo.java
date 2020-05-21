package com.wey.ten.era.rbac.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
@TableName(value = "user_info", resultMap = "BaseResultMap")
public class UserInfo {


    /**用户Id*/
    @ApiModelProperty(value="用户Id")
    @TableId
    private Integer userId;

    /**账号*/
    @ApiModelProperty(value="账号")
    private String accountNumber;

    /**密码*/
    @ApiModelProperty(value="密码")
    private String password;

    /**用户状态*/
    @ApiModelProperty(value="用户状态",hidden=true)
    private Integer userStatus;

    /**姓名*/
    @ApiModelProperty(value="姓名")
    private String userName;

    /**电话*/
    @ApiModelProperty(value="电话")
    private String phoneNumber;

    /**邮箱*/
    @ApiModelProperty(value="邮箱")
    private String email;

    /**创建时间*/
    @ApiModelProperty(hidden=true)
    private Date createTime;

    /**修改时间*/
    @ApiModelProperty(hidden=true)
    private Date updateTime;

    /**是否主账号*/
    @ApiModelProperty(hidden=true)
    private Integer isParent;

    /**所属企业*/
    @ApiModelProperty(hidden=true)
    @TableField(value = "affiliated_company")
    private Integer affiliatedCompany;

    /**企业信息*/
    @ApiModelProperty(hidden=true)
    @TableField(exist=false)
    private EnterpriseInfo enterpriseInfo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public void setAffiliatedCompany(Integer affiliatedCompany) {
        this.affiliatedCompany = affiliatedCompany;
    }

    public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public Integer getAffiliatedCompany() {
        return affiliatedCompany;
    }

    public EnterpriseInfo getEnterpriseInfo() {
        return enterpriseInfo;
    }
}
