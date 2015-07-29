package com.zhbitacm.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xinhuo.exception.DaoException;
import com.xinhuo.util.DesUtil;

/**
 * 超级管理员
 * @author xiezefan
 *
 */
@Entity
@Table(name="tb_system_superadmin")
public class SuperAdmin {
	@Id
	@Column(length = 36, name = "id")
	private String id;
	@Column(length = 50)
	private String username;
	@Column(length = 256)
	private String password;
	@Column(length = 50)
	private String realname;
	private Date lastLoginTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id.toLowerCase();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() throws DaoException {
		return DesUtil.decryptDES(password);
	}
	public void setPassword(String password) throws DaoException {
		this.password = DesUtil.encryptDES(password);
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
