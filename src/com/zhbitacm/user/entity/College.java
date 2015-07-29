package com.zhbitacm.user.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 学院
 * @author liaoruihua
 *
 */
@Entity
@Table(name="tb_user_college")
public class College {
	@Id
	@Column(length = 36)
	private String id;
	@Column(length = 200)
	private String name;
	@Column(length = 200)
	private String mark;

	//专业
	@OneToMany(targetEntity = Major.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Major> majors;
	
	private Date createTime;
	private Date editTime;
	private boolean isActive;
	
	public void addMajor(Major major){
		majors.add(major);
	}
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
	public List<Major> getMajors() {
		return majors;
	}
	public void setMajors(List<Major> majors) {
		this.majors = majors;
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
