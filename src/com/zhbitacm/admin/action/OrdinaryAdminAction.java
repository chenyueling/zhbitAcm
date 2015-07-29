package com.zhbitacm.admin.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.formbean.OrdinaryAdminForm;
import com.zhbitacm.admin.service.OrdinaryAdminService;
import com.zhbitacm.admin.vo.VoOrdinaryAdmin;
import com.zhbitacm.util.HttpSessionUtil;

@Controller("ordinaryAdminAction")
@Scope("prototype")
public class OrdinaryAdminAction extends ActionSupport implements ModelDriven<OrdinaryAdminForm> {
	private static final long serialVersionUID = 2327429762395188575L;
	private OrdinaryAdminForm dataForm = new OrdinaryAdminForm();
	@Resource
	private OrdinaryAdminService ordinaryAdminService = null;
	
	//普通管理员业务方法
	public void login() {
		Result result = null;
		try {
			result = ordinaryAdminService.userLogin(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public String loginOut() {
		HttpSessionUtil.removeOrdinaryAdmin();
		return "loginout";
	}
	
	public void updatePassword() {
		Result result = null;
		try {
			result = ordinaryAdminService.updatePassword(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	
	//超级管理员业务方法
	public void save() {
		Result result = null;
		try {
			result = ordinaryAdminService.saveAdmin(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void update() {
		Result result = null;
		try {
			result = ordinaryAdminService.updateAdmin(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void active() {
		Result result = null;
		try {
			result = ordinaryAdminService.active(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void cancel() {
		Result result = null;
		try {
			result = ordinaryAdminService.cancel(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void list() throws IOException {
		JsonEasyUI<VoOrdinaryAdmin> result = null;
		try {
			result = ordinaryAdminService.getDate(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpServletResponse response = ServletActionContext.getResponse();    
			response.setCharacterEncoding("UTF-8");    
	        PrintWriter out = response.getWriter();
	        //直接输入响应的内容
	        JSONObject jsonObject = JSONObject.fromObject(result);
			System.out.println(jsonObject.toString());
	        out.print(jsonObject.toString());       
	        out.flush();    
	        out.close();
		}
	}
	
	
	public OrdinaryAdminForm getModel() {
		return dataForm;
	}
	

}
