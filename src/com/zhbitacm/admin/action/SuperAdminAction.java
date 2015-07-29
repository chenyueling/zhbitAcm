package com.zhbitacm.admin.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.formbean.SuperAdminForm;
import com.zhbitacm.admin.service.SuperAdminService;
import com.zhbitacm.util.HttpSessionUtil;

@Controller("superAdminAction")
@Scope("prototype")
public class SuperAdminAction extends ActionSupport implements ModelDriven<SuperAdminForm> {
	private static final long serialVersionUID = -2707306456218164582L;
	private SuperAdminForm dataForm = new SuperAdminForm();
	@Resource
	private SuperAdminService superAdminService = null;
	
	public void login() throws IOException {
		Result result = null;
		try {
			result = superAdminService.userLogin(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public String loginOut() {
		HttpSessionUtil.removeSuperAdmin();
		return "loginout";
	}
	
	public String updateUsername() {
		Result result = null;
		try {
			result = superAdminService.updateUsername(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		}finally{
			addActionMessage(result.getData());
		}
		return result.getResult();
	}
	
	public void updatePassword() {
		Result result = null;
		try {
			result = superAdminService.updatePassword(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	
	
	public SuperAdminForm getModel() {
		return dataForm;
	}
	
}
