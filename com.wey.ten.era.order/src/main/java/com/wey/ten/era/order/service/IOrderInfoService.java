package com.wey.ten.era.order.service;

import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.OrderInfo;
import com.wey.ten.era.order.entity.vo.OrderInfoVo;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IOrderInfoService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public ResultObject selectOrderInfoById(Integer id);

    /**
     * 查询订单列表
     * 
     * @param orderInfo 订单
     * @return 订单集合
     */
    public ResultObject selectOrderInfoList(OrderInfoVo orderInfoVo);

    /**
     * 新增订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    public ResultObject insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    public ResultObject updateOrderInfo(OrderInfo orderInfo);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    public ResultObject deleteOrderInfoByIds(Integer[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public ResultObject deleteOrderInfoById(Integer id);
}
