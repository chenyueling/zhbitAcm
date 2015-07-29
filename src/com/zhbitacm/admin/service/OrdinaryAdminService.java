package com.zhbitacm.admin.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.formbean.OrdinaryAdminForm;
import com.zhbitacm.admin.vo.VoOrdinaryAdmin;

public interface OrdinaryAdminService extends Dao {
	public Result userLogin(OrdinaryAdminForm dataForm) throws Exception;
	public Result saveAdmin(OrdinaryAdminForm dataForm) throws DaoException;
	public Result updateAdmin(OrdinaryAdminForm dataForm) throws DaoException;
	public Result active(OrdinaryAdminForm dataForm);
	public Result cancel(OrdinaryAdminForm dataForm);
	public JsonEasyUI<VoOrdinaryAdmin> getDate(OrdinaryAdminForm dataForm);
	public Result updatePassword(OrdinaryAdminForm dataForm) throws DaoException;
}
