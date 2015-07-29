package com.zhbitacm.news.service;



import java.io.FileNotFoundException;
import java.io.IOException;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.JsonUeditor;
import com.zhbitacm.news.formbean.ArticleImageForm;

public interface ArticleImageService extends Dao{
	//超级管理员保存文章图片,获取文章图片 
	public JsonUeditor saveImage(ArticleImageForm dataForm) throws FileNotFoundException, IOException;
	public String getData();
	
	//普通管理员保存文章图片,获取文章图片
	public JsonUeditor ordinarySaveImage(ArticleImageForm dataForm) throws FileNotFoundException, IOException;
	public String ordinaryGetData();
}
