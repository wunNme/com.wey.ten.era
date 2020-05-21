package com.wey.ten.era.rbac.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.base.entity.AppClazz;
import com.wey.ten.era.rbac.base.service.IAppClazzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 应用信息Controller
 */
@Api(value = "应用controller", tags = { "应用接口" })
@RestController
@RequestMapping("/base/clazz")
public class AppClazzController {

	@Autowired
	private IAppClazzService appClazzService;

	@GetMapping("/treeselect")
	@ApiOperation(value = "获取菜单接口")
	public ResultObject list(AppClazz appClazz) {
		List<AppClazz> appClazzs = appClazzService.selectAppClazzList(appClazz);
		return ResultObject.success(appClazzService.buildAppClazzTreeSelect(appClazzs));
	}
}
