package com.wey.ten.era.application.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineTestUtils {
	
	private static final String attributesKey = "requestAttributesName";
	
	private static final String attributesValue = "requestAttributesValue";
	
	public static Map<String, String> getRequestParams(String requestParams){
		Map<String, String> map = new HashMap<String, String>();
		JSONArray  jsonArray = JSONArray.fromObject(requestParams);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.containsKey(attributesKey)&&jsonObject.containsKey(attributesValue)){
				map.put(jsonObject.getString(attributesKey), jsonObject.getString(attributesValue));
			}
		}
		return map;
	}
}
