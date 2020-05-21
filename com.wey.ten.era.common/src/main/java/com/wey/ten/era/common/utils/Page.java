package com.wey.ten.era.common.utils;

import java.io.Serializable;

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 每页显示个数
	 */
	private int limit = 10;
	/**
	 * 当前页数
	 */
	private int page = 1;
	
	/**
	 * 排序字段
	 */
	private String asc;
	
	/**
	 * 排序方式
	 */
	private String desc;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
