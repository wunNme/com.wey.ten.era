package com.wey.ten.era.rbac.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EnterpriseInfoDao extends BaseMapper<EnterpriseInfo> {
	
    EnterpriseInfo detailEnterpriseInfo(Integer enterpriseId);
    
    EnterpriseInfo detailEnterpriseInfoByUserId(Integer userId);
    
    Integer saveEnterpriseInfo(EnterpriseInfo enterpriseInfo);

    Integer updateAuditStatus(Map<String, String> mapParams);
    
    /**
     * 多表查询，分页
     * @param page 分页对象 可以为空
     * @param param 参数 可以为空
     * @return
     */
    List<EnterpriseInfo> findList(Page<EnterpriseInfo> page,@Param("e") Map<String,Object> param);


    void removeEnterpriseInfo(Integer enterpriseId);

    void updateEnterpriseInfo(Map<String, String> mapParams);

    /*EnterpriseInfo detailUserInfo(Integer enterpriseId);*/
}
