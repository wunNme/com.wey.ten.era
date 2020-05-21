package com.wey.ten.era.order.service;

import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.ShoppingCartInfo;
import com.wey.ten.era.order.entity.vo.ShoppingCartInfoVo;

/**
 * 购物车Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IShoppingCartInfoService 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车ID
     * @return 购物车
     */
    public ResultObject selectShoppingCartInfoById(Integer id);

    /**
     * 查询购物车列表
     * 
     * @param shoppingCartInfo 购物车
     * @return 购物车集合
     */
    public ResultObject selectShoppingCartInfoList(ShoppingCartInfoVo shoppingCartInfoVo);

    /**
     * 新增购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    public ResultObject insertShoppingCartInfo(ShoppingCartInfo shoppingCartInfo);

    /**
     * 修改购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    public ResultObject updateShoppingCartInfo(ShoppingCartInfo shoppingCartInfo);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车ID
     * @return 结果
     */
    public ResultObject deleteShoppingCartInfoByIds(Integer[] ids);

    /**
     * 删除购物车信息
     * 
     * @param id 购物车ID
     * @return 结果
     */
    public ResultObject deleteShoppingCartInfoById(Integer id);
    
    public ResultObject deleteShoppingCartInfoByOrderId(String orderId);
}
