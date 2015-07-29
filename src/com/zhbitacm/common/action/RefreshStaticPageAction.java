package com.zhbitacm.common.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.Result;
import com.zhbitacm.common.service.RefreshStaticPageService;

@Scope("prototype")
@Controller("refreshStaticPageAction")
public class RefreshStaticPageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource
	private RefreshStaticPageService refreshStaticPageService = null;
	public void refreshIndex(){
		Result result = null;
		try {
			result = refreshStaticPageService.refreshIndex();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void refreshAllPage(){
		
	}
	
}
