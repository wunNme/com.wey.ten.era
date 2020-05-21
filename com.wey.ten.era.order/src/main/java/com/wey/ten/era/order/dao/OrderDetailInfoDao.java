package com.wey.ten.era.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.order.entity.OrderDetailInfo;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Mapper
public interface OrderDetailInfoDao extends BaseMapper<OrderDetailInfo>
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public OrderDetailInfo selectOrderDetailInfoById(Integer id);
    
    public List<OrderDetailInfo> selectOrderDetailInfoByOrderId(String orderId);

    /**
     * 查询订单列表
     * 
     * @param orderDetailInfo 订单
     * @return 订单集合
     */
    public List<OrderDetailInfo> selectOrderDetailInfoList(Page<OrderDetailInfo> page,@Param("e") Map<String,Object> param);

    /**
     * 新增订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    public int insertOrderDetailInfo(OrderDetailInfo orderDetailInfo);

    /**
     * 修改订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    public int updateOrderDetailInfo(OrderDetailInfo orderDetailInfo);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOrderDetailInfoById(Integer id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderDetailInfoByIds(Integer[] ids);
}
