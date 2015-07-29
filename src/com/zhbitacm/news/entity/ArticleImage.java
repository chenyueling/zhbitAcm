package com.zhbitacm.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_news_articleimage")
public class ArticleImage {	
	/** ID */
	@Id
	@Column(length=36,name="id")
	private String id;
	/** 图片描述 */
	@Column(length=100)
	private String info;
	/** 图片服务器路径 */
	@Column(length=50)
	private String path;
	/** 上传者Id */
	@Column(length=36)
	private String ownerId = "";
	/** 图片上传时间 */
	private Date createTime;
	@Column(length=36, name="articleIdtmp")
	private String articleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
}
