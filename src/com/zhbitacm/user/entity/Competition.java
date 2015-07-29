package com.zhbitacm.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 比赛
 * @author chenyueling
 *
 */
@Entity
@Table(name = "tb_user_competition")
public class Competition {
	@Id
	@Column(length = 36)
	private String id;
	@Column(length = 200)
	private String name;
	//比赛信息
	@Column(length = 8000)
	private String info;
	//比赛地址
	@Column(length = 500)
	private String address;
/*	@OneToMany(targetEntity=CompetitionGroup.class, cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="teams_id")
	private List<CompetitionGroup> teams;*/
	//创建时间，编辑时间，开始时间，结束时间
	private Date createTime;
	private Date editTime;
	private Date startTime;
	private Date endTime;
	@Column(length = 200)
	private String type;
	//最多参赛者
	private int maxPlayer;
	//比赛状态
	private String status; 
	//报名比赛
	private String registerContest;
	
	private boolean isActive;
	
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMaxPlayer() {
		return maxPlayer;
	}
	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setRegisterContest(String registerContest) {
		this.registerContest = registerContest;
	}
	public String getRegisterContest() {
		return registerContest;
	}
	
	
}
