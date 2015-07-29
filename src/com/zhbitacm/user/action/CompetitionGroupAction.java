/**
 * 
 */
package com.zhbitacm.user.action;

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
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.User;
import com.zhbitacm.user.formbean.CompetitionGroupForm;
import com.zhbitacm.user.service.CompetitionGroupService;
import com.zhbitacm.user.service.CompetitionerService;
import com.zhbitacm.user.service.UserService;
import com.zhbitacm.user.vo.VoCompetitionGroup;
import com.zhbitacm.util.HttpSessionUtil;

/**
 * @author liaoruihua
 *
 */
@Controller("competitionGroupAction")
@Scope("prototype")
public class CompetitionGroupAction extends ActionSupport implements ModelDriven<CompetitionGroupForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1181837882605441966L;

	private CompetitionGroupForm dataForm = new CompetitionGroupForm();
	@Resource
	private CompetitionGroupService competitionGroupService = null;
	@Resource
	private CompetitionerService competitionerService = null;
	@Resource
	private UserService userService = null;

	public void save() {
		Result result = null;
		try {
			result = competitionGroupService.saveCompetitionGroup(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void saveTeam(){
		Result result = null;
		try {
			result = competitionGroupService.saveCompetitionGroup(dataForm);
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
			result = competitionGroupService.deleteCompetitionGroup(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void addCompetitioner(){
		Result result = null;
		try {
			result = competitionGroupService.addCompetitioner(dataForm);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JsonUtil.output(result);
		}
	}

	public String getVoGroup(){
		HttpServletRequest request = ServletActionContext.getRequest();
		VoCompetitionGroup group = null;
		String keyCode = null;
		keyCode = (String) request.getAttribute("keyCode");
	
		dataForm.setKeyCode(keyCode);
		
		try {
			group = competitionGroupService.getVoGroup(dataForm);
			
			request.setAttribute("voCompetitionGroup", group);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public void updateGroup() {
		Result result = null;
		try {
			result = competitionGroupService.updateCompetitionGroup(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void list() throws IOException{
		JsonEasyUI<VoCompetitionGroup> result = null;
		try {
			result = competitionGroupService.getDate(dataForm);
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
	public String getGroups() {
		List<VoCompetitionGroup> result = null;
		System.out.println(dataForm.getContestname());
		result = competitionGroupService.getGroups(dataForm);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("groups", result);
		return "groups";
	}

	public String createTeam(){
		
		setAttribute();
		return "createTeam";
	}
	
	public String joinTeam(){
		setAttribute();
		return "joinTeam";
	}

	private void setAttribute() {
		String userId = HttpSessionUtil.getUser();
		User user = userService.findById(User.class, userId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("competitionId", dataForm.getCompetitionId());
		request.setAttribute("username", user.getUsername());
		request.setAttribute("phone", user.getPhone());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("stuId", user.getStuId());
		request.setAttribute("userId", user.getId());
	}
	
	
	public void saveCompetitioner(){
		Result result = null;
		try {
			result = competitionerService.saveCompetitioner(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void updateCompetitioner(){
		Result result = null;
		try {
			result = competitionerService.updateCompetitioner(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	public void editStatus(){
		Result result = null;
		try {
			result = competitionGroupService.editStatus(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void addRewards(){
		Result result = null;
		try {
			result = competitionGroupService.addRewards(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public CompetitionGroupForm getModel() {
		// TODO Auto-generated method stub
		return dataForm;
	}

}
