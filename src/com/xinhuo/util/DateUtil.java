package com.xinhuo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xinhuo.exception.DaoException;

/**
 * 日期工具类
 * @author xiezefan
 *
 */
public class DateUtil {
	private static Pattern[] dataPattern;
	private static SimpleDateFormat[] dataFormat;
	private static SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdformat3 = new SimpleDateFormat("HH:mm:ss");	
	static {
		//2013-08-07
		Pattern pattern0 = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})$");
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
		//2013-08-07 00:26:15
		Pattern pattern1 = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})$");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//20-3-08-07 00:26
		Pattern pattern2 = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})$");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dataPattern = new Pattern[] {pattern0, pattern1, pattern2};
		dataFormat = new SimpleDateFormat[] {format0, format1, format2};
	}
	
	/**
	 * 根据字符串获取日期
	 * @param timeStr
	 * @return
	 * @throws DaoException
	 */
	public static Date format(String timeStr) throws DaoException{
		Matcher matcher = null;
		int i = -1;
		for (i=0; i<dataPattern.length; i++) {
			matcher = dataPattern[i].matcher(timeStr);
			if (matcher.find() == true) {
				break;
			}
		}
		if (i == -1) {
			return null;
		}
		Date result = null;
		try {
			result = dataFormat[i].parse(timeStr);
		} catch (ParseException e) {
			throw new DaoException("格式化日期字符串  " + timeStr + " 失败");
		}
		return result;
	}
	
	/**
	 * 根据字符串获取SQL查询专用日期
	 * @param timeStr
	 * @return
	 * @throws DaoException
	 */
	public static java.sql.Date formatSQL(String timeStr) throws DaoException{
		Matcher matcher = null;
		int i = -1;
		for (i=0; i<dataPattern.length; i++) {
			matcher = dataPattern[i].matcher(timeStr);
			if (matcher.find() == true) {
				break;
			}
		}
		if (i == -1) {
			return null;
		}
		Date result = null;
		try {
			result = dataFormat[i].parse(timeStr);
		} catch (ParseException e) {
			throw new DaoException("格式化日期字符串  " + timeStr + " 失败");
		}
		return new java.sql.Date(result.getTime());
	}
	
	/**
	 * 将日期转换成字符串 yyyy-MM-dd HH:mm:ss
	 * @param data
	 * @return
	 */
	public static String dataToString(Date data) {
		return sdformat.format(data); 
	}
	
	public static String dataToStringdata(Date data){
		return sdformat2.format(data);
	}

	public static String dataToStringtime(Date data){
		return sdformat3.format(data);
	}
}
