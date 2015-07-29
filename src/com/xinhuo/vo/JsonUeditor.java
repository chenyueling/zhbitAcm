package com.xinhuo.vo;

public class JsonUeditor {
	private String url;
	private String state;
	private String title;
	private String id;
	public JsonUeditor() {
	}
	public JsonUeditor(String url, String state, String title) {
		super();
		this.url = url;
		this.state = state;
		this.title = title;
	}
	public JsonUeditor(String url, String state, String title, String id) {
		super();
		this.url = url;
		this.state = state;
		this.title = title;
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
