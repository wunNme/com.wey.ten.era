package com.wey.ten.era.rbac.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	@ApiModelProperty(value = "用户登录账号", required = true)
	private String username;

	@ApiModelProperty(value = "用户登录密码", required = true)
	private String password;

	@ApiModelProperty(value = "图片验证码", required = true)
	private String code;

}
