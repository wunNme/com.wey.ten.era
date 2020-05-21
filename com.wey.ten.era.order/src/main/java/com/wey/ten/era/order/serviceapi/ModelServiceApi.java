package com.wey.ten.era.order.serviceapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wey.ten.era.common.utils.ResultObject;

/**
 * 用户模块API
 * @author yangws
 *
 */
@FeignClient(name="com.wey.ten.era.models")
public interface ModelServiceApi {

	@GetMapping("/models/industrymodel/detail/{id}")
    public ResultObject getModelById(@RequestParam("id") Integer id);
}
