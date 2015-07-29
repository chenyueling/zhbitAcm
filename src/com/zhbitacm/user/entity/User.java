package com.zhbitacm.user.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user_user")
public class User {
	@Id
	@Column(length = 36)
	private String id;

	// 真实姓名
	@Column(length = 200)
	private String name;
	// 用户名
	@Column(length = 200)
	private String username;
	// 昵称
	@Column(length = 200)
	private String nickname;
	@Column(length = 256)
	private String password;
	@Column(length = 300)
	private String headImage;
	// 学校
	@ManyToOne(targetEntity = School.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private School school;
	/*
	 * //学院
	 * 
	 * @ManyToOne(targetEntity=College.class,fetch = FetchType.LAZY,cascade =
	 * CascadeType.REFRESH) private College college; //专业
	 * 
	 * @ManyToOne(targetEntity=Major.class,fetch = FetchType.LAZY,cascade =
	 * CascadeType.REFRESH) private Major major;
	 */

	// 学院
	@Column(length = 300)
	private String college;

	// 专业
	@Column(length = 300)
	private String major;
	// 年级
	@Column(length = 30)
	private String grade;

	@Column(length = 36)
	private String schoolId;
	// 学号
	@Column(length = 50)
	private String stuId;
	@Column(length = 50)
	private String phone;
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 2)
	private Sex sex;
	// 班级
	@Column(length = 200)
	private String clazz;
	// 生日
	private Date birthDay;
	@Column(length = 50)
	private String qq;
	@Column(length = 200)
	private String email;
	// 用户简介
	@Column(length = 8000)
	private String userIntro;
	private boolean isActive;
	@ManyToOne(targetEntity = Level.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Level level;
	@Column(length = 36)
	private String levelId;
	private Date lastLoginTime;
	private Date createTime;
	private Date editTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserIntro() {
		return userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Date getEditTime() {
		return editTime;
	}

}
