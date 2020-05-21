package com.wey.ten.era.rbac.system.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExcel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Excel(name = "账号")
	private String account;

	@Excel(name = "密码")
	private String password;

	@Excel(name = "姓名")
	private String name;

	@Excel(name = "手机号码")
	private String mobile;

	@Excel(name = "邮箱")
	private String email;

}
