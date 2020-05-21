package com.wey.ten.era.application.models.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.application.models.entity.ModelClassification;
import com.wey.ten.era.application.models.service.ModelClassificationService;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;

/**
 * 模型分類控制层
 * @author lenovo
 *
 */
@Api(value = "ModelClassificationController", description = "模型分类接口")
@RestController
@RequestMapping(value = "/models/modelc")
public class ModelClassificationController {

	@Autowired
	private ModelClassificationService modelClassificationService ;
	
	@ApiOperation(value="新增分类", notes="新增分类")
	@PostMapping(value = "save")
	public ResultObject saveIndustryModel(@RequestBody ModelClassification modelClassification){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		modelClassificationService.add(modelClassification);
		return resultObject;
	}
	@ApiOperation(value="修改分类", notes="修改分类")
	@PostMapping(value = "update")
	public ResultObject updateIndustryModel(@RequestBody ModelClassification modelClassification){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		modelClassificationService.update(modelClassification);
		return resultObject;
	}
	@ApiOperation(value="查询分类", notes="查询分类")
	@GetMapping(value = "getAll")
	public ResultObject getAll(){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(modelClassificationService.getAll());
		return resultObject;
	}
	
}
