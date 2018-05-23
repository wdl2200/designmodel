package com.cmts.xm.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringHelperUtils {
	
	
	public static void main(String[] args) {
		String str = "01010605";
		String inrow = "";
		String incol = "";
		inrow = str.substring(str.length()-4,str.length()-2);
		incol = str.substring(str.length()-2,str.length());
		System.out.println(inrow);
		System.out.println(incol);
		
//		String   ss   =   "0123 ";  //要确保字符串为一个数值，否则会出异常
//		
//		System.out.println(Float.parseFloat(ss));
//		
//		String modules="MEM_ABC,MEM_HYZ,MEM_HAHA";
//		String strmod = "";
//		String[] strsplit = modules.split(",");
//		for(String s : strsplit){
//			strmod += "'"+s+"',";
//		}

		
//		System.out.println(strmod);
		
	}
	 
	
	public static int getHashcode(String baseImageUrl){
		int key = (baseImageUrl.hashCode() & 0x7FFFFFFF)%8;
		if(key < 1) key = 1;
		return key;
	}

	/*
	 * 检查是否是数字
	 */
	public static boolean isNumeric(String str) {
		if(str == null || "".equals(str))
			return false;
		if(str.startsWith("-")){
			str = str.substring(1,str.length());
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str.trim());
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
    
	/**
	 * 判断一个字符串是空字串或者为 null
	 * @param sz
	 * @return 如果一个字串是 null 或 ""，返回 true
	 */
	public static final boolean isEmptyString(String sz)
	{
		if (sz == null)
		{
			return true;
		}
		sz=sz.trim();
		if (sz.length() == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
 	 * 替换字符串中html
 	 * @param str
 	 * @return
 	 */
	public static String ReplaceHtml(String str){
		String bstr = "";
		if(!"".equals(str) && str != null){
			Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL); 
			Matcher matcher = pattern.matcher(str); 
			bstr = matcher.replaceAll(""); 
		}
		return bstr;
	}
	
	/**
	 * 替换字符串中html，并返回指定长度
	 */
	public static String SubStr(String str,int l,int m){
		String bstr = "";
		bstr = ReplaceHtml(str);
		if(!"".equals(bstr)){
			if(bstr.length() > m){
				bstr = bstr.substring(l, m);
			}
		}
		return bstr;
	}
	/**
	 * 截取字符串 前台调用 显示size个字符，多余...代替
	 * @param str 原字符串
	 * @param size 显示的字数
	 * @param fix
	 * @return
	 */
	public static String subString(String str,int size,String fix){
		String bstr = "";
		if(!isEmptyString(str)){
			if(str.length() > size + 1){
				bstr = str.substring(0,size) + fix;
			}else{
				bstr = str;
			}
		}
		return bstr;
	}
	
	 
	/**
	 * 截取字符串 前台调用 显示size个字符(中文长度为2,字母长度为1)， 
	 * @param str 原字符串
	 * @param size 显示的字数
	 * @param fix  多余字符替换字符串
	 * @return
	 * @throws Exception
	 */
	public static String subStringChinese(String str,int size,String fix){   
          int reInt = 0;   
          String reStr = "";   
          if (!isEmptyString(str)){
        	  String chinese = "[\u4e00-\u9fa5]";  
        	  char[] tempChar = str.toCharArray();   
              for (int i = 0; (i< tempChar.length && size > reInt); i++) {   
                  String s1 = str.valueOf(tempChar[i]);  
                  if (s1.matches(chinese)) {  
  	                // 中文字符长度为2  
                	  reInt += 2;  
	  	           } else {  
	  	                // 其他字符长度为1  
	  	            	reInt += 1;  
	  	          }  
	              reStr += tempChar[i];  
              }   
              if (size == reInt || (size == reInt - 1)) {
            	  reStr += fix;  
              }
          }
          return reStr;   
	}  
 
	/**
	 * 判断字符串是否不能全数字和不能有特殊字符
	 * 规则，只能全字母或者字母和数字组合
	 * by susx
	 */
	public static boolean isNumStr(String str){
		if(!isNumeric(str)){
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Matcher isNum = pattern.matcher(str);
		    if(isNum.matches()){
		    	return true; 
		    }else{
		    	return false; 
		    }
		}
		return false;
	}
	
	/**
	 * 传入完整的url返回域名
	 * 例如：传入 http://asdf.58.com.cn 返回：asdf
	 * @param str
	 * @return
	 */
	public static String getDoMain(String str){
		String nstr = null;
		if(!"".equals(str)){
			nstr = str.replace("http://", "");
			if(!"".equals(nstr)){
				nstr = nstr.toLowerCase();
				if(nstr.contains(".")){
					nstr = nstr.split("\\.")[0].toString();
					if(nstr.length()>=4&&nstr.length()<=20)
						return nstr;
				}
			}
		}
		return "";
	}
	/**
	 * 组装字符串
	 * @param content
	 * @param indexfix
	 * @param endfix
	 * @return
	 */
	public static String viewContent(Object content,String indexfix,String endfix){
		if(content == null || 
				content.toString().isEmpty() || 
				"null".equalsIgnoreCase(content.toString())){
			return "";
		}
		return indexfix+content.toString()+endfix;
	}
	 
	/**
	 * 计算两个时间的差距值（后者减去前者）
	 * @param date1 Date类型
	 * @param date2 Date类型
	 * @param unit 单位（HOUR,DAY,MINITE）
	 * @return
	 */
	public static double timeInterval(Date date1,Date date2,String unit){
		if(date1 == null || date2==null)
			return 0;
		if(isEmptyString(unit))
			unit = "DAY";
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		
		long tm = l2 - l1;
		if("DAY".equalsIgnoreCase(unit)){
			return tm/(1000*60*60*24);
		}else if("HOUR".equalsIgnoreCase(unit)){
			return tm/(1000*60*60);
		}else if("MINITE".equalsIgnoreCase(unit)){
			return tm/(1000*60);
		}else{
			return tm/(1000*60*60*24);
		}
	}
	 
	/**
	 * 将字符串转换为Long类型 不能转换 
	 * @param str
	 * @param fix
	 * @return
	 */
	public static Long tryParseLong(String str,Long fix){
		if(isNumeric(str)){
			Long s = Long.valueOf(str);
			return s;
		}
		return fix;
	}
	/**
	 * 将字符串转换为Integer类型 不能转换 
	 * @param str
	 * @param fix
	 * @return
	 */
	public static Integer tryParseInteger(String str,Integer fix){
		if(isNumeric(str)){
			Integer s = Integer.valueOf(str);
			return s;
		}
		return fix;
	}
}
