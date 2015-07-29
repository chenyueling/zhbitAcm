package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.UserForm;
import com.zhbitacm.user.vo.VoUser;


public interface UserService extends Dao{
	public Result userRegister(UserForm dataForm) throws Exception;
	public Result userLogin(UserForm dataForm) throws Exception;
	public void getUserInfo() throws Exception;
	public void beforeUpdate() throws Exception;
	public Result userUpdate(UserForm dataForm) throws Exception;
	public Result userSave(UserForm dataForm) throws Exception;
	public Result updatePassword(UserForm dataForm) throws Exception;
	public Result deleteUser(UserForm dataForm) throws Exception;
	public Result addLevel(UserForm dataForm) throws Exception;
	public Result active(UserForm dataForm);
	public Result cancel(UserForm dataForm);
	public JsonEasyUI<VoUser> getDate(UserForm dataForm);
}
