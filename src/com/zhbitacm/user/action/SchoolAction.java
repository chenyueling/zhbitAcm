/**
 * 
 */
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
import com.zhbitacm.user.formbean.SchoolForm;
import com.zhbitacm.user.service.SchoolService;
import com.zhbitacm.user.vo.VoSchool;

/**
 * @author liaoruihua
 * 
 */

@Controller("schoolAction")
@Scope("prototype")
public class SchoolAction extends ActionSupport implements
		ModelDriven<SchoolForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4002305558810349947L;
	SchoolForm dataForm = new SchoolForm();
	@Resource
	SchoolService schoolService = null;

	public void save() {
		Result result = null;
		try {
			result = schoolService.saveSchool(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void cancel() {
		Result result = null;
		try {
			result = schoolService.cancel(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void active(){
		Result result = null;
		try {
			result = schoolService.active(dataForm);
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
			result = schoolService.updateSchool(dataForm);
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
			result = schoolService.deleteSchool(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void list() throws IOException{
		JsonEasyUI<VoSchool> result = null;
		try {
			result = schoolService.getDate(dataForm);
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
	
	public SchoolForm getModel() {
		return dataForm;
	}
}
