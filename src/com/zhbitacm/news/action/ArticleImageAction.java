package com.zhbitacm.news.action;



import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonUeditor;
import com.zhbitacm.news.formbean.ArticleImageForm;
import com.zhbitacm.news.service.ArticleImageService;

@Controller("articleImageAction")
@Scope("prototype")
public class ArticleImageAction extends ActionSupport implements ModelDriven<ArticleImageForm> {
	private static final long serialVersionUID = 1L;
	private ArticleImageForm dataForm = new ArticleImageForm();
	@Resource
	private ArticleImageService articleImageService = null; 
	//超级管理员保存文章图片,获取文章图片的分发方法
	public void save() {
		JsonUeditor result = null;
		try {
			result = articleImageService.saveImage(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void list() throws IOException {
		String result = null;
		try {
			result = articleImageService.getData();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		}
	}
	
	
	//普通管理员保存文章图片,获取文章图片的分发方法
	public void ordinarySave() {
		JsonUeditor result = null;
		try {
			result = articleImageService.ordinarySaveImage(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	//普通管理员保存文章图片,获取文章图片的分发方法
	public void ordinaryList() throws IOException {
		String result = null;
		try {
			result = articleImageService.ordinaryGetData();
			System.out.println("k:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		}
	}
	
	public ArticleImageForm getModel() {
		return dataForm;
	}

}
