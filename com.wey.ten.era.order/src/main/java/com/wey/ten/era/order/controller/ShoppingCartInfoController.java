package com.wey.ten.era.order.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.ShoppingCartInfo;
import com.wey.ten.era.order.entity.vo.ShoppingCartInfoVo;
import com.wey.ten.era.order.service.IShoppingCartInfoService;

/**
 * 购物车Controller
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartInfoController 
{
    @Autowired
    private IShoppingCartInfoService shoppingCartInfoService;
    
    @Autowired
	private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询购物车列表
     */
    @ApiOperation(value="查询购物车列表", notes="查询购物车列表")
    @PostMapping("/list")
    public ResultObject list(@RequestBody ShoppingCartInfoVo shoppingCartInfoVo)
    {
    	ResultObject resultObject = null;
		try {
			resultObject = shoppingCartInfoService.selectShoppingCartInfoList(shoppingCartInfoVo);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
    }

    /**
     * 导出购物车列表
     */
    @PostMapping("/export")
    public ResultObject export(@RequestBody ShoppingCartInfo shoppingCartInfo)
    {
    	ResultObject resultObject=null;
//    	resultObject=shoppingCartInfoService.deleteOrderInfoByIds(ids);
        return resultObject;
    }

    /**
     * 获取购物车详细信息
     */
    @ApiOperation(value="获取购物车详细信息", notes="获取购物车详细信息")
    @GetMapping(value = "/detail/{id}")
    public ResultObject getInfo(@PathVariable("id") Integer id)
    {
    	ResultObject resultObject=null;
    	resultObject=shoppingCartInfoService.selectShoppingCartInfoById(id);
        return resultObject;
    }

    /**
     * 新增购物车
     */
    @ApiOperation(value="新增购物车", notes="新增购物车")
    @PostMapping("/add")
    public ResultObject add(@RequestBody ShoppingCartInfo shoppingCartInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=shoppingCartInfoService.insertShoppingCartInfo(shoppingCartInfo);
        return resultObject;
    }

    /**
     * 修改购物车
     */
    @ApiOperation(value="修改购物车", notes="修改购物车")
    @PostMapping("/update")
    public ResultObject edit(@RequestBody ShoppingCartInfo shoppingCartInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=shoppingCartInfoService.updateShoppingCartInfo(shoppingCartInfo);
        return resultObject;
    }

    /**
     * 删除购物车
     */
    @ApiOperation(value="删除购物车", notes="删除购物车")
	@GetMapping("/delete/{ids}")
    public ResultObject remove(@PathVariable Integer[] ids)
    {
		ResultObject resultObject=null;
    	resultObject=shoppingCartInfoService.deleteShoppingCartInfoByIds(ids);
        return resultObject;
    }
}
