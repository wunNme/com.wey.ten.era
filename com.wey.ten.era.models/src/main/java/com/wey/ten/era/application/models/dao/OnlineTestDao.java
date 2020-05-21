package com.wey.ten.era.application.models.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.application.models.entity.OnlineTest;
//extends BaseMapper<OnlineTest>
@Mapper
public interface OnlineTestDao extends BaseMapper<OnlineTest>{

	/**
	 * 添加在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	 void saveOnlineTest(OnlineTest onlineTest);
	
	/**
	 * 修改在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	 void updateOnlineTest(OnlineTest onlineTest);
	
	
	/**
	 * 删除在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	 void delOnlineTest(Integer id);
	
	/**
	 * 分页查询在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	 List<OnlineTest> queryOnlineTest(Page<OnlineTest> page,@Param("e") Map<String,Object> param);
	 
	 Integer getCount(OnlineTest onlineTest);
	
	/**
	 * 详情在线测试
	 * @param OnlineTestVo
	 * @return
	 */
	 OnlineTest detailOnlineTest(Integer id);
	 
}
