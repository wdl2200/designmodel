package com.cmts.xm.utils;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class HttpUtils {
	
	private static Logger log = Logger.getLogger(HttpUtils.class);

	public static synchronized String http(String url, Map<String, Object> params) {
		log.info("接口地址：" + url);
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		String ssb =null;
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}			
			ssb = sb.toString().substring(0,sb.toString().length()-1);		
		}
		
		log.info("请求参数：" + ssb);
		
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(3000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			
			if (params != null) {
				osw.write(ssb);
			}
			
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("接口访问异常：" + e);
			return "{\"code\":-1,\"msg\":\"接口访问异常\"}";
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			log.info("接口返回参数：" + buffer.toString());
		} catch (Exception e) {
			buffer.append("{\"code\":-1,\"msg\":\"接口访问异常\"}");
			e.printStackTrace();
			log.error("读取接口返回内容异常：" + e);
		}

		return buffer.toString();
	}
	 
}


