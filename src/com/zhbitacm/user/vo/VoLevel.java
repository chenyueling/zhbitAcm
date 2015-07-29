package com.zhbitacm.user.vo;

import com.zhbitacm.user.entity.Level;

public class VoLevel {
	private String id;
	private String mark;
	private String createTime;
	private String editTime;
	private String title;
	private String year;
	private String level;
	
	
	public VoLevel(Level level){
		this.id = level.getId();
		this.mark = level.getMark();
		this.createTime = level.getCreateTime().toString();
		this.editTime = level.getEditTime().toString();
		this.level = level.toString();
		this.year = level.getYear();
		this.title = level.getTitle();
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


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getEditTime() {
		return editTime;
	}


	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
