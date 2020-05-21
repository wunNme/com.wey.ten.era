package com.wey.ten.era.application.models.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.application.models.dao.IndustryModelDao;
import com.wey.ten.era.application.models.entity.IndustryModel;
import com.wey.ten.era.application.models.entity.vo.IndustryModelVo;
import com.wey.ten.era.application.models.service.IndustryModelService;
import com.wey.ten.era.application.serviceapi.UserServiceApi;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.common.utils.StringUtils;
//extends ServiceImpl<IndustryModelDao, IndustryModel>
@Service
@Transactional(readOnly = false)
public class IndustryModelServiceImpl extends ServiceImpl<IndustryModelDao, IndustryModel> implements IndustryModelService{
	
	@Autowired
	private IndustryModelDao industryModelDao;
	
	@Autowired
	private UserServiceApi userServiceApi;
	/**
	 * 添加引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject saveIndustryModel(IndustryModel industryModel){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		industryModelDao.saveIndustryModel(industryModel);
		return resultObject;
	}
	
	/**
	 * 修改引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject updateIndustryModel(IndustryModel industryModel){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		industryModelDao.updateIndustryModel(industryModel);
		return resultObject;
	}
	
	
	/**
	 * 删除引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject delIndustryModel(Integer id){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		industryModelDao.delIndustryModel(id);
		return resultObject;
	}
	
	/**
	 * 分页查询引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject queryIndustryModel(IndustryModelVo industryModel){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		Page page=new Page();
//		page.setData(industryModelDao.queryIndustryModel(industryModel));
//		page.setTotalCount(industryModelDao.getCount(industryModel));
//		resultObject.setData(page);;
		Map<String,Object> keyValues=StringUtils.transBean2Map(industryModel);
		PageResult<IndustryModel> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
	}
	
	private PageResult<IndustryModel> findPage(Map<String, Object> params) {
		long total = 0;
		Page<IndustryModel> pager = null;
		List<IndustryModel> industryModelList = null;
		Integer page = MapUtils.getInteger(params, "page");
		Integer limit = MapUtils.getInteger(params, "limit");
		if (page != null && limit != null) {
			pager = new Page<IndustryModel>(page, limit);
			setOrderBy(params, pager);
			industryModelList = industryModelDao.queryIndustryModel(pager, params);
			total = pager.getTotal();
		}else {
			industryModelList = industryModelDao.queryIndustryModel(pager, params);
		}
		if(industryModelList!=null && industryModelList.size()>0){
			for (int i = 0; i < industryModelList.size(); i++) {
				IndustryModel industryModel=industryModelList.get(i);
				try {
					getUser(industryModel);
					industryModelList.set(i, industryModel);
				} catch (Exception e) {
				}
			}
		}
		return PageResult.<IndustryModel> builder().data(industryModelList).code(0).count(total).build();
	}
	
	private void setOrderBy(Map<String, Object> params, Page<IndustryModel> pager) {
		if(params.containsKey("asc")&&params.get("asc")!=null){
			if(params.get("asc").toString().split(",").length>0){
				pager.setAscs(Arrays.asList(params.get("asc").toString().split(",")));
			}else{
				pager.setAsc(params.get("asc").toString());
			}
		}
		if(params.containsKey("desc")&&params.get("desc")!=null){
			if(params.get("desc").toString().split(",").length>1){
				pager.setDescs(Arrays.asList(params.get("desc").toString().split(",")));
				
			}else{
				pager.setDesc(params.get("desc").toString());
			}
		}
		if(params.containsKey("desc")&&params.get("desc")!=null&&params.containsKey("asc")&&params.get("asc")!=null){
			pager.setDesc("createTime");
		}
	}
	/**
	 * 详情引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject detailIndustryModel(Integer id){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		IndustryModel industryModel=industryModelDao.detailIndustryModel(id);
		getUser(industryModel);
		resultObject.setData(industryModel);
		return resultObject;
	}

	private void getUser(IndustryModel industryModel) {
		ResultObject res=userServiceApi.getUserById(industryModel.getUserId().toString());
		if(ErrorCode.SUCCESS.getValue().equals(res.getErrorCode())){
			Map<String, String> params=(Map<String, String>)res.getData();
			if(params!=null){
				industryModel.setIndustryName(params.get("industryName")!=null ?params.get("industryName").toString():"");
				industryModel.setEnterprisName(params.get("enterprisName")!=null ?params.get("enterprisName").toString():"");
			}
		}
	}

	@Override
	public void addPageviews(String id) {
		Map<String, Integer> paramsMap = new HashMap<String, Integer>();
		paramsMap.put("id", Integer.valueOf(id));
		paramsMap.put("currentVersion", industryModelDao.detailIndustryModel(Integer.valueOf(id)).getCurrentVersion());
		industryModelDao.addPageviews(paramsMap);
	}

	@Override
	public void updateUseStatus(Map<String, Integer> paramsMap) {
		industryModelDao.updateUseStatus(paramsMap);
		
	}

	@Override
	public Integer getCount(IndustryModel industryModel) {
		return industryModelDao.getCount(industryModel);
	}

}
