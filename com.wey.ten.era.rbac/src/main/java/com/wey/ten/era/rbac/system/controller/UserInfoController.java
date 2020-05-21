package com.wey.ten.era.rbac.system.controller;

import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.*;
import com.wey.ten.era.rbac.system.entity.UserInfo;
import com.wey.ten.era.rbac.system.excel.UserExcel;
import com.wey.ten.era.rbac.system.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
@Api(value = "用户controller", tags = { "用户操作接口" })
@RestController
@RequestMapping("/user/userInfo")
public class UserInfoController extends PasswordController{

	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 查询用户列表
	 */
	@ApiOperation(value = "查询用户列表", notes = "查询用户列表")
	@GetMapping(value = "detail/{userId}")
	public ResultObject detail(@PathVariable(name = "userId") String userId) {
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(userId)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = userInfoService.detailUserInfo(Integer.valueOf(userId));
		}
		return resultObject;
	}

	/**
	 * 查询用户企业行业名字
	 */
	@ApiOperation(value = "查询用户企业行业名字", notes = "查询用户企业行业名字")
	@GetMapping(value = "/getUserByUserId/{userId}")
	public ResultObject getUserByUserId(@PathVariable(name = "userId") String userId) {
		ResultObject resultObject = null;
		if (StringUtils.isEmpty(userId)) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = userInfoService.getUserByUserId(Integer.valueOf(userId));
		}
		return resultObject;
	}

	/**
	 * 删除用户
	 */
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@GetMapping(value = "del/{userId}")
	public ResultObject delUserInfo(@PathVariable(name = "userId") Integer userId) {
		ResultObject resultObject = null;
		if (userId == null) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = userInfoService.delUserInfo(userId);
		}
		return resultObject;
	}

	/**
	 * 修改保存用户
	 */
	@ApiOperation(value = "修改保存用户", notes = "修改保存用户")
	@PostMapping(value = "update")
	public ResultObject updateUserInfo(@RequestBody Map<String, String> mapParams) {
		ResultObject resultObject = null;
		resultObject = userInfoService.updateUserInfo(mapParams);
		return resultObject;
	}

	/**
	 * 条件查询用户信息
	 */
	@ApiOperation(value = "条件查询用户信息", notes = "条件查询用户信息")
	@PostMapping(value = "queryUserInfo")
	public ResultObject queryUserInfo(@RequestBody Map<String, Object> param) {
		PageResult<UserInfo> pageResult = userInfoService.findPage(param);
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(pageResult);
		return resultObject;
	}

	/**
	 * 导出excel
	 *
	 * @return
	 */
	@PostMapping("/excel/template")
	@ApiOperation(value = "导出账号模板")
	public void exportUser(HttpServletResponse response) throws IOException {
		// 导出操作
		List<UserExcel> excels = new ArrayList<UserExcel>();
		ExcelUtil.exportExcel(excels, null, "子账号导入模板", UserExcel.class, "user", response);
	}

	@PostMapping(value = "/excel/import/{parentId}")
	@ApiOperation(value = "导入账号")
	public ResultObject importExcl(@PathVariable(value = "parentId") Integer parentId,
			@RequestParam("file") MultipartFile excl) throws Exception {
		int rowNum = 0;
		int result = 0;
		if (!excl.isEmpty()) {
			List<UserExcel> list = ExcelUtil.importExcel(excl, 0, 1, UserExcel.class);
			rowNum = list.size();
			if (rowNum > 0) {
				List<UserInfo> userInfos = new ArrayList<>(rowNum);
				list.forEach(u -> {
					UserInfo userInfo = new UserInfo();
					if (parentId == 0) { // 主账号
						userInfo.setIsParent(0);
					} else {
						userInfo.setAffiliatedCompany(parentId);
						userInfo.setIsParent(1);
					}
					userInfo.setAccountNumber(u.getAccount());
					userInfo.setEmail(u.getEmail());
					userInfo.setPassword(u.getPassword());
					userInfo.setPhoneNumber(u.getMobile());
					userInfo.setUserStatus(1);
					userInfo.setUserName(u.getName());
					userInfos.add(userInfo);
				});
				result = userInfoService.batchSaveInfo(userInfos, true);
			}
		}
		return result > 0 ? ResultObject.success("导入数据成功，一共【" + rowNum + "】行") : ResultObject.fail("导入失败");
	}

	/**
	 * 通过手机号码或者邮箱修改密码(验证码校验)
	 */
	@ApiOperation(value = "通过手机号码或者邮箱修改密码(验证码校验)", notes = "通过手机号码或者邮箱修改密码(验证码校验)")
	@PostMapping("/verificationCodeCheck")
	public ResultObject verificationCodeCheck(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject=this.verificationCodeCheckExtract(params);
		return resultObject;
	}

	/**
	 * 通过手机号码或者邮箱修改密码
	 */
	@ApiOperation(value = "通过手机号码或者邮箱修改密码", notes = "通过手机号码或者邮箱修改密码)")
	@PostMapping("/alterPassword")
	public ResultObject alterPassword(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		String account = params.get("account");
		String type = params.get("type");
		if (!StringUtils.isEmpty(params.get("userId")) && !StringUtils.isEmpty(account) && !StringUtils.isEmpty(type) && !StringUtils.isEmpty(params.get("newPassword")) && !StringUtils.isEmpty(params.get("password")) && !StringUtils.isEmpty(params.get("confirmPassword"))) {
//			resultObject = this.verificationCodeCheckExtract(params);
//			if (resultObject.getErrorCode().equals("200")) {
			// oldPassword 数据库中的MD5加密密码
			UserInfo userInfo = userInfoService.getPasswordByAccount(account, type);
			String oldPassword = userInfo.getPassword();
			// newPassword 当前密码 decryptPassword byte64位解密密码
			String decryptPassword = Base64Util.decode(params.get("newPassword"));
			params.put("newPassword", Md5Util.string2MD5(decryptPassword));
			if (!oldPassword.equalsIgnoreCase(params.get("newPassword"))) {
				return new ResultObject(ErrorCode.PASSWORD_CURRENT_ERROR);
			} else if (!params.get("password").equalsIgnoreCase(params.get("confirmPassword"))) {
				return new ResultObject(ErrorCode.PASSWORD_INCINSISTENT_ERROR);
			}
			String password = Base64Util.decode(params.get("password"));
			params.put("password", Md5Util.string2MD5(password));
			params.remove("confirmPassword");
			userInfoService.modifyPassword(params);
		} else {
			return new ResultObject(ErrorCode.REQUEST_PARAMS_ERROR);
		}
//	}
		return resultObject;
	}

	/**
	 * 通过手机号码或者邮箱修改绑定方式
	 */
	@ApiOperation(value = "通过手机号码或者邮箱修改绑定方式", notes = "通过手机号码或者邮箱修改绑定方式")
	@PostMapping("/modifyBinding")
	public ResultObject modifyBinding(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject = this.verificationCodeCheckExtract(params);
		if (resultObject.getErrorCode().equals("200")) {
			userInfoService.modifyBinding(params);
		}
		return resultObject;
	}



}
