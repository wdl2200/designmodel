package com.cmts.xm.dao.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogName {
	
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