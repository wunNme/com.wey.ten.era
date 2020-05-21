package com.wey.ten.era.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.order.entity.UserPurchaseHistory;

/**
 * 我的购买记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Mapper
public interface UserPurchaseHistoryDao extends
		BaseMapper<UserPurchaseHistory> {
	/**
	 * 查询我的购买记录
	 * 
	 * @param id
	 *            我的购买记录ID
	 * @return 我的购买记录
	 */
	public UserPurchaseHistory selectUserPurchaseHistoryById(Integer id);

	/**
	 * 查询我的购买记录列表
	 * 
	 * @param userPurchaseHistory
	 *            我的购买记录
	 * @return 我的购买记录集合
	 */
	public List<UserPurchaseHistory> selectUserPurchaseHistoryList(
			Page<UserPurchaseHistory> page,@Param("e") Map<String,Object> param);

	/**
	 * 新增我的购买记录
	 * 
	 * @param userPurchaseHistory
	 *            我的购买记录
	 * @return 结果
	 */
	public int insertUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory);

	/**
	 * 修改我的购买记录
	 * 
	 * @param userPurchaseHistory
	 *            我的购买记录
	 * @return 结果
	 */
	public int updateUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory);

	/**
	 * 删除我的购买记录
	 * 
	 * @param id
	 *            我的购买记录ID
	 * @return 结果
	 */
	public int deleteUserPurchaseHistoryById(Integer id);

	/**
	 * 批量删除我的购买记录
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteUserPurchaseHistoryByIds(Integer[] ids);
}
