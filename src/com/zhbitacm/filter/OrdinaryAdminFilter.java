package com.zhbitacm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhbitacm.util.HttpSessionUtil;

/**
 * 普通管理员过滤器
 * @author xiezefan
 *
 */
public class OrdinaryAdminFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String url = req.getRequestURI();
		System.out.println("OrdinaryAdminFilter, URL:" + url);
		
		String admin_statu = HttpSessionUtil.getOrdinaryAdminStatus();
		if (admin_statu == null) {// 登录超时
			res.sendRedirect(req.getContextPath() + "/admin/system/ordinary_login.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
