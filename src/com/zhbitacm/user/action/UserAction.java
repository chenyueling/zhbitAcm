package com.zhbitacm.user.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.UserForm;
import com.zhbitacm.user.service.UserService;
import com.zhbitacm.user.vo.VoUser;
import com.zhbitacm.util.HttpSessionUtil;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<UserForm>{
	/**
	 *
	 */
	private static final long serialVersionUID = -8874570385880643342L;
	private UserForm dataForm = new UserForm();
	@Resource
	private UserService userService = null;
	
	public void register(){
		Result result = null;
		
		try {
			result = userService.userRegister(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void login(){
		Result result = null;
		try {
			System.out.println("11121312");
			result = userService.userLogin(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	//静态页面异步验证用户登录情况
	public void validateStatus(){
		Result result = null;
		try {
			String username = HttpSessionUtil.getString(HttpSessionUtil.user_username);
			result = new Result("success",username);
			//写入Cookie
			HttpServletResponse response = ServletActionContext.getResponse();
			Cookie cookie = null;
			if(username == null){
				 cookie = new Cookie("zhbitacm_username", null);
			}else{
			     cookie = new Cookie("zhbitacm_username", URLEncoder.encode(username,"utf-8"));
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public String logout(){
		HttpSessionUtil.removeUser();
		return "logout";
	}
	
	public void updatePassword(){
		Result result = null;
		
		try {
			result = userService.updatePassword(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public String getUserInfo(){
		try {
			System.out.println("userinfo");
			userService.getUserInfo();
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void save(){
		Result result = null;
		
		try {
			result = userService.userSave(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
		
	}
	
	public String beforeupdate(){
		try {
			System.out.println("before");
			userService.beforeUpdate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public void update(){
		Result result = null;
		System.out.println("update************");
		try {
			result = userService.userUpdate(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void addLevel(){
		Result result = null;
		System.out.println("addLevel************");
		try {
			result = userService.addLevel(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void delete(){
		Result result = null;
		try {
			result = userService.deleteUser(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void active(){
		Result result = null;
		try {
			result = userService.active(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void cancel(){
		Result result = null;
		try {
			result = userService.cancel(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.unknownError();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void list() throws IOException{
		JsonEasyUI<VoUser> result = null;
		try {
			result = userService.getDate(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
			
	}
	public UserForm getModel() {
		return dataForm;
	}

}
