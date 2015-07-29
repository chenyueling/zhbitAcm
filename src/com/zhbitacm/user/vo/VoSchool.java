package com.zhbitacm.user.vo;

import com.zhbitacm.user.entity.School;

public class VoSchool {
	private String id;
	private String name;
	private String mark;
	private String address;
	private String createTime;
	private String editTime;
	private String status;
	public VoSchool(School school){
		this.id = school.getId();
		this.setName(school.getName());
		this.mark = school.getMark();
		this.address = school.getAddress();
		this.createTime = school.getCreateTime().toString();
		this.editTime = school.getEditTime().toString();
		this.status = school.isActive()? "激活" : "注销";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateDate(String createTime) {
		this.createTime = createTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditTime() {
		return editTime;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
