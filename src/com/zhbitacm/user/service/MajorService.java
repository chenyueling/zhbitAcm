package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.MajorForm;
import com.zhbitacm.user.vo.VoMajor;

public interface MajorService extends Dao{
	public Result saveMajor(MajorForm dataForm) throws DaoException;
	public Result updateMajor(MajorForm dataForm) throws DaoException;
	public Result active(MajorForm dataForm);
	public Result cancel(MajorForm dataForm);
	public JsonEasyUI<VoMajor> getDate(MajorForm dataForm);
}
