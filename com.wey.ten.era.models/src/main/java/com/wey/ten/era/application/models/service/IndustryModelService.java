package com.wey.ten.era.application.models.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.application.models.entity.IndustryModel;
import com.wey.ten.era.application.models.entity.vo.IndustryModelVo;
import com.wey.ten.era.common.utils.ResultObject;

public interface IndustryModelService extends IService<IndustryModel>{//extends IService<IndustryModel>

	/**
	 * 添加引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject saveIndustryModel(IndustryModel industryModel);
	
	/**
	 * 修改引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject updateIndustryModel(IndustryModel industryModel);
	
	
	/**
	 * 删除引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject delIndustryModel(Integer id);
	
	/**
	 * 分页查询引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject queryIndustryModel(IndustryModelVo industryModel);
	
	/**
	 * 详情引用模型
	 * @param industryModel
	 * @return
	 */
	public ResultObject detailIndustryModel(Integer id);
	
	public void addPageviews(String id);
	
	public void updateUseStatus(Map<String, Integer> paramsMap);
	
	Integer getCount(IndustryModel industryModel);
}
