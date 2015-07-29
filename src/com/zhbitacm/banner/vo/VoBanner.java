package com.zhbitacm.banner.vo;

import com.xinhuo.util.FileUtil;
import com.zhbitacm.banner.entity.Banner;

public class VoBanner {
	private String id;
	private String title;
	private String url;
	private String info;
	private String imgUrl;
	private long index;
	public VoBanner() {	
	}
	public VoBanner(Banner banner) {
		this.id = banner.getId();
		this.title = banner.getTitle();
		this.url = banner.getUrl();
		this.info = banner.getInfo();
		this.imgUrl = FileUtil.rootPath + "/" + "banner" + "/" + banner.getImgUrl();
		this.index = banner.getSort();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}
}
