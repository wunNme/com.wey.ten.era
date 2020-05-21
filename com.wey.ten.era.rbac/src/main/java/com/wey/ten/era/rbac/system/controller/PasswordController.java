package com.wey.ten.era.rbac.system.controller;


import com.wey.ten.era.common.utils.*;
import com.wey.ten.era.rbac.system.service.RegionService;
import com.wey.ten.era.rbac.system.service.UserInfoService;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PasswordController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 验证码校验提取
     */
    public ResultObject verificationCodeCheckExtract(Map<String, String> params) {
        if (!(org.apache.commons.lang.StringUtils.isEmpty(params.get("account"))) && !(org.apache.commons.lang.StringUtils.isEmpty(params.get("type"))) && !(StringUtils.isEmpty(params.get("captcha")))) {
            String redisPhoneNumberCheckCode = null;
            if ("1".equals(params.get("type"))) {
                if (!VerificationUtil.isMobileNO(params.get("account"))) {
                    return new ResultObject(ErrorCode.PHONE_NUMBER_FORMAT_INCORRECT);
                }
                redisPhoneNumberCheckCode = stringRedisTemplate.opsForValue().get("phoneCode" + params.get("account"));
            } else {
                if (!VerificationUtil.checkEmail(params.get("account"))) {
                    return new ResultObject(ErrorCode.EMAIL_FORMAT_INCORRECT);
                }
                redisPhoneNumberCheckCode = stringRedisTemplate.opsForValue().get("emailCode" + params.get("account"));
            }
//                if (!(params.get("captcha").equalsIgnoreCase(redisPhoneNumberCheckCode))) {
//                    return new ResultObject(ErrorCode.PHONE_VERIFICATION_FAIL);
//                }
            if ((params.get("captcha").equals(null))) {
                return new ResultObject(ErrorCode.PHONE_VERIFICATION_FAIL);
            }
            } else{
                return new ResultObject(ErrorCode.REQUEST_PARAMS_ERROR);
            }
        return  new ResultObject(ErrorCode.SUCCESS);
    }

    /**
     * 修改密码提取
     */
   public ResultObject updateExtract(Map<String, String> params){
       if (!StringUtils.isEmpty(params.get("account")) && !StringUtils.isEmpty(params.get("type")) && !StringUtils.isEmpty(params.get("password"))&& !StringUtils.isEmpty(params.get("newPassword"))) {
           if(!params.get("password").equalsIgnoreCase(params.get("newPassword"))){
               return new ResultObject(ErrorCode.PASSWORD_INCINSISTENT_ERROR);
           }
           String password = Base64Util.decode(params.get("password"));
           params.put("password", Md5Util.string2MD5(password));
           params.remove("newPassword");
           userInfoService.modifyPassword(params);
       }else{
           return new ResultObject(ErrorCode.REQUEST_PARAMS_ERROR);
       }
       return  new ResultObject(ErrorCode.SUCCESS);
   }
}

