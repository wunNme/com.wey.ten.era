package com.wey.ten.era.rbac.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
public interface UserInfoService extends IService<UserInfo> {

    ResultObject detailUserInfo(Integer userId);

    ResultObject saveUserInfo(Map<String,String> mapParams);

    boolean verificationPhoneOnly(String phoneNumber);
    
    boolean verificationEmailOnly(String email);

    ResultObject delUserInfo(Integer userId);

    ResultObject updateUserInfo(Map<String,String> mapParams);
    
    UserInfo checkLoginAccount(Integer loginType, String userName);
    
    ResultObject updateAffiliatedCompany(Map<String,String> mapParams);

    /*ResultObject queryUserInfo(UserInfo userInfo);*/

    List<UserInfo> findList(Map<String, Object> param);

    PageResult<UserInfo> findPage(Map<String, Object> param);

    ResultObject getUserByUserId(Integer userId);

    ResultObject sendSimpleMail(String email,String message);

    ResultObject modifyPassword(Map<String, String> params);
    
    /**
     * 保存用户信息
     * @param userInfo 用户
     * @param saveFlag 用来判断是否校验用户信息
     * @return
     */
    public int saveInfo(UserInfo userInfo,boolean saveFlag);
    
    /**
     * 批次保存用户信息
     * @param userInfos 用户列表
     * @param saveFlag 用来判断是否校验用户信息
     * @return 
     */
    public int batchSaveInfo(List<UserInfo> userInfos,boolean saveFlag);

    ResultObject modifyBinding(Map<String, String> params);

    ResultObject alterPassword(Map<String, String> params);

    UserInfo getPasswordByAccount(String account, String type);
}
