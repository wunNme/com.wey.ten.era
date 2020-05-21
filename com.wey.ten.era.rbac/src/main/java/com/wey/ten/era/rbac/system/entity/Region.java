package com.wey.ten.era.rbac.system.entity;

public class Region {

	private Integer id;

	private String name;

	private Integer pid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

}
