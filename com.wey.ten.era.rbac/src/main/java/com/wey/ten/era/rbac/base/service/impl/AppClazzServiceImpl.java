package com.wey.ten.era.rbac.base.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wey.ten.era.common.constant.UserConstants;
import com.wey.ten.era.common.exception.CustomException;
import com.wey.ten.era.common.utils.StringUtils;
import com.wey.ten.era.rbac.base.entity.AppClazz;
import com.wey.ten.era.rbac.base.mapper.AppClazzMapper;
import com.wey.ten.era.rbac.base.service.IAppClazzService;
import com.wey.ten.era.rbac.utils.web.TreeSelect;

@Service
public class AppClazzServiceImpl implements IAppClazzService {
	@Autowired
	private AppClazzMapper appClazzMapper;

	@Override
	public List<AppClazz> selectAppClazzList(AppClazz appClazz) {
		return appClazzMapper.selectAppClazzList(appClazz);
	}

	@Override
	public List<AppClazz> buildAppClazzTree(List<AppClazz> appClazzs) {
		List<AppClazz> returnList = new ArrayList<AppClazz>();
		List<Long> tempList = new ArrayList<Long>();
		for (AppClazz appClazz : appClazzs) {
			tempList.add(appClazz.getAppClazzId());
		}
		for (Iterator<AppClazz> iterator = appClazzs.iterator(); iterator.hasNext();) {
			AppClazz appClazz = (AppClazz) iterator.next();
			// 如果是顶级节点, 遍历该父节点的所有子节点
			if (!tempList.contains(appClazz.getParentId())) {
				recursionFn(appClazzs, appClazz);
				returnList.add(appClazz);
			}
		}
		if (returnList.isEmpty()) {
			returnList = appClazzs;
		}
		return returnList;
	}

	@Override
	public List<TreeSelect> buildAppClazzTreeSelect(List<AppClazz> appClazzs) {
		List<AppClazz> appClazzTrees = buildAppClazzTree(appClazzs);
		return appClazzTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
	}

	@Override
	public AppClazz selectAppClazzById(Long appClazzId) {
		return appClazzMapper.selectAppClazzById(appClazzId);
	}

	public int selectNormalChildrenDeptById(Long appClazzId) {
		return appClazzMapper.selectNormalChildrenAppClazzById(appClazzId);
	}

	@Override
	public boolean hasChildByAppClazzId(Long appClazzId) {
		int result = appClazzMapper.hasChildByAppClazzId(appClazzId);
		return result > 0 ? true : false;
	}

	@Override
	public boolean checkAppClazzExistUser(Long appClazzId) {
		int result = appClazzMapper.checkAppClazzExistUser(appClazzId);
		return result > 0 ? true : false;
	}

	@Override
	public String checkAppClazzNameUnique(AppClazz appClazz) {
		Long appClazzId = StringUtils.isNull(appClazz.getAppClazzId()) ? -1L : appClazz.getAppClazzId();
		AppClazz info = appClazzMapper.checkAppClazzNameUnique(appClazz.getClazzName(), appClazz.getParentId());
		if (StringUtils.isNotNull(info) && info.getAppClazzId().longValue() != appClazzId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	@Override
	public int insertAppClazz(AppClazz appClazz) {
		AppClazz info = appClazzMapper.selectAppClazzById(appClazz.getParentId());
		// 如果父节点不为正常状态,则不允许新增子节点
		if (!UserConstants.CLAZZ_NORMAL.equals(info.getStatus())) {
			throw new CustomException("分类停用，不允许新增");
		}
		appClazz.setAncestors(info.getAncestors() + "," + appClazz.getParentId());
		return appClazzMapper.insertAppClazz(appClazz);
	}

	@Override
	public int updateAppClazz(AppClazz appClazz) {
		AppClazz newParentDept = appClazzMapper.selectAppClazzById(appClazz.getParentId());
		AppClazz oldDept = appClazzMapper.selectAppClazzById(appClazz.getAppClazzId());
		if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
			String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getAppClazzId();
			String oldAncestors = oldDept.getAncestors();
			appClazz.setAncestors(newAncestors);
			updateDeptChildren(appClazz.getAppClazzId(), newAncestors, oldAncestors);
		}
		int result = appClazzMapper.updateAppClazz(appClazz);
		if (UserConstants.CLAZZ_NORMAL.equals(appClazz.getStatus())) {
			updateParentAppClazzStatus(appClazz);
		}
		return result;
	}

	/**
	 * 修改该部门的父级部门状态
	 * 
	 * @param dept 当前部门
	 */
	private void updateParentAppClazzStatus(AppClazz appClazz) {
		String updateBy = appClazz.getUpdateBy();
		appClazz = appClazzMapper.selectAppClazzById(appClazz.getAppClazzId());
		appClazz.setUpdateBy(updateBy);
		appClazzMapper.updateAppClazzStatus(appClazz);
	}

	/**
	 * 修改子元素关系
	 * 
	 * @param deptId       被修改的部门ID
	 * @param newAncestors 新的父ID集合
	 * @param oldAncestors 旧的父ID集合
	 */
	public void updateDeptChildren(Long appClazzId, String newAncestors, String oldAncestors) {
		List<AppClazz> children = appClazzMapper.selectChildrenAppClazzById(appClazzId);
		for (AppClazz child : children) {
			child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
		}
		if (children.size() > 0) {
			appClazzMapper.updateAppClazzChildren(children);
		}
	}

	@Override
	public int deleteAppClazzById(Long appClazzId) {
		return appClazzMapper.deleteAppClazzById(appClazzId);
	}

	/**
	 * 递归列表
	 */
	private void recursionFn(List<AppClazz> list, AppClazz t) {
		// 得到子节点列表
		List<AppClazz> childList = getChildList(list, t);
		t.setChildren(childList);
		for (AppClazz tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<AppClazz> it = childList.iterator();
				while (it.hasNext()) {
					AppClazz n = (AppClazz) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<AppClazz> getChildList(List<AppClazz> list, AppClazz t) {
		List<AppClazz> tlist = new ArrayList<AppClazz>();
		Iterator<AppClazz> it = list.iterator();
		while (it.hasNext()) {
			AppClazz n = (AppClazz) it.next();
			if (StringUtils.isNotNull(n.getParentId())
					&& n.getParentId().longValue() == t.getAppClazzId().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<AppClazz> list, AppClazz t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}

	@Override
	public int selectNormalChildrenAppClazzById(Long appClazzId) {
		return appClazzMapper.selectNormalChildrenAppClazzById(appClazzId);
	}
}
