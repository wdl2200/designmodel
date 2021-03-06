package com.cmts.xm.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
	
	/**
	 * 对应表名
	 * @return
	 */
	public String value() default "";
	
//	/**
//	 * 是否由数据库生成Id
//	 * @return
//	 */
//	public boolean generateId() default false;
}