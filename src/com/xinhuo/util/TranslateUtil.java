package com.xinhuo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class TranslateUtil {
	private static String APIKEY = "ZgfPG1whz9Q8qGwgxnNp89NX";
	public static String CHINESE = "zh";
	public static String ENGHLISH = "en";
	public static String JAPANESE = "jp";
	
	public static String translate(String words, String from, String to) throws Exception{
		String ursStr = "http://openapi.baidu.com/public/2.0/bmt/translate"
				+ "?client_id=" + TranslateUtil.APIKEY
				+ "&from=" + from
				+ "&to=" + to
				+ "&q=" + words;
		URL url = new URL(ursStr);
		InputStream in = url.openStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in, "GB2312"));
        String s = null;
        String jsonStr = "";
        while((s=bin.readLine())!=null){
        	jsonStr += s;
        }
        JSONObject json = new JSONObject(jsonStr);
        System.out.println(json);
        JSONArray transResult = null;
        try {
        	transResult = json.getJSONArray("trans_result");
        } catch (JSONException e) {
        	System.out.println(json.getString("error_msg"));
        	return "";
        }
	    
	    JSONObject result = transResult.getJSONObject(0);
		return result.getString("dst");
	}
	
	public static void main(String[] args) throws Exception {
		
	    System.out.println(TranslateUtil.translate("澳门", TranslateUtil.CHINESE, TranslateUtil.ENGHLISH));
	    
	}
}
