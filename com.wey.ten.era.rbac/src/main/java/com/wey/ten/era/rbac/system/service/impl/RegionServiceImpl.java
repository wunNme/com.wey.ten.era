package com.wey.ten.era.rbac.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wey.ten.era.common.utils.Md5Util;
import com.wey.ten.era.rbac.system.dao.RegionDao;
import com.wey.ten.era.rbac.system.dao.UserInfoDao;
import com.wey.ten.era.rbac.system.entity.Region;
import com.wey.ten.era.rbac.system.entity.UserInfo;
import com.wey.ten.era.rbac.system.service.RegionService;

@Service
@Transactional(readOnly = false)
public class RegionServiceImpl implements RegionService {

	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
    private UserInfoDao userInfoDao;
	@Override
	public List<Region> getRegion(Integer pid) {
		return regionDao.getRegion(pid);
	}
	@Override
	public void encode() {
		List<UserInfo> list=userInfoDao.getAll();
		for (int i = 0; i < list.size(); i++) {
			UserInfo user=list.get(i);
			user.setPassword(Md5Util.string2MD5(user.getPassword()));
			userInfoDao.updatePassword(user);
		}
		
	}
	

}
