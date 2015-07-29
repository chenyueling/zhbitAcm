package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.CollegeForm;
import com.zhbitacm.user.vo.VoCollege;

public interface CollegeService extends Dao{
	public Result addMajor(CollegeForm dataForm)throws DaoException;
	public Result saveCollege(CollegeForm dataForm) throws DaoException;
	public Result updateCollege(CollegeForm dataForm) throws DaoException;
	public Result active(CollegeForm dataForm);
	public Result cancel(CollegeForm dataForm);
	public JsonEasyUI<VoCollege> getDate(CollegeForm dataForm);
}
