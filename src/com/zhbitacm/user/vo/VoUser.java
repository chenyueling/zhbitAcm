package com.zhbitacm.user.vo;

import com.xinhuo.util.DateUtil;
import com.zhbitacm.user.entity.User;

public class VoUser {
	private String id;
	private String username;
	private String nickname;
	private String name;
	private String status;
	private String lastLoginTime;
	private String createTime;
	private String editTime;
	private String school;
	private String major;
	private String college;
	private String grade;
	private String level;
	private String levelmark;
	
	public VoUser(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.nickname = user.getNickname();
		this.status = user.isActive()? "激活" : "注销";
		this.lastLoginTime = user.getLastLoginTime() == null ? "从未登陆过" : DateUtil.dataToString(user.getLastLoginTime());
		this.createTime = DateUtil.dataToString(user.getCreateTime());
		this.editTime = DateUtil.dataToString(user.getEditTime());
		this.major = user.getMajor();
		this.college = user.getCollege();
		this.grade = user.getGrade();
		if(null != user.getLevel()){
			this.level = user.getLevel().toString();
			this.levelmark = user.getLevel().getMark();
		}
		if(null != user.getSchool()){
			this.school = user.getSchool().getName();
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setLevelmark(String levelmark) {
		this.levelmark = levelmark;
	}

	public String getLevelmark() {
		return levelmark;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCollege() {
		return college;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchool() {
		return school;
	}
	
	
}
