package com.wey.ten.era.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.order.entity.ShoppingCartInfo;

/**
 * 购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Mapper
public interface ShoppingCartInfoDao extends BaseMapper<ShoppingCartInfo>
{
    /**
     * 查询购物车
     * 
     * @param id 购物车ID
     * @return 购物车
     */
    public ShoppingCartInfo selectShoppingCartInfoById(Integer id);
    
    void deleteShoppingCartInfoByOrderId(String orderId);

    /**
     * 查询购物车列表
     * 
     * @param shoppingCartInfo 购物车
     * @return 购物车集合
     */
    public List<ShoppingCartInfo> selectShoppingCartInfoList(Page<ShoppingCartInfo> page,@Param("e") Map<String,Object> param);

    /**
     * 新增购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    public int insertShoppingCartInfo(ShoppingCartInfo shoppingCartInfo);

    /**
     * 修改购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    public int updateShoppingCartInfo(ShoppingCartInfo shoppingCartInfo);

    /**
     * 删除购物车
     * 
     * @param id 购物车ID
     * @return 结果
     */
    public int deleteShoppingCartInfoById(Integer id);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShoppingCartInfoByIds(Integer[] ids);
}
