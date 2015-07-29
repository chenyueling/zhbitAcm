package com.xinhuo.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WeatherUtil {
	
	/**
	 * 获取特定地区的天气状况，返回长度为23的List
	 * String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。
	 * String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。
	 * String(12) 到 String(16)：第二天的 气温，概况，风向和风力，图标一，图标二。
	 * String(17) 到 String(21)：第三天的 气温，概况，风向和风力，图标一，图标二。
	 * String(22) 被查询的城市或地区的介绍
	 * @param cityName
	 * @return
	 * @throws DocumentException
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getWeather(String cityName) throws DocumentException, MalformedURLException {
		String urls = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName=" + cityName;
		URL url = new URL(urls);
		SAXReader reader = new SAXReader(); 
        Document document = reader.read(url);
        
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点 
        List<String> list = new ArrayList<String>();
        for (Element e : elementList) {
        	list.add(e.getText()); 
        }
        return list;
	}
	
	public static void main(String[] args) throws DocumentException, MalformedURLException {
		List<String> list = WeatherUtil.getWeather("澳门");
		System.out.println(list);
	}
}
