package com.zhbitacm.user.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.xinhuo.dao.DaoSupport;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.entity.CompetitionGroup;
import com.zhbitacm.user.entity.Competitioner;
import com.zhbitacm.user.entity.Level;
import com.zhbitacm.user.entity.School;
import com.zhbitacm.user.entity.User;
import com.zhbitacm.user.formbean.UserForm;
import com.zhbitacm.user.vo.VoUser;
import com.zhbitacm.util.HttpSessionUtil;


@Service("userService")
public class UserServiceImpl extends DaoSupport implements UserService{

	public Result userRegister(UserForm dataForm) throws Exception {
		String username = dataForm.getUsername();
		System.out.println(username);
		boolean isUsernameExist = this.checkUsernameExist(username);
		if(isUsernameExist){
			return new Result("error", "用户名已存在");
		}
		String password = dataForm.getPassword();
		String password2 = dataForm.getPassword2();
		if(!password.equals(password2)){
			return new Result("error", "两次输入的密码不一致");
		}
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setNickname(dataForm.getNickname());
		user.setPassword(dataForm.getPassword());
		user.setEmail(dataForm.getEmail());
		user.setCreateTime(new Date());
		user.setEditTime(new Date());
		user.setActive(true);
		HttpSessionUtil.putUser(user.getId(), user.getUsername(), user.getLastLoginTime());
	    this.save(user);
		return Result.success();
	}

	public Result userLogin(UserForm dataForm) throws Exception {
//		boolean isValidata = ValidataCode.verification(dataForm.getValidataCode());
//		if(isValidata == false){
//			return Result.validataCodeError();
//		}
		String username = dataForm.getUsername();
		String password = dataForm.getPassword();
//		String password = RSAUtil.decrypt(dataForm.getPassword());
//		password = DesUtil.encryptDES(password);
		String whereSQL = "username=? and password=?";
		Object[] whereParams = {username, password};
		User user = this.find(User.class, whereSQL, whereParams);
		if(user == null){
			return Result.passwordError();
		}
		if(!user.isActive()){
			return new Result("error", "您的帐号已被注销,如有疑问, 请联系管理员");
		}
		HttpSessionUtil.putUser(user.getId(), user.getUsername(), user.getLastLoginTime());
		
		user.setLastLoginTime(new Date());
		this.update(user);
		
		return new Result("success", "登录成功");
	}
	
	public void getUserInfo() throws Exception {
		String userId = HttpSessionUtil.getUser();
		User user = this.findById(User.class, userId);
		List<Competitioner> competitionerList = this.findAll(Competitioner.class, "userId=?", new String[]{userId});
		List<Competition> competitionList = new ArrayList<Competition>();
		List<CompetitionGroup> competitionGroupList = new ArrayList<CompetitionGroup>();
		if(!competitionerList.isEmpty()){
			for(Competitioner competitioner : competitionerList){
				Competition competition = competitioner.getCompetition();
				//Date time = competition.getStartTime().toLocaleString();
	//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//			String nowTime = simpleDateFormat.format(time);
	//			competition.setStartTime(startTime)
				if(competition != null){
					System.out.println(competition.getStartTime());
					competitionList.add(competition);
					CompetitionGroup competitionGroup = this.find(CompetitionGroup.class, "competition=? and keyCode=?", new Object[]{competition,competitioner.getKeyCode()});
					System.out.println(competitionGroup.getRewards());
					competitionGroupList.add(competitionGroup);
				}
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null != user && null != user.getSchool()){
			request.setAttribute("school", user.getSchool().getName());
		}
		request.setAttribute("user", user);
		request.setAttribute("competitionList", competitionList);
		request.setAttribute("competitionGroupList", competitionGroupList);
	}
	
	public void beforeUpdate() throws Exception{
		System.out.println("++:"+HttpSessionUtil.getString(HttpSessionUtil.user_id));
		System.out.println("userId");
		String userId = HttpSessionUtil.getUser();
		System.out.println(userId);
		User user = this.findById(User.class, userId);
		System.out.println("userid"+user.getId());
		//List<School> schoolList = this.findAll(School.class, null, null);
		List<School> schoolList = this.findAll(School.class, "1=1", new Object[]{});
//		QueryResult<School> rs = this.getScrollDate(School.class, null, null, null,null, 10, 10, "createTime", "desc");
//		List<School> schoolList = rs.getResultList();

		if(schoolList.isEmpty()){
			schoolList = new ArrayList<School>();
		}
System.out.println("school size:"+schoolList.size());
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null != user && null != user.getSchool()){
			request.setAttribute("school", user.getSchool().getName());
			request.setAttribute("schoolmark", user.getSchool().getMark());
			System.out.println(user.getSchool().getName());
		}
		request.setAttribute("user", user);
		request.setAttribute("schoollist", schoolList);
	}
	
	public Result userUpdate(UserForm dataForm) throws Exception {
		String userId = HttpSessionUtil.getUser();
		User user = this.findById(User.class, userId);
		School school = this.find(School.class, "mark=?", new String[]{dataForm.getSchool()});
		Date birthday = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		birthday = simpleDateFormat.parse(dataForm.getBirthday());
		user.setName(dataForm.getName());
		user.setSchool(school);
		user.setMajor(dataForm.getMajor());
		user.setGrade(dataForm.getGrade());
		user.setCollege(dataForm.getCollege());
		user.setStuId(dataForm.getStuId());
		user.setClazz(dataForm.getClazz());
		user.setBirthDay(birthday);
		user.setPhone(dataForm.getPhone());
		user.setQq(dataForm.getQq());
		user.setEmail(dataForm.getEmail());
		user.setNickname(dataForm.getNickname());
		user.setUserIntro(dataForm.getUserIntro());
		user.setBirthDay(birthday);
		user.setEditTime(new Date());
		this.update(user);
		return Result.success();
	}

	public Result userSave(UserForm dataForm) throws Exception {
		String username = dataForm.getUsername();
		boolean isUsernameExist = this.checkUsernameExist(username);
		if(isUsernameExist){
			return new Result("error", "用户名已存在");
		}
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setName(dataForm.getName());
		user.setPassword(dataForm.getPassword());
		user.setCreateTime(new Date());
		user.setEditTime(new Date());
		user.setActive(true);
		this.update(user);
		return Result.success();
	}

	public Result updatePassword(UserForm dataForm) throws Exception {
		String userId = HttpSessionUtil.getUser();
		User user = this.findById(User.class, userId);
		String password = dataForm.getPassword();
		String password2 = dataForm.getPassword2();
		System.out.println(user.getPassword());
		if (!user.getPassword().equals(dataForm.getOldPassword())) {
			return Result.passwordError();
		}
		if (!password.equals(password2)) {
			return new Result("error", "两次输入的密码不一致");
		}
		if (user.getPassword().equals(dataForm.getPassword())){
			return new Result("error", "新密码不能与旧密码相同");
		}
		user.setPassword(password);
		this.update(user);
		HttpSessionUtil.removeUser();
		return Result.success();
	}

	public Result deleteUser(UserForm dataForm) throws Exception {

		String idstr = dataForm.getId();
		if (idstr != null && idstr.length() > 0) {
			String whereSQL = "";
			String[] id = idstr.split(";");
			boolean isFirst = true;
			for (int i = 0; i < id.length; i++) {
				if (isFirst) {
					whereSQL = "id=?";
					isFirst = false;
				} else {
					whereSQL += " or id=?";
				}
			}
			List<User> list = this.findAll(User.class, whereSQL, id);

			for (User user : list) {
				this.delete(user);
			}
		} else {
			return new Result("error", "该用户不存在");
		}
		return Result.success();
	}
	
	public Result active(UserForm dataForm) {
		User user = this.findById(User.class, dataForm.getId());
		if(user.isActive()){
			return new Result("error", "该用户已经是激活状态");
		}
		user.setActive(true);
		this.update(user);
		return Result.success();
	}

	public Result cancel(UserForm dataForm) {
		User user = this.findById(User.class, dataForm.getId());
		System.out.println(dataForm.getId()+"*****************");
		if(!user.isActive()){
			return new Result("error", "该用户已经是注销状态");
		}
		user.setActive(false);
		this.update(user);
		return Result.success();
	}

	public JsonEasyUI<VoUser> getDate(UserForm dataForm) {
		JsonEasyUI<VoUser> result = new JsonEasyUI<VoUser>();
		QueryResult<User> qr = this.getScrollDate(User.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoUser> user_l = new ArrayList<VoUser>();
		for(User user : qr.getResultList()){
			user_l.add(new VoUser(user));
		}
		result.setRows(user_l);
		
		List<Level> level_l = null;
		level_l = this.findAll(Level.class, "1=1", new Object[] {});
	/*	HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("levellist", level_l);*/
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("levellist", level_l);
		return result; 
	}

	
	/**
	 * 检测用户名是否存在
	 */
	public boolean checkUsernameExist(String username){
		String whereSQL = "username=?";
		Object[] whereParams = {username};
		long count = this.getDataCount(User.class, whereSQL, whereParams);
		return count > 0;
	}

	public Result addLevel(UserForm dataForm) throws Exception {
		// TODO Auto-generated method stub
		if(null != dataForm.getLevelmark() && dataForm.getLevelmark().equals("null")){
			return new Result("error", "您没有做任何修改");
		}
		
		User user = this.findById(User.class, dataForm.getId());
		
		Level level = null;
		if(null != dataForm.getLevelmark() && !dataForm.getLevelmark().equals("null")){
			String whereSQL = "mark=?";
			Object[] whereParams = {dataForm.getLevelmark()};
			level = this.find(Level.class, whereSQL, whereParams);
			user.setLevel(level);
			user.setLevelId(level.getId());
		}
		user.setEditTime(new Date());
		return Result.success();
	}


}
