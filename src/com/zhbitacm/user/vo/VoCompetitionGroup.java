package com.zhbitacm.user.vo;

import com.xinhuo.util.DateUtil;
import com.zhbitacm.user.entity.CompetitionGroup;
import com.zhbitacm.user.entity.Competitioner;

public class VoCompetitionGroup {

	private String id;
	private String name;
	private String engName;
	private String rewards;
	private String keyCode;
	private String createTime;
	private String editTime;
	private String status;
	// 属于哪次比赛
	private String contestname;
	//参赛人员
	private String[] competitioners;
	private String captain;
	private String player_1;
	private String player_2;
	private String isActive;
	
	private String start;
	private String end;
	private String date;
	private String maxPlayer;
	private String address;
	
	public VoCompetitionGroup(CompetitionGroup competitionGroup){
		this.id = competitionGroup.getId();
		this.name = competitionGroup.getName();
		this.engName = competitionGroup.getEngName();
		this.rewards = competitionGroup.getRewards();
		this.createTime = DateUtil.dataToString(competitionGroup.getCreateTime());
		this.editTime = DateUtil.dataToString(competitionGroup.getEditTime());
		this.keyCode = competitionGroup.getKeyCode();
		this.contestname = competitionGroup.getContestname();
		this.competitioners = getAllCompetitioner(competitionGroup);
		this.status = competitionGroup.getStatus().getDescribe();
		this.setIsActive(competitionGroup.isActive()? "激活" : "注销");
	}
	
	private String[] getAllCompetitioner(CompetitionGroup competitionGroup){
		String[] compe = new String[3];
		int i=0;
		for(Competitioner com : competitionGroup.getCompetitioners()){
			compe[i] = com.getName();
			switch(i){
			case 0:this.captain = compe[i];
				break;
			case 1:this.player_1 = compe[i];
				break;
			case 2:this.player_2 = compe[i];
				break;
			default:break;
			}
			i++;
		}
System.out.println(competitionGroup.getCompetitioners().size());
		return compe;
	}
	public String getContestname() {
		return contestname;
	}

	public void setContestname(String contestname) {
		this.contestname = contestname;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public String getPlayer_1() {
		return player_1;
	}

	public void setPlayer_1(String player_1) {
		this.player_1 = player_1;
	}

	public String getPlayer_2() {
		return player_2;
	}

	public void setPlayer_2(String player_2) {
		this.player_2 = player_2;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(String maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String[] getCompetitioners() {
		return competitioners;
	}

	public void setCompetitioners(String[] competitioners) {
		this.competitioners = competitioners;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getEngName() {
		return engName;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditTime() {
		return editTime;
	}


}
