package com.zhbitacm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.xinhuo.util.DateUtil;

public class HttpSessionUtil {
	public static final String superAdmin_id = "superAdmin_id";
	public static final String superAdmin_realname = "superAdmin_realname";
	public static final String superAdmin_lastLoginTime = "superAdmin_lastLoginTime";

	public static final String ordinaryAdmin_id = "ordinaryAdmin_id";
	public static final String associationAdmin_id = "associationAdmin_id";
	public static final String ordinaryAdmin_realname = "ordinaryAdmin_realname";
	public static final String associationAdmin_realname = "associationAdmin_realname";
	public static final String ordinary_lastLoginTime = "ordinaryAdmin_lastLoginTime";
	public static final String associationAdmin_lastLoginTime = "associationAdmin_lastLoginTime";
	
	public static final String user_id = "user_id";
	public static final String user_username = "user_username";
	public static final String user_lastLoginTime = "user_lastLoginTime";
	
	public static Object getObject(String key){
		Map<String, Object> m = ActionContext.getContext().getSession();
		return m.get(key);
	}
	
	public static String getString(String key){
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(key);
	}
	
	//超级管理员
	public static void putSuperAdmin(String id, String realname, Date lastLoginTime) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(superAdmin_id, id);
		session.put(superAdmin_realname, realname);
		if (lastLoginTime != null) {
			String dataStr = DateUtil.dataToString(lastLoginTime);
			session.put(superAdmin_lastLoginTime, dataStr);
		} else {
			session.put(superAdmin_lastLoginTime, "第一次登录");
		}
	}
	
	public static String getSuperAdminStatus() {
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(superAdmin_id);
	}
	
	public static void removeSuperAdmin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(superAdmin_id);
		session.remove(superAdmin_realname);
		session.remove(superAdmin_lastLoginTime);
	}
	
	//普通管理员
	public static void putOrdinaryAdmin(String id, String realname, Date lastLoginTime) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(ordinaryAdmin_id, id);
		session.put(ordinaryAdmin_realname, realname);
		if (lastLoginTime != null) {
			String dataStr = DateUtil.dataToString(lastLoginTime);
			session.put(ordinary_lastLoginTime, dataStr);
		} else {
			session.put(ordinary_lastLoginTime, "第一次登录");
		}
	}
	
	
	//协会普通管理员
	public static void putAssociationAdmin(String id, String realname, Date lastLoginTime) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(associationAdmin_id, id);
		session.put(associationAdmin_realname, realname);
		if (lastLoginTime != null) {
			String dataStr = DateUtil.dataToString(lastLoginTime);
			session.put(associationAdmin_lastLoginTime, dataStr);
		} else {
			session.put(associationAdmin_lastLoginTime, "第一次登录");
		}
	}
	
	
	//普通用户
	public static void putUser(String id, String username, Date lastLoginTime) throws UnsupportedEncodingException{
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(user_id, id);
		session.put(user_username, username);
		if (lastLoginTime != null){
			String dataStr = DateUtil.dataToString(lastLoginTime);
			session.put(user_lastLoginTime, dataStr);
		} else {
			session.put(user_lastLoginTime, "第一次登录");
		}
		
		//写入Cookie
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie("zhbitacm_username", URLEncoder.encode(username,"utf-8"));
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	public static String getOrdinaryAdminStatus() {
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(ordinaryAdmin_id);
	}

	public static void removeOrdinaryAdmin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(associationAdmin_id);
		session.remove(associationAdmin_realname);
		session.remove(associationAdmin_lastLoginTime);
	}
	
	
	public static String getAssociationAdminStatus() {
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(associationAdmin_id);
	}

	public static void removeAssociationAdmin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(associationAdmin_id);
		session.remove(associationAdmin_realname);
		session.remove(associationAdmin_lastLoginTime);
	}
	
	
	public static String getUser(){
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(user_id);
	}
	
	public static void removeUser(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(user_id);
		session.remove(user_username);
		session.remove(user_lastLoginTime);
		
		//写入Cookie
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie("zhbitacm_username", null);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getUserStatus() {
		Map<String, Object> m = ActionContext.getContext().getSession();
		return (String)m.get(user_id);
	}
}
