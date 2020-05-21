package com.wey.ten.era.rbac.system.domain;

import com.wey.ten.era.rbac.system.entity.UserInfo;

public class LoginResponse {
	private String token;

	private String refreshToken;

	private String username;

	private UserInfo userInfo;

	public String getToken() {
		return token;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
