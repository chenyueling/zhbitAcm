package com.zhbitacm.banner.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.xinhuo.dao.Dao;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.banner.formbean.BannerForm;
import com.zhbitacm.banner.vo.VoBanner;

public interface BannerService extends Dao {
	public Result saveBanner(BannerForm dataForm) throws FileNotFoundException, IOException;
	public Result updateBanner(BannerForm dataForm) throws FileNotFoundException, IOException;
	public Result operate(BannerForm dataForm);
	public Result deleteBanner(BannerForm dataForm);
	public JsonEasyUI<VoBanner> getData(BannerForm dataForm);
	/**
	 * 展示Banner
	 * @return
	 */
	public List<VoBanner> showBanner();
	
}
