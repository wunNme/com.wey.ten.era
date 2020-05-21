package com.wey.ten.era.order.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.common.utils.StringUtils;
import com.wey.ten.era.order.dao.OrderDetailInfoDao;
import com.wey.ten.era.order.entity.OrderDetailInfo;
import com.wey.ten.era.order.entity.vo.OrderDetailInfoVo;
import com.wey.ten.era.order.service.IOrderDetailInfoService;
import com.wey.ten.era.order.serviceapi.ModelServiceApi;
import com.wey.ten.era.order.serviceapi.UserServiceApi;

/**
 * 订单Service业务层处理
 * 
 * @author yangws
 * @date 2020-05-18
 */
@Service
public class OrderDetailInfoServiceImpl implements IOrderDetailInfoService 
{
    @Autowired
    private OrderDetailInfoDao orderDetailInfoDao;

    @Autowired
    private ModelServiceApi modelServiceApi;
    
    @Autowired
    private UserServiceApi userServiceApi;
    
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public ResultObject selectOrderDetailInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	OrderDetailInfo orderDetailInfo=orderDetailInfoDao.selectOrderDetailInfoById(id);
    	setData(orderDetailInfo);
    	resultObject.setData(orderDetailInfo);
        return resultObject;
    }
    
	private void setData(OrderDetailInfo orderDetailInfo) {
		//加载用户，行业，服务商信息
		orderDetailInfo.setUserInfo(userServiceApi.getUserById(orderDetailInfo.getBuyerUserId().toString()));
    	//获取产品信息  后期需要修改，这里只获取了模型
		orderDetailInfo.setProductObject(modelServiceApi.getModelById(orderDetailInfo.getSoftwareId()));
	}
    
    @Override
    public ResultObject selectOrderDetailInfoByOrderId(String orderId)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(orderDetailInfoDao.selectOrderDetailInfoByOrderId(orderId));
        return resultObject;
    }

    /**
     * 查询订单列表
     * 
     * @param orderDetailInfo 订单
     * @return 订单
     */
    @Override
    public ResultObject selectOrderDetailInfoList(OrderDetailInfoVo orderDetailInfoVo)
    {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		Map<String,Object> keyValues=StringUtils.transBean2Map(orderDetailInfoVo);
		PageResult<OrderDetailInfo> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
    }

    private PageResult<OrderDetailInfo> findPage(Map<String, Object> params) {
  		long total = 0;
  		Page<OrderDetailInfo> pager = null;
  		List<OrderDetailInfo> orderDetailInfos = null;
  		Integer page = MapUtils.getInteger(params, "page");
  		Integer limit = MapUtils.getInteger(params, "limit");
  		if (page != null && limit != null) {
  			pager = new Page<OrderDetailInfo>(page, limit);
  			setOrderBy(params, pager);
  			orderDetailInfos = orderDetailInfoDao.selectOrderDetailInfoList(pager, params);
  			total = pager.getTotal();
  		}else {
  			orderDetailInfos = orderDetailInfoDao.selectOrderDetailInfoList(pager, params);
  		}
  		if(orderDetailInfos!=null && orderDetailInfos.size()>0){
  			for (int i = 0; i < orderDetailInfos.size(); i++) {
  				OrderDetailInfo orderDetailInfo=orderDetailInfos.get(i);
  				try {
  					setData(orderDetailInfo);
  					orderDetailInfos.set(i, orderDetailInfo);
  				} catch (Exception e) {
  				}
  			}
  		}
  		return PageResult.<OrderDetailInfo> builder().data(orderDetailInfos).code(0).count(total).build();
  	}
    
    public static void setOrderBy(Map<String, Object> params, Page<OrderDetailInfo> pager) {
		if(params.containsKey("asc")&&params.get("asc")!=null){
			if(params.get("asc").toString().split(",").length>0){
				pager.setAscs(Arrays.asList(params.get("asc").toString().split(",")));
			}else{
				pager.setAsc(params.get("asc").toString());
			}
		}
		if(params.containsKey("desc")&&params.get("desc")!=null){
			if(params.get("desc").toString().split(",").length>1){
				pager.setDescs(Arrays.asList(params.get("desc").toString().split(",")));
				
			}else{
				pager.setDesc(params.get("desc").toString());
			}
		}
		if(params.containsKey("desc")&&params.get("desc")!=null&&params.containsKey("asc")&&params.get("asc")!=null){
			pager.setDesc("createTime");
		}
	}
    /**
     * 新增订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    @Override
    public ResultObject insertOrderDetailInfo(OrderDetailInfo orderDetailInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(orderDetailInfoDao.insertOrderDetailInfo(orderDetailInfo));
        return resultObject;
    }

    /**
     * 修改订单
     * 
     * @param orderDetailInfo 订单
     * @return 结果
     */
    @Override
    public ResultObject updateOrderDetailInfo(OrderDetailInfo orderDetailInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(orderDetailInfoDao.updateOrderDetailInfo(orderDetailInfo));
        return resultObject;
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public ResultObject deleteOrderDetailInfoByIds(Integer[] ids)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(orderDetailInfoDao.deleteOrderDetailInfoByIds(ids));
        return resultObject;
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public ResultObject deleteOrderDetailInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(orderDetailInfoDao.deleteOrderDetailInfoById(id));
        return resultObject;
    }
}
