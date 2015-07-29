package com.zhbitacm.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.entity.CompetitionGroup;
import com.zhbitacm.user.entity.Competitioner;
import com.zhbitacm.user.entity.GroupStatus;
import com.zhbitacm.user.formbean.CompetitionGroupForm;
import com.zhbitacm.user.vo.VoCompetition;
import com.zhbitacm.user.vo.VoCompetitionGroup;

@Service("competitionGroupService")
public class CompetitionGroupServiceImpl extends DaoSupport implements
		CompetitionGroupService {

	@Resource
	private CompetitionerService competitionerService;

	private String createKeyCode(String name) {
		String KeyCode = null;
		String endStr = String.format("%04d", new Random().nextInt(10000));
		KeyCode = new Date().getTime() + endStr ;
		return KeyCode;
	}

	public Result saveCompetitionGroup(CompetitionGroupForm dataForm)
			throws DaoException {

		if (competitionerService.checkCompetitionerExist(dataForm.getUserId(),
				dataForm.getCompetitionId())) {
			return new Result("error", "该会员账号已加入本场比赛的其他队伍，需要更改请联系管理员");
		}

		String name = dataForm.getName();
		String engName = dataForm.getEngName();

		boolean isGroupnameExist = this.checkGroupnameExist(name,
				dataForm.getCompetitionId());
		boolean isGroupEngNameExist = this.checkGroupEngNameExist(engName,
				dataForm.getCompetitionId());
		if (isGroupnameExist == true) {
			return new Result("error", "队伍中文名已经存在");
		}

		if (isGroupEngNameExist == true) {
			return new Result("error", "队伍英文名已经存在");
		}

		Competition competition = this.findById(Competition.class,
				dataForm.getCompetitionId());
		CompetitionGroup group = new CompetitionGroup();

		String keyCode = createKeyCode(engName);

		dataForm.setKeyCode(keyCode);

		Competitioner competitioner = competitionerService.createCompetitioner(
				dataForm, competition);
		competitionerService.saveCompetitioner(dataForm, competitioner);

		group.setCompetition(competition);
		group.setContestname(competition.getName());
		group.setName(name);
		group.setEngName(engName);
		group.setKeyCode(keyCode);
		group.setId(UUID.randomUUID().toString());
		group.setStatus(GroupStatus.CHECKING);
		group.setCaptain(competitioner);
		group.setCreateTime(new Date());
		group.setEditTime(new Date());
		List<Competitioner> competitioners = new ArrayList<Competitioner>();
		competitioners.add(competitioner);
		group.setCompetitioners(competitioners);

		group.setActive(true);
		this.update(group);

		return new Result("success", group.getKeyCode());
	}

	public Result addCompetitioner(CompetitionGroupForm dataForm)
			throws DaoException {

		if (competitionerService.checkCompetitionerExist(dataForm.getUserId(),
				dataForm.getCompetitionId())) {
			return new Result("error", "该会员账号已加入本场比赛的其他队伍，需要更改请联系管理员");
		}

		Competition competition = this.findById(Competition.class,
				dataForm.getCompetitionId());
		CompetitionGroup competitionGroup = findByKeyCode(dataForm.getKeyCode());
		if (null == competitionGroup) {
			return new Result("error", "没有找到该队伍，请认真核对队伍标识码");
		}

		if (null != competitionGroup
				&& competitionGroup.getCompetitioners().size() == competition
						.getMaxPlayer()) {
			return new Result("error", "该队伍人数已满");
		}

		Competitioner competitioner = competitionerService.createCompetitioner(
				dataForm, competition);
		competitionerService.saveCompetitioner(dataForm, competitioner);

		competitionGroup.getCompetitioners().add(competitioner);
		competitionGroup.setEditTime(new Date());
		this.update(competitionGroup);

		return new Result("success", competitionGroup.getKeyCode());
	}

	public Result updateCompetitionGroup(CompetitionGroupForm dataForm)
			throws DaoException {
		CompetitionGroup competitionGroup = this.findById(
				CompetitionGroup.class, dataForm.getId());
		String name = dataForm.getName();
		boolean isGroupnameExist = this.checkGroupnameExist(name,
				competitionGroup);
		if (isGroupnameExist == true) {
			return new Result("error", "队伍名已经存在");
		}

		competitionGroup.setName(name);
		GroupStatus status = GroupStatus.getGroupStatus(dataForm.getStatus());
		competitionGroup.setStatus(status);
		competitionGroup.setEditTime(new Date());
		this.update(competitionGroup);
		return Result.success();
	}

	public Result active(CompetitionGroupForm dataForm) {
		CompetitionGroup competitionGroup = this.findById(
				CompetitionGroup.class, dataForm.getId());
		if (competitionGroup.isActive() == true) {
			return new Result("error", "该队伍已经是激活状态");
		}
		competitionGroup.setActive(true);
		this.update(competitionGroup);
		return Result.success();
	}

	public Result cancel(CompetitionGroupForm dataForm) {
		CompetitionGroup competitionGroup = this.findById(
				CompetitionGroup.class, dataForm.getId());
		if (competitionGroup.isActive() == false) {
			return new Result("error", "该队伍已经是注销状态");
		}
		competitionGroup.setActive(false);
		this.update(competitionGroup);
		return Result.success();
	}

	public JsonEasyUI<VoCompetitionGroup> getDate(CompetitionGroupForm dataForm) {
		JsonEasyUI<VoCompetitionGroup> result = new JsonEasyUI<VoCompetitionGroup>();
		QueryResult<CompetitionGroup> qr = this.getScrollDate(
				CompetitionGroup.class, null, null, dataForm.getSearchTitle(),
				dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(),
				dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoCompetitionGroup> competitionGroup_l = new ArrayList<VoCompetitionGroup>();
		for (CompetitionGroup competitionGroup : qr.getResultList()) {
			competitionGroup_l.add(new VoCompetitionGroup(competitionGroup));
		}
		result.setRows(competitionGroup_l);
		return result;
	}

	public VoCompetitionGroup getVoGroup(CompetitionGroupForm dataForm)
			throws DaoException {
		String o = dataForm.getO();
		CompetitionGroup competitionGroup = null;
		if (null != o && o.equals("o")) {
			competitionGroup = this.findById(CompetitionGroup.class,
					dataForm.getId());
		} else {
			competitionGroup = this.findByKeyCode(dataForm.getKeyCode());
		}
		VoCompetitionGroup voGroup = null;
		Competition competition = null;
		if (null != competitionGroup) {
			competition = competitionGroup.getCompetition();
			VoCompetition voCompetition = new VoCompetition(competition);
			voGroup = new VoCompetitionGroup(competitionGroup);
			voGroup.setDate(voCompetition.getDate());
			voGroup.setAddress(voCompetition.getAddress());
			voGroup.setStart(voCompetition.getStart());
			voGroup.setEnd(voCompetition.getEnd());
			voGroup.setMaxPlayer(voCompetition.getMaxPlayer());
			if (null != o && o.equals("o")) {
				voGroup.setKeyCode("请到用户信息参赛列表里查看队伍信息");
			}
		}
		return voGroup;
	}

	private CompetitionGroup findByKeyCode(String keyCode) {
		String whereSQL = "keyCode=?";
		Object[] whereParams = { keyCode };
		CompetitionGroup competitionGroup = this.find(CompetitionGroup.class,
				whereSQL, whereParams);
		return competitionGroup;
	}

	private boolean checkGroupnameExist(String name,
			CompetitionGroup competitionGroup) {
		String whereSQL = "name=? and id!=?";
		Object[] whereParams = { name, competitionGroup.getId() };
		long count = this.getDataCount(CompetitionGroup.class, whereSQL,
				whereParams);
		return count > 0;
	}

	private boolean checkGroupnameExist(String name, String contestId) {
		String whereSQL = "name=? and competition_id=?";
		Object[] whereParams = { name, contestId };
		long count = this.getDataCount(CompetitionGroup.class, whereSQL,
				whereParams);
		return count > 0;
	}

	private boolean checkGroupEngNameExist(String engName, String contestId) {
		String whereSQL = "engName=? and competition_id=?";
		Object[] whereParams = { engName, contestId };
		long count = this.getDataCount(CompetitionGroup.class, whereSQL,
				whereParams);
		return count > 0;
	}

	public Result editStatus(CompetitionGroupForm dataForm) throws DaoException {
		CompetitionGroup competitionGroup = this.findById(
				CompetitionGroup.class, dataForm.getId());
		String status = dataForm.getStatus();

		if (null != status) {
			if ("CHECKING".equals(status)) {
				competitionGroup.setStatus(GroupStatus.CHECKING);
			} else if ("PASS".equals(status)) {
				competitionGroup.setStatus(GroupStatus.PASS);
			} else if ("UNPASS".equals(status)) {
				competitionGroup.setStatus(GroupStatus.UNPASS);
			} else if ("FINISH".equals(status)) {
				competitionGroup.setStatus(GroupStatus.FINISH);
			} else if ("ABORT".equals(status)) {
				competitionGroup.setStatus(GroupStatus.ABORT);
			}
			System.out.println(status);
			System.out.println(competitionGroup.getStatus().getDescribe());
		}

		competitionGroup.setEditTime(new Date());
		this.update(competitionGroup);
		return Result.success();
	}

	public Result addRewards(CompetitionGroupForm dataForm) throws DaoException {
		CompetitionGroup competitionGroup = this.findById(
				CompetitionGroup.class, dataForm.getId());
		String rewards = dataForm.getRewards();
		if (null != rewards) {
			competitionGroup.setRewards(rewards);
		}
		competitionGroup.setEditTime(new Date());
		this.update(competitionGroup);
		return Result.success();
	}

	public List<VoCompetitionGroup> getGroups(CompetitionGroupForm dataForm) {
		List<CompetitionGroup> groups = null;
		String contestId = dataForm.getCompetitionId();
		String whereSql = "competition_id='" + contestId + "'";
		groups = this
				.findAll(CompetitionGroup.class, whereSql, new String[] {});
		List<VoCompetitionGroup> group_l = new ArrayList<VoCompetitionGroup>();
		for (CompetitionGroup group : groups) {
			group_l.add(new VoCompetitionGroup(group));
		}
		return group_l;
	}

	public Result deleteCompetitionGroup(CompetitionGroupForm dataForm)
			throws DaoException {

		String idstr = dataForm.getId();
		if (idstr != null && idstr.length() > 0) {
			String whereSQL = "";
			String[] id = idstr.split(";");
			boolean isFirst = true;
			for (int i = 0; i < id.length; i++) {
				if (isFirst) {
					whereSQL = "id=?";
					isFirst = false;
				} else {
					whereSQL += " or id=?";
				}
			}
			List<CompetitionGroup> list = this.findAll(CompetitionGroup.class,
					whereSQL, id);
			for (CompetitionGroup competitionGroup : list) {
				this.delete(competitionGroup);
				for (Competitioner competitioner : competitionGroup
						.getCompetitioners()) {
					this.delete(competitioner);
				}
			}
		} else {
			return new Result("error", "队伍对象不存在");
		}
		return Result.success();
	}

}
