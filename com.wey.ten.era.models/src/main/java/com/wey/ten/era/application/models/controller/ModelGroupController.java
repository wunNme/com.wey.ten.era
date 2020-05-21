package com.wey.ten.era.application.models.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.application.models.entity.ModelGroup;
import com.wey.ten.era.application.models.service.ModelGroupService;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;

/**
 * 模型分類控制层
 * @author lenovo
 *
 */
@Api(value = "ModelGroupController", description = "分组接口")
@RestController
@RequestMapping(value = "/models/modelgroup")
public class ModelGroupController {

	@Autowired
	private ModelGroupService modelGroupService ;
	
	@ApiOperation(value="新增分组", notes="新增分组")
	@PostMapping(value = "save")
	public ResultObject saveIndustryModel(@RequestBody ModelGroup modelGroup){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		modelGroupService.add(modelGroup);
		return resultObject;
	}
	
	@ApiOperation(value="修改分组", notes="修改分组")
	@PostMapping(value = "update")
	public ResultObject updateIndustryModel(@RequestBody ModelGroup modelGroup){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		modelGroupService.update(modelGroup);
		return resultObject;
	}
	
	@ApiOperation(value="查询分组", notes="查询分组")
	@PostMapping(value = "getAll")
	public ResultObject getAll(@RequestBody ModelGroup modelGroup){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(modelGroupService.getAll(modelGroup));
		return resultObject;
	}
	
}
