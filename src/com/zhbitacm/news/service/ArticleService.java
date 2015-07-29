package com.zhbitacm.news.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.news.formbean.ArticleForm;
import com.zhbitacm.news.vo.VoArticle;

public interface ArticleService extends Dao{	
		public Result active(ArticleForm dataForm);
		public Result cancel(ArticleForm dataForm);
		public Result deleteArticle(ArticleForm dataForm);
		public Result updateArticle(ArticleForm dataForm) throws FileNotFoundException, IOException;
		public Result saveArticle(ArticleForm dataForm) throws FileNotFoundException, IOException;
		public JsonEasyUI<VoArticle> getData(ArticleForm dataForm);
		public VoArticle getArticleById(ArticleForm dataForm);
		public List<Object> getArticleList(ArticleForm dataForm);
		public String addPageView(ArticleForm dataForm);
		/**
		 * 获取文章列表数据
		 * @param dataForm
		 * @return
		 * @throws Exception
		 */
		public List<Object> showArticleList(ArticleForm dataForm);

}