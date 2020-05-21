package com.wey.ten.era.rbac.system.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "enterprise_info", resultMap = "BaseResultMap")
public class EnterpriseInfo {
	private static final long serialVersionUID = 1L;

	/** 企业账号 */
	@ApiModelProperty(value = "企业账号")
	@TableId(value = "enterprise_id")
	private Integer enterpriseId;

	/** 关联用户id */
	@ApiModelProperty(value = "关联用户id")
	@TableField(value = "user_id")
	private Integer userId;

	/** 企业名称 */
	@ApiModelProperty(value = "企业名称")
	private String enterprisName;

	/** 企业认证类型:1(采购商);2(服务商);3(平台租户)。 */
	@ApiModelProperty(value = "企业认证类型:1(采购商);2(服务商);3(平台租户)")
	private Integer enterprisAuthenticationType;

	/** 企业类型 */
	@ApiModelProperty(value = "企业类型")
	private String enterprisType;

	/** 认证状态：1(未认证);2(审核中);3(已认证);4(认证失败)。 */
	@ApiModelProperty(value = "认证状态：1(未认证);2(审核中);3(已认证);4(认证失败)")
	private Integer enterprisStatus;

	/** 企业审核失败原因 */
	@ApiModelProperty(value = "企业审核失败原因 ")
	private String enterprisAuditFailure;

	/** 企业所属行业 */
	@ApiModelProperty(value = "企业所属行业 ")
	private Integer enterprisIndustry;

	/** 企业统一社会信用代码/注册号/组织机构代码 */
	@ApiModelProperty(value = "企业统一社会信用代码/注册号/组织机构代码")
	private String enterprisRegistrationCode;

	/** 企业营业执照(彩色照片) */
	@ApiModelProperty(value = "企业营业执照(彩色照片) ")
	private String enterprisBusinessLicenssePath;

	/** 企业注册地址 */
	@ApiModelProperty(value = "企业注册地址 ")
	private String enterprisAddress;

	/** 企业经营开始时间 */
	@ApiModelProperty(value = "企业经营开始时间")
	private Date enterprisStartTime;

	/** 企业经营结束时间 */
	@ApiModelProperty(value = "企业经营结束时间")
	private Date enterprisEndTime;

	/** 企业法人 */
	@ApiModelProperty(value = "企业法人")
	private String enterprisLegalPerson;

	/** 企业法人手机号码 */
	@ApiModelProperty(value = "企业法人手机号码")
	private Long enterprisPhoneNumber;

	/** 企业法人身份证号码 */
	@ApiModelProperty(value = "企业法人身份证号码")
	private Long enterprisIdNumber;

	/** 企业法人身份证照片(正面) */
	@ApiModelProperty(value = "企业法人身份证照片(正面)")
	private String enterprisIdImagePathPositive;

	/** 企业法人身份证照片(反面) */
	@ApiModelProperty(value = "企业法人身份证照片(反面)")
	private String enterprisIdImagePathAnti;

	/** 企业服务方向1(软件提供);2(能力提供);3(设备提供)。 */
	@ApiModelProperty(value = "企业服务方向1(软件提供);2(能力提供);3(设备提供)")
	private Integer enterprisServiceDirection;

	/** 企业客服电话座机 */
	@ApiModelProperty(value = "企业客服电话座机")
	private String enterprisCustomerTelephone;

	/** 企业认证创建时间 */
	@ApiModelProperty(value = "企业认证创建时间", hidden = true)
	private Date enterprisCreateTime;

	/** 企业认证修改时间 */
	@ApiModelProperty(value = "企业认证修改时间", hidden = true)
	private Date enterprisUpdateTime;

	/** 企业认证提交时间 */
	@ApiModelProperty(value = "企业认证提交时间", hidden = true)
	private Date enterprisSubmissionTime;

	/** 企业上传资料证明 */
	@ApiModelProperty(value = "企业上传资料证明", hidden = true)
	private String enterprisEvidence;
	
	   /** 行业名称 */
	@ApiModelProperty(value = "企业上传资料证明", hidden = true)
	@TableField(exist=false)
    private String industryName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setEnterprisName(String enterprisName) {
		this.enterprisName = enterprisName;
	}

	public void setEnterprisAuthenticationType(Integer enterprisAuthenticationType) {
		this.enterprisAuthenticationType = enterprisAuthenticationType;
	}

	public void setEnterprisType(String enterprisType) {
		this.enterprisType = enterprisType;
	}

	public void setEnterprisStatus(Integer enterprisStatus) {
		this.enterprisStatus = enterprisStatus;
	}

	public void setEnterprisAuditFailure(String enterprisAuditFailure) {
		this.enterprisAuditFailure = enterprisAuditFailure;
	}

	public void setEnterprisIndustry(Integer enterprisIndustry) {
		this.enterprisIndustry = enterprisIndustry;
	}

	public void setEnterprisRegistrationCode(String enterprisRegistrationCode) {
		this.enterprisRegistrationCode = enterprisRegistrationCode;
	}

	public void setEnterprisBusinessLicenssePath(String enterprisBusinessLicenssePath) {
		this.enterprisBusinessLicenssePath = enterprisBusinessLicenssePath;
	}

	public void setEnterprisAddress(String enterprisAddress) {
		this.enterprisAddress = enterprisAddress;
	}

	public void setEnterprisStartTime(Date enterprisStartTime) {
		this.enterprisStartTime = enterprisStartTime;
	}

	public void setEnterprisEndTime(Date enterprisEndTime) {
		this.enterprisEndTime = enterprisEndTime;
	}

	public void setEnterprisLegalPerson(String enterprisLegalPerson) {
		this.enterprisLegalPerson = enterprisLegalPerson;
	}

	public void setEnterprisPhoneNumber(Long enterprisPhoneNumber) {
		this.enterprisPhoneNumber = enterprisPhoneNumber;
	}

	public void setEnterprisIdNumber(Long enterprisIdNumber) {
		this.enterprisIdNumber = enterprisIdNumber;
	}

	public void setEnterprisIdImagePathPositive(String enterprisIdImagePathPositive) {
		this.enterprisIdImagePathPositive = enterprisIdImagePathPositive;
	}

	public void setEnterprisIdImagePathAnti(String enterprisIdImagePathAnti) {
		this.enterprisIdImagePathAnti = enterprisIdImagePathAnti;
	}

	public void setEnterprisServiceDirection(Integer enterprisServiceDirection) {
		this.enterprisServiceDirection = enterprisServiceDirection;
	}

	public void setEnterprisCustomerTelephone(String enterprisCustomerTelephone) {
		this.enterprisCustomerTelephone = enterprisCustomerTelephone;
	}

	public void setEnterprisCreateTime(Date enterprisCreateTime) {
		this.enterprisCreateTime = enterprisCreateTime;
	}

	public void setEnterprisUpdateTime(Date enterprisUpdateTime) {
		this.enterprisUpdateTime = enterprisUpdateTime;
	}

	public void setEnterprisSubmissionTime(Date enterprisSubmissionTime) {
		this.enterprisSubmissionTime = enterprisSubmissionTime;
	}

	public void setEnterprisEvidence(String enterprisEvidence) {
		this.enterprisEvidence = enterprisEvidence;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getEnterprisName() {
		return enterprisName;
	}

	public Integer getEnterprisAuthenticationType() {
		return enterprisAuthenticationType;
	}

	public String getEnterprisType() {
		return enterprisType;
	}

	public Integer getEnterprisStatus() {
		return enterprisStatus;
	}

	public String getEnterprisAuditFailure() {
		return enterprisAuditFailure;
	}

	public Integer getEnterprisIndustry() {
		return enterprisIndustry;
	}

	public String getEnterprisRegistrationCode() {
		return enterprisRegistrationCode;
	}

	public String getEnterprisBusinessLicenssePath() {
		return enterprisBusinessLicenssePath;
	}

	public String getEnterprisAddress() {
		return enterprisAddress;
	}

	public Date getEnterprisStartTime() {
		return enterprisStartTime;
	}

	public Date getEnterprisEndTime() {
		return enterprisEndTime;
	}

	public String getEnterprisLegalPerson() {
		return enterprisLegalPerson;
	}

	public Long getEnterprisPhoneNumber() {
		return enterprisPhoneNumber;
	}

	public Long getEnterprisIdNumber() {
		return enterprisIdNumber;
	}

	public String getEnterprisIdImagePathPositive() {
		return enterprisIdImagePathPositive;
	}

	public String getEnterprisIdImagePathAnti() {
		return enterprisIdImagePathAnti;
	}

	public Integer getEnterprisServiceDirection() {
		return enterprisServiceDirection;
	}

	public String getEnterprisCustomerTelephone() {
		return enterprisCustomerTelephone;
	}

	public Date getEnterprisCreateTime() {
		return enterprisCreateTime;
	}

	public Date getEnterprisUpdateTime() {
		return enterprisUpdateTime;
	}

	public Date getEnterprisSubmissionTime() {
		return enterprisSubmissionTime;
	}

	public String getEnterprisEvidence() {
		return enterprisEvidence;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
}
