/**
 * 
 */
package com.zhbitacm.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.DateUtil;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.formbean.CompetitionForm;
import com.zhbitacm.user.vo.VoCompetition;

@Service("competitionService")
public class CompetitionServiceImpl extends DaoSupport implements
		CompetitionService {


	public Result saveCompetition(CompetitionForm dataForm) throws DaoException {
		String name = dataForm.getName();
		boolean isContentsnameExist = this.checkContentsnameExist(name);
		if (isContentsnameExist == true) {
			return new Result("error", "比赛名已经存在");
		}
		Competition competition = new Competition();
		String maxPlayer = dataForm.getMaxPlayer();
		int maxP;
		if("one".equals(maxPlayer)){
			maxP = 1;
		}else{
			maxP = 3;
		}
		String info = dataForm.getInfo();
		String address = dataForm.getAddress();
		String type = dataForm.getType();
		String registerContest = dataForm.getRegisterContest();
		String startTime = dataForm.getStartTime();
		String endTime = dataForm.getEndTime();
		String status = dataForm.getStatus();
		competition.setName(name);
		competition.setId(UUID.randomUUID().toString());
		competition.setAddress(address);
		competition.setType(type);
		competition.setMaxPlayer(maxP);
		competition.setStatus(status);
		competition.setInfo(info);
		competition.setRegisterContest(registerContest);
		competition.setStartTime(DateUtil.format(startTime));
		competition.setEndTime(DateUtil.format(endTime));
		competition.setCreateTime(new Date());
		competition.setEditTime(new Date());
		competition.setActive(true);
		this.update(competition);
		
		return Result.success();	
	}

	public Result updateCompetition(CompetitionForm dataForm)
			throws DaoException {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		String name = dataForm.getName();
		String address = dataForm.getAddress();
		String info = dataForm.getInfo();

		if(null != name && name.trim().equals("")){
			return new Result("error", "比赛名不能为空");
		}
		boolean isContentsnameExist = this.checkContentsnameExist(name, competition);
		if (isContentsnameExist == true) {
			return new Result("error", "比赛名已经存在");
		}
		if(null != name && !name.trim().equals("")){
			competition.setName(name);
		}
		if(null != address && !address.trim().equals("")){
			competition.setAddress(address);
		}
		if(null != info && !info.trim().equals("")){
			competition.setInfo(info);
		}
		String type = dataForm.getType();
		String startTime = dataForm.getStartTime();
		String endTime = dataForm.getEndTime();
		competition.setType(type);
		competition.setStartTime(DateUtil.format(startTime));
		competition.setEndTime(DateUtil.format(endTime));
		competition.setEditTime(new Date());
		this.update(competition);
		
		return Result.success();
	}
	
	public Result deleteCompetition(CompetitionForm dataForm) 
		throws DaoException{

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
			List<Competition> list = this.findAll(Competition.class, whereSQL, id);

			for (Competition competition : list) {
				this.delete(competition);
			}
		} else {
			return new Result("error", "比赛对象不存在");
		}
		return Result.success();
	}
	
	public Result active(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if (competition.isActive() == true) {
			return new Result("error", "该比赛已经是激活状态");
		}
		competition.setActive(true);
		this.update(competition);
		return Result.success();
	}

	public Result cancel(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if (competition.isActive() == false) {
			return new Result("error", "该比赛已经是注销状态");
		}
		competition.setActive(false);
		this.update(competition);
		return Result.success();
	}

	public JsonEasyUI<VoCompetition> getDate(CompetitionForm dataForm) {
		JsonEasyUI<VoCompetition> result = new JsonEasyUI<VoCompetition>();
		QueryResult<Competition> qr = this.getScrollDate(Competition.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoCompetition> competition_l = new ArrayList<VoCompetition>();
		for (Competition competition : qr.getResultList()) {
			competition_l.add(new VoCompetition(competition));
		}
		result.setRows(competition_l);
		return result;	
	}
	
	
	
	public List<Object> getContests(CompetitionForm dataForm){
		/*List<VoCompetition> competition_l = new ArrayList<VoCompetition>();
		
		List<Competition> contests = null;		
		contests = this.findAll(Competition.class, "1=1", new String[]{});
		for (Competition competition : contests) {
			competition_l.add(new VoCompetition(competition));
		}
		return competition_l;*/
		String whereSQL = null;
		Object[] whereParams = null;
		QueryResult<Competition> qr = this.getScrollDate(Competition.class, whereSQL, whereParams, null, null, dataForm.getPage(), 10, "createTime", "desc");
		List<VoCompetition> competition_l = new ArrayList<VoCompetition>();
		for (Competition competition : qr.getResultList()) {
			competition_l.add(new VoCompetition(competition));
		}
		long totalPage = 0;
		long totalRecord = qr.getTotalrecord();
		totalPage = totalRecord/10;
		if(totalPage % 10 != 0){
			totalPage = totalRecord/10+1;
		}
		List<Object> result = new ArrayList<Object>();
		result.add(competition_l);
		result.add(totalRecord);
		result.add(totalPage);
		result.add(dataForm.getPage());
		return result;
	}

	/**
	 * 检测比赛名是否存在
	 * @param username
	 * @return
	 */
	private boolean checkContentsnameExist(String name) {
		String whereSQL = "name=?";
		Object[] whereParams = {name};
		long count = this.getDataCount(Competition.class, whereSQL, whereParams);
		return count > 0;
	}	
	/**
	 * 检测比赛名是否存在
	 * @param username
	 * @return
	 */
	private boolean checkContentsnameExist(String name, Competition competition) {
		String whereSQL = "name=? and id!=?";
		Object[] whereParams = {name, competition.getId()};
		long count = this.getDataCount(Competition.class, whereSQL, whereParams);
		return count > 0;
	}

	

	public VoCompetition getCompetitionById(CompetitionForm dataForm) {
		String id = dataForm.getId();
		Competition competition = findById(Competition.class, id);
		if (competition == null) {
			return null;
		}
		
		VoCompetition result = new VoCompetition(competition);
		
		return result;
	}


	public Result startApply(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if ("Start".equals(competition.getRegisterContest())) {
			return new Result("error", "该比赛报名已经开始");
		}
		if ("Ended".equals(competition.getStatus())){
			return new Result("error", "该比赛已经结束");
		}
		competition.setRegisterContest("Start");
		this.update(competition);
		return Result.success();
	}

	public Result endApply(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if ("Ended".equals(competition.getRegisterContest())) {
			return new Result("error", "该比赛报名已经结束");
		}
		competition.setRegisterContest("Ended");
		this.update(competition);
		return Result.success();
	}

	public Result startContest(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if ("Running".equals(competition.getStatus())) {
			return new Result("error", "该比赛已经开始");
		}
		competition.setRegisterContest("Ended");
		competition.setStatus("Running");
		this.update(competition);
		return Result.success();
	}

	public Result endContest(CompetitionForm dataForm) {
		Competition competition = this.findById(Competition.class, dataForm.getId());
		if ("Ended".equals(competition.getStatus())) {
			return new Result("error", "该比赛已经结束");
		}
		competition.setRegisterContest("Ended");
		competition.setStatus("Ended");
		this.update(competition);
		return Result.success();
	}


	

}
