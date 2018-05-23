package com.cmts.xm.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cmts.xm.dao.annotation.Column;
import com.cmts.xm.dao.annotation.Id;
import com.cmts.xm.dao.annotation.Table;
import com.cmts.xm.dao.annotation.Transient;
import com.cmts.xm.dao.exception.NoColumnAnnotationFoundException;
import com.cmts.xm.dao.exception.NoDefinedGetterException;
import com.cmts.xm.dao.exception.NoIdAnnotationFoundException;

/**
 * Turn model to sql
 * 
 * @author Administrator
 *
 */
public class ModelSqlUtils {

	private static Logger logger = Logger.getLogger(ModelSqlUtils.class);

	/**
	 * 从po对象中分析出insert语句
	 * 
	 * @param po
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> SqlParamsPairs getInsertFromObject(T po) throws Exception {

		// 用来存放insert语句
		StringBuffer insertSql = new StringBuffer();
		// 用来存放?号的语句
		StringBuffer paramsSql = new StringBuffer();

		// 用来存放参数值
		List<Object> params = new ArrayList<Object>();

		// 分析表名
		String tableName = getTableName(po.getClass());

		insertSql.append("insert into " + tableName + " (");

		// 计数器
		int count = 0;

		// 分析列
		Field[] fields = po.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Transient tranAnno = f.getAnnotation(Transient.class);
			if (tranAnno != null) {
				// 如果有 Transient 标记直接跳过
				continue;
			}

			if ("serialVersionUID".equals(f.getName())) {
				continue;
			}

			// 获取具体参数值
			Method getter = getGetter(po.getClass(), f);

			if (getter == null) {
				continue;
			}

			Object value = getter.invoke(po);
			if (value == null) {
				// 如果参数值是null就直接跳过（不允许覆盖为null值，规范要求更新的每个字段都要有值，没有值就是空字符串）
				continue;
			}

			// 获取字段名
			String columnName = getColumnNameFromGetter(getter, f);

			if (count != 0) {
				insertSql.append(",");
			}
			insertSql.append(columnName);

			if (count != 0) {
				paramsSql.append(",");
			}
			paramsSql.append("?");

			params.add(value);
			count++;
		}

		insertSql.append(") values (");
		insertSql.append(paramsSql + ")");

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(insertSql.toString(),
				params.toArray());
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;

	}

	/**
	 * 获取属性的getter方法
	 * 
	 * @param clazz
	 * @param f
	 * @return
	 */
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

	/**
	 * 从po类获取表名
	 * 
	 * @param po
	 * @return
	 */
	private static <T> String getTableName(Class<T> clazz) {
		String className = null;
		Table tableAnno = clazz.getAnnotation(Table.class);
		if (tableAnno == null) {
			className = clazz.getName();
		}else{
			className = tableAnno.value();
		}
		return className;
//		return CamelNameUtils.camel2underscore(className.substring(className
//				.lastIndexOf(".") + 1));
	}

	/**
	 * 从对象中获取update语句
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static SqlParamsPairs getUpdateFromObject(Object po)
			throws Exception {

		// 用来存放insert语句
		StringBuffer updateSql = new StringBuffer();

		// 用来存放where语句
		StringBuffer whereSql = new StringBuffer();

		// 用来存放参数值
		List<Object> params = new ArrayList<Object>();

		// 用来存储id
		List<Object> idValues = new ArrayList<Object>();

		// 分析表名
		String tableName = getTableName(po.getClass());

		updateSql.append("update " + tableName + " set");

		// 分析列
		Field[] fields = po.getClass().getDeclaredFields();

		// 用于计数
		int count = 0;
		
		boolean isDoubleKey = false;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			Transient tranAnno = f.getAnnotation(Transient.class);
			if (tranAnno != null) {
				// 如果有 Transient 标记直接跳过
				continue;
			}
			// 获取具体参数值
			Method getter = getGetter(po.getClass(), f);

			if (getter == null) {
				continue;
			}

			Object value = getter.invoke(po);
			if (value == null) {
				// 如果参数值是null就直接跳过（不允许覆盖为null值，规范要求更新的每个字段都要有值，没有值就是空字符串）
				continue;
			}


			// 获取字段名
			String columnName = getColumnNameFromGetter(getter, f);

			// 看看是不是主键
			Id idAnno = f.getAnnotation(Id.class);
			if (idAnno != null) {
				if(isDoubleKey){
					whereSql.append(" and ");
				}
				// 如果是主键
				whereSql.append(columnName + " = ?");
				idValues.add(value);
				isDoubleKey = true;
				continue;
			}

			// 如果是普通列
			params.add(value);

			if (count != 0) {
				updateSql.append(",");
			}
			updateSql.append(" " + columnName + " = ?");

			count++;
		}

		updateSql.append(" where ");
		updateSql.append(whereSql);
		params.addAll(idValues);

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(updateSql.toString(),
				params.toArray());
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;

	}

	/**
	 * 从对象中获取delete语句
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static SqlParamsPairs getDeleteFromObject(Object po)
			throws Exception {

		// 用来存放insert语句
		StringBuffer deleteSql = new StringBuffer();

		// 用来存储id
		Object idValue = null;

		// 分析表名
		String tableName = getTableName(po.getClass());

		deleteSql.append("delete from " + tableName + " where ");

		@SuppressWarnings("rawtypes")
		Class clazz = po.getClass();
		// 分析列
		Field[] fields = clazz.getDeclaredFields();

		// 用于寻找id字段
		Id idAnno = null;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			// 找id字段
			@SuppressWarnings("unchecked")
			Method getter = getGetter(clazz, f);

			if (getter == null) {
				// 没有get方法直接跳过
				continue;
			}

			// 看是不是主键
			idAnno = f.getAnnotation(Id.class);
			if (idAnno == null) {
				continue;
			}

			// 看有没有定义column
			String columnName = getColumnNameFromGetter(getter, f);

			deleteSql.append(columnName + " = ?");

			idValue = getter.invoke(po, new Object[] {});

			break;
		}

		// 全部遍历完如果找不到主键就抛异常
		if (idAnno == null) {
			throw new NoIdAnnotationFoundException(clazz);
		}

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(deleteSql.toString(),
				new Object[] { idValue });
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;

	}
	
	/**
	 * 从对象中获取delete语句
	 * @param <T>
	 * @param <T>
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static <T> SqlParamsPairs getDeleteById(Class<T> clazz,Object idValue)
			throws Exception {

		// 用来存放insert语句
		StringBuffer deleteSql = new StringBuffer();

		// 分析表名
		String tableName = getTableName(clazz);

		deleteSql.append("delete from " + tableName + " where ");

		// 分析列
		Field[] fields = clazz.getDeclaredFields();

		// 用于寻找id字段
		Id idAnno = null;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			// 找id字段
			Method getter = getGetter(clazz, f);

			if (getter == null) {
				// 没有get方法直接跳过
				continue;
			}

			// 看是不是主键
			idAnno = f.getAnnotation(Id.class);
			if (idAnno == null) {
				continue;
			}

			// 看有没有定义column
			String columnName = getColumnNameFromGetter(getter, f);

			deleteSql.append(columnName + " = ?");

			break;
		}

		// 全部遍历完如果找不到主键就抛异常
		if (idAnno == null) {
			throw new NoIdAnnotationFoundException(clazz);
		}

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(deleteSql.toString(),
				new Object[] { idValue });
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;

	}
	
	/**
	 * 从对象中获取delete语句
	 * @param <T>
	 * @param <T>
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static <T> SqlParamsPairs getCountByWhere(String whereSql,Class<T> clazz,Object[] param)
			throws Exception {

		// 用来存放count语句
		StringBuffer countSql = new StringBuffer();
		// 分析表名
		String tableName = getTableName(clazz);

		countSql.append("select count(*) from " + tableName + " " + whereSql);

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(countSql.toString(),param);
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;

	}
	
	public static <T> SqlParamsPairs getSelectSqlFromObject(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		// 用来存放sql语句
		StringBuffer getSql = new StringBuffer();
		
		String tableName = getTableName(t.getClass());
		
		getSql.append("select * from " + tableName);
		
		StringBuffer whereSql = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		
		// 分析列
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(t.getClass(), f);
			if (getter == null) {
				// 没有get方法直接跳过
				continue;
			}
			// get column name
			String columnName = getColumnNameFromGetter(getter, f);

			Object value = getter.invoke(t);
			if (value == null) {
				continue;
			}
			if (!whereSql.toString().equals("")) {
				whereSql.append("and " + columnName + " = ? ");
			}else{
				whereSql.append(columnName + " = ? ");
			}
			
			paramsList.add(value);
		}
		if (!whereSql.toString().equals("")) {
			getSql.append(" where " + whereSql.toString());
		}
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(getSql.toString(),
				paramsList.toArray());
		logger.debug(sqlAndParams.toString());

		return sqlAndParams; 
	}

	/**
	 * 获取根据主键查对象的sql和参数
	 * 
	 * @param po
	 * @param id
	 * @return
	 * @throws NoIdAnnotationFoundException
	 * @throws NoColumnAnnotationFoundException
	 * @throws NoDefinedGetterException
	 * @throws
	 * @throws Exception
	 */
	public static <T> SqlParamsPairs getGetFromObject(Class<T> clazz, Object id)
			throws NoIdAnnotationFoundException,
			NoColumnAnnotationFoundException {

		// 用来存放get语句
		StringBuffer getSql = new StringBuffer();

		// 分析表名
		String tableName = getTableName(clazz);

		getSql.append("select * from " + tableName + " where ");

		// 分析列
		Field[] fields = clazz.getDeclaredFields();
		Id idAnno = null;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			// 找id字段
			idAnno = f.getAnnotation(Id.class);
			if (idAnno == null) {
				continue;
			}
			
			Method getter = getGetter(clazz, f);
			if (getter == null) {
				// 没有get方法直接跳过
				continue;
			}
			// get column name
			String columnName = getColumnNameFromGetter(getter, f);

			getSql.append(columnName + " = ?");

			break;
		}

		// 全部遍历完如果找不到主键就抛异常
		if (idAnno == null) {
			throw new NoIdAnnotationFoundException(clazz);
		}

		SqlParamsPairs sqlAndParams = new SqlParamsPairs(getSql.toString(),
				new Object[] { id });
		logger.debug(sqlAndParams.toString());

		return sqlAndParams;
	}

	/**
	 * use getter to guess column name, if there is annotation then use
	 * annotation value, if not then guess from field name
	 * 
	 * @param getter
	 * @param clazz
	 * @param f
	 * @return
	 * @throws NoColumnAnnotationFoundException
	 */
	private static String getColumnNameFromGetter(Method getter, Field f) {
		String columnName = "";
		Column columnAnno = getter.getAnnotation(Column.class);
		if (columnAnno != null) {
			// 如果是列注解就读取name属性
			columnName = columnAnno.value();
		}

		if (columnName == null || "".equals(columnName)) {
			columnName = f.getName();
		}
		return columnName;
	}

}
