package com.zhbitacm.admin.vo;

import com.xinhuo.util.DateUtil;
import com.zhbitacm.admin.entity.OrdinaryAdmin;

public class VoOrdinaryAdmin {
	private String id;
	private String username;
	private String realname;
	private String status;
	private String lastLoginTime;
	private String createTime;
	public VoOrdinaryAdmin(OrdinaryAdmin admin) {
		this.id = admin.getId();
		this.username = admin.getUsername();
		this.realname = admin.getRealname();
		this.status = admin.isActive() ? "激活" : "注销";
		this.lastLoginTime = admin.getLastLoginTime() == null ? "从未登录过" : DateUtil.dataToString(admin.getLastLoginTime());
		this.createTime = DateUtil.dataToString(admin.getCreateTime());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
