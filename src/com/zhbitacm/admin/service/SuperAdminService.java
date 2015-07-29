package com.zhbitacm.admin.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.formbean.SuperAdminForm;

public interface SuperAdminService extends Dao {
	public Result userLogin(SuperAdminForm dataForm) throws Exception;
	public Result updateUsername(SuperAdminForm dataForm) throws DaoException;
	public Result updatePassword(SuperAdminForm dataForm) throws DaoException;
}
