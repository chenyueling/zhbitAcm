package com.xinhuo.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

/**
 * JSON工具类
 * @author xiezefan
 *
 */
public class JsonUtil {
	/**
	 * 输出json
	 * @param json
	 */
	public static void output(Object json) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// 直接输入响应的内容
			JSONObject jsonObject = JSONObject.fromObject(json);
			System.out.println(jsonObject.toString());
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
