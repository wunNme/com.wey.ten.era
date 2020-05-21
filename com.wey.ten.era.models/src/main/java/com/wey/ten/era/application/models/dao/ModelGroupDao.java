package com.wey.ten.era.application.models.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wey.ten.era.application.models.entity.ModelGroup;
//extends BaseMapper<ModelGroup>
@Mapper
public interface ModelGroupDao extends BaseMapper<ModelGroup>{
	
	void add(ModelGroup modelGroup);
	
	void update(ModelGroup modelGroup);
	
	List<ModelGroup> getAll(ModelGroup modelGroup);
}
