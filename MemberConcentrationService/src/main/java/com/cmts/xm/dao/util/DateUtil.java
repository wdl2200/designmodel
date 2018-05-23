package com.cmts.xm.dao.util;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;
import com.cmts.xm.utils.Md5;

import net.sf.json.JSONObject;

/**
 * 
 * @Description: 工具类
 * @ClassName: DateUtil
 * @author: 王淼 
 * @date: 2017年8月31日 下午2:53:01  
 *
 */
@SuppressWarnings({ "all" })
public class DateUtil {
	
	private static Logger log = Logger.getLogger(DateUtil.class);
	
	private static ResourceBundle resource = ResourceBundle.getBundle("globalsConfig");
	
	/**
	 * 
	 * @Description: 获取系统当前时间  HH:mm
	 * @Title: systemDateTime  
	 * @author: 王淼 
	 * @Date: 2017年8月31日 上午10:07:56
	 *
	 * @return
	 *   
	 * @throws
	 */
	public static String systemDateTime(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 
	 * @Description: 将时间戳转换为时间 yyyyMMddHHmmssSSS
	 * @Title: stampToDate  
	 * @author: 王淼 
	 * @Date: 2017年8月16日 下午1:39:52
	 *
	 * @return
	 *   
	 * @throws
	 */
	public static String stampToDate(){
		long currentTime = System.currentTimeMillis(); 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date(currentTime);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 
	 * @Description: 获取系统当前时间  HH:mm
	 * @Title: systemTime  
	 * @author: 王淼 
	 * @Date: 2017年8月31日 上午10:07:56
	 *
	 * @return
	 *   
	 * @throws
	 */
	public static String systemTime(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}
	
	/**
	 * 
	 * @Description: 获取当前系统日期时间
	 * @Title: simpleDateFormat  
	 * @author: 王淼 
	 * @Date: 2017年9月4日 下午1:28:56
	 *
	 * @param formatDate 日期时间格式 默认为 yyyy-MM-dd HH:mm:ss
	 * @return
	 *   
	 * @throws
	 */
	public static String simpleDateFormat(String formatDate){
		if("".equals(formatDate)){
			formatDate = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		String date = format.format(new Date());
		return date;
	}
	
	/**
	 * 
	 * @Description: 转换日期时间格斯
	 * @Title: conversionSimpleDateFormat  
	 * @author: 王淼 
	 * @Date: 2017年9月4日 下午1:29:36
	 *
	 * @param formatDate  要转换成的日期时间格式
	 * @param date 要转换的日期时间
	 * @return
	 *   
	 * @throws
	 */
	public static String conversionSimpleDateFormat(String date, String formatDate){
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		String newDate = "";
		try {
			newDate = format.format(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 
	 * @Description: 数据验证方法
	 * @Title: validationDate  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 上午9:41:24
	 *
	 * @param placeno 影院编码
	 * @param verifyInfo 验证字符串
	 * @return true 验证失败  false 验证成功
	 *   
	 * @throws
	 */
	public static boolean validationDate(String placeno, String verifyInfo){
		log.info("开始进行数据验证----验证参数_placeno：" + placeno + "_verifyInfo：" + verifyInfo);
		Md5 md5 = new Md5();
		String myverifyInfo = md5.getMD5ofStr(placeno + simpleDateFormat("yyyy-MM-dd") + resource.getString("groundKey")).substring(8, 24).toLowerCase();
		log.info("对比数据：" + placeno + "---" +simpleDateFormat("yyyy-MM-dd") + "---" + resource.getString("groundKey") + "MD5转换后KEY：" + myverifyInfo);
		if(!myverifyInfo.equals(verifyInfo)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Description: 解析json参数
	 * @Title: procJson  
	 * @author: 王淼 
	 * @Date: 2018年5月21日 下午4:09:06
	 *
	 * @param postdata
	 * @return
	 *   
	 * @throws
	 */
	public static JsonBean procJson(String postdata) throws Exception{
		log.info("传入参数_转码前：" + postdata);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("daybook", C0002_MEM_DAYBOOK.class);
		classMap.put("member", C0003_MEM_MEMBER.class);
		classMap.put("account", C0001_MEM_ACCOUNT.class);
		String newpostdata = URLDecoder.decode(postdata, "UTF-8");
		log.info("传入参数_转码后：" + newpostdata);
		JsonBean jsonbean = (JsonBean) JSONObject.toBean(JSONObject.fromObject(newpostdata), JsonBean.class, classMap);
		return jsonbean;
	}
	
}
