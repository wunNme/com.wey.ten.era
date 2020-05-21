package com.wey.ten.era.order.utils;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wey.ten.era.order.entity.OrderInfo;

public class OrderUtil {
	public static void setOrderBy(Map<String, Object> params, Page<OrderInfo> pager) {
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
}
