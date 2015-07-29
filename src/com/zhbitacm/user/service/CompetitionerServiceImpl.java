package com.zhbitacm.user.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.entity.Competitioner;
import com.zhbitacm.user.entity.User;
import com.zhbitacm.user.formbean.CompetitionGroupForm;

@Service("competitionerService")
public class CompetitionerServiceImpl extends DaoSupport implements
		CompetitionerService {

	public Result saveCompetitioner(CompetitionGroupForm dataForm,
			Competitioner competitioner) {
		this.update(competitioner);
		return Result.success();
	}

	public Result saveCompetitioner(CompetitionGroupForm dataForm) {
		Competition competition = this.findById(Competition.class,
				dataForm.getCompetitionId());
		Competitioner competitioner = createCompetitioner(dataForm, competition);
		this.update(competitioner);
		return null;
	}

	public Competitioner createCompetitioner(CompetitionGroupForm dataForm,
			Competition competition) {
		Competitioner competitioner = new Competitioner();
		competitioner.setId(UUID.randomUUID().toString());
		competitioner.setCreateTime(new Date());
		competitioner.setEmail(dataForm.getEmail());
		competitioner.setPhone(dataForm.getPhone());
		competitioner.setName(dataForm.getUsername());
		competitioner.setKeyCode(dataForm.getKeyCode());
		String user_id = dataForm.getUserId();
		User user = this.findById(User.class, user_id);
		competitioner.setUser(user);
		competitioner.setCompetition(competition);
		competitioner.setUserId(user_id);

		return competitioner;
	}

	public Result updateCompetitioner(CompetitionGroupForm dataForm) {

		return Result.success();
	}

	public boolean checkCompetitionerExist(String userId, String contestId) {
		String whereSQL = "user_id=? and competition_id=?";
		Object[] whereParams = { userId, contestId };
		long count = this.getDataCount(Competitioner.class, whereSQL,
				whereParams);
		return count > 0;
	}

}
