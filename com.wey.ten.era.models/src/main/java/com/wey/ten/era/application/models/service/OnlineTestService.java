package com.wey.ten.era.application.models.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.application.models.entity.OnlineTest;
import com.wey.ten.era.application.models.entity.vo.OnlineTestVo;
import com.wey.ten.era.common.utils.ResultObject;

public interface OnlineTestService extends IService<OnlineTest>{//extends IService<OnlineTest>

	/**
	 * 添加在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject saveOnlineTest(OnlineTest onlineTest);
	
	/**
	 * 修改在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject updateOnlineTest(OnlineTest onlineTest);
	
	
	/**
	 * 删除在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject delOnlineTest(Integer id);
	
	/**
	 * 分页查询在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject queryOnlineTest(OnlineTestVo onlineTest);
	
	/**
	 * 详情在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject detailOnlineTest(Integer id);
	
	/**
	 * 详情在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	public ResultObject onlineTest(Map<String, String> params);

}
