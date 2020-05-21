package com.wey.ten.era.application.models.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.application.models.entity.IndustryModel;
//extends BaseMapper<IndustryModel>
@Mapper
public interface IndustryModelDao extends BaseMapper<IndustryModel>{

	/**
	 * 添加引用模型
	 * @param industryModel
	 * @return
	 */
	 void saveIndustryModel(IndustryModel industryModel);
	
	/**
	 * 修改引用模型
	 * @param industryModel
	 * @return
	 */
	 void updateIndustryModel(IndustryModel industryModel);
	
	
	/**
	 * 删除引用模型
	 * @param industryModel
	 * @return
	 */
	 void delIndustryModel(Integer id);
	
	/**
	 * 分页查询引用模型
	 * @param industryModel
	 * @return
	 */
	 List<IndustryModel> queryIndustryModel(Page<IndustryModel> page,@Param("e") Map<String,Object> param);
	
	/**
	 * 详情引用模型
	 * @param industryModel
	 * @return
	 */
	 IndustryModel detailIndustryModel(Integer id);
	 
	 void addPageviews(Map<String, Integer> paramsMap);
	 
	 void updateUseStatus(Map<String, Integer> paramsMap);
	 
	 Integer getCount(IndustryModel industryModel);
}
