package com.zhbitacm.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user_school")
public class School {
	@Id
	@Column(length = 36)
	private String id;
	@Column(length = 200)
	private String name;
	@Column(length = 200)
	private String mark;
	@Column(length = 500)
	private String address;
	private Date createTime;
	private Date editTime;
	private boolean isActive;
/*	
	//学院
	@OneToMany(targetEntity = College.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<College> colleges;*/
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
}
