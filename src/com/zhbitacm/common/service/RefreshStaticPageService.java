package com.zhbitacm.common.service;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.Result;

public interface RefreshStaticPageService extends Dao{
	public Result refreshIndex();
	public Result refreshAllPage();
}
