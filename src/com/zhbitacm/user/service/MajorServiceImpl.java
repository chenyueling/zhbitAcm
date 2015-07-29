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
import com.zhbitacm.user.entity.Major;
import com.zhbitacm.user.formbean.MajorForm;
import com.zhbitacm.user.vo.VoMajor;

@Service("majorService")
public class MajorServiceImpl extends DaoSupport implements MajorService {


	public Result saveMajor(MajorForm dataForm) throws DaoException {
		String name = dataForm.getName();
		boolean isMajornameExist = this.checkMajornameExist(name);
		if (isMajornameExist == true) {
			return new Result("error", "专业名已经存在");
		}
		Major major = new Major();
		major.setId(UUID.randomUUID().toString());
		major.setName(name);
		major.setCreateTime(new Date());
		major.setActive(true);
		this.update(major);
		
		return Result.success();	
	}

	public Result updateMajor(MajorForm dataForm) throws DaoException {
		Major major = this.findById(Major.class, dataForm.getId());
		String name = dataForm.getName();
		boolean isMajornameExist = this.checkMajornameExist(name, major);
		if (isMajornameExist == true) {
			return new Result("error", "专业名已经存在");
		}
		major.setName(name);
		this.update(major);
		return Result.success();
	}

	private boolean checkMajornameExist(String name, Major major) {
		// TODO Auto-generated method stub
		return false;
	}

	public Result active(MajorForm dataForm) {
		Major major = this.findById(Major.class, dataForm.getId());
		if (major.isActive() == true) {
			return new Result("error", "该管理员已经是激活状态");
		}
		major.setActive(true);
		this.update(major);
		return Result.success();
	}

	public Result cancel(MajorForm dataForm) {
		Major major = this.findById(Major.class, dataForm.getId());
		if (major.isActive() == false) {
			return new Result("error", "该管理员已经是注销状态");
		}
		major.setActive(false);
		this.update(major);
		return Result.success();	}

	public JsonEasyUI<VoMajor> getDate(MajorForm dataForm) {
		JsonEasyUI<VoMajor> result = new JsonEasyUI<VoMajor>();
		QueryResult<Major> qr = this.getScrollDate(Major.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoMajor> major_l = new ArrayList<VoMajor>();
		for (Major major : qr.getResultList()) {
			major_l.add(new VoMajor(major));
		}
		result.setRows(major_l);
		return result;
	}
	
	
	private boolean checkMajornameExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}
}
