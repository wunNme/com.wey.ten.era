package com.wey.ten.era.application.models.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.application.models.entity.ModelGroup;

public interface ModelGroupService extends IService<ModelGroup>{//extends IService<ModelGroup>
	void add(ModelGroup modelGroup);
	
	void update(ModelGroup modelGroup);
	
	List<ModelGroup> getAll(ModelGroup modelGroup);
}
