package com.zhbitacm.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.DesUtil;
import com.xinhuo.util.QueryResult;
import com.xinhuo.util.RSAUtil;
import com.xinhuo.util.ValidataCode;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.entity.OrdinaryAdmin;
import com.zhbitacm.admin.formbean.OrdinaryAdminForm;
import com.zhbitacm.admin.vo.VoOrdinaryAdmin;
import com.zhbitacm.util.HttpSessionUtil;

@Service("ordinaryAdminService")
public class OrdinaryAdminServiceImpl extends DaoSupport implements OrdinaryAdminService {
	
	public Result userLogin(OrdinaryAdminForm dataForm) throws Exception {
		boolean isValidata = ValidataCode.verification(dataForm.getValidataCode());
		if (isValidata == false) {
			return Result.validataCodeError();
		}
		String username = dataForm.getUsername();
		String password = RSAUtil.decrypt(dataForm.getPassword());
		password = DesUtil.encryptDES(password);
		String whereSQL = "username=? and password=?";
		Object[] whereParams = {username, password};
		OrdinaryAdmin admin = this.find(OrdinaryAdmin.class, whereSQL, whereParams);
		if (admin == null) {
			return Result.passwordError();
		}
		if (admin.isActive() == false) {
			return new Result("error", "你的帐号已经被注销,如有疑问，请联系管理员");
		}
		HttpSessionUtil.putOrdinaryAdmin(admin.getId(), admin.getRealname(), admin.getLastLoginTime());
		
		admin.setLastLoginTime(new Date());
		this.update(admin);
		
		return Result.success();
	}
	
	public Result updatePassword(OrdinaryAdminForm dataForm) throws DaoException {
		String password = dataForm.getPassword();
		String password2 = dataForm.getPassword2();
		if (password.equals(password2) == false) {
			return new Result("error", "两次输入的密码不一致");
		}
		String id = HttpSessionUtil.getString(HttpSessionUtil.ordinaryAdmin_id);
		OrdinaryAdmin admin = this.findById(OrdinaryAdmin.class, id);
		if (admin.getPassword().equals(dataForm.getOldPassword()) == false) {
			return Result.passwordError();
		}
		admin.setPassword(password);
		this.update(admin);
		HttpSessionUtil.removeSuperAdmin();
		return Result.success();
	}
	
	
	public Result saveAdmin(OrdinaryAdminForm dataForm) throws DaoException {
		String realname = dataForm.getRealname();
		boolean isUsernameExist = this.checkUsernameExist(realname);
		if (isUsernameExist == true) {
			return new Result("error", "用户名已经存在");
		}
		boolean isRealnameExist = this.checkRealnameExist(realname);
		if (isRealnameExist == true) {
			return new Result("error", "真实姓名已经存在");
		}
		
		OrdinaryAdmin admin = new OrdinaryAdmin();
		admin.setId(UUID.randomUUID().toString());
		admin.setUsername(realname);
		admin.setRealname(realname);
		admin.setPassword(dataForm.getPassword());
		admin.setCreateTime(new Date());
		admin.setActive(true);
		this.update(admin);
		
		return Result.success();
	}
	
	public Result updateAdmin(OrdinaryAdminForm dataForm) throws DaoException {
		OrdinaryAdmin admin = this.findById(OrdinaryAdmin.class, dataForm.getId());
		String realname = dataForm.getRealname();
		String username = dataForm.getUsername();
		boolean isUsernameExist = this.checkUsernameExist(username, admin);
		if (isUsernameExist == true) {
			return new Result("error", "用户名已经存在");
		}
		boolean isRealnameExist = this.checkRealnameExist(realname, admin);
		if (isRealnameExist == true) {
			return new Result("error", "真实姓名已经存在");
		}
		
		admin.setUsername(username);
		admin.setRealname(realname);
		if ("".equals(dataForm.getPassword()) == false) {
			admin.setPassword(dataForm.getPassword());
		}
		this.update(admin);
		return Result.success();
	}
	
	public Result active(OrdinaryAdminForm dataForm) {
		OrdinaryAdmin admin = this.findById(OrdinaryAdmin.class, dataForm.getId());
		if (admin.isActive() == true) {
			return new Result("error", "该管理员已经是激活状态");
		}
		admin.setActive(true);
		this.update(admin);
		return Result.success();
	}
	
	public Result cancel(OrdinaryAdminForm dataForm) {
		OrdinaryAdmin admin = this.findById(OrdinaryAdmin.class, dataForm.getId());
		if (admin.isActive() == false) {
			return new Result("error", "该管理员已经是注销状态");
		}
		admin.setActive(false);
		this.update(admin);
		return Result.success();
	}
	
	public JsonEasyUI<VoOrdinaryAdmin> getDate(OrdinaryAdminForm dataForm) {
		JsonEasyUI<VoOrdinaryAdmin> result = new JsonEasyUI<VoOrdinaryAdmin>();
		QueryResult<OrdinaryAdmin> qr = this.getScrollDate(OrdinaryAdmin.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoOrdinaryAdmin> admin_l = new ArrayList<VoOrdinaryAdmin>();
		for (OrdinaryAdmin admin : qr.getResultList()) {
			admin_l.add(new VoOrdinaryAdmin(admin));
		}
		result.setRows(admin_l);
		return result;
	}
	
	
	
	
	
	
	
	/**
	 * 检测用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean checkUsernameExist(String username) {
		String whereSQL = "username=?";
		Object[] whereParams = {username};
		long count = this.getDataCount(OrdinaryAdmin.class, whereSQL, whereParams);
		return count > 0;
	}
	
	/**
	 * 检测用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean checkUsernameExist(String username, OrdinaryAdmin except) {
		String whereSQL = "username=? and id!=?";
		Object[] whereParams = {username, except.getId()};
		long count = this.getDataCount(OrdinaryAdmin.class, whereSQL, whereParams);
		return count > 0;
	}
	
	/**
	 * 检测真实姓名是否存在
	 * @param username
	 * @return
	 */
	public boolean checkRealnameExist(String realname) {
		String whereSQL = "realname=?";
		Object[] whereParams = {realname};
		long count = this.getDataCount(OrdinaryAdmin.class, whereSQL, whereParams);
		return count > 0;
	}
	
	/**
	 * 检测真实姓名是否存在
	 * @param username
	 * @return
	 */
	public boolean checkRealnameExist(String realname, OrdinaryAdmin except) {
		String whereSQL = "realname=? and id!=?";
		Object[] whereParams = {realname, except.getId()};
		long count = this.getDataCount(OrdinaryAdmin.class, whereSQL, whereParams);
		return count > 0;
	}
	

}
