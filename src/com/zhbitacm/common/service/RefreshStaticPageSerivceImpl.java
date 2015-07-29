package com.zhbitacm.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.xinhuo.dao.DaoSupport;
import com.xinhuo.util.QueryResult;
import com.xinhuo.vo.Result;
import com.zhbitacm.banner.entity.Banner;
import com.zhbitacm.banner.vo.VoBanner;
import com.zhbitacm.news.entity.Article;
import com.zhbitacm.news.vo.VoArticle;
import com.zhbitacm.user.entity.Competition;
import com.zhbitacm.user.vo.VoCompetition;
import com.zhbitacm.util.ConstantUtil;
import com.zhbitacm.util.StaticUtil;

@Service("refreshStaticPageService")
public class RefreshStaticPageSerivceImpl extends DaoSupport implements
		RefreshStaticPageService {

	public Result refreshAllPage() {
		return Result.success();
	}

	public Result refreshIndex() {
		
		String whereSQL = "isActive=?";
		Object[] whereParams = {true};
		
		//比赛列表	
		QueryResult<Competition> queryResultCompetition = getScrollDate(Competition.class, whereSQL, whereParams, null, null, 1, 3, "createTime", "desc");
		List<VoCompetition> competitions =  new ArrayList<VoCompetition>();
		for (Competition competition : queryResultCompetition.getResultList()) {
			competitions.add(new VoCompetition(competition,true));
		}
		
		//新闻列表
		QueryResult<Article> queryResultArticle = getScrollDate(Article.class, whereSQL, whereParams, null, null, 1, 4, "createTime", "desc");
		List<VoArticle> articles =  new ArrayList<VoArticle>();
		for (Article article : queryResultArticle.getResultList()) {
			articles.add(new VoArticle(article));
		}
		
		//banner
		whereParams = null;
		whereSQL = null;
		QueryResult<Banner> queryResultBanner = getScrollDate(Banner.class, whereSQL, whereParams, null, null, 1, 6, "sort", "desc");
		List<VoBanner> banners =  new ArrayList<VoBanner>();
		for (Banner banner : queryResultBanner.getResultList()) {
			banners.add(new VoBanner(banner));
		}
		

		//主页静态化
		 HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		System.out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		data.put("voCompetitions", competitions);
		data.put("voArticles", articles);
		data.put("voBanner", banners);
		StaticUtil.crateHTML(data, ConstantUtil.indexStaticTemplate, ConstantUtil.index_page);
		
		
		return Result.success();
	}
}
