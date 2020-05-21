package com.wey.ten.era.application.models.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.json.JSONUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wey.ten.era.application.models.dao.OnlineTestDao;
import com.wey.ten.era.application.models.entity.OnlineTest;
import com.wey.ten.era.application.models.entity.vo.OnlineTestVo;
import com.wey.ten.era.application.models.service.OnlineTestService;
import com.wey.ten.era.application.utils.HttpUtils;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.common.utils.StringUtils;

@Service
@Transactional(readOnly = false)
//extends ServiceImpl<OnlineTestDao,OnlineTest>
public class OnlineTestServiceImpl extends ServiceImpl<OnlineTestDao,OnlineTest> implements OnlineTestService{
	
	@Autowired
	private OnlineTestDao onlineTestDao;
	/**
	 * 添加在线测试
	 * @param OnlineTest
	 * @return
	 */
	public ResultObject saveOnlineTest(OnlineTest OnlineTest){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		onlineTestDao.saveOnlineTest(OnlineTest);
		return resultObject;
	}
	
	/**
	 * 修改在线测试
	 * @param OnlineTest
	 * @return
	 */
	public ResultObject updateOnlineTest(OnlineTest OnlineTest){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		onlineTestDao.updateOnlineTest(OnlineTest);
		return resultObject;
	}
	
	
	/**
	 * 删除在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject delOnlineTest(Integer id){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		onlineTestDao.delOnlineTest(id);
		return resultObject;
	}
	
	/**
	 * 分页查询在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject queryOnlineTest(OnlineTestVo onlineTest){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		Page page = new Page();
//		page.setData(onlineTestDao.queryOnlineTest(onlineTest));
//		page.setTotalCount(onlineTestDao.getCount(onlineTest));
//		resultObject.setData(page);
		Map<String,Object> keyValues=StringUtils.transBean2Map(onlineTest);
		PageResult<OnlineTest> pageResult = findPage(keyValues);
		if (pageResult != null) {
			resultObject.setData(pageResult.getData());
		}
		return resultObject;
	}
	
	private PageResult<OnlineTest> findPage(Map<String, Object> params) {
		long total = 0;
		Page<OnlineTest> pager = null;
		List<OnlineTest> onlineTestList = null;
		Integer page = MapUtils.getInteger(params, "page");
		Integer limit = MapUtils.getInteger(params, "limit");
		if (page != null && limit != null) {
			pager = new Page<OnlineTest>(page, limit);
//			setOrderBy(params, pager);
			onlineTestList = onlineTestDao.queryOnlineTest(pager, params);
			total = pager.getTotal();
		}else {
			onlineTestList = onlineTestDao.queryOnlineTest(pager, params);
		}
		return PageResult.<OnlineTest> builder().data(onlineTestList).code(0).count(total).build();
	}

	private void setOrderBy(Map<String, Object> params, Page<OnlineTest> pager) {
		if(params.containsKey("asc")&&params.get("asc")!=null){
			if(params.get("asc").toString().split(",").length>0){
				pager.setAscs(Arrays.asList(params.get("asc").toString().split(",")));
			}else{
				pager.setAsc(params.get("asc").toString());
			}
		}
		if(params.containsKey("desc")&&params.get("desc")!=null){
			if(params.get("desc").toString().split(",").length>0){
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
	 * 详情在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject detailOnlineTest(Integer id){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(onlineTestDao.detailOnlineTest(id));
		return resultObject;
	}

	/**
	 * 在线测试请求
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultObject onlineTest(Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		OnlineTest onlinet = onlineTestDao.detailOnlineTest(Integer.valueOf(params.get("id")));
		try {
			
			String accessAdress = onlinet.getAccessAdress();
			String requestType = onlinet.getRequestType();
//		String resultFormat = onlinet.getResultFormat();
			/**
			 * 获取前端传递的MAP转换为请求参数
			 */
			Map<String, String> mapRequest= new HashMap<String, String>();
			String requestParams = params.get("requestParams");
//			Gson gson = new Gson();
//			List<Map<String,String>> list =gson.fromJson(requestParams, new TypeToken<List<Map<String, String>>>() {}.getType());
			JSONArray jsonArray = JSONArray.fromObject(requestParams);
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String,String> mapParams=jsonArray.getJSONObject(i);
				mapRequest.put(mapParams.get("requestAttributesName"), mapParams.get("requestAttributesValue"));
			}
			JSONObject jsonObject = JSONObject.fromObject(mapRequest);
			/**
			 * 请求参数转换失败
			 */
//		String resultErrorStructure = onlinet.getResultErrorStructure();
			Map<String, Object> requestResult=new HashMap<String, Object>();
			if("get".equalsIgnoreCase(requestType)){
				Gson gson = new Gson();
			    Map<String, Object> map = new HashMap<String, Object>();
			    map = gson.fromJson(accessAdress, map.getClass());//关键
			    requestResult=HttpUtils.httpGet(accessAdress, jsonObject);
			}else if("post".equalsIgnoreCase(requestType)){
				requestResult=HttpUtils.httpPost(accessAdress, jsonObject);
			}else{
				new ResultObject(ErrorCode.REQUEST_FAIL_ERROR);
			}
			onlinet.setResultDataStructure(JSONUtil.parseObj(requestResult).toString());
		} catch (JsonSyntaxException e) {
			new ResultObject(ErrorCode.REQUEST_FAIL_ERROR);
		}
		resultObject.setData(onlinet);
		return resultObject;
	}
		public static void main(String[] args) {
			Map<String, String> requestResult=new HashMap<String, String>();
			requestResult.put("tel", "13530863415");
			HttpUtils.httpGet("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm", requestResult);
		}
}
