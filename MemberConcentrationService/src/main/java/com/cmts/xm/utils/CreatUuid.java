package com.cmts.xm.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreatUuid {
	/**
	 * 随机生成有数据有字母的串
	 * 
	 * @param scFor
	 *            生成位数
	 * @return
	 */
	public static String create(int scFor) {
		String[] a2 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
		"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
		"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	
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
	
	
	public static void main(String[] args) {
		 for(int i=0;i<100;i++){
			 System.out.println(getToNum(18));
		 }
	}
	
	/**
	 * 生成位数
	 * @param num 传入位数
	 * @return
	 */
	public static String getToNum(int num) {
		if(num<15)
			return "";
		String bnum = System.currentTimeMillis()+"";
		String ranmarr="";
		for(int i=bnum.length();i<num;i++){
			ranmarr= ranmarr + ranm(10)+"";
		}
		bnum = bnum + ranmarr;
		return bnum;
	}
	private static int ranm(int s){
		return (int)(Math.random()*s);
	}
	
}
