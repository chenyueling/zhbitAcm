package com.zhbitacm.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
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
import com.zhbitacm.user.formbean.LevelForm;
import com.zhbitacm.user.service.LevelService;
import com.zhbitacm.user.vo.VoLevel;


@Controller("levelAction")
@Scope("prototype")
public class LevelAction extends ActionSupport implements
		ModelDriven<LevelForm>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2804408591067691275L;
	LevelForm dataForm = new LevelForm();
	@Resource
	LevelService levelService = null;

	public void save() {
		Result result = null;
System.out.println(dataForm.getTitle());

		try {
			result = levelService.saveLevel(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	
	
	public void update() {
		Result result = null;
		try {
			result = levelService.updateLevel(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void delete() {
		Result result = null;
		try {
			result = levelService.deleteLevel(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void list() throws IOException{
		JsonEasyUI<VoLevel> result = null;
		try {
			result = levelService.getDate(dataForm);
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
	
	
	public LevelForm getModel() {
		// TODO Auto-generated method stub
		return dataForm;
	}
	
}
