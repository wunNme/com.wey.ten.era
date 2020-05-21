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
import com.wey.ten.era.order.entity.UserPurchaseHistory;
import com.wey.ten.era.order.entity.vo.UserPurchaseHistoryVo;
import com.wey.ten.era.order.service.IUserPurchaseHistoryService;

/**
 * 我的應用Controller
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@RestController
@RequestMapping("/userAppliaction")
public class UserPurchaseHistoryController 
{
    @Autowired
    private IUserPurchaseHistoryService userPurchaseHistoryService;

    /**
     * 查询我的购买记录列表
     */
    @ApiOperation(value="查询我的应用", notes="查询我的应用")
    @GetMapping("/list")
    public ResultObject list(UserPurchaseHistoryVo userPurchaseHistoryVo)
    {
        ResultObject resultObject = null;
		try {
			resultObject = userPurchaseHistoryService.selectUserPurchaseHistoryList(userPurchaseHistoryVo);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
		}
		return resultObject;
    }

    /**
     * 导出我的购买记录列表
     */
    @GetMapping("/export")
    public ResultObject export(UserPurchaseHistory userPurchaseHistory)
    {
        return null;
    }

    /**
     * 获取我的购买记录详细信息
     */
    @ApiOperation(value="查询应用详情", notes="查询应用详情")
    @GetMapping(value = "/detail/{id}")
    public ResultObject getInfo(@PathVariable("id") Integer id)
    {
    	ResultObject resultObject=null;
    	resultObject=userPurchaseHistoryService.selectUserPurchaseHistoryById(id);
        return resultObject;
    }

    /**
     * 新增我的购买记录
     */
    @ApiOperation(value="新增应用", notes="新增应用")
    @PostMapping("/add")
    public ResultObject add(@RequestBody UserPurchaseHistory userPurchaseHistory)
    {
    	ResultObject resultObject=null;
    	resultObject=userPurchaseHistoryService.insertUserPurchaseHistory(userPurchaseHistory);
        return resultObject;
    }

    /**
     * 修改我的购买记录
     */
    @ApiOperation(value="修改应用", notes="修改应用")
    @PostMapping("/update")
    public ResultObject edit(@RequestBody UserPurchaseHistory userPurchaseHistory)
    {
    	ResultObject resultObject=null;
    	resultObject=userPurchaseHistoryService.updateUserPurchaseHistory(userPurchaseHistory);
        return resultObject;
    }

    /**
     * 删除我的购买记录
     */
    @ApiOperation(value="删除应用", notes="删除应用")
	@GetMapping("/delete/{ids}")
    public ResultObject remove(@PathVariable Integer[] ids)
    {
		ResultObject resultObject=null;
    	resultObject=userPurchaseHistoryService.deleteUserPurchaseHistoryByIds(ids);
        return resultObject;
    }
}
