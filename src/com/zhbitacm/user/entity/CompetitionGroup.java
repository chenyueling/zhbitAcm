package com.zhbitacm.user.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 参赛分组
 * 
 * @author chenyueling
 * 
 */
@Entity
@Table(name = "tb_user_competitiongroup")
public class CompetitionGroup {
	@Id
	@Column(length = 36)
	private String id;
	private Date createTime;
	private Date editTime;
	//队伍名
	@Column(length = 100)
	private String name;
	//英文名
	@Column(length = 100)
	private String engName;
	// 审核状态
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 2)
	private GroupStatus status;

	// 获奖情况
	private String rewards;
	// 组队码
	private String keyCode;

	//队长
	@OneToOne(targetEntity = Competitioner.class)
	private Competitioner captain;
	
	//比赛名
	@Column(length = 100)
	private String contestname;
	
	private boolean isActive;
	
	// 属于哪次比赛
	@ManyToOne(targetEntity = Competition.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Competition competition;
	//参赛人员
	@OneToMany(targetEntity = Competitioner.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Competitioner> competitioners;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public GroupStatus getStatus() {
		return status;
	}

	public void setStatus(GroupStatus status) {
		this.status = status;
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

	public Competitioner getCaptain() {
		return captain;
	}

	public void setCaptain(Competitioner captain) {
		this.captain = captain;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.contestname = competition.getName();
		this.competition = competition;
	}

	public List<Competitioner> getCompetitioners() {
		return competitioners;
	}

	public void setCompetitioners(List<Competitioner> competitioners) {
		this.competitioners = competitioners;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getEngName() {
		return engName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setContestname(String contestname) {
		this.contestname = contestname;
	}

	public String getContestname() {
		return contestname;
	}
	
}
