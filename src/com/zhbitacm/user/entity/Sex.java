package com.zhbitacm.user.entity;


public enum Sex {
	MAN("男"),
	WOMAN("女"),
	SECRET("保密");
	private String describe;

	private Sex(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return describe;
	}
	
	public static Sex getSex(String describe) {
		if ("MAN".equals(describe) || "男".equals(describe)) {
			return Sex.MAN;
		} else if ("WOMAN".equals(describe) || "女".equals(describe)) {
			return Sex.WOMAN;
		} else if ("SECRET".equals(describe) || "保密".equals(describe)) {
			return Sex.SECRET;
		} else{
			return Sex.SECRET;
		}
		
	}
}
