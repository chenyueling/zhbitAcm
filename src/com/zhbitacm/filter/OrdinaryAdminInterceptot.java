package com.zhbitacm.filter;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhbitacm.util.HttpSessionUtil;

/**
 * 普通管理员拦截器
 * @author xiezefan
 *
 */
public class OrdinaryAdminInterceptot extends MethodFilterInterceptor {
	private static final long serialVersionUID = -259946539531502267L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("In OrdinaryAdminInterceptot");
		String status = HttpSessionUtil.getOrdinaryAdminStatus();
		if (status == null) {
			//登录超时
			return "logintimeout";
		}
		return invocation.invoke();
	}

}
