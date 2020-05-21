package com.wey.ten.era.rbac.config;

import java.util.TimerTask;
import com.wey.ten.era.common.ip.AddressUtils;
import com.wey.ten.era.common.spring.SpringUtils;
import com.wey.ten.era.rbac.system.entity.SysOperLog;
import com.wey.ten.era.rbac.system.service.ISysOperLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * 异步工厂（产生任务用）
 * 
 * @author ruoyi
 */
@Slf4j
public class AsyncFactory {

	/**
	 * 操作日志记录
	 * 
	 * @param operLog 操作日志信息
	 * @return 任务task
	 */
	public static TimerTask recordOper(final SysOperLog operLog) {
		return new TimerTask() {
			@Override
			public void run() {
				// 远程查询操作地点
				operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
				SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
				log.isDebugEnabled();
			}
		};
	}
}
