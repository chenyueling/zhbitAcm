package com.zhbitacm.user.vo;

import com.xinhuo.util.DateUtil;
import com.zhbitacm.user.entity.Competition;

public class VoCompetition {
	private String id;
	private String name;
	private String mark;
	private String info;
	private String type;
	private String maxPlayer;
	private String registerContest;
	private String address;
	private String createTime;
	private String editTime;
	private String startTime;
	private String endTime;
	private String start;

	private String end;
	private String status;
	private String date;
	private String isActive;
	
	public VoCompetition(Competition competition,boolean forIndex){
		this.id = competition.getId();
		this.createTime = DateUtil.dataToStringdata(competition.getCreateTime());
		this.name = competition.getName();
	}
	
	public VoCompetition(Competition competition){
		this.id = competition.getId();
		this.name = competition.getName();
		this.address = competition.getAddress();
		this.createTime = DateUtil.dataToString(competition.getCreateTime());
		this.startTime = DateUtil.dataToString(competition.getStartTime());
		this.endTime = DateUtil.dataToString(competition.getEndTime());
		this.info = competition.getInfo();
		this.editTime = (DateUtil.dataToString(competition.getEditTime()));
		this.date = (DateUtil.dataToStringdata(competition.getStartTime()));
		this.start = (DateUtil.dataToStringtime(competition.getStartTime()));
		this.end = (DateUtil.dataToStringtime(competition.getEndTime()));
		this.registerContest = competition.getRegisterContest();
		this.type = competition.getType();
		this.status = competition.getStatus();
		if(1 == competition.getMaxPlayer()){
			this.maxPlayer = "个人赛";
		}else{
			this.maxPlayer = "组队赛";
		}
		this.isActive = competition.isActive()? "激活" : "注销";
	}

	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegisterContest() {
		return registerContest;
	}

	public void setRegisterContest(String registerContest) {
		this.registerContest = registerContest;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMaxPlayer(String maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public String getMaxPlayer() {
		return maxPlayer;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
	
}
