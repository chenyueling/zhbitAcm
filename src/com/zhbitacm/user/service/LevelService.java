package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.LevelForm;
import com.zhbitacm.user.vo.VoLevel;

public interface LevelService extends Dao{
	public Result saveLevel(LevelForm dataForm) throws DaoException;
	public Result updateLevel(LevelForm dataForm) throws DaoException;	
	public Result deleteLevel(LevelForm dataForm) throws DaoException;
	public JsonEasyUI<VoLevel> getDate(LevelForm dataForm);
}
