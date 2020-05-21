package com.wey.ten.era.rbac.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;

import java.util.List;
import java.util.Map;

public interface EnterpriseInfoService extends IService<EnterpriseInfo> {

	List<EnterpriseInfo> findList(Map<String, Object> param);

	PageResult<EnterpriseInfo> findPage(Map<String, Object> param);

	ResultObject detailEnterpriseInfo(Integer enterpriseId);
	
	ResultObject detailEnterpriseInfoByUserId(Integer enterpriseId);

	ResultObject saveEnterpriseInfo(EnterpriseInfo enterpriseInfo);

	void updateAuditStatus(Map<String, String> mapParams);

	ResultObject removeEnterpriseInfo(Integer enterpriseId);

    ResultObject updateEnterpriseInfo(Map<String,String> mapParams);
}
