package com.wey.ten.era.rbac.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.dao.EnterpriseInfoDao;
import com.wey.ten.era.rbac.system.dao.IndustryInfoDao;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.entity.vo.IndustryInfoVo;
import com.wey.ten.era.rbac.system.service.EnterpriseInfoService;

/**
 * 企业信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-10
 */
@Service
@Transactional(readOnly = false)
public class EnterpriseInfoServiceImpl extends ServiceImpl<EnterpriseInfoDao, EnterpriseInfo> implements EnterpriseInfoService {

	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;
	
	@Autowired
	 private IndustryInfoDao industryInfoDao;

	@Override
	public ResultObject detailEnterpriseInfo(Integer enterpriseId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		EnterpriseInfo enterpriseInfo=enterpriseInfoDao.detailEnterpriseInfo(enterpriseId);
		if(enterpriseInfo.getEnterprisIndustry()!=null){
			IndustryInfoVo industryInfoVo=industryInfoDao.getDetail(enterpriseInfo.getEnterprisIndustry());
			enterpriseInfo.setIndustryName(industryInfoVo.getIndustryType());
		}
		resultObject.setData(enterpriseInfo);
		return resultObject;
	}
	
	@Override
	public ResultObject detailEnterpriseInfoByUserId(Integer userId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		EnterpriseInfo enterpriseInfo=enterpriseInfoDao.detailEnterpriseInfoByUserId(userId);
		if(enterpriseInfo.getEnterprisIndustry()!=null){
			IndustryInfoVo industryInfoVo=industryInfoDao.getDetail(enterpriseInfo.getEnterprisIndustry());
			enterpriseInfo.setIndustryName(industryInfoVo.getIndustryType());
		}
		resultObject.setData(enterpriseInfo);
		return resultObject;
	}

	@Override
	public ResultObject saveEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		enterpriseInfoDao.saveEnterpriseInfo(enterpriseInfo);
		return resultObject;
	}

	@Override
	public void updateAuditStatus(Map<String, String> mapParams) {
		enterpriseInfoDao.updateAuditStatus(mapParams);
	}


	@Override
	public ResultObject removeEnterpriseInfo(Integer enterpriseId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		enterpriseInfoDao.removeEnterpriseInfo(enterpriseId);
		return resultObject;
	}

	@Override
	public ResultObject updateEnterpriseInfo(Map<String, String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		enterpriseInfoDao.updateEnterpriseInfo(mapParams);
		return resultObject;
	}

	@Override
	public List<EnterpriseInfo> findList(Map<String, Object> param) {
		PageResult<EnterpriseInfo> pageResult = findPage(param);
		if (pageResult != null) {
			return pageResult.getData();
		}
		return null;
	}

	@Override
	public PageResult<EnterpriseInfo> findPage(Map<String, Object> param) {
		long total = 0;
		// 分页对象
		Page<EnterpriseInfo> pager = null;
		List<EnterpriseInfo> enterpriseInfos = null;
		// page:当前页   limit:每页显示的数据
		Integer page = MapUtils.getInteger(param, "page");
		Integer limit = MapUtils.getInteger(param, "limit");
		if (page != null && limit != null) {
			pager = new Page<EnterpriseInfo>(page, limit);
			enterpriseInfos = enterpriseInfoDao.findList(pager, param);
			// 总条数
			total = pager.getTotal();
		}else {
			enterpriseInfos = enterpriseInfoDao.findList(pager, param);
		}
		if(enterpriseInfos!=null && enterpriseInfos.size()>0){
			for (int i = 0; i < enterpriseInfos.size(); i++) {
				EnterpriseInfo enterpriseInfo=enterpriseInfos.get(i);
				if(enterpriseInfo.getEnterprisIndustry()!=null){
					IndustryInfoVo industryInfoVo=industryInfoDao.getDetail(enterpriseInfo.getEnterprisIndustry());
					enterpriseInfo.setIndustryName(industryInfoVo.getIndustryType());
				}
			}
		}
		// 构建一个对象
		return PageResult.<EnterpriseInfo>builder().data(enterpriseInfos).code(0).count(total).build();
	}
}
