package com.zhbitacm.admin.service;


import java.util.Date;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.DesUtil;
import com.xinhuo.util.RSAUtil;
import com.xinhuo.util.ValidataCode;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.entity.SuperAdmin;
import com.zhbitacm.admin.formbean.SuperAdminForm;
import com.zhbitacm.util.HttpSessionUtil;

@Service("superAdminService")
public class SuperAdminServiceImpl extends DaoSupport implements
		SuperAdminService {
	
	public Result userLogin(SuperAdminForm dataForm) throws Exception {
		boolean isValidata = ValidataCode.verification(dataForm.getValidataCode());
		if (isValidata == false) {
			return Result.validataCodeError();
		}
		String username = dataForm.getUsername();
		String password = RSAUtil.decrypt(dataForm.getPassword());
		password = DesUtil.encryptDES(password);
		String whereSQL = "username=? and password=?";
		Object[] whereParams = {username, password};
		SuperAdmin admin = this.find(SuperAdmin.class, whereSQL, whereParams);
		if (admin == null) {
			return Result.passwordError();
		}
		HttpSessionUtil.putSuperAdmin(admin.getId(), admin.getRealname(), admin.getLastLoginTime());
		
		admin.setLastLoginTime(new Date());
		this.update(admin);
		
		return Result.success();
	}
	
	public Result updateUsername(SuperAdminForm dataForm) throws DaoException {
		String id = HttpSessionUtil.getString(HttpSessionUtil.superAdmin_id);
		SuperAdmin admin = this.findById(SuperAdmin.class, id);
		if (admin.getPassword().equals(dataForm.getPassword()) == false) {
			return Result.passwordError();
		}
		admin.setUsername(dataForm.getUsername());
		this.update(admin);
		return Result.success();
	}
	
	public Result updatePassword(SuperAdminForm dataForm) throws DaoException {
		String password = dataForm.getPassword();
		String password2 = dataForm.getPassword2();
		if (password.equals(password2) == false) {
			return new Result("error", "两次输入的密码不一致");
		}
		String id = HttpSessionUtil.getString(HttpSessionUtil.superAdmin_id);
		SuperAdmin admin = this.findById(SuperAdmin.class, id);
		if (admin.getPassword().equals(dataForm.getOldPassword()) == false) {
			return Result.passwordError();
		}
		admin.setPassword(password);
		this.update(admin);
		HttpSessionUtil.removeSuperAdmin();
		return Result.success();
		
	}
}
