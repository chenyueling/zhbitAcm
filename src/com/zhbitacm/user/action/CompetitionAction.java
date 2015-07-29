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
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.formbean.CompetitionForm;
import com.zhbitacm.user.service.CompetitionService;
import com.zhbitacm.user.vo.VoCompetition;

/**
 * @author liaoruihua
 * 
 */

@Controller("competitionAction")
@Scope("prototype")
public class CompetitionAction extends ActionSupport implements
		ModelDriven<CompetitionForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8821534293793500317L;
	private CompetitionForm dataForm = new CompetitionForm();
	@Resource
	private CompetitionService competitionService = null;

	public void save() {
		Result result = null;
		try {
			result = competitionService.saveCompetition(dataForm);
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
			result = competitionService.cancel(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void active() {
		Result result = null;
		try {
			result = competitionService.active(dataForm);
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
			result = competitionService.updateCompetition(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	// 通过id获取比赛
	public String editById() {
		VoCompetition result = competitionService.getCompetitionById(dataForm);
System.out.println(result.getId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("competition", result);
		return "editPage";
	}

	public String getContests() {
		List<Object> result = null;

		result = competitionService.getContests(dataForm);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("contests", result.get(0));
		request.setAttribute("totalRecord", result.get(1));
		request.setAttribute("totalPage", result.get(2));
		request.setAttribute("pageNo", result.get(3));
		return "contests";
	}

	public void delete() {
		Result result = null;
		try {
			result = competitionService.deleteCompetition(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void startApply() {
		Result result = null;
		try {
			result = competitionService.startApply(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void endApply() {
		Result result = null;
		try {
			result = competitionService.endApply(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void startContest() {
		Result result = null;
		try {
			result = competitionService.startContest(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void endContest() {
		Result result = null;
		try {
			result = competitionService.endContest(dataForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JsonUtil.output(result);
		}
	}

	public void list() throws IOException {
		JsonEasyUI<VoCompetition> result = null;
		try {
			result = competitionService.getDate(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// 直接输入响应的内容
			JSONObject jsonObject = JSONObject.fromObject(result);
			System.out.println(jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}
	}

	public CompetitionForm getModel() {
		// TODO Auto-generated method stub
		return dataForm;
	}

}
