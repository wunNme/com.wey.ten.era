package com.wey.ten.era.rbac.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 应用分类表 base_app_clazz
 * 
 * @author wuj
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppClazz implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 分类ID */
	private Long appClazzId;

	/** 父分类ID */
	private Long parentId;

	/** 祖级列表 */
	private String ancestors;

	/** 分类名称 */
	@NotBlank(message = "分类名称不能为空")
	@Size(min = 0, max = 30, message = "分类名称长度不能超过30个字符")
	private String clazzName;

	/** 显示顺序 */
	@NotBlank(message = "显示顺序不能为空")
	private String orderNum;

	/** 负责人 */
	private String leader;

	/** 联系电话 */
	@Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
	private String phone;

	/** 邮箱 */
	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	private String email;

	/** 分类状态:0正常,1停用 */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	/** 父分类名称 */
	private String parentName;

	private String createBy;

	private Date createTime;

	private String updateBy;

	private Date updateTime;

	/** 子分类 */
	@Builder.Default
	private List<AppClazz> children = new ArrayList<AppClazz>();

}
