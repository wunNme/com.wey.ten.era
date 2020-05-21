package com.wey.ten.era.order.service;

import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.UserPurchaseHistory;
import com.wey.ten.era.order.entity.vo.UserPurchaseHistoryVo;

/**
 * 我的购买记录Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IUserPurchaseHistoryService 
{
    /**
     * 查询我的购买记录
     * 
     * @param id 我的购买记录ID
     * @return 我的购买记录
     */
    public ResultObject selectUserPurchaseHistoryById(Integer id);

    /**
     * 查询我的购买记录列表
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 我的购买记录集合
     */
    public ResultObject selectUserPurchaseHistoryList(UserPurchaseHistoryVo userPurchaseHistoryVo);

    /**
     * 新增我的购买记录
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 结果
     */
    public ResultObject insertUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory);

    /**
     * 修改我的购买记录
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 结果
     */
    public ResultObject updateUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory);

    /**
     * 批量删除我的购买记录
     * 
     * @param ids 需要删除的我的购买记录ID
     * @return 结果
     */
    public ResultObject deleteUserPurchaseHistoryByIds(Integer[] ids);

    /**
     * 删除我的购买记录信息
     * 
     * @param id 我的购买记录ID
     * @return 结果
     */
    public ResultObject deleteUserPurchaseHistoryById(Integer id);
}
