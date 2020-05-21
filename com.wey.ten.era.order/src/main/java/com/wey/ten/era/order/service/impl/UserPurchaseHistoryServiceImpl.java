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
import com.wey.ten.era.order.dao.UserPurchaseHistoryDao;
import com.wey.ten.era.order.entity.UserPurchaseHistory;
import com.wey.ten.era.order.entity.vo.UserPurchaseHistoryVo;
import com.wey.ten.era.order.service.IUserPurchaseHistoryService;
import com.wey.ten.era.order.serviceapi.ModelServiceApi;
import com.wey.ten.era.order.serviceapi.UserServiceApi;

/**
 * 我的购买记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class UserPurchaseHistoryServiceImpl implements IUserPurchaseHistoryService 
{
    @Autowired
    private UserPurchaseHistoryDao userPurchaseHistoryDao;
    
    @Autowired
    private ModelServiceApi modelServiceApi;
    
    @Autowired
    private UserServiceApi userServiceApi;

    /**
     * 查询我的购买记录
     * 
     * @param id 我的购买记录ID
     * @return 我的购买记录
     */
    @Override
    public ResultObject selectUserPurchaseHistoryById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	UserPurchaseHistory userPurchaseHistory=userPurchaseHistoryDao.selectUserPurchaseHistoryById(id);
    	setData(userPurchaseHistory);
    	resultObject.setData(userPurchaseHistory);
        return resultObject;
    }

	private void setData(UserPurchaseHistory userPurchaseHistory) {
		//加载用户，行业，服务商信息
    	userPurchaseHistory.setUserInfo(userServiceApi.getUserById(userPurchaseHistory.getUserId().toString()));
    	//获取产品信息  后期需要修改，这里只获取了模型
    	userPurchaseHistory.setProductObject(modelServiceApi.getModelById(userPurchaseHistory.getSoftwareId()));
	}

    /**
     * 查询我的购买记录列表
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 我的购买记录
     */
    @Override
    public ResultObject selectUserPurchaseHistoryList(UserPurchaseHistoryVo userPurchaseHistoryVo)
    {
        ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		Map<String,Object> keyValues=StringUtils.transBean2Map(userPurchaseHistoryVo);
		PageResult<UserPurchaseHistory> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
	}
	
	
	private PageResult<UserPurchaseHistory> findPage(Map<String, Object> params) {
		long total = 0;
		Page<UserPurchaseHistory> pager = null;
		List<UserPurchaseHistory> userPurchaseHistoryList = null;
		Integer page = MapUtils.getInteger(params, "page");
		Integer limit = MapUtils.getInteger(params, "limit");
		if (page != null && limit != null) {
			pager = new Page<UserPurchaseHistory>(page, limit);
			setOrderBy(params, pager);
			userPurchaseHistoryList = userPurchaseHistoryDao.selectUserPurchaseHistoryList(pager, params);
			total = pager.getTotal();
		}else {
			userPurchaseHistoryList = userPurchaseHistoryDao.selectUserPurchaseHistoryList(pager, params);
		}
		if(userPurchaseHistoryList!=null && userPurchaseHistoryList.size()>0){
			for (int i = 0; i < userPurchaseHistoryList.size(); i++) {
				UserPurchaseHistory userPurchaseHistory=userPurchaseHistoryList.get(i);
				try {
					setData(userPurchaseHistory);
					userPurchaseHistoryList.set(i, userPurchaseHistory);
				} catch (Exception e) {
					//对应应用无法加载，不影响接口正常是使用
				}
			}
		}
		return PageResult.<UserPurchaseHistory> builder().data(userPurchaseHistoryList).code(0).count(total).build();
	}
	  public static void setOrderBy(Map<String, Object> params, Page<UserPurchaseHistory> pager) {
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
     * 新增我的购买记录
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 结果
     */
    @Override
    public ResultObject insertUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(userPurchaseHistoryDao.insertUserPurchaseHistory(userPurchaseHistory));
        return resultObject;
    }

    /**
     * 修改我的购买记录
     * 
     * @param userPurchaseHistory 我的购买记录
     * @return 结果
     */
    @Override
    public ResultObject updateUserPurchaseHistory(UserPurchaseHistory userPurchaseHistory)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(userPurchaseHistoryDao.updateUserPurchaseHistory(userPurchaseHistory));
        return resultObject;
    }

    /**
     * 批量删除我的购买记录
     * 
     * @param ids 需要删除的我的购买记录ID
     * @return 结果
     */
    @Override
    public ResultObject deleteUserPurchaseHistoryByIds(Integer[] ids)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(userPurchaseHistoryDao.deleteUserPurchaseHistoryByIds(ids));
        return resultObject;
    }

    /**
     * 删除我的购买记录信息
     * 
     * @param id 我的购买记录ID
     * @return 结果
     */
    @Override
    public ResultObject deleteUserPurchaseHistoryById(Integer id)
    {
    	ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
    	resultObject.setData(userPurchaseHistoryDao.deleteUserPurchaseHistoryById(id));
        return resultObject;
    }
}
