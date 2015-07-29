package com.zhbitacm.user.service;

import java.util.List;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.CompetitionForm;
import com.zhbitacm.user.vo.VoCompetition;

public interface CompetitionService extends Dao{
	public Result saveCompetition(CompetitionForm dataForm) throws DaoException;
	public Result updateCompetition(CompetitionForm dataForm) throws DaoException;
	public Result deleteCompetition(CompetitionForm dataForm) throws DaoException;	
	public Result startApply(CompetitionForm dataForm);
	public Result endApply(CompetitionForm dataForm);
	public Result startContest(CompetitionForm dataForm);
	public Result endContest(CompetitionForm dataForm);
	public Result cancel(CompetitionForm dataForm);
	public Result active(CompetitionForm dataForm);
	public VoCompetition getCompetitionById(CompetitionForm dataForm);
	public JsonEasyUI<VoCompetition> getDate(CompetitionForm dataForm);
	public List<Object> getContests(CompetitionForm dataForm);
}
