package com.zhbitacm.news.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.util.FileUtil;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.JsonEasyUI;
import com.xinhuo.vo.Result;
import com.zhbitacm.admin.entity.OrdinaryAdmin;
import com.zhbitacm.news.entity.Article;
import com.zhbitacm.news.entity.ArticleImage;
import com.zhbitacm.news.formbean.ArticleForm;
import com.zhbitacm.news.vo.VoArticle;
import com.zhbitacm.util.ConstantUtil;
import com.zhbitacm.util.HttpSessionUtil;
import com.zhbitacm.util.StaticUtil;

/**
 * 文章服务实现类
 * 
 * @author chenyueling
 * 
 */
@Service("articleService")
public class ArticleServiceImpl extends DaoSupport implements ArticleService {

	public Result active(ArticleForm dataForm) {
		Article article = this.findById(Article.class, dataForm.getId());
		if (article.isActive() == true) {
			return new Result("error", "该文章已经是激活状态");
		}
		article.setActive(true);
		this.update(article);
		
		//文章静态化
		 HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		data.put("voArticle", new VoArticle(article));
		StaticUtil.crateHTML(data, ConstantUtil.articleStaticTemplate, ConstantUtil.StaticPage_Page+article.getStaticPage());
		
		
		return Result.success();
	}

	public Result cancel(ArticleForm dataForm) {
		Article article = this.findById(Article.class, dataForm.getId());
		if (article.isActive() == false) {
			return new Result("error", "该文章已经是注销状态");
		}
		article.setActive(false);
		this.update(article);
		//注销文章时删除静态化页面
		FileUtil.deleteFile(ConstantUtil.StaticPage_Page+article.getStaticPage());
		return Result.success();
	}

	public JsonEasyUI<VoArticle> getData(ArticleForm dataForm) {
		JsonEasyUI<VoArticle> result = new JsonEasyUI<VoArticle>();
		QueryResult<Article> qr = this.getScrollDate(Article.class, null, null,
				dataForm.getSearchTitle(), dataForm.getSearch(), dataForm
						.getPage(), dataForm.getRows(), dataForm.getSort(),
				dataForm.getOrder());
		result.setTotal(qr.getTotalrecord());
		List<VoArticle> article_l = new ArrayList<VoArticle>();
		for (Article article : qr.getResultList()) {
			article_l.add(new VoArticle(article,true));
		}
		result.setRows(article_l);

		return result;
	}

	public Result saveArticle(ArticleForm dataForm)
			throws FileNotFoundException, IOException {
		String id = HttpSessionUtil.getString(HttpSessionUtil.ordinaryAdmin_id);
		OrdinaryAdmin admin = findById(OrdinaryAdmin.class, id);

		// 保存封面图片
		String fileName = "";
		File image = dataForm.getImage();
		if (image == null) {
			return new Result("error", "请选择封面图片");
		}
		boolean isAllowType = FileUtil.validateImageType(dataForm
				.getImageContentType());
		if (isAllowType == false) {
			return new Result("error", "文件类型不允许");
		}
		fileName = FileUtil.createRandomName(dataForm.getImageFileName());
		String filePath = ConstantUtil.articleCover + fileName;
		FileUtil.saveFile(image, new File(filePath));
		String image600X600 = ConstantUtil.articleCover600X600 + fileName;
		FileUtil.compressPic(image, image600X600, 600, 600, true);

		String articleId = UUID.randomUUID().toString();
		// 获取文章图片
		String[] articleImages = dataForm.getArticleImage();
		List<ArticleImage> artImg_l = null;
		String whereSQL;
		Object[] whereParams;
		if (articleImages != null) {
			boolean isFirst = true;
			whereSQL = "";
			whereParams = new Object[articleImages.length];
			for (int i = 0; i < articleImages.length; i++) {
				if (isFirst == true) {
					whereSQL += " id=? ";
					whereParams[i] = articleImages[i];
					isFirst = false;
				} else {
					whereSQL += " or id=? ";
					whereParams[i] = articleImages[i];
				}
			}
			artImg_l = this.findAll(ArticleImage.class, whereSQL, whereParams);
			if (artImg_l.size() > 0) {
				for (ArticleImage a : artImg_l) {
					a.setArticleId(articleId);
				}
			}
		} else {
			artImg_l = new ArrayList<ArticleImage>();
		}

		Article article = new Article();
		article.setId(articleId);
		article.setActive(true);
		article.setAuthor(dataForm.getAuthor());
		article.setContent(dataForm.getContent());
		article.setImgUrl(fileName);
		article.setCreateTime(new Date());
		article.setPageView(0);
		article.setPublisher(admin);
		article.setSmallTitle(dataForm.getSmallTitle());
		article.setSource(dataForm.getSource());
		article.setTitle(dataForm.getTitle());
		article.setArticleImages(artImg_l);
		// 保存文章对象
		this.save(article);
		
		//文章静态化
		 HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		data.put("voArticle", new VoArticle(article));
		StaticUtil.crateHTML(data, ConstantUtil.articleStaticTemplate, ConstantUtil.StaticPage_Page+article.getStaticPage());
		
		
		return Result.success();
	}

	public Result updateArticle(ArticleForm dataForm)
			throws FileNotFoundException, IOException {
		String id = dataForm.getId();
		Article article = findById(Article.class, id);

		if (article == null) {
			return new Result("error", "该文章不存在!");
		}

		String adminId = HttpSessionUtil
				.getString(HttpSessionUtil.ordinaryAdmin_id);
		OrdinaryAdmin editer = findById(OrdinaryAdmin.class, adminId);

		// 保存封面图片
		File image = dataForm.getImage();
		if (image != null) {
			boolean isAllowType = FileUtil.validateImageType(dataForm
					.getImageContentType());
			if (isAllowType == false) {
				return new Result("error", "文件类型不允许");
			}
			String fileName = FileUtil.createRandomName(dataForm
					.getImageFileName());
			String filePath = ConstantUtil.articleCover + fileName;
			String image600X600 = ConstantUtil.articleCover600X600 + fileName;
			// 删除原来的封面图片
			FileUtil
					.deleteFile(ConstantUtil.articleCover + article.getImgUrl());
			FileUtil.deleteFile(ConstantUtil.articleCover600X600
					+ article.getImgUrl());
			FileUtil.saveFile(image, new File(filePath));

			FileUtil.compressPic(image, image600X600, 600, 600, true);
			article.setImgUrl(fileName);
		}

		// 获取文章图片
		String[] articleImages = dataForm.getArticleImage();
		List<ArticleImage> artImg_l = article.getArticleImages();
		List<ArticleImage> artImg_temp = null;
		if (articleImages != null) {
			boolean isFirst = true;
			String whereSQL = "";
			Object[] whereParams = new Object[articleImages.length];
			for (int i = 0; i < articleImages.length; i++) {
				if (isFirst == true) {
					whereSQL += " id=? ";
					whereParams[i] = articleImages[i];
					isFirst = false;
				} else {
					whereSQL += " or id=? ";
					whereParams[i] = articleImages[i];
				}
			}
			artImg_temp = this.findAll(ArticleImage.class, whereSQL,
					whereParams);
			if (artImg_temp.size() > 0) {
				for (ArticleImage a : artImg_temp) {
					a.setArticleId(article.getId());
				}
			}
		} else {
			artImg_temp = new ArrayList<ArticleImage>();
		}

		for (int i = 0; i < artImg_temp.size(); i++) {
			artImg_l.add(artImg_temp.get(i));
		}

		article.setAuthor(dataForm.getAuthor());
		article.setContent(dataForm.getContent());
		article.setSmallTitle(dataForm.getSmallTitle());
		article.setSource(dataForm.getSource());
		article.setTitle(dataForm.getTitle());
		article.setEditer(editer);
		article.setArticleImages(artImg_l);
		article.setEditTime(new Date());
		// 更新文章
		update(article);
		
		//文章静态化
		 HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		data.put("voArticle", new VoArticle(article));
		StaticUtil.crateHTML(data, ConstantUtil.articleStaticTemplate, ConstantUtil.StaticPage_Page+article.getStaticPage());
		
		return Result.success();
	}

	public Result deleteArticle(ArticleForm dataForm) {

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
			List<Article> list = this.findAll(Article.class, whereSQL, id);

			for (Article article : list) {

				// 删除封面图片
				String imgUrl = ConstantUtil.articleCover + article.getImgUrl();
				String image600X600 = ConstantUtil.articleCover600X600
						+ article.getImgUrl();
				FileUtil.deleteFile(imgUrl);
				FileUtil.deleteFile(image600X600);
				// 删除文章绑定图片
				List<ArticleImage> articleImages = article.getArticleImages();
				// delete(article);
				for (ArticleImage articleImage : articleImages) {
					String filePath = FileUtil.getServerRoot("article")
							+ articleImage.getPath();
					FileUtil.deleteFile(filePath);
					System.out.println(filePath);
					delete(articleImage);
				}

				this.delete(article);
				//删除文章时删除静态页面
				FileUtil.deleteFile(ConstantUtil.StaticPage_Page+article.getStaticPage());
			}
		} else {
			return new Result("error", "文章对象不存在");
		}
		return Result.success();
	}

	public VoArticle getArticleById(ArticleForm dataForm) {
		String id = dataForm.getId();
		Article article = findById(Article.class, id);
		if (article == null) {
			return null;
		}
		
		VoArticle result = new VoArticle(article);
		
		return result;
	}

	public List<Object> getArticleList(ArticleForm dataForm) {
		List<Object> result = new ArrayList<Object>();
		String whereSQL = "isActive=?";
		Object[] whereParams = {true};
		QueryResult<Article> qr = this.getScrollDate(Article.class, whereSQL,
				whereParams, dataForm.getSearchTitle(), dataForm.getSearch(),
				dataForm.getPage(), 5, "createTime",
				"desc");
		List<VoArticle> article_l = new ArrayList<VoArticle>();
		for (Article article : qr.getResultList()) {
			article_l.add(new VoArticle(article,true));
			
		}
		long totalRecord = qr.getTotalrecord();
		long totalPage = 0;
		if(totalRecord%5 == 0){
			totalPage = totalRecord / 5;
		}else{
			totalPage = totalRecord / 5 + 1;
		}
		result.add(article_l);
		result.add(totalRecord);
		result.add(totalPage);
		result.add(dataForm.getPage());
		return result;
	}

	//前台获取文章列表
	public List<Object> showArticleList(ArticleForm dataForm) {
		return null;
	}

	// 文章阅读量增加
	public String addPageView(ArticleForm dataForm) {
		String id = dataForm.getId();
		Article article = findById(Article.class, id);
		int pageView = article.getPageView() + 1;
		article.setPageView(pageView);
		update(article);
		return pageView + "";
	}


	
}
