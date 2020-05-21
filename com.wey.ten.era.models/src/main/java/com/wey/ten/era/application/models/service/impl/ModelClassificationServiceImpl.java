package com.wey.ten.era.application.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.application.models.dao.ModelClassificationDao;
import com.wey.ten.era.application.models.entity.ModelClassification;
import com.wey.ten.era.application.models.service.ModelClassificationService;

@Service
@Transactional(readOnly = false)
//extends ServiceImpl<ModelClassificationDao,ModelClassification> 
public class ModelClassificationServiceImpl extends ServiceImpl<ModelClassificationDao,ModelClassification> implements
		ModelClassificationService {
	
	@Autowired
	private ModelClassificationDao modelClassificationDao;

	@Override
	public void add(ModelClassification modelClassification) {
		modelClassificationDao.add(modelClassification.getClassificationName());
	}

	@Override
	public void update(ModelClassification modelClassification) {
		modelClassificationDao.update(modelClassification);
	}

	@Override
	public List<ModelClassification> getAll() {
		return modelClassificationDao.getAll();
	}

}
