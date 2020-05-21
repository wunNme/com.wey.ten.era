package com.wey.ten.era.rbac.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wey.ten.era.rbac.system.entity.Region;

@Mapper
public interface RegionDao {
        
	List<Region> getRegion(Integer pid);
}
