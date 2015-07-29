package com.zhbitacm.news.vo;

import com.xinhuo.util.DateUtil;
import com.xinhuo.util.FileUtil;
import com.zhbitacm.news.entity.Article;

public class VoArticle {
	
	public VoArticle(){}
	public VoArticle(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.author = article.getAuthor();
		this.smallTitle = article.getSmallTitle();
		this.source = article.getSource();
		this.content = article.getContent(); 
		this.imageUrl = FileUtil.rootPath +"/articleCover/"+article.getImgUrl();
		this.publishTime = DateUtil.dataToString(article.getCreateTime());
		this.pageView = article.getPageView()+"";
		this.staticPage = "public/"+article.getStaticPage();
	}
	
	
	public VoArticle(Article article,boolean forList) {
		
		this.id = article.getId();
		this.title = article.getTitle();
		this.author = article.getAuthor();
		this.smallTitle = article.getSmallTitle();
		this.publisher = article.getPublisher().getRealname();
		this.publishTime = DateUtil.dataToString(article.getCreateTime());
		this.editer = article.getEditer() == null?"无":article.getEditer().getRealname();
		this.editTime = article.getEditTime() == null?"没有修改过":DateUtil.dataToString(article.getEditTime());
		this.pageView = article.getPageView()+"";
		this.status = article.isActive()?"激活":"注销";
		this.imageUrl = FileUtil.rootPath +"/articleCover/"+article.getImgUrl();
		this.staticPage = "public/"+article.getStaticPage();
	}
	
	
	private String id;
	private String title;
	private String author;
	private String smallTitle;
	private String publisher;
	private String publishTime;
	private String editer;
	private String editTime;
	private String pageView;
	private String status;
	private String source;
	private String content;
	private String imageUrl;
	private String staticPage;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSmallTitle() {
		return smallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getEditer() {
		return editer;
	}
	public void setEditer(String editer) {
		this.editer = editer;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getPageView() {
		return pageView;
	}
	public void setPageView(String pageView) {
		this.pageView = pageView;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getStaticPage() {
		return staticPage;
	}
	public void setStaticPage(String staticPage) {
		this.staticPage = staticPage;
	}
	
	

}
