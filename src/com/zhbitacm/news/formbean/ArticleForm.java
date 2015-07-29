package com.zhbitacm.news.formbean;

import java.io.File;

import com.xinhuo.formbean.BaseForm;


public class ArticleForm extends BaseForm{
	private String id;
	private String author;        //文章作者
	private String content;      //正文
	private String smallTitle;  //小标题
	private String source;    //文章来源
	private String title;     //标题
	private String AssociationType;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private String[] articleImage;
	
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
	public String getAssociationType() {
		return AssociationType;
	}
	public void setAssociationType(String associationType) {
		AssociationType = associationType;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String[] getArticleImage() {
		return articleImage;
	}
	public void setArticleImage(String[] articleImage) {
		this.articleImage = articleImage;
	}

	
	
}
