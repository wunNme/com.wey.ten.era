package com.wey.ten.era.rbac.system.controller;


import io.swagger.annotations.Api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.service.RegionService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
@Api(value="省份模块",tags={"省份接口"})
@RestController
@RequestMapping("/user/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(value = "getRegion/{pid}")
    public ResultObject getRegion(@PathVariable(name="pid") String pid){
        ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
        if (StringUtils.isEmpty(pid)) {
        	pid="0";
        }
        resultObject.setData(regionService.getRegion(Integer.valueOf(pid)));
        return resultObject;
    }
}

