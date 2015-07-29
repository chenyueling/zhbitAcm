package com.zhbitacm.banner.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinhuo.util.JsonUtil;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.banner.formbean.BannerForm;
import com.zhbitacm.banner.service.BannerService;
import com.zhbitacm.banner.vo.VoBanner;

@Controller("bannerAction")
@Scope("prototype")
public class BannerAction extends ActionSupport implements ModelDriven<BannerForm> {
	private static final long serialVersionUID = -6058740375296684923L;
	private BannerForm dataForm = new BannerForm();
	@Resource
	private BannerService bannerService = null;
	
	public void save() throws IOException {
		Result result = null;
		try {
			result = bannerService.saveBanner(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error("BannerAction-save", e);
		} finally {
			JsonUtil.output(result);
		}
	}

	public void update() throws IOException {
		Result result = null;
		try {
			result = bannerService.updateBanner(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error("BannerAction-update", e);
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void delete() throws IOException {
		Result result = null;
		try {
			result = bannerService.deleteBanner(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error("BannerAction-delete", e);
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void list() throws IOException {
		JsonEasyUI<VoBanner> result = null;
		try {
			result = bannerService.getData(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error("BannerAction-list", e);
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void operate() throws IOException {
		Result result = null;
		try {
			result = bannerService.operate(dataForm);
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error("BannerAction-operate", e);
		} finally {
			JsonUtil.output(result);
		}
	}
	
	public void show() {
		List<VoBanner> banner_l = bannerService.showBanner();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("voBanners", banner_l);
	}

	public BannerForm getModel() {
		return dataForm;
	}
	public BannerForm getDataForm() {
		return dataForm;
	}
	public void setDataForm(BannerForm dataForm) {
		this.dataForm = dataForm;
	}
}
