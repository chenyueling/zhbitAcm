package com.zhbitacm.news.entity;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhbitacm.admin.entity.OrdinaryAdmin;

@Entity
@Table(name="tb_news_article")
public class Article {
	@Id
	@Column(length = 36, name = "id")
	private String id;
	@Column(length = 20)
	private String author;        //文章作者
	@Column(length = 10000)
	private String content;      //正文
	@Column(length = 50)
	private  String smallTitle;  //小标题
	@Column(length = 50)
	private String source;    //文章来源
	@Column(length = 50)
	private String title;     //标题
	@Column(length = 36)
	private String publisherId;      //发布者
	@ManyToOne(targetEntity=OrdinaryAdmin.class,fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	private OrdinaryAdmin publisher;
	private Date createTime;    // 发布时间
	private Date editTime;     //修改时间
	private boolean isActive;    //文章状态
	//修改者
	@ManyToOne(targetEntity=OrdinaryAdmin.class,fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	private OrdinaryAdmin editer;
	@Column(length = 36)
	private String editerId; 
	//文章图片Id
	@OneToMany(targetEntity=ArticleImage.class,fetch=FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name="articleId")
	private List<ArticleImage> articleImages;
	//文章封面图片
	@Column(length = 100)
	private String imgUrl;
	private int pageView;
	private String staticPage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSmallTitle() {
		return smallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		String endStr = String.format("%04d", new Random().nextInt(10000));
		this.staticPage = createTime.getTime() + endStr + ".html";
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public OrdinaryAdmin getPublisher() {
		return publisher;
	}
	public void setPublisher(OrdinaryAdmin publisher) {
		this.publisherId = publisher.getId();
		this.publisher = publisher;
	}
	public OrdinaryAdmin getEditer() {
		return editer;
	}
	public void setEditer(OrdinaryAdmin editer) {
		this.editerId = editer.getId();
		this.editer = editer;
	}
	public String getEditerId() {
		return editerId;
	}
	public void setEditerId(String editerId) {
		this.editerId = editerId;
	}
	public List<ArticleImage> getArticleImages() {
		return articleImages;
	}
	public void setArticleImages(List<ArticleImage> articleImages) {
		this.articleImages = articleImages;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getPageView() {
		return pageView;
	}
	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
	public String getStaticPage() {
		return staticPage;
	}
	public void setStaticPage(String staticPage) {
		this.staticPage = staticPage;
	}
}
