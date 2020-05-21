package com.wey.ten.era.order.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.common.utils.StringUtils;
import com.wey.ten.era.order.dao.OrderInfoDao;
import com.wey.ten.era.order.entity.OrderDetailInfo;
import com.wey.ten.era.order.entity.OrderInfo;
import com.wey.ten.era.order.entity.vo.OrderInfoVo;
import com.wey.ten.era.order.service.IOrderDetailInfoService;
import com.wey.ten.era.order.service.IOrderInfoService;
import com.wey.ten.era.order.service.IShoppingCartInfoService;
import com.wey.ten.era.order.serviceapi.ModelServiceApi;
import com.wey.ten.era.order.serviceapi.UserServiceApi;
import com.wey.ten.era.order.utils.OrderNo;
import com.wey.ten.era.order.utils.OrderUtil;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService 
{
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private ModelServiceApi modelServiceApi;
    
    @Autowired
    private UserServiceApi userServiceApi;
    
    @Autowired
    private IOrderDetailInfoService orderDetailInfoService;
    
    @Autowired
    private IShoppingCartInfoService shoppingCartInfoService;
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public ResultObject selectOrderInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	OrderInfo orderInfo=orderInfoDao.selectOrderInfoById(id);
    	orderInfo.setOrderDetail(orderDetailInfoService.selectOrderDetailInfoByOrderId(orderInfo.getOrderId()).getData());
    	resultObject.setData(orderInfo);
        return resultObject;
    }

    /**
     * 查询订单列表
     * 
     * @param orderInfo 订单
     * @return 订单
     */
    @Override
    public ResultObject selectOrderInfoList(OrderInfoVo orderInfoVo)
    {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		Map<String,Object> keyValues=StringUtils.transBean2Map(orderInfoVo);
		PageResult<OrderInfo> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
    }

    
    private PageResult<OrderInfo> findPage(Map<String, Object> params) {
		long total = 0;
		Page<OrderInfo> pager = null;
		List<OrderInfo> orderInfoList = null;
		Integer page = MapUtils.getInteger(params, "page");
		Integer limit = MapUtils.getInteger(params, "limit");
		if (page != null && limit != null) {
			pager = new Page<OrderInfo>(page, limit);
			OrderUtil.setOrderBy(params, pager);
			orderInfoList = orderInfoDao.selectOrderInfoList(pager, params);
			total = pager.getTotal();
		}else {
			orderInfoList = orderInfoDao.selectOrderInfoList(pager, params);
		}
		if(orderInfoList!=null && orderInfoList.size()>0){
			for (int i = 0; i < orderInfoList.size(); i++) {
				OrderInfo orderInfo=orderInfoList.get(i);
				try {
					orderInfo.setOrderDetail(orderDetailInfoService.selectOrderDetailInfoByOrderId(orderInfo.getOrderId()).getData());
					orderInfoList.set(i, orderInfo);
				} catch (Exception e) {
				}
			}
		}
		return PageResult.<OrderInfo> builder().data(orderInfoList).code(0).count(total).build();
	}
	
	
    /**
     * 新增订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public ResultObject insertOrderInfo(OrderInfo orderInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	//目前只有模型，后期根据产品来增加 如软件 R等
    	try {
			orderInfo.setOrderId(OrderNo.NextOrderNo("M"));
			orderInfoDao.insertOrderInfo(orderInfo);
			if(orderInfo.getOrderDetail()!=null){
				List<OrderDetailInfo> orderDetailInfos=(List<OrderDetailInfo>)orderInfo.getOrderDetail();
				for (int i = 0; i < orderDetailInfos.size(); i++) {
					orderDetailInfoService.insertOrderDetailInfo(orderDetailInfos.get(i));
				}
			}
		} catch (Exception e) {
			return new ResultObject(ErrorCode.ORDER_ADD_FAIL);
		}
    	//订单创建成功后删除购物车
    	shoppingCartInfoService.deleteShoppingCartInfoByOrderId(orderInfo.getOrderId());
    	//后期加入积分，加入日志等功能
    	
        return resultObject;
    }

    /**
     * 修改订单
     * 
     * @param orderInfo 订单
     * @return 结果
     */
    @Override
    public ResultObject updateOrderInfo(OrderInfo orderInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	orderInfoDao.updateOrderInfo(orderInfo);
        return resultObject;
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public ResultObject deleteOrderInfoByIds(Integer[] ids)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	orderInfoDao.deleteOrderInfoByIds(ids);
        return resultObject;
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public ResultObject deleteOrderInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	orderInfoDao.deleteOrderInfoById(id);
        return resultObject;
    }
}
