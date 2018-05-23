package com.cmts.xm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * 通用日期处理类
 * 
 */
public class DateUtils {

	private static DateUtils instance;



	// public static DateBuilder getInstance() {
	// return new DateBuilder();
	// }
	public static DateUtils getInstance() {
		if (instance == null) {
			instance = new DateUtils();
		}
		return instance;
	}

	/**
	 * 取得系统当前时间
	 * 
	 * @return String yyyy-mm-dd
	 */
	public static String getCurrentDate_Simple() {
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH) + 1;
		int day = rightNow.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}
	
	public static String getDateToCH(String datestr){
		if(datestr!=""){
			String str = "";
			String[] strsplit = datestr.split("-");
			if(strsplit.length==3){
				str = Integer.parseInt(strsplit[0].toString())+"年";
				str += Integer.parseInt(strsplit[1].toString())+"月";
				str += Integer.parseInt(strsplit[2].toString())+"日";
			}
			return str;
		}
		return "";
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	/**
	 * 日期格式化
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDate(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(format.format(date));
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateByLongTime(long time) {
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String getCurrentDateOrder() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	public static String getCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);

	}

	/**
	 * 取得系统当前时间前n个月的相对应的一天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getMonthOfDayBeforeCurrentDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -month);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 取得系统当前时间后n个月的相对应的一天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getMonthOfDayAfterCurrentDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, +month);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}
	/**
	 * 获取某月的第一天
	 * 
	 * @return 返回时间长整型
	 */
	public static String getFirstDayOfMonthString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 获取某月的最后一天
	 * 
	 * @return 返回时间长整型
	 */
	public static String getLastDayOfMonthString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return convertDateToString(convertStringToDate(year + "-" + month + "-" + day));
	}

	/**
	 * 得到指定时间小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 获取指定时间天
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static int getMinute() {
		Calendar c = Calendar.getInstance();
		int min = c.get(Calendar.MINUTE);
		return min;
	}

	/**
	 * 取得系统当前时间的前n天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getDayBeforeCurrentDate(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -day);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 取得系统当前时间后n天
	 * 
	 * @param n
	 *            int
	 * @return String yyyy-mm-dd
	 */
	public static String getDayAfterCurrentDate(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}
	
	public static Date getDateTimeAfterCurrentDate(int day) throws ParseException{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String newDate = getDayAfterCurrentDate(day);
		String time = format.format(date);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatTime.parse(newDate+" "+time);
	}


	/**
	 * 获取日期的星期
	 * 
	 * @param strDate
	 *            日期
	 * @return 星期数字
	 */
	public static int getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		return week;

	}
	
	public static String getWeeks(String date) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(date));
			int week = c.get(Calendar.DAY_OF_WEEK) - 1;
			return getWeekString(week);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public static String getWeekString(int week) {
		String strWeek = "";
		switch (week) {
		case 0:
			strWeek = "星期日";
			break;
		case 1:
			strWeek = "星期一";
			break;
		case 2:
			strWeek = "星期二";
			break;
		case 3:
			strWeek = "星期三";
			break;
		case 4:
			strWeek = "星期四";
			break;
		case 5:
			strWeek = "星期五";
			break;
		case 6:
			strWeek = "星期六";
			break;
		case 7:
			strWeek = "星期日";
			break;
		default:
			strWeek = "";
		}
		return strWeek;
	}

	/**
	 * 将一个日期字符串转化成日期
	 * 
	 * @param sDate
	 *            String
	 * @return Date yyyy-mm-dd
	 */
	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			date = df.parse(strDate);

		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}
	public static Date convertStringToDate2(String strDate){
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
			date = df.parse(strDate);

		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}
	public static Date convertStringToDate3(String strDate, String format){
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			date = df.parse(strDate);
			
		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return date;
	}
	
	public static String convertStringToDate(Date strDate, String format){
		if(strDate!=null){
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				return dateFormat.format(strDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 输入两个字符串型的日期，比较两者的大小
	 * 
	 * @param fromDate
	 *            String
	 * @param toDate
	 *            String
	 * @return boolean before为true
	 */
	public static boolean compareTwoDateBigOrSmall(String fromDate, String toDate) {
		Date dateFrom = convertStringToDate(fromDate);
		Date dateTo = convertStringToDate(toDate);
		if (dateFrom.before(dateTo)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 输入两个字符串型的时间，比较两者的大小,date1 大返回true
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compare_date(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断data1 是否在date2时间中
	 * @param date1		yyyy-MM-dd HH:mm:ss
	 * @param date2		yyyy-MM-dd HH:mm:ss#yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean compare_date2(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2.split("#")[0]);
			Date dt3 = df.parse(date2.split("#")[1]);
			if(dt2.getTime() < dt1.getTime() && dt1.getTime()<= dt3.getTime() ){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将一个日期字符串转化成Calendar
	 * 
	 * @param sDate
	 *            String
	 * @return Calendar
	 */
	public static Calendar convertDateStringToCalendar(String strDate) {
		Date date = convertStringToDate(strDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 将一个日期转化成Calendar
	 * 
	 * @param date
	 *            Date
	 * @return Calendar
	 */
	public static Calendar convertDateToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 取得某个特定时间前n个月相对应的一天
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayOfMonthBeforeSpecificDate(String strDate, int month) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.MONTH, -month);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个特定时间前N小时的时间
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd HH:mm:ss
	 */
	public static String getDayOfHourBefore(String strDate, int hour) {

		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = df.parse(strDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, -hour);
			strDate = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY) + ":"
					+ c.get(Calendar.MILLISECOND) + ":" + c.get(Calendar.SECOND);
		} catch (Exception e) {
			System.out.println("日期转换失败:" + e.getMessage());
		}
		return strDate;
	}

	/**
	 * 取得某个特定时间后n个月相对应的一天
	 * 
	 * @param sDate
	 *            String
	 * @param n
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayOfMonthAfterSpecificDate(String strDate, int month) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.MONTH, month);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个特定时间前n天,格式为yyyy-mm-dd
	 * 
	 * @param sDate
	 *            String
	 * @param day
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayBeforeSpecificDate(String strDate, int day) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.DAY_OF_MONTH, -day);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}

	/**
	 * 取得某个时间后n天,格式为yyyy-mm-dd
	 * 
	 * @param sDate
	 *            String
	 * @param day
	 *            int
	 * @return yyyy-mm-dd
	 */
	public static String getDayAfterSpecificDate(String strDate, int day) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)));
	}
	
	public static String getDayAfterSpecificDate(String strDate, int day,String format) {
		Calendar c = convertDateStringToCalendar(strDate);
		c.add(Calendar.DAY_OF_MONTH, day);
		return convertDateToString(convertStringToDate("" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE)),format);
	}

	/**
	 * 判断系统当前时间是否为闰年
	 * 
	 * @return
	 */
	public boolean isLeapYear() {
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		if (0 == year % 4 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 将一个字符串转化为标准日期
	 * 
	 * @param str
	 * @return
	 */
	public Date convertStringToStandardDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		Date dateTime = null;
		if (!"".equals(str)) {
			try {
				date = sdf.parse(str);
				dateTime = new Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return dateTime;
	}

	/**
	 * 将日期转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public static String convertDateToString(Date date,String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 将日期转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToString_str(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}
	/**
	 * 将日期转换为字符串
	 * @param date 日期
	 * @param pattern 格式字符串
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		if(date == null)
			return "";
		if(pattern ==null || "".equals(pattern)){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat dateFormat = null;
		try{
			dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		}catch(Exception e){
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
		
	}

	/**
	 * 获取指定时间月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获取指定时间年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得上一个小时
	 */
	public static String getPreHour(String format, int step) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - step);
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * 取得某日期的上一个月时间
	 */
	public static String getPrevMonthOfDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStringToDate(date));
		cal.add(Calendar.MONTH, -1); // 前个月
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * 取得某日期的下一个月时间
	 */
	public static String getNextMonthOfDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStringToDate(date));
		cal.add(Calendar.MONTH, 1); // 后个月
		return sdf.format(cal.getTime());
	}

	public static Hashtable<Integer, Integer> getWeekNumberHT(long beginDate, long endDate) {
		Hashtable<Integer, Integer> weeks = new Hashtable<Integer, Integer>();
		for (int i = 1; i <= 7; i++) {
			weeks.put(i, 0);
		}
		long q = beginDate;
		for (q = beginDate; q <= endDate; q = q + new Long(24 * 3600 * 1000).longValue()) {
			Calendar c = Calendar.getInstance();
			Date date = new Date(q);
			c.setTime(date);
			int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
			if (dayofweek == 0) {
				dayofweek = 7;
			}
			if (weeks.containsKey(dayofweek)) {
				weeks.put(dayofweek, weeks.get(dayofweek) + 1);
			} else {
				weeks.put(dayofweek, 1);
			}
		}
		return weeks;
	}

	/**
	 * 上一星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getPrevWeekOfDate(String date) {
		return getDayBeforeSpecificDate(date, 7);
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	/**
	 * 返回某段时间内 每一天
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getDaysByFromTo(String beginDateStr,String endDateStr){
		Date beiginDate = convertStringToDate(beginDateStr);
		endDateStr = getDayAfterSpecificDate(endDateStr, 1);
		Date endDate = convertStringToDate(endDateStr);
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		if(currentDate.before(beiginDate)){
			currentDate = beiginDate;
		}
		String currentDateStr = sdf.format(currentDate);
		while(currentDate.before(endDate)){
			dateList.add(currentDateStr);
			currentDateStr = getDayAfterSpecificDate(currentDateStr, 1);
			currentDate = convertStringToDate(currentDateStr);
		}
		return dateList;
	}
	
	/**
	 * 传入2个时间点返回共计多少分钟
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 */
	public static long dateDiff(String startTime, String endTime, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数
		long diff;
		try {
			//获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
			long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
			if(diff/nm<0){
				return 24*60+diff/nm;
			}
			return diff/nm;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/** 
	* 获得指定日期的前一天 
	* @param specifiedDay 
	* @return 
	* @throws Exception 
	*/ 
	public static String getSpecifiedDayBefore(String specifiedDay){
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day-1); 
		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
	}
	
	/** 
	* 获得指定日期的后一天 
	* @param specifiedDay 
	* @return 
	*/ 
	public static String getSpecifiedDayAfter(String specifiedDay){ 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+1);
		String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayAfter; 
	} 
	
	/**
	 * 根据传入的开始时间和  相加分钟返回结束时间
	 * 传入20:00 , 60 	返回		23:00
	 * @param strdate
	 * @param mm
	 * @return
	 */
	public static String toChangeAddTime(String strdate,String mm){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date;
		String v= "";
		try {
			date = sdf.parse(strdate);
			v = sdf.format(date.getTime() + Integer.parseInt(mm) * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	/**
	 * @Title:        addDateTime 
	 * @Description:  返回当前日期 时间+15分钟
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        苏少轩
	 * @Date          2017-3-28 上午11:25:39
	 */
	public static String addDateTime() {
		Date ndate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date afterDate = new Date(ndate.getTime() + 60000*15);	//15分钟
		System.out.println(sdf.format(afterDate));
		return sdf.format(afterDate);
	}
	/** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
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
	
	public static void main(String[] args) throws Exception{
		String datestr = "2018-03-14 11:11:11";
		System.out.println(datestr.substring(0, 10));
		System.out.println(datestr.substring(11, 16));
	}
}
