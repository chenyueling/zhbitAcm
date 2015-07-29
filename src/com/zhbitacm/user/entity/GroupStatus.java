package com.zhbitacm.user.entity;


public enum GroupStatus {
	CHECKING("审核中"),
	PASS("审核通过"),
	UNPASS("审核未通过"),
	FINISH("完成比赛"),
	ABORT("放弃比赛");
	
	private String describe;

	private GroupStatus(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return describe;
	}
	
	public static GroupStatus getGroupStatus(String describe) {
		if ("CHECKING".equals(describe) || "审核中".equals(describe)) {
			return GroupStatus.CHECKING;
		} else if ("PASS".equals(describe) || "通过审核".equals(describe)) {
			return GroupStatus.PASS;
		} else if ("UNPASS".equals(describe) || "审核未通过".equals(describe)) {
			return GroupStatus.UNPASS;
		}else if("FINISH".equals(describe) || "完成比赛".equals(describe)){
			return GroupStatus.FINISH;
		}else if("ABORT".equals(describe) || "放弃比赛".equals(describe)){
			return GroupStatus.ABORT;
		}else {
			return GroupStatus.CHECKING;
		}
		
	}
}
