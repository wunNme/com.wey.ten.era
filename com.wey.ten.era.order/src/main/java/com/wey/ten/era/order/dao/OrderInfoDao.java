package com.wey.ten.era.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.order.entity.OrderInfo;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfo>
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public OrderInfo selectOrderInfoById(Integer id);

    /**
     * 查询订单列表
     * 
     * @param orderInfo 订单
     * @return 订单集合
     */
    public List<OrderInfo> selectOrderInfoList(Page<OrderInfo> page,@Param("e") Map<String,Object> param);

    /**
     * 新增订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOrderInfoById(Integer id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderInfoByIds(Integer[] ids);
}
