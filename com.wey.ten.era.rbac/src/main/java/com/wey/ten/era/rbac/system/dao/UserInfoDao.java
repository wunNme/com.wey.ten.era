package com.wey.ten.era.rbac.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.entity.UserInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

    UserInfo detailUserInfo(Integer userId);

    void saveUserInfo(Map<String,String> mapParams);

    void delUserInfo(Integer userId);

    void updateUserInfo(Map<String,String> mapParams);

    int verificationPhoneOnly(String phoneNumber);
    
    int verificationEmailOnly(String email);
    
    void updateAffiliatedCompany(Map<String,String> mapParams);
    
    List<UserInfo> getAll();
    
    void updatePassword(UserInfo user);

    void modifyPassword(Map<String, String> params);

    List<UserInfo> findList(Page<UserInfo> pager,@Param("e")  Map<String, Object> param);

    Map<String,String> getUserByUserId(Integer userId);
    
    /**
     *通过邮箱查找唯一
     * @param email
     * @return
     */
    public UserInfo getByEmail(String email);
    
    /**
     * 通过手机号码查找唯一
     * @param mobile
     * @return
     */
    public UserInfo getByMobile(String mobile);
    
    /**
     * 通过账号查找唯一
     * @param account
     * @return
     */
    public UserInfo getByAccount(String account);

    void modifyBinding(Map<String, String> params);

    UserInfo getPasswordByAccount(String account, String type);
}
