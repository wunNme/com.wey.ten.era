package com.wey.ten.era.application.serviceapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wey.ten.era.common.utils.ResultObject;

/**
 * 用户模块API
 * @author yangws
 *
 */
@FeignClient(name="com.wey.ten.era.rbac")
public interface UserServiceApi {

	@GetMapping("/user/userInfo/getUserByUserId/{userId}")
    public ResultObject getUserById(@RequestParam("userId") String userId);
}
