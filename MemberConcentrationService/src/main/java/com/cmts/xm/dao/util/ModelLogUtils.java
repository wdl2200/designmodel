/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2016年3月31日
* 功能：
* 作 者：孙淳
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import com.cmts.xm.dao.annotation.LogName;

public class ModelLogUtils {

	private static Logger logger = Logger.getLogger(ModelSqlUtils.class);
	
	private static <T> Method getGetter(Class<T> clazz, Field f) {
		String getterName = "get" + f.getName().substring(0,1).toUpperCase()+ f.getName().substring(1);
		Method getter = null;
		try {
			getter = clazz.getMethod(getterName);
		} catch (Exception e) {
			logger.debug(getterName + " doesn't exist!", e);
		}
		return getter;
	}
	
	public static <T> String getModelLog(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Field[] fields = t.getClass().getDeclaredFields();
		String logName = "";
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(t.getClass(), f);
			
			if (getter == null) {
				continue;
			}
			LogName logAnno = f.getAnnotation(LogName.class);
			if (logAnno == null) {
				continue;
			}else{
				Object value = getter.invoke(t);
				if (value == null) {
					// 如果参数值是null就直接跳过（不允许覆盖为null值，规范要求更新的每个字段都要有值，没有值就是空字符串）
					value = "";
				}
				logName += " " + logAnno.value() + ":" + value + " -";
			}
		}
		
		if(StringUtils.isNotBlank(logName)){
			logName = logName.substring(0,logName.length() - 1);
		}
		
		return logName;
	}
	
	
}

