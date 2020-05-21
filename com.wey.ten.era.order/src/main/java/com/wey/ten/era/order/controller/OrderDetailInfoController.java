package com.wey.ten.era.order.controller;

import io.swagger.annotations.Api;
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
import com.wey.ten.era.order.entity.OrderDetailInfo;
import com.wey.ten.era.order.entity.vo.OrderDetailInfoVo;
import com.wey.ten.era.order.service.IOrderDetailInfoService;

/**
 * 订单Controller
 * 
 * @author yangws
 * @date 2020-05-18
 */
@Api(value = "OrderDetailInfoController", description = "订单详情接口")
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailInfoController
{
    @Autowired
    private IOrderDetailInfoService orderDetailInfoService;

    /**
     * 查询订单列表
     */
    @ApiOperation(value="订单详情列表", notes="订单详情列表")
    @PostMapping("/list")
    public ResultObject list(OrderDetailInfoVo orderDetailInfoVo)
    {
    	ResultObject resultObject=null;
    	resultObject = orderDetailInfoService.selectOrderDetailInfoList(orderDetailInfoVo);
        return resultObject;
    }

    /**
     * 导出订单列表
     */
    @GetMapping("/export")
    public ResultObject export(OrderDetailInfo orderDetailInfo)
    {
//        List<OrderDetailInfo> list = orderDetailInfoService.selectOrderDetailInfoList(orderDetailInfo);
//        ExcelUtil<OrderDetailInfo> util = new ExcelUtil<OrderDetailInfo>(OrderDetailInfo.class);
//        return util.exportExcel(list, "info");
    	return  null;
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
			resultObject = orderDetailInfoService.selectOrderDetailInfoById(id);
		}
		return resultObject;
    }

    /**
     * 新增订单
     */
    @ApiOperation(value="新增订单详情", notes="新增订单详情")
    @PostMapping(value = "/save")
    public ResultObject add(@RequestBody OrderDetailInfo orderDetailInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=orderDetailInfoService.insertOrderDetailInfo(orderDetailInfo);
        return resultObject;
    }

    /**
     * 修改订单
     */
    @ApiOperation(value="修改订单详情", notes="修改订单详情")
    @PostMapping("/update")
    public ResultObject edit(@RequestBody OrderDetailInfo orderDetailInfo)
    {
    	ResultObject resultObject=null;
    	resultObject=orderDetailInfoService.updateOrderDetailInfo(orderDetailInfo);
        return resultObject;
    }

    /**
     * 删除订单
     */
    @ApiOperation(value="批量删除订单详情", notes="批量删除订单详情")
	@GetMapping("/delete/{ids}")
    public ResultObject remove(@PathVariable Integer[] ids)
    {
    	ResultObject resultObject=null;
    	resultObject=orderDetailInfoService.deleteOrderDetailInfoByIds(ids);
        return resultObject;
    }
}
