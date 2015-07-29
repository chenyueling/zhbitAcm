package com.zhbitacm.news.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.news.formbean.ArticleForm;
import com.zhbitacm.news.service.ArticleService;
import com.zhbitacm.news.vo.VoArticle;


@Controller("articleAction")
@Scope("prototype")
public class ArticleAction extends ActionSupport implements
		ModelDriven<ArticleForm> {

	
	private static final long serialVersionUID = 1L;
	private ArticleForm dataForm = new ArticleForm();
	@Resource
	private ArticleService articleService;
	public void save() {                               //超级管理员的行为默认使用 原有规范方法  入 sava() list() ...
		Result result = null;
		try {
			result = articleService.saveArticle(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}

	
	public void list() throws IOException {
		JsonEasyUI<VoArticle> result = null;
		try {
			result = articleService.getData(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpServletResponse response = ServletActionContext.getResponse();    
			response.setCharacterEncoding("UTF-8");    
	        PrintWriter out = response.getWriter();
	        //直接输入响应的内容
	        JSONObject jsonObject = JSONObject.fromObject(result);
			System.out.println(jsonObject.toString());
	        out.print(jsonObject.toString());       
	        out.flush();    
	        out.close();
		}
	}
	
	
	
	public void active() {
		Result result = null;
		try {
			result = articleService.active(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void cancel() {
		Result result = null;
		try {
			result = articleService.cancel(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	
	//文章修改
	public void update() {
		Result result = null;
		try {
			result = articleService.updateArticle(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	//通过id获取文章
	public String editById(){
		VoArticle result = articleService.getArticleById(dataForm);
		HttpServletRequest request = ServletActionContext.getRequest();    
		request.setAttribute("article", result);
		return "editPage";
	}
	
	
	public void delete(){
		Result result = null;
		try {
			result = articleService.deleteArticle(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public String articleList(){
		List<Object> list = articleService.getArticleList(dataForm);
		HttpServletRequest request = ServletActionContext.getRequest();  
		request.setAttribute("voArticles", list.get(0));
		request.setAttribute("totalRecords",list.get(1));
		request.setAttribute("totalPage",list.get(2));
		request.setAttribute("pageNo",list.get(3));
		return "news_list";
	}
	
	
	public ArticleForm getModel() {
		return dataForm;
	}
}
