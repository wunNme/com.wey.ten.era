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
import com.wey.ten.era.order.dao.ShoppingCartInfoDao;
import com.wey.ten.era.order.entity.ShoppingCartInfo;
import com.wey.ten.era.order.entity.vo.ShoppingCartInfoVo;
import com.wey.ten.era.order.service.IShoppingCartInfoService;
import com.wey.ten.era.order.serviceapi.ModelServiceApi;
import com.wey.ten.era.order.serviceapi.UserServiceApi;

/**
 * 购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class ShoppingCartInfoServiceImpl implements IShoppingCartInfoService 
{
    @Autowired
    private ShoppingCartInfoDao shoppingCartInfoDao;
    
    @Autowired
    private ModelServiceApi modelServiceApi;
    
    @Autowired
    private UserServiceApi userServiceApi;
    /**
     * 查询购物车
     * 
     * @param id 购物车ID
     * @return 购物车
     */
    @Override
    public ResultObject selectShoppingCartInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	ShoppingCartInfo shoppingCartInfo = shoppingCartInfoDao.selectShoppingCartInfoById(id);
    	setData(shoppingCartInfo);
    	resultObject.setData(shoppingCartInfo);
        return resultObject;
    }
    
	private void setData(ShoppingCartInfo shoppingCartInfo) {
		//加载用户，行业，服务商信息
		shoppingCartInfo.setUserInfo(userServiceApi.getUserById(shoppingCartInfo.getUserId().toString()));
    	//获取产品信息  后期需要修改，这里只获取了模型
		shoppingCartInfo.setProductObject(modelServiceApi.getModelById(shoppingCartInfo.getSoftwareId()));
	}
    /**
     * 查询购物车列表
     * 
     * @param shoppingCartInfo 购物车
     * @return 购物车
     */
    @Override
    public ResultObject selectShoppingCartInfoList(ShoppingCartInfoVo shoppingCartInfoVo)
    {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		Map<String,Object> keyValues=StringUtils.transBean2Map(shoppingCartInfoVo);
		PageResult<ShoppingCartInfo> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
	}
	
	
	private PageResult<ShoppingCartInfo> findPage(Map<String, Object> params) {
		long total = 0;
		Page<ShoppingCartInfo> pager = null;
		List<ShoppingCartInfo> shoppingCartInfoList = null;
		Integer page = MapUtils.getInteger(params, "page");
		Integer limit = MapUtils.getInteger(params, "limit");
		if (page != null && limit != null) {
			pager = new Page<ShoppingCartInfo>(page, limit);
			setOrderBy(params, pager);
			shoppingCartInfoList = shoppingCartInfoDao.selectShoppingCartInfoList(pager, params);
			total = pager.getTotal();
		}else {
			shoppingCartInfoList = shoppingCartInfoDao.selectShoppingCartInfoList(pager, params);
		}
		if(shoppingCartInfoList!=null && shoppingCartInfoList.size()>0){
			for (int i = 0; i < shoppingCartInfoList.size(); i++) {
				ShoppingCartInfo shoppingCartInfo=shoppingCartInfoList.get(i);
				try {
					setData(shoppingCartInfo);
					shoppingCartInfoList.set(i, shoppingCartInfo);
				} catch (Exception e) {
				}
			}
		}
		return PageResult.<ShoppingCartInfo> builder().data(shoppingCartInfoList).code(0).count(total).build();
	}
	  public static void setOrderBy(Map<String, Object> params, Page<ShoppingCartInfo> pager) {
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
     * 新增购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    @Override
    public ResultObject insertShoppingCartInfo(ShoppingCartInfo shoppingCartInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(shoppingCartInfoDao.insertShoppingCartInfo(shoppingCartInfo));
        return resultObject;
    }

    /**
     * 修改购物车
     * 
     * @param shoppingCartInfo 购物车
     * @return 结果
     */
    @Override
    public ResultObject updateShoppingCartInfo(ShoppingCartInfo shoppingCartInfo)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(shoppingCartInfoDao.updateShoppingCartInfo(shoppingCartInfo));
        return resultObject;
    }

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车ID
     * @return 结果
     */
    @Override
    public ResultObject deleteShoppingCartInfoByIds(Integer[] ids)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(shoppingCartInfoDao.deleteShoppingCartInfoByIds(ids));
        return resultObject;
    }

    /**
     * 删除购物车信息
     * 
     * @param id 购物车ID
     * @return 结果
     */
    @Override
    public ResultObject deleteShoppingCartInfoById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(shoppingCartInfoDao.deleteShoppingCartInfoById(id));
        return resultObject;
    }
    
    @Override
    public ResultObject deleteShoppingCartInfoByOrderId(String orderId)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	shoppingCartInfoDao.deleteShoppingCartInfoByOrderId(orderId);
        return resultObject;
    }
}
