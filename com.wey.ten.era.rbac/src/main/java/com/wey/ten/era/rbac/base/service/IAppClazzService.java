package com.wey.ten.era.rbac.base.service;

import java.util.List;

import com.wey.ten.era.rbac.base.entity.AppClazz;
import com.wey.ten.era.rbac.utils.web.TreeSelect;

public interface IAppClazzService {

	public List<AppClazz> selectAppClazzList(AppClazz appClazz);

	public List<AppClazz> buildAppClazzTree(List<AppClazz> appClazzs);

	public List<TreeSelect> buildAppClazzTreeSelect(List<AppClazz> appClazzs);

	public AppClazz selectAppClazzById(Long appClazzId);

	public int selectNormalChildrenAppClazzById(Long appClazzId);

	public boolean hasChildByAppClazzId(Long appClazzId);

	public boolean checkAppClazzExistUser(Long appClazzId);

	public String checkAppClazzNameUnique(AppClazz appClazz);

	public int insertAppClazz(AppClazz appClazz);

	public int updateAppClazz(AppClazz appClazz);

	public int deleteAppClazzById(Long appClazzId);
}
