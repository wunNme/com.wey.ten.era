package com.wey.ten.era.application.models.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.application.models.entity.OnlineTest;
import com.wey.ten.era.application.models.entity.vo.OnlineTestVo;
import com.wey.ten.era.application.models.service.OnlineTestService;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;

/**
 * 模型控制层
 * @author lenovo
 *
 */
@Api(value = "OnlineTestController", description = "在线测试接口")
@RestController
@RequestMapping(value = "/models/onlineTest")
public class OnlineTestController {
	
	@Autowired
	private OnlineTestService onlineTestService ;
	
	@ApiOperation(value="在线测试详情", notes="在线测试详情")
	@GetMapping(value = "detail/{id}")
	public ResultObject detail(@PathVariable(name="id") String id){
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(id)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = onlineTestService.detailOnlineTest(Integer.valueOf(id));
		}
		return resultObject;
	}
	
	@ApiOperation(value="新增在线测试", notes="新增在线测试")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "industryModelId", value = "所属模型ID", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "onlineModelName", value = "在线模型名称", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "interfaceName", value = "接口名称", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "interfaceExplanaion", value = "接口说明", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "accessAdress", value = "接口访问地址", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "requestType", value = "请求方式 格式：get/post", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "protocal", value = "协议", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "encodingFormat", value = "编码格式", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "resultFormat", value = "返回格式", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "requestParams", value = "请求参数 格式：[{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'},{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'}]",  required = false ,dataType = "integer" ),
//			@ApiImplicitParam(name = "resultDataStructure", value = "返回数据结构 格式：[{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'},{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'}]", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "resultErrorStructure", value = "返回码 格式:[{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'},{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'}]", required = false ,dataType = "integer")
//	})
	@PostMapping(value = "save")
	public ResultObject saveOnlineTest(@RequestBody OnlineTest onlineTest){
		ResultObject resultObject = null;
		try {
			resultObject = onlineTestService.saveOnlineTest(onlineTest);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}
	
	@ApiOperation(value="修改在线测试", notes="修改在线测试")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "id", value = "id", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "industryModelId", value = "所属模型ID", required = true ,dataType = "integer"),
//			@ApiImplicitParam(name = "onlineModelName", value = "在线模型名称", required = false ,dataType = "String"),
//			@ApiImplicitParam(name = "interfaceName", value = "接口名称", required = true ,dataType = "String"),
//			@ApiImplicitParam(name = "interfaceExplanaion", value = "接口说明", required = false ,dataType = "String"),
//			@ApiImplicitParam(name = "accessAdress", value = "接口访问地址", required = true ,dataType = "String"),
//			@ApiImplicitParam(name = "requestType", value = "请求方式 格式：get/post", required = true ,dataType = "String"),
//			@ApiImplicitParam(name = "protocal", value = "协议", required = false ,dataType = "String"),
//			@ApiImplicitParam(name = "encodingFormat", value = "编码格式", required = false ,dataType = "String"),
//			@ApiImplicitParam(name = "resultFormat", value = "返回格式", required = false ,dataType = "String"),
//			@ApiImplicitParam(name = "requestParams", value = "请求参数 格式：[{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'},{'requestAttributesName':'接口参数key','requestAttributesValue':'默认值/value','required':'是否必填','explanation':'说明'}]",  required = false ,dataType = "String" ),
//			@ApiImplicitParam(name = "resultDataStructure", value = "返回数据结构 格式：[{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'},{'requestAttributesName':'返回字段','type':'类型','explanation':'说明'}]", required = false ,dataType = "integer"),
//			@ApiImplicitParam(name = "resultErrorStructure", value = "返回码 格式:[{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'},{'errorCode':'返回码','errorType':'错误类型','errorMsg':'错误描述','solutions':'解决方法'}]", required = false ,dataType = "String")
//	})
	@PostMapping(value = "update")
	public ResultObject updateOnlineTest(@RequestBody OnlineTest onlineTest){
		ResultObject resultObject = null;
		try {
			resultObject = onlineTestService.updateOnlineTest(onlineTest);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}
	
	@ApiOperation(value="在线测试删除", notes="在线测试删除")
	@GetMapping(value = "del/{id}")
	public ResultObject delOnlineTest(@PathVariable(name="id") String id){
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(id)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = onlineTestService.delOnlineTest(Integer.valueOf(id));
		}
		return resultObject;
	}
	
//	@ApiOperation(value="查询在线测试", notes="查询在线测试")
//	@PostMapping(value = "queryOnlineTest")
//	public ResultObject queryOnlineTest(@RequestBody OnlineTest onlineTest){
//		ResultObject resultObject = null;
//		onlineTest.setStartRow((onlineTest.getCurrentPage()-1)*onlineTest.getPageSize());
//		onlineTest.setEndRow(onlineTest.getPageSize());
//		resultObject = onlineTestService.queryOnlineTest(onlineTest);
//		return resultObject;
//	}
	@ApiOperation(value="查询在线测试", notes="查询在线测试")
	@PostMapping(value = "queryOnlineTest")
	public ResultObject queryOnlineTest(@RequestBody OnlineTestVo onlineTest){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		onlineTest.setStartRow((onlineTest.getCurrentPage()-1)*onlineTest.getPageSize());
//		onlineTest.setEndRow(onlineTest.getPageSize());
//		resultObject = onlineTestService.queryOnlineTest(onlineTest);
		resultObject= onlineTestService.queryOnlineTest(onlineTest);
		return resultObject;
	}
	
	@ApiOperation(value="在线测试", notes="在线测试")
	@PostMapping(value = "onlineTest")
	public ResultObject onlineTest(@RequestBody Map<String, String> params){
		ResultObject resultObject = null;
		if (params==null) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = onlineTestService.onlineTest(params);
		}
		return resultObject;
	}
}
