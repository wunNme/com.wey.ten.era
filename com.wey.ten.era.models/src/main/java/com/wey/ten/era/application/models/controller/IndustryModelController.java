package com.wey.ten.era.application.models.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.application.models.entity.IndustryModel;
import com.wey.ten.era.application.models.entity.vo.IndustryModelVo;
import com.wey.ten.era.application.models.service.IndustryModelService;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;

/**
 * 模型控制层
 * @author lenovo
 *
 */
@Api(value = "IndustryModelController", description = "模型接口")
@RestController
@RequestMapping(value = "/models/industrymodel")
public class IndustryModelController {
	
	@Autowired
	private IndustryModelService industryModelService ;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@ApiOperation(value="模型详情", notes="模型详情")
	@GetMapping(value = "detail/{id}")
	public ResultObject detail(@PathVariable(name="id") String id){
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(id)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = industryModelService.detailIndustryModel(Integer.valueOf(id));
		}
		return resultObject;
	}
	@ApiOperation(value="新建模型", notes="新建模型")
	@PostMapping(value = "save")
	public ResultObject saveIndustryModel(@RequestBody IndustryModel industryModel){
		ResultObject resultObject = null;
		try {
			resultObject = industryModelService.saveIndustryModel(industryModel);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}
	
	@ApiOperation(value="修改模型", notes="修改模型")
	@PostMapping(value = "update")
	public ResultObject updateIndustryModel(@RequestBody IndustryModel industryModel){
		ResultObject resultObject = null;
		try {
			resultObject = industryModelService.updateIndustryModel(industryModel);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}
	@ApiOperation(value="删除模型", notes="删除模型")
	@GetMapping(value = "del/{id}")
	public ResultObject delIndustryModel(@PathVariable(name="id") String id){
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(id)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = industryModelService.delIndustryModel(Integer.valueOf(id));
		}
		return resultObject;
	}
	@ApiOperation(value="条件查询模型", notes="条件查询模型支持分页，支持一下字段")
	@PostMapping(value = "queryIndustryModel")
	public ResultObject queryIndustryModel(@RequestBody IndustryModelVo industryModel){
		ResultObject resultObject = null;
//		industryModel.setStartRow((industryModel.getCurrentPage()-1)*industryModel.getPageSize());
//		industryModel.setEndRow(industryModel.getPageSize());
		resultObject = industryModelService.queryIndustryModel(industryModel);
		return resultObject;
	}
	
//	@ApiOperation(value="条件查询模型", notes="条件查询模型支持分页，支持一下字段")
//	@PostMapping(value = "queryIndustryModel")
//	public ResultObject queryIndustryModel(@RequestBody Page<IndustryModel>  page){
//		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
////		industryModel.setStartRow((industryModel.getCurrentPage()-1)*industryModel.getPageSize());
////		industryModel.setEndRow(industryModel.getPageSize());
//		resultObject.setData(industryModelService.page(page));
//		return resultObject;
//	}
	
	/**
	 * 浏览量
	 * @param id
	 * @return
	 */
	@ApiOperation(value="新增浏览量", notes="新增浏览量")
	@GetMapping(value = "addPageviews/{id}")
	public ResultObject addPageviews(@PathVariable(name="id") String id){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		if (StringUtils.isEmpty(id)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			industryModelService.addPageviews(id);
		}
		return resultObject;
	}
	@ApiOperation(value="模型上/下架", notes="模型上/下架")
	@PostMapping(value = "updateUseStatus")
	public ResultObject updateUseStatus(@RequestBody Map<String, Integer> paramsMap){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		industryModelService.updateUseStatus(paramsMap);
		return resultObject;
	}
}
