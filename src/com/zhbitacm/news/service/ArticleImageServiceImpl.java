package com.zhbitacm.news.service;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.util.FileUtil;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonUeditor;
import com.zhbitacm.news.entity.ArticleImage;
import com.zhbitacm.news.formbean.ArticleImageForm;
import com.zhbitacm.util.HttpSessionUtil;

@Service("articleImageService")
public class ArticleImageServiceImpl extends DaoSupport implements
		ArticleImageService {

	public JsonUeditor saveImage(ArticleImageForm dataForm) throws FileNotFoundException, IOException {
		File image = dataForm.getImage();
		if (image == null) {
			return new JsonUeditor("", "文件传输错误", "");
		}
		boolean isAllowType = FileUtil.validateImageType(dataForm.getImageContentType());
		if (isAllowType == false) {
			return new JsonUeditor("", "文件类型不允许", "");
		}
		//图片水印
		//ImageUtils.pressImage(image, ConstantUtil.waterImage, ConstantUtil.waterAlpha);
		String fileName = FileUtil.createRandomName(dataForm.getImageFileName());
		String filePath = FileUtil.getServerRoot("article") + fileName;
		FileUtil.saveFile(image, new File(filePath));
		ArticleImage img = new ArticleImage();
		img.setId(UUID.randomUUID().toString());
		img.setPath(fileName);
		img.setInfo(dataForm.getPictitle());
		img.setOwnerId(HttpSessionUtil.getString(HttpSessionUtil.ordinaryAdmin_id));
		img.setCreateTime(new Date());
		this.save(img);
		return new JsonUeditor(FileUtil.rootPath + "/article/" + fileName, "success", dataForm.getPictitle(), img.getId());
	}
	
	public String getData() {
		QueryResult<ArticleImage> qr = this.getScrollDate(ArticleImage.class, null, null, null, null, 1, 100, "createTime", "desc");
		String result = "";
		for (ArticleImage img : qr.getResultList()) {
			result += (FileUtil.rootPath + "/article/" + img.getPath());
			result += "ue_separate_ue";
		}
		return result;
	}
	
	
	
	public JsonUeditor ordinarySaveImage(ArticleImageForm dataForm) throws FileNotFoundException, IOException {
		File image = dataForm.getImage();
		if (image == null) {
			return new JsonUeditor("", "文件传输错误", "");
		}
		boolean isAllowType = FileUtil.validateImageType(dataForm.getImageContentType());
		if (isAllowType == false) {
			return new JsonUeditor("", "文件类型不允许", "");
		}
		//图片水印
		//ImageUtils.pressImage(image, ConstantUtil.waterImage, ConstantUtil.waterAlpha);
		String fileName = FileUtil.createRandomName(dataForm.getImageFileName());
		String filePath = FileUtil.getServerRoot("article") + fileName;
		FileUtil.saveFile(image, new File(filePath));
		ArticleImage img = new ArticleImage();
		img.setId(UUID.randomUUID().toString());
		img.setPath(fileName);
		img.setInfo(dataForm.getPictitle());
		//System.out.println(dataForm.getPictitle());
		img.setOwnerId(HttpSessionUtil.getString(HttpSessionUtil.ordinaryAdmin_id));
		img.setCreateTime(new Date());
		this.save(img);
		return new JsonUeditor(FileUtil.rootPath + "/article/" + fileName, "success", dataForm.getPictitle(), img.getId());
	}
	
	public String ordinaryGetData() {
		String whereSQL = "ownerId=?";
	    Object[] whereParams = {HttpSessionUtil.getString(HttpSessionUtil.ordinaryAdmin_id)};
		QueryResult<ArticleImage> qr = this.getScrollDate(ArticleImage.class,whereSQL, whereParams, null, null, 1, 100, "createTime", "desc");
		String result = "";
		for (ArticleImage img : qr.getResultList()) {
			result += (FileUtil.rootPath + "/article/" + img.getPath());
			result += "ue_separate_ue";
		}
		return result;
	}
	
	

}
