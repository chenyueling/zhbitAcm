package com.zhbitacm.filter;


import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhbitacm.util.HttpSessionUtil;

/**
 * 用户拦截器
 * @author chenyueling
 *
 */
public class UserInterceptot extends MethodFilterInterceptor {
	private static final long serialVersionUID = -259946539531502267L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("In UserInterceptot");
		String status = HttpSessionUtil.getUserStatus();
		if (status == null) {
			//登录超时
			//移除用户客户端cookie
			//写入Cookie
			HttpServletResponse response = ServletActionContext.getResponse();
			Cookie cookie = null;
				 cookie = new Cookie("zhbitacm_username", null);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			return "logintimeout";
		}
		return invocation.invoke();
	}

}
