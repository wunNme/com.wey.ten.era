package com.wey.ten.era.rbac.system.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.service.EnterpriseInfoService;

/**
 * 企业信息Controller
 *
 * @author ruoyi
 * @date 2020-05-10
 */
@Api(value = "企业认证controller", tags = { "企业认证操作接口" })
@RestController
@RequestMapping("/user/enterpriseIdInfo")
public class EnterpriseInfoController {

	@Autowired
	private EnterpriseInfoService enterpriseInfoService;

	/**
	 * 查询企业信息详情
	 */
	@ApiOperation(value = "查询企业信息详情", notes = "查询企业信息详情")
	@GetMapping(value = "detail/{enterpriseId}")
	public ResultObject detailEnterpriseInfo(@PathVariable(name="enterpriseId") Integer enterpriseId) {
		ResultObject resultObject = null;
		if (enterpriseId == null) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = enterpriseInfoService.detailEnterpriseInfo(enterpriseId);
		}
		return resultObject;
	}

	/**
	 * 条件查询企业信息
	 */
	@ApiOperation(value = "条件查询企业信息", notes = "条件查询企业信息")
	@PostMapping(value = "queryEnterpriseInfo")
	public ResultObject queryEnterpriseInfo(@RequestBody Map<String, Object> param) {
		PageResult<EnterpriseInfo> pageResult = enterpriseInfoService.findPage(param);
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(pageResult);
		return resultObject;
	}

	/**
	 * 企业认证
	 */
	@ApiOperation(value = "企业认证", notes = "企业认证")
	@PostMapping(value = "saveEnterpriseInfo")
	public ResultObject saveEnterpriseInfo(@RequestBody EnterpriseInfo enterpriseInfo) {
		ResultObject resultObject = null;
		try {
			resultObject = enterpriseInfoService.saveEnterpriseInfo(enterpriseInfo);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}

	/**
	 * 修改企业信息
	 */
	@ApiOperation(value = "修改企业信息", notes = "修改企业信息")
	@PostMapping("/updateEnterpriseInfo")
	public ResultObject updateEnterpriseInfo(@RequestBody Map<String,String> mapParams) {
		ResultObject resultObject = null;
		try {
			resultObject = enterpriseInfoService.updateEnterpriseInfo(mapParams);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
}

	/**
	 * 企业审核
	 */
	@ApiOperation(value = "企业审核", notes = "企业审核")
	@PostMapping("/updateAuditStatus")
	public ResultObject updateAuditStatus(@RequestBody Map<String, String> mapParams) {
		ResultObject resultObject = new ResultObject();
		enterpriseInfoService.updateAuditStatus(mapParams);
		return resultObject;
	}

	/**
	 * 删除企业信息
	 */
	@ApiOperation(value = "删除企业信息", notes = "删除企业信息")
	@PostMapping(value = "del/{enterpriseId}")
	@ResponseBody
	public ResultObject removeEnterpriseInfo(@PathVariable(name = "enterpriseId") Integer enterpriseId) {
		ResultObject resultObject = null;
		if (enterpriseId == null) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = enterpriseInfoService.removeEnterpriseInfo(enterpriseId);
		}
		return resultObject;
	}
}
