package com.wey.ten.era.rbac.system.controller;

import io.swagger.annotations.ApiOperation;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wey.ten.era.common.ResponseResult;
import com.wey.ten.era.common.aspectj.annotation.Log;
import com.wey.ten.era.common.aspectj.enums.BusinessType;
import com.wey.ten.era.common.enums.ResponseCodeEnum;
import com.wey.ten.era.common.utils.Base64Util;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.FileUtils;
import com.wey.ten.era.common.utils.JWTUtil;
import com.wey.ten.era.common.utils.Md5Util;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.domain.LoginRequest;
import com.wey.ten.era.rbac.system.domain.LoginResponse;
import com.wey.ten.era.rbac.system.domain.RefreshRequest;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.entity.UserInfo;
import com.wey.ten.era.rbac.system.service.EnterpriseInfoService;
import com.wey.ten.era.rbac.system.service.PictureService;
import com.wey.ten.era.rbac.system.service.UserInfoService;

@RestController
@RequestMapping(value = "/user/oauth")
@ApiOperation(value = "授权中心")
public class LoginController extends PasswordController {

	/**
	 * Apollo 或 Nacos
	 */
	@Value("${secretKey:123456}")
	private String secretKey;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private EnterpriseInfoService enterpriseInfoService;

	@Autowired
	private PictureService pictureService;

	/**
	 * 登录
	 */
	@PostMapping("/login")
	@ApiOperation(value = "用户登录")
	@Log(title = "用户登陆", businessType = BusinessType.GRANT)
	public ResultObject login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultObject.fail("参数错误");
		}

		String code = stringRedisTemplate.opsForValue().get("ImgVerificationCodeMark");
//		if (code == null || request.getCode() == null || !StringUtils.equalsIgnoreCase(code, request.getCode())) {
//			return ResultObject.fail("图片验证码错误");
//		}
		String username = request.getUsername();
		String password = request.getPassword();
		password = Base64Util.decode(password);
		// 假设查询到用户ID是1001
		UserInfo loginUser = userInfoService.checkLoginAccount(1, username);
		if (loginUser == null) {
			loginUser = userInfoService.checkLoginAccount(2, username);
		}
		if (loginUser == null) {
			loginUser = userInfoService.checkLoginAccount(3, username);
		}
		if (loginUser != null) {

			// md5加密
			password = Md5Util.string2MD5(password);
			if (!loginUser.getPassword().equals(password)) {
				return ResultObject.fail("密码错误");
			}
			String userId = loginUser.getUserId().toString();
			// 生成Token
			String token = JWTUtil.generateToken(userId, secretKey);

			// 生成刷新Token
			String refreshToken = UUID.randomUUID().toString().replace("-", "");

			// 放入缓存
			HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
//			            hashOperations.put(refreshToken, "token", token);                                  
//			            hashOperations.put(refreshToken, "user", username);                                
//			            stringRedisTemplate.expire(refreshToken, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLIS

			/**
			 * 如果可以允许用户退出后token如果在有效期内仍然可以使用的话，那么就不需要存Redis
			 * 因为，token要跟用户做关联的话，就必须得每次都带一个用户标识，
			 * 那么校验token实际上就变成了校验token和用户标识的关联关系是否正确，且token是否有效
			 */

//			            String key = MD5Encoder.encode(userId.getBytes());                                 

			String key = userId;
			hashOperations.put(key, "token", token);
			hashOperations.put(key, "refreshToken", refreshToken);
			stringRedisTemplate.expire(key, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(token);
			loginResponse.setRefreshToken(refreshToken);
			loginResponse.setUsername(userId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			List<EnterpriseInfo> enterpriseInfos = enterpriseInfoService.findList(param);
			if (enterpriseInfos != null && enterpriseInfos.size() > 0) {
				EnterpriseInfo enterpriseInfo = enterpriseInfos.get(0);
				loginUser.setAffiliatedCompany(enterpriseInfo.getEnterpriseId());
				loginUser.setEnterpriseInfo(enterpriseInfo);
			}
			loginResponse.setUserInfo(loginUser);
			return ResultObject.success(loginResponse);
		}
		return ResultObject.fail("账号不存在");
	}

	/**
	 * 退出
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/logout")
	public ResponseResult logout(@RequestParam("userId") String userId) {
		HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
		String key = userId;
		hashOperations.delete(key);
		return ResponseResult.success();
	}

	/**
	 * 刷新Token
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/refreshToken")
	public ResponseResult refreshToken(@RequestBody @Validated RefreshRequest request, BindingResult bindingResult) {
		String userId = request.getUserId();
		String refreshToken = request.getRefreshToken();
		HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
		String key = userId;
		String originalRefreshToken = hashOperations.get(key, "refreshToken");
		if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
			return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getCode(),
					ResponseCodeEnum.LOGIN_ERROR.getMessage());
		}

		// 生成新token
		String newToken = JWTUtil.generateToken(userId, secretKey);
		hashOperations.put(key, "token", newToken);
		stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

		return ResponseResult.success(newToken);
	}

	@ApiOperation(value = "上传文件", notes = "上传文件")
	@PostMapping(value = "file_upload")
	public ResultObject fileUpload(@RequestParam("file") MultipartFile file) {
		ResultObject resultObject;
		if (file != null) {
			try {
				resultObject = FileUtils.uploadFile(file.getBytes(), file.getOriginalFilename());
			} catch (Exception e) {
				resultObject = new ResultObject(ErrorCode.FILE_UPLOAD_FAIL);
			}
		} else {
			resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}

	/**
	 * 手机号码验证
	 */
	@ApiOperation(value = "手机号码验证", notes = "手机号码验证")
	@PostMapping(value = "verificationPhone")
	public ResultObject verificationPhone(@RequestBody Map<String, String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		if (StringUtils.isEmpty(mapParams.get("captcha")) || StringUtils.isEmpty(mapParams.get("userName"))
				|| StringUtils.isEmpty(mapParams.get("phoneNumber"))) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		// 手机验证码是否一致 预留

		boolean result = userInfoService.verificationPhoneOnly(mapParams.get("phoneNumber"));
		resultObject.setData(result);
		if (result) {
			resultObject = new ResultObject(ErrorCode.PHONE_EXISTED_ERROR);
		}
		return resultObject;
	}

	/**
	 * 邮箱验证
	 */
	@ApiOperation(value = "邮箱验证", notes = "邮箱验证")
	@PostMapping(value = "verificationEmail")
	public ResultObject verificationEmail(@RequestBody Map<String, String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		if (StringUtils.isEmpty(mapParams.get("captcha")) || StringUtils.isEmpty(mapParams.get("userName"))
				|| StringUtils.isEmpty(mapParams.get("email"))) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		// 邮箱验证码是否一致 预留
//		hashOperations.get(mapParams.get("email"), "emailCode");
		String emailCode = stringRedisTemplate.opsForValue().get("emailCode" + mapParams.get("email"));
		if (StringUtils.isEmpty(emailCode)) {
			return new ResultObject(ErrorCode.EMAIL_SEND_FAILURE);
		} else if (!mapParams.get("captcha").equals(emailCode)) {
			return new ResultObject(ErrorCode.PHONE_VERIFICATION_FAIL);
		} else {
			stringRedisTemplate.delete("emailCode" + mapParams.get("email"));
			boolean result = userInfoService.verificationEmailOnly(mapParams.get("email"));
			resultObject.setData(result);
			if (result) {
				resultObject = new ResultObject(ErrorCode.EMAIL_EXISTED_ERROR);
			}
		}
		return resultObject;
	}

	/**
	 * 新增用户(注册)
	 */

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@PostMapping(value = "saveUser")
	public ResultObject saveUserInfo(@RequestBody Map<String, String> mapParams) {
		ResultObject resultObject = null;
		// 任意一个参数为空，直接异常
		try {
			if (StringUtils.isEmpty(mapParams.get("email")) || StringUtils.isEmpty(mapParams.get("password"))
					|| StringUtils.isEmpty(mapParams.get("newPassword"))
					|| StringUtils.isEmpty(mapParams.get("userName"))
					|| StringUtils.isEmpty(mapParams.get("phoneNumber"))) {
				return new ResultObject(ErrorCode.PARAMS_ERROR);
			}
			boolean result = userInfoService.verificationPhoneOnly(mapParams.get("phoneNumber"));
			if (result) {
				return new ResultObject(ErrorCode.PHONE_EXISTED_ERROR);
			}
			boolean resultPhone = userInfoService.verificationEmailOnly(mapParams.get("email"));
			if (resultPhone) {
				return new ResultObject(ErrorCode.EMAIL_EXISTED_ERROR);
			}
			String password = mapParams.get("password");
			if (!password.equals(mapParams.get("newPassword"))) {
				resultObject = new ResultObject(ErrorCode.PASSWORD_INCINSISTENT_ERROR);
			} else {
				password = Base64Util.decode(password);
				mapParams.put("password", Md5Util.string2MD5(password));
				mapParams.remove("newPassword");
				// 添加用户
				resultObject = userInfoService.saveUserInfo(mapParams);
				// 注册成功跳转到主页面
			}
		} catch (Exception e) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
	}

	/**
	 * 发送邮件验证码
	 */
	@ApiOperation(value = "发送邮件验证码", notes = "发送邮件验证码")
	@PostMapping("sendSimpleMail")
	public ResultObject sendSimpleMail(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		if (params == null) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		if (!params.containsKey("captcha") || !params.containsKey("email")) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		if (null == params.get("captcha") || "".equals(params.get("captcha"))) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		if (null == params.get("email") || "".equals(params.get("email"))) {
			return new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		String code = stringRedisTemplate.opsForValue().get("ImgVerificationCodeMark");
		if (StringUtils.isEmpty(code)) {
			return new ResultObject(ErrorCode.IMAGE_SEND_FAILURE);
		} else if (!params.get("captcha").equals(code)) {
			return new ResultObject(ErrorCode.PHONE_VERIFICATION_FAIL);
		}
		String checkCode = String.valueOf(new Random().nextInt(8999) + 10000);
		String message = "您的注册验证码为：" + checkCode;
		try {
			userInfoService.sendSimpleMail(params.get("email"), message);
			// 放入缓存
			stringRedisTemplate.opsForValue().set("emailCode" + params.get("email"), checkCode, 180, TimeUnit.SECONDS);
		} catch (Exception e) {
			return new ResultObject(ErrorCode.EMAIL_SEND_FAIL);
		}
		return resultObject;
	}

	@ApiOperation(value = "获取图片验证码", notes = "返回ResultDataDto", response = ResultObject.class)
	@PostMapping(path = "/getPictureCode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultObject getPictureCode(HttpServletRequest request) {
		ResultObject resultObject = new ResultObject();
		String param = "ImgVerificationCodeMark";
		String base64ImgStr = pictureService.createPicVerificationCode(param, request);
		if (StringUtils.isNotBlank(base64ImgStr)) {
			resultObject.setData(base64ImgStr);
			resultObject.setErrorCode(ErrorCode.SUCCESS.getValue());
			return resultObject;
		} else {
			resultObject.setErrorCode(ErrorCode.PARAMETER_ILLEGAL.getValue());
			return resultObject;
		}
	}

	/**
	 * 通过手机号码或者邮箱修改密码(验证码校验)
	 */
	@ApiOperation(value = "通过手机号码或者邮箱修改密码(验证码校验)", notes = "通过手机号码或者邮箱修改密码(验证码校验)")
	@PostMapping("/verificationCodeCheck")
	public ResultObject verificationCodeCheck(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject = this.verificationCodeCheckExtract(params);
		return resultObject;
	}

	/**
	 * 通过手机号码或者邮箱修改密码
	 */
	@ApiOperation(value = "通过手机号码或者邮箱修改密码", notes = "通过手机号码或者邮箱修改密码)")
	@PostMapping("/modifyPassword")
	public ResultObject modifyPassword(@RequestBody Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject = this.updateExtract(params);
		return resultObject;
	}
}