package com.xinhuo.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author xiezefan
 *
 */
public class PropertiesUtil {
	/* 默认配置文件路径  */
	private static String filePath;
	static {
		//默认配置文件的相对路径
		String relativePath = "/hdx.properties";
		filePath = PropertiesUtil.class.getResource(relativePath).toString();
		//除去字符串开头的file:/
		filePath = filePath.substring(6);
	}
	

	
	/**
	 * 从默认配置文件中读取配置
	 * @param key
	 * @return
	 */
	public static String readValue(String key) {
		return readValue(filePath, key);
	}

	/**
	 * 读取配置
	 * @param filePath 配置文件路径
	 * @param key 配置名称
	 * @return 配置值
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		InputStream ips = null;
		try {
			ips = new BufferedInputStream(new FileInputStream(filePath));
			props.load(ips);
			String value = props.getProperty(key);
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ips != null) {
				try {
					ips.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}//try ends
	} //readValue() ends
	
	/**
	 * 在默认配置文件中修改或最佳配置
	 * @param key
	 * @param value
	 */
	public static void writeProperties(String key,String value) {
		PropertiesUtil.writeProperties(PropertiesUtil.filePath, key, value);
	}
	
	
	/**
	 * 修改或追加配置
	 * @param filePath
	 * @param key
	 * @param value
	 */
	public static void writeProperties(String filePath, String key,String value) {
		Properties props = new Properties();
		FileInputStream fis = null;  
		OutputStream ops = null;
		try {
			fis = new FileInputStream(filePath);
			props.load(fis);// 将属性文件流装载到Properties对象中  
			ops = new FileOutputStream(filePath);
			props.setProperty(key, value);
			props.store(ops, "set");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ops != null) {
				try {
					ops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * 读取以','为分界的参数集合
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static List<String> readValues(String filePath, String key) {
		String[] values = PropertiesUtil.readValue(filePath, key).split(",");
		List<String> list = new ArrayList<String>();
		for (String value : values) {
			list.add(value);
		}
		return list;
	}
	
	/**
	 * 从默认配置文件中读取以','为分界的参数集合
	 * @param key
	 * @return
	 */
	public static List<String> readValues(String key) {	
		return readValues(filePath, key);
	}
		 
	
	
	
}	

