package com.xinhuo.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.entity.SuperAdmin;
import com.zhbitacm.admin.service.SuperAdminService;

@Controller("initDataAction")
@Scope("prototype")
public class InitDataAction extends ActionSupport {
	private static final long serialVersionUID = -523802202400447766L;
	@Resource
	private SuperAdminService superAdminService = null;
	public String initData() {
		Result result = null;
		try {
			long count = superAdminService.getDataCount(SuperAdmin.class, null, null);
			if (count == 0) {
				SuperAdmin admin = new SuperAdmin();
				admin.setId(UUID.randomUUID().toString());
				admin.setUsername("admin");
				admin.setPassword("123456");
				admin.setRealname("超级管理员");
				superAdminService.save(admin);
				
				
				result = Result.success();
			} else {
				result = new Result("error", "已经初始化过了");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			addActionMessage(result.getData());
		}
		return result.getResult();
	}
}
