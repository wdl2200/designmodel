package com.cmts.xm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


/** 
 * by susx
 * 生成随机数
*/
public class RandomNumberGenerator {

	 /**
	 * 随机指定范围内N个不重复的数
	 * 利用HashSet的特征，只能存放不同的值
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n 随机数个数
	 * @param HashSet<Integer> set 随机数结果集
	 * @param znum 总个数
	 */
    public static void randomSet(int min, int max, int n, HashSet<Long> set, int znum) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            Long num = (long) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < znum) {
        	randomSet(min, max, znum - setSize , set, znum);// 递归
        }
    }
    
    

    public static void main(String[] args) {
	 	//100w 随机数
    	int maxval = 99999999;
    	int minval = 10000000;
    	int num    = 1000000;
    	
    	
    	System.out.println("开始时间:"+new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS") .format(new Date()));
		HashSet<Long> set = new HashSet<Long>();
		randomSet(minval,maxval,num,set,num);
//        for (int j : set) {
//        	System.out.println(j);
//		}
        System.out.println(set.size());
        System.out.println("结束时间:"+new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS") .format(new Date()));
        
        
        
        
		
	}
}
