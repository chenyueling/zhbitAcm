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
import com.zhbitacm.user.entity.Level;
import com.zhbitacm.user.formbean.LevelForm;
import com.zhbitacm.user.vo.VoLevel;

@Service("levelService")
public class LevelServiceImpl extends DaoSupport implements LevelService {


	public Result saveLevel(LevelForm dataForm) throws DaoException {
		String mark = dataForm.getMark();
		boolean isLevelmarkExist = this.checkLevelmarkExist(mark);
		if (isLevelmarkExist == true) {
			return new Result("error", "头衔标记已经存在");
		}
		Level level = new Level();
		level.setId(UUID.randomUUID().toString());
		level.setCreateTime(new Date());
		level.setMark(mark);
		level.setTitle(dataForm.getTitle());
		level.setYear(dataForm.getYear());
		level.setEditTime(new Date());
		level.setLeveltitle(level.toString());
System.out.println(dataForm.getTitle());
		
		this.update(level);
		
		return Result.success();	
	}

	public Result updateLevel(LevelForm dataForm) throws DaoException {
		Level level = this.findById(Level.class, dataForm.getId());
		level.setYear(dataForm.getYear());
		level.setTitle(dataForm.getTitle());
		level.setEditTime(new Date());
		
		this.update(level);
		return Result.success();
	}

	
	public Result deleteLevel(LevelForm dataForm)throws DaoException{

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
			List<Level> list = this.findAll(Level.class, whereSQL, id);

			for (Level level : list) {
				this.delete(level);
			}
		} else {
			return new Result("error", "头衔对象不存在");
		}
		return Result.success();
	}
	
	private boolean checkLevelmarkExist(String mark) {
		String whereSQL = "mark=?";
		Object[] whereParams = { mark };
		long count = this.getDataCount(Level.class, whereSQL,
				whereParams);
		return count > 0;
	}

	public JsonEasyUI<VoLevel> getDate(LevelForm dataForm) {
		JsonEasyUI<VoLevel> result = new JsonEasyUI<VoLevel>();
		QueryResult<Level> qr = this.getScrollDate(Level.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoLevel> level_l = new ArrayList<VoLevel>();
		for (Level level : qr.getResultList()) {
			level_l.add(new VoLevel(level));
		}
		result.setRows(level_l);
		return result;
	}
	
	
}
