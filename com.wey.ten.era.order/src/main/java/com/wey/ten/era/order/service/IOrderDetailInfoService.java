package com.wey.ten.era.order.service;

import java.util.List;

import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.OrderDetailInfo;
import com.wey.ten.era.order.entity.vo.OrderDetailInfoVo;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IOrderDetailInfoService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public ResultObject selectOrderDetailInfoById(Integer id);
    
    public ResultObject selectOrderDetailInfoByOrderId(String orderId);

    /**
     * 查询订单列表
     * 
     * @param orderDetailInfo 订单
     * @return 订单集合
     */
    public ResultObject selectOrderDetailInfoList(OrderDetailInfoVo orderDetailInfoVo);

    /**
     * 新增订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    public ResultObject insertOrderDetailInfo(OrderDetailInfo orderDetailInfo);

    /**
     * 修改订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    public ResultObject updateOrderDetailInfo(OrderDetailInfo orderDetailInfo);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    public ResultObject deleteOrderDetailInfoByIds(Integer[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public ResultObject deleteOrderDetailInfoById(Integer id);
}
