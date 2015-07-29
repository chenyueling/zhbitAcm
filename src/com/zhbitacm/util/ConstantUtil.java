package com.zhbitacm.util;

import org.apache.struts2.ServletActionContext;

import com.xinhuo.util.FileUtil;

public class ConstantUtil {
	/**文章封面存放路径*/
	public static final String articleCover = FileUtil.getServerRoot("articleCover");
	/**文章封面存放路径*/
	public static final String articleCover600X600 = FileUtil.getServerRoot("articleCover");
	
	public static final String articleStaticTemplate = "WEB-INF/template/shownew.html";
	
	public final static String StaticPage_Page = ServletActionContext.getServletContext().getRealPath("/") + "public/";

	public final static String index_page = ServletActionContext.getServletContext().getRealPath("/")+"index.html";
	
	public static final String indexStaticTemplate = "WEB-INF/template/index.html";
}
