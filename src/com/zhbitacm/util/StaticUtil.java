package com.zhbitacm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 静态化工具
 * @author chenyueling
 *
 */
public class StaticUtil {
	/**  
	  * 生成静态页面主方法  
	  * @param data 一个Map的数据结果集  
	  * @param templatePath ftl模版路径  
	  * @param targetHtmlPath 生成静态页面的路径  
	  */  
	public static void crateHTML(Map<String,Object> data,String templatePath,String targetHtmlPath){   
	
	ServletContext context = ServletActionContext.getServletContext();
	
	Configuration freemarkerCfg = new Configuration();   
	//加载模版   
	freemarkerCfg.setServletContextForTemplateLoading(context, "/");   
	freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");   
	try {   
	    //指定模版路径   
	    Template template = freemarkerCfg.getTemplate(templatePath,"UTF-8");   
	    template.setEncoding("UTF-8");   
	    File htmlFile = new File(targetHtmlPath);   
	          Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));   
	          //处理模版     
	          template.process(data, out);   
	          out.flush();   
	          out.close();   
	} catch (Exception e) {   
	    e.printStackTrace();   
	}
	}
}
