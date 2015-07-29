package com.zhbitacm.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.exception.DaoException;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.user.entity.School;
import com.zhbitacm.user.formbean.SchoolForm;
import com.zhbitacm.user.vo.VoSchool;


@Service("schoolService")
public class SchoolServiceImpl extends DaoSupport implements SchoolService {

	public Result addCollege(SchoolForm dataForm) throws DaoException {
		return null;
	}

	public Result saveSchool(SchoolForm dataForm) throws DaoException {
		String name = dataForm.getName();
		boolean isSchoolnameExist = this.checkSchoolnameExist(name);
		if (isSchoolnameExist == true) {
			return new Result("error", "学校名已经存在");
		}
		String address = dataForm.getAddress();
		String mark = dataForm.getMark();
		School school = new School();
		school.setId(UUID.randomUUID().toString());
		school.setName(name);
		school.setAddress(address);
		school.setMark(mark);
		school.setCreateTime(new Date());
		school.setEditTime(new Date());
		school.setActive(true);
		this.update(school);
		
		return Result.success();	
	}



	public Result updateSchool(SchoolForm dataForm) throws DaoException {
		School school = this.findById(School.class, dataForm.getId());
		String name = dataForm.getName();
		String address = dataForm.getAddress();
		String mark = dataForm.getMark();
		if(name.trim().equals("")){
			return new Result("error", "学校名不能为空");
		}
		boolean isSchoolnameExist = this.checkSchoolnameExist(name, school);
		if (isSchoolnameExist == true) {
			return new Result("error", "学校名已经存在");
		}
		if(null != name && !name.trim().equals("")){
			school.setName(name);
		}
		if(null != address && !address.trim().equals("")){
			school.setAddress(address);
		}
		school.setMark(mark);
		school.setEditTime(new Date());
		this.update(school);
		
		return Result.success();	
	}


	public Result deleteSchool(SchoolForm dataForm) throws DaoException {

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
			List<School> list = this.findAll(School.class, whereSQL, id);

			for (School school : list) {
				this.delete(school);
			}
		} else {
			return new Result("error", "学校对象不存在");
		}
		return Result.success();
	}
	public Result active(SchoolForm dataForm) {
		School school = this.findById(School.class, dataForm.getId());
		if (school.isActive() == true) {
			return new Result("error", "该学校已经是激活状态");
		}
		school.setActive(true);
		this.update(school);
		return Result.success();
	}

	public Result cancel(SchoolForm dataForm) {
		School school = this.findById(School.class, dataForm.getId());
		if (school.isActive() == false) {
			return new Result("error", "该学校已经是注销状态");
		}
		school.setActive(false);
		this.update(school);
		return Result.success();
	}

	public JsonEasyUI<VoSchool> getDate(SchoolForm dataForm) {
		JsonEasyUI<VoSchool> result = new JsonEasyUI<VoSchool>();
		QueryResult<School> qr = this.getScrollDate(School.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoSchool> school_l = new ArrayList<VoSchool>();
		for (School school : qr.getResultList()) {
			school_l.add(new VoSchool(school));
		}
		result.setRows(school_l);
		return result;
	}
	
	/**
	 * 检测学校名是否存在
	 * @param username
	 * @return
	 */
	private boolean checkSchoolnameExist(String name) {
		String whereSQL = "name=?";
		Object[] whereParams = {name};
		long count = this.getDataCount(School.class, whereSQL, whereParams);
		return count > 0;
	}	
	/**
	 * 检测学校名是否存在
	 * @param username
	 * @return
	 */
	private boolean checkSchoolnameExist(String name, School school) {
		String whereSQL = "name=? and id!=?";
		Object[] whereParams = {name, school.getId()};
		long count = this.getDataCount(School.class, whereSQL, whereParams);
		return count > 0;
	}


}
