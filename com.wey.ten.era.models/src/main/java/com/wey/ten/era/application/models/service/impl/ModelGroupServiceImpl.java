package com.wey.ten.era.application.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.application.models.dao.ModelGroupDao;
import com.wey.ten.era.application.models.entity.ModelGroup;
import com.wey.ten.era.application.models.service.ModelGroupService;

@Service
@Transactional(readOnly = false)
// extends ServiceImpl<ModelGroupDao,ModelGroup>
public class ModelGroupServiceImpl extends ServiceImpl<ModelGroupDao,ModelGroup> implements ModelGroupService {
	
	@Autowired
	private ModelGroupDao modelGroupDao;

	@Override
	public void add(ModelGroup modelGroup) {
		modelGroupDao.add(modelGroup);
	}

	@Override
	public void update(ModelGroup modelGroup) {
		modelGroupDao.update(modelGroup);
	}

	@Override
	public List<ModelGroup> getAll(ModelGroup modelGroup) {
		return modelGroupDao.getAll(modelGroup);
	}

}
