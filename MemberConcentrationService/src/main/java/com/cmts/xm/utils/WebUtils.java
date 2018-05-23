package com.cmts.xm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class WebUtils {


	public static String urlURLEncoder(String str) throws UnsupportedEncodingException{
		return URLEncoder.encode(str, "UTF-8");
	}
	 
	  
}
