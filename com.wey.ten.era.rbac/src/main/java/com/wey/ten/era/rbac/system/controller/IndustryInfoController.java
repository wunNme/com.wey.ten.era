package com.wey.ten.era.rbac.system.controller;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.service.IndustryInfoService;

@Api(value="行业controller",tags={"行业操作接口"})
@RestController
@RequestMapping("/user/IndustryInfo")
public class IndustryInfoController {

    @Autowired
    private IndustryInfoService industryInfoService;

    /**
     * 查询行业信息列表
     */
    @GetMapping(value = "getAll")
    public ResultObject getAll()
    {
        ResultObject resultObject = null;
        resultObject = industryInfoService.getAll();
        return resultObject;
    }
}
