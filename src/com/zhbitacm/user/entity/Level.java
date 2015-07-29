package com.zhbitacm.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_user_level")
public class Level {
	@Id
	@Column(length = 36)
	private String id;
	@Column(length = 200) 
	private String title;
	@Column(length = 200)
	private String mark;
	@Column(length = 50)
	private String year;
	private Date createTime;
	private Date editTime;
	private String leveltitle;
	
	@Override
	public String toString() {
		return year+" "+title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public void setLeveltitle(String leveltitle) {
		this.leveltitle = leveltitle;
	}
	public String getLeveltitle() {
		return leveltitle;
	}
	
	
}
