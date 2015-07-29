package com.xinhuo.vo;

public class Result {
	private String data;
	private String result;
	
	public Result() {
	}
	public Result(String result, String data) {
		super();
		this.data = data;
		this.result = result;
	}
	
	public static Result objectNotFound() {
		Result result = new Result();
		result.data = "未找到该对象";
		result.result = "error";
		return result;
	}
	
	public static Result success() {
		Result result = new Result();
		result.data = "操作成功";
		result.result = "success";
		return result;
	}
	
	public static Result validataCodeError() {
		Result result = new Result();
		result.data = "您输入的验证码错误";
		result.result = "error";
		return result;
	}
	
	public static Result passwordError() {
		Result result = new Result();
		result.data = "您输入的密码错误";
		result.result = "error";
		return result;
	}
	
	public static Result unknownError() {
		Result result = new Result();
		result.data = "未知错误,请联系管理员";
		result.result = "error";
		return result;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
