package com.zhbitacm.user.service;

import java.util.List;

import com.xinhuo.dao.Dao;
import com.xinhuo.exception.DaoException;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.CompetitionGroupForm;
import com.zhbitacm.user.vo.VoCompetitionGroup;

public interface CompetitionGroupService extends Dao{
	public Result saveCompetitionGroup(CompetitionGroupForm dataForm) throws DaoException;
	public Result updateCompetitionGroup(CompetitionGroupForm dataForm) throws DaoException;
	public Result addCompetitioner(CompetitionGroupForm dataForm) throws DaoException;
	public Result editStatus(CompetitionGroupForm dataForm) throws DaoException;
	public Result addRewards(CompetitionGroupForm dataForm) throws DaoException;
	public Result deleteCompetitionGroup(CompetitionGroupForm dataForm) throws DaoException;
	public VoCompetitionGroup getVoGroup(CompetitionGroupForm dataForm) throws DaoException;
	public Result cancel(CompetitionGroupForm dataForm);
	public Result active(CompetitionGroupForm dataForm);
	public JsonEasyUI<VoCompetitionGroup> getDate(CompetitionGroupForm dataForm);
	public List<VoCompetitionGroup> getGroups(CompetitionGroupForm dataForm);
}
