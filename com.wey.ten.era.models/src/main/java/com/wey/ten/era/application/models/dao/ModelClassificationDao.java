package com.wey.ten.era.application.models.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wey.ten.era.application.models.entity.ModelClassification;
//extends BaseMapper<ModelClassification>
@Mapper
public interface ModelClassificationDao extends BaseMapper<ModelClassification>{
	
	void add(String classificationName);
	
	void update(ModelClassification modelClassification);
	
	List<ModelClassification> getAll();
}
