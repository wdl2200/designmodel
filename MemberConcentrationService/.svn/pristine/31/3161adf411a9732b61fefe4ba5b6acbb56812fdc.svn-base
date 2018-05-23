/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2016年4月11日
* 功能：
* 作 者：李庆国
* 版 本：1.0.0
* =========================================================
*/
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
public @interface NoCheckPermission {

	public String value() default "";
}

