package com.wey.ten.era.order.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.order.entity.OrderInfo;
import com.wey.ten.era.order.entity.vo.OrderInfoVo;
import com.wey.ten.era.order.service.IOrderInfoService;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController
{
    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 查询订单列表
     */
    @ApiOperation(value="查询订单列表", notes="查询订单列表")
    @PostMapping("/list")
    public ResultObject list(@RequestBody OrderInfoVo orderInfoVo)
    {
    	ResultObject resultObject = null;
		try {
			resultObject = orderInfoService.selectOrderInfoList(orderInfoVo);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
    }

    /**
     * 导出订单列表
     */
//    @ApiOperation(value="查询订单列表", notes="查询订单列表")
    @GetMapping("/export")
    public ResultObject export(OrderInfo orderInfo)
    {
    	ResultObject resultObject=null;
//    	resultObject=orderDetailInfoService.updateOrderDetailInfo(orderDetailInfo);
        return resultObject;
    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation(value="获取订单详细信息", notes="获取订单详细信息")
    @GetMapping(value = "/detail/{id}")
    public ResultObject getInfo(@PathVariable("id") Integer id)
    {
    	ResultObject resultObject = null;
		if (null == id) {
			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
		} else {
			resultObject = orderInfoService.selectOrderInfoById(id);
		}
		return resultObject;
    }

    /**
     * 新增订单
     */
    @ApiOperation(value="新增订单", notes="新增订单")
    @PostMapping("/add")
    public ResultObject add(@RequestBody OrderInfo orderInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=orderInfoService.insertOrderInfo(orderInfo);
        return resultObject;
    }

    /**
     * 修改订单
     */
    @ApiOperation(value="修改订单", notes="修改订单")
    @PostMapping("/update")
    public ResultObject edit(@RequestBody OrderInfo orderInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=orderInfoService.updateOrderInfo(orderInfo);
        return resultObject;
    }

    /**
     * 删除订单
     */
	@GetMapping("/delete/{ids}")
    public ResultObject remove(@PathVariable Integer[] ids)
    {
		ResultObject resultObject=null;
    	resultObject=orderInfoService.deleteOrderInfoByIds(ids);
        return resultObject;
    }
}
