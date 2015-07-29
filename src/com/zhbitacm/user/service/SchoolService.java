package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.SchoolForm;
import com.zhbitacm.user.vo.VoSchool;

public interface SchoolService extends Dao{
	public Result addCollege(SchoolForm dataForm)throws DaoException;
	public Result saveSchool(SchoolForm dataForm) throws DaoException;
	public Result deleteSchool(SchoolForm dataForm) throws DaoException;
	public Result updateSchool(SchoolForm dataForm) throws DaoException;
	public Result active(SchoolForm dataForm);
	public Result cancel(SchoolForm dataForm);
	public JsonEasyUI<VoSchool> getDate(SchoolForm dataForm);
}
