package com.zhbitacm.banner.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.util.FileUtil;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.banner.entity.Banner;
import com.zhbitacm.banner.formbean.BannerForm;
import com.zhbitacm.banner.vo.VoBanner;

@Service("bannerService")
public class BannerServiceImpl extends DaoSupport implements BannerService {

	public Result deleteBanner(BannerForm dataForm) {
		
		String idstr = dataForm.getId();
		if (idstr != null && idstr.length() > 0) {
			String whereSQL = "";
			String[] id = idstr.split(";");
			boolean isFirst = true;
			for (int i=0; i<id.length; i++) {
				if (isFirst) {
					whereSQL = "id=?";
					isFirst = false;
				} else {
					whereSQL += " or id=?";
				}
			}
			List<Banner> list = this.findAll(Banner.class, whereSQL, id);
			
			for (Banner b : list) {
				String oldPath = FileUtil.getServerRoot("/banner", b.getImgUrl());
				FileUtil.deleteFile(oldPath);
				this.delete(b);
			}
			
			
			return Result.success();
		} else {
			return Result.objectNotFound();
		}
	}

	public JsonEasyUI<VoBanner> getData(BannerForm dataForm) {
		QueryResult<Banner> qr = this.getScrollDate(Banner.class, null, null, dataForm.getSearchTitle(), dataForm.getSearch(), dataForm.getPage(), dataForm.getRows(), dataForm.getSort(), dataForm.getOrder());
		
		List<VoBanner> rows = new ArrayList<VoBanner>();
		
		for (Banner banner : qr.getResultList()) {
			rows.add(new VoBanner(banner));
		}
		
		JsonEasyUI<VoBanner> result = new JsonEasyUI<VoBanner>();
		result.setTotal(qr.getTotalrecord());
		result.setRows(rows);
		
		return  result;
	}

	public Result operate(BannerForm dataForm) {
		
		
		String whereSQL = "";
		LinkedHashMap<String , String> orderBy = new LinkedHashMap<String, String>();
		Object[] whereParams = new Object[] {dataForm.getIndex()};
		String errorMsg = "";
		if ("UP".equals(dataForm.getOperateType())) {
			whereSQL = "sort>?";
			orderBy.put("sort", "asc");
			errorMsg = "该图片已经在第一位，不能上移";
		} else if ("DOWN".equals(dataForm.getOperateType())) {
			whereSQL = "sort<?";
			orderBy.put("sort", "desc");
			errorMsg = "该图片已经在最后一位，不能下移";
		} else {
			return Result.success();
		}
		
		List<Banner> other_l = this.findAll(Banner.class, whereSQL, whereParams, orderBy);
		if (other_l != null && other_l.size() > 0) {
			Banner other = other_l.get(0);
			Banner banner = this.findById(Banner.class, dataForm.getId());
			if (banner == null) {
				return Result.objectNotFound();
			}
			long temp = banner.getSort();
			banner.setSort(other.getSort());
			other.setSort(temp);
			this.update(banner);
			this.update(other);
			
			
			return Result.success();
		} else {
			return new Result("error", errorMsg);
		}
	}

	public Result saveBanner(BannerForm dataForm) throws FileNotFoundException, IOException {
		
		long bannerCount = this.getDataCount(Banner.class, null, null);
		if (bannerCount >= 7) {
			return new Result("error", "最多上传6张Banner");
		}
		
		File file = dataForm.getFile();
		if (file == null) {
			return new Result("error", "请选择文件上传");
		}
		
		boolean isAllowType = FileUtil.validateImageType(dataForm.getFileContentType());
		if (isAllowType == false) {
			return new Result("error", "图片类型不允许");
		}

		String filename = FileUtil.createRandomName(dataForm.getFileFileName());
		String savePath = FileUtil.getServerRoot("/banner", filename);
		FileUtil.saveFile(file, new File(savePath));
		
		Banner banner = new Banner();
		banner.setId(UUID.randomUUID().toString());
		banner.setTitle(dataForm.getTitle());
		banner.setUrl(dataForm.getUrl());
		banner.setInfo(dataForm.getInfo());
		banner.setImgUrl(filename);
		banner.setSort(new Date().getTime());
		this.save(banner);
		
		
		return Result.success();
	}

	public Result updateBanner(BannerForm dataForm) throws FileNotFoundException, IOException {
		
		Banner banner = this.findById(Banner.class, dataForm.getId());
		if (banner == null) {
			return Result.objectNotFound();
		}
		banner.setInfo(dataForm.getInfo());
		banner.setTitle(dataForm.getTitle());
		banner.setUrl(dataForm.getUrl());
		
		File file = dataForm.getFile();
		if (file != null) {
			boolean isAllowType = FileUtil.validateImageType(dataForm.getFileContentType());
			if (isAllowType == false) {
				return new Result("error", "图片类型不允许");
			}
			String filename = FileUtil.createRandomName(dataForm.getFileFileName());
			String savePath = FileUtil.getServerRoot("/banner", filename);
			FileUtil.saveFile(file, new File(savePath));
			
			String oldPath = FileUtil.getServerRoot("/banner", banner.getImgUrl());
			FileUtil.deleteFile(oldPath);
			
			banner.setImgUrl(filename);
		}
		
		this.update(banner);
		
		
		return Result.success();
	}
	
	public List<VoBanner> showBanner() {
		List<VoBanner> result = new ArrayList<VoBanner>();
		List<Banner> list = this.findAll(Banner.class, null, null, "sort", "desc");
		if (list != null && list.size() > 0) {
			if (list.size() > 5) {
				for (int i=0; i<5; i++) {
					result.add(new VoBanner(list.get(i)));
				}
			} else {
				for (Banner b : list) {
					result.add(new VoBanner(b));
				}
			}
			return result;
		}  else {
			
			return result;
		}
	}
}
