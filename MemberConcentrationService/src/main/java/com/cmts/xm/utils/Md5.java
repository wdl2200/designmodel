// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Md5.java

package com.cmts.xm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Md5
{

    private static char[] sysdate;

	public static String hex(byte[] array)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i)
        {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                .toUpperCase().substring(1, 3));
        }
        return sb.toString();
    }


    public static String getMD5ofStr(String message)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e)
        {
        }
        catch (UnsupportedEncodingException e)
        {
        }
        return null;
    }


    public static void main(String[] args) throws ParseException{
//    	Date dNow = new Date();   //当前时间
//    	Date dBefore = new Date();//当前日期的前一天
//    	Calendar calendar = Calendar.getInstance();  //得到日历
//    	calendar.setTime(dNow);//把当前时间赋给日历
//    	calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
//    	dBefore = calendar.getTime();   //得到前一天的时间
//    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
//    	String defaultStartDate = sdf.format(dBefore);    //格式化前一天
//    	System.out.println(defaultStartDate);
//    	String a ="D:\\apache-tomcat-6.0.35\\webapps\\hiyou\\xls\\《一场风花雪月的事》2013-08-03.xls";
//   	    double d1;//上午场排气率
//		double d2;//下午场排气率
//		double d3;//午夜场排气率
//		//排期率保留两位小数
//		DecimalFormat df = new DecimalFormat("##0.00");  
//		int cinemacount=65;//共有XXX家影院
//		int featurecount=365;//共放映XXXX场
//		d1=(double)cinemacount/featurecount;
//		String rates=df.format(d1);   
//    	double x = (double)1/4;
//         System.out.println(x+"");  
		//d1=(61/368)*100;
//		d2=(afternooncount/featurecount)*100;
//		d3=(nightcount/featurecount)*100;
//   	   
//        int i=1;
//        int sum=0;
//        while(i<6){
//        	sum+=i*3;
//        	i++;
//        }
////        System.out.println(sum);
//    	String d="2013-12-26";
//    	String s="00:56";
//    	String tb="2014-01-21 11:12";
//    	String ta="2014-02-09 11:11";
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
//    	SimpleDateFormat dfs = new SimpleDateFormat("MM-dd");//设置日期格式
////    	Date t1=df.parse(s);
//    	Date t2=df.parse(tb);
//    	Date t3=df.parse(ta);
//    	Date t=dfs.parse(d);
//    	if(t3.after(t2)){
//    		System.out.println("cuowu");
//    	}else{
//    		System.out.println("zhengque");
//    	}
//    	获取当前时间的下一天（明天）
		Calendar c = Calendar.getInstance();
//		c.setTime(t); 
//		int day=c.get(Calendar.DATE); 
//		c.set(Calendar.DATE,day-1); 
		String today=new SimpleDateFormat("MM月dd日").format(c.getTime()); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		c.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(sdf.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH,1);
		System.out.println(sdf.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH,1);
		System.out.println(sdf.format(c.getTime()));
		
//		System.out.println(dayAfter);
//        String tomorrow=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//    	String a=df.format(new Date());
//    	String b=s.replaceAll("：", "");
//    	int r=b.compareTo(a);
//    	System.out.println();
//    	Calendar c = Calendar.getInstance();
//    	c.add(Calendar.DATE, 1);
//     String tomorrow=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//    	System.out.println(tomorrow); 
//    	String a="2,14/2,15/2,16/";
//    	String[] b=a.split("/");
//    	String sb="";
//    	for(int i=0;i<b.length;i++){
//    	   sb += "," + b[i].replace(",", "排")+"号 ";
//    	}
//    	String str = sb.substring(1);
//    	 System.out.println(str);
//    	int rn=40;
//    	int pageNo=0;
//    	double n=0;
//    	if(rn<20||rn==20){
//			pageNo=1;
//		}else{
//			int m=rn/20;
//			 n=rn%20;
//			if(n!=0.0){
//				pageNo=m+1;
//			}else{
//				pageNo=m;
//			}
//		}
//    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//    	Date now = null;
//		String c=sdf.format(now);
////    	int date = Integer.parseInt(c);
    	System.out.println(today);
    }
    /**
	 * 随机生成有数据有字母的串
	 * 
	 * @param scFor
	 *            生成位数
	 * @return
	 */
	public static String create(int scFor) {
		String[] a2 = { "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		SimpleDateFormat simFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = simFormat.format(new Date());

		Random r = new Random();
		String result = "";
		int i = 0;
		while (result.length() < scFor) {
			int temp = r.nextInt(a2.length);
			result = result + a2[temp];
			if (result.length() % 2 == 0) {
				if (i <= dateStr.length()) {
					result = result + dateStr.split("")[i];
				}
			}
			i++;
		}
		return result;
	}

    /**
     * MD5参数验证方法
     */
    public static boolean paramMd5Validate(String param[],String md5Value) throws UnsupportedEncodingException{
    	String allparam="";
    	for(String pa : param){
    		allparam+=pa;
    	}
    	if(Md5.getMD5ofStr(URLEncoder.encode(allparam,"utf-8").toLowerCase()).toLowerCase().equals(md5Value)){
        	return true;
        }
        return false;
    }
//    public static String returnImg(String img){
////    	int begine = img.indexOf("img src=\"");
////    	int end = img.indexOf(".jpg",begine);//gif
////    	System.out.println("起始位置"+begine);
////    	System.out.println("结束位置"+end);
////    	System.out.println("截取出来的" +
////    			""+img.substring(begine+9,end+4));
//    	//img.indexOf(ch)
//    	String a ="D:\\apache-tomcat-6.0.35\\webapps\\hiyou\\xls\\《一场风花雪月的事》2013-08-03.xls";
//    	System.out.println(a.substring(a.indexOf("《")+1));
//    	return "";
//    }

}
