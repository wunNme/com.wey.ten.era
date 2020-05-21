package com.wey.ten.era.rbac.system.service;

import java.util.List;

import com.wey.ten.era.rbac.system.entity.Region;

public interface RegionService {

	
	public List<Region> getRegion(Integer pid);
	
	void encode();
}
