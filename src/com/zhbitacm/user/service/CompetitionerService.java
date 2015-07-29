package com.zhbitacm.user.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.entity.Competitioner;
import com.zhbitacm.user.formbean.CompetitionGroupForm;

public interface CompetitionerService extends Dao{
	public Competitioner createCompetitioner(CompetitionGroupForm dataForm,Competition competition);
	public Result saveCompetitioner(CompetitionGroupForm dataForm, Competitioner competitioner);
	public Result saveCompetitioner(CompetitionGroupForm dataForm);
	public Result updateCompetitioner(CompetitionGroupForm dataForm);
	public boolean checkCompetitionerExist(String userId, String contestId);
}
