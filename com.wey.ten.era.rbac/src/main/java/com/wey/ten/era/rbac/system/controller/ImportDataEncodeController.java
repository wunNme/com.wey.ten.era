package com.wey.ten.era.rbac.system.controller;


import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.service.RegionService;

@Api(value="导入用户数据MD5加密",tags={"导入用户数据MD5加密"})
@RestController
@RequestMapping("/user/encode")
public class ImportDataEncodeController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/encode")
    public ResultObject encode(){
        ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
        regionService.encode();
        return resultObject;
    }
}

