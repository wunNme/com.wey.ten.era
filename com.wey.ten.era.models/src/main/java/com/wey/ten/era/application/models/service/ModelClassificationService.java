package com.wey.ten.era.application.models.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.application.models.entity.ModelClassification;

public interface ModelClassificationService extends IService<ModelClassification>{//extends IService<ModelClassification>
	void add(ModelClassification modelClassification);
	
	void update(ModelClassification modelClassification);
	
	List<ModelClassification> getAll();
}
