package com.cmts.xm.utils;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

 


/**
 * 项目配置文件
 * @author susx
 *
 */
public class Globals {
	
	private static Logger logs = Logger.getLogger(Globals.class);
	
	//站点变量配置文件路径
	private static Properties properties = new Properties();
	  
	public static Map<String, Object> FeatureLogMap = null;
	
	
	static {
		try {
			
			
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = cl.getResourceAsStream("globalsConfig.properties");
			properties.load(inputStream);
			
			
			PropertyConfigurator.configureAndWatch("log4j.properties");
			
			FeatureLogMap = new HashMap<String, Object>();
		} catch(Exception e){
			logs.error("读取globals配置文件出错");
		}
	}
	
	public static final String NAMESPACE="CinemaManager";
	
	public static final int PAGESIZE10 = 10;
	public static final int PAGESIZE15 = 15;
	public static final int PAGESIZE20 = 20;
	
	//中图middle
	public static final String SMALLIMAGESIZE = "120*90";
	public static final String MIDDLEIMAGESIZE = "200*200";
	public static final String BIGIMAGESIZE = "640*640";
	//最大查询记录数量
	public static Integer MAX_RESULT = 1000000;
	//默认空图片
	public static final String DEFAULT_PIC = "/n/images/none.gif";
	
	public static final String DEFAULT_PATH = "/CinemaManager";
	
	//缓存事件配置
	public static final long MEMC_TIME_600 = Long.parseLong((String)properties.get("MEMC_TIME_600"));//缓存10分钟
	public static final long MEMC_TIME_1800 = Long.parseLong((String)properties.get("MEMC_TIME_1800"));//缓存30分钟
	public static final long MEMC_TIME_3600 = Long.parseLong((String)properties.get("MEMC_TIME_3600"));//缓存1小时
	  
	
	//座位图最大行列
	public static final int MAX_SEATGRAPH_ROW = Integer.parseInt((String)properties.get("SEATGRAPHROW"));
	public static final int MAX_SEATGRAPH_COL = Integer.parseInt((String)properties.get("SEATGRAPHCOL"));
	
	//拍片宝
	public static final String PPB_URL = "paipian.snbigdata.com";
	public static final String PPB_APPCODE = "cmtsclient";
	public static final String PPB_CHECKKEY = "cmts2016";
	
	//接口 verifyinfo 中 groundKey值
	public static final String SYNC_ORDER_ORDERNO = "cinemaServer_cinemaOrderInfo2.action";
	
	//座位图图片数据
	public static final int FEATURE_COLOR_NUM = Integer.parseInt((String)properties.get("FEATURE_COLOR_NUM"));
	public static final String FEATURE_COLOR_VAL = (String)properties.get("FEATURE_COLOR_VAL");
	public static final String FEATURE_COLOR_AUTO = (String)properties.get("FEATURE_COLOR_AUTO");
	
	//版本号
	public static final String SYSTEM_VERSION = (String)properties.get("SYSTEM_VERSION");
}
 