package com.wey.ten.era.application.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConvertUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, String> getStrToMap(String str){
		try {
			Gson gson = new Gson();
			 Map<String, String> map = new HashMap<String, String>();
			 map = gson.fromJson(str, map.getClass());//关键
			 return map;
		} catch (JsonSyntaxException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getStrToMapT(String str){
		try {
			Gson gson = new Gson();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map = gson.fromJson(str, map.getClass());//关键
			 return map;
		} catch (JsonSyntaxException e) {
			return null;
		}
	}
}
