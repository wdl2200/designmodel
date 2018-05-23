package com.cmts.xm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cmts.xm.dao.exception.NoColumnAnnotationFoundException;
import com.cmts.xm.dao.exception.NoIdAnnotationFoundException;
import com.cmts.xm.dao.util.PageView;

@Component
public interface DaoFactory {
	
	/**
	 * 根据主键获取实体
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return
	 * @throws NoIdAnnotationFoundException
	 * @throws NoColumnAnnotationFoundException
	 */
	@SuppressWarnings("rawtypes")
	public <T> T find(Class clazz, Serializable id) throws NoIdAnnotationFoundException, NoColumnAnnotationFoundException;
	
	/**
	 * 根据sql语句获取实体
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 * @throws NoIdAnnotationFoundException
	 * @throws NoColumnAnnotationFoundException
	 */
	@SuppressWarnings("rawtypes")
	public <T> T findByWhere(String sql,Object[] params,Class clazz) throws NoIdAnnotationFoundException, NoColumnAnnotationFoundException;
	
	/**
	 * 添加实体
	 * @param <T>
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public <T> boolean insert(T t) throws Exception;;
	
	/**
	 * 添加数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean insertsql(String sql, Object[] params) throws Exception;
	
	/**
	 * 修改实体
	 * @param <T>
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public <T> boolean update(T t) throws Exception;
	
	/**
	 * 根据传入实体删除
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Object po) throws Exception;

	/**
	 * 删除根据主键
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean delete(Class clazz, Serializable id) throws Exception;
	
	
	/**
	 * 根据sql删除
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public boolean deletesql(String sql, Object[] params) throws Exception;
	
	/**
	 *根据实体查询出该实体对应表的数据集合
	 * @param t 实体
	 * @return 实体集合
	 * @throws Exception
	 *List<T>
	 * @exception
	 * @since  1.0.0
	 */
	public <T> List<T> queryForList(T t) throws Exception;
	
	/**
	 * 根据实体查询出该实体对应表的分页数据集合
	 * @param t 实体
	 * @param page 页码
	 * @param size 每页数量
	 * @return 分页类
	 * @throws Exception
	 *PageView<T>
	 * @exception
	 * @since  1.0.0
	 */
	public <T> PageView<T> queryForList(T t,int page,int size) throws Exception;
	
	
	/**
	 * 根据sql查出实体集合
	 * @param <T>
	 * @param sql	sql语句： select * from v where id>?
	 * @param params	参数 : Object[]{0}
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryForList(String sql, Object[] params, Class<T> clazz) throws Exception;
	
	public <T> List<T> queryForList(String sql, Object[] params, Class<T> clazz,String sql1, Object[] params1) throws Exception;
	
	/**
	 * 返回总数
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getCount(String sql,Object[] params) throws Exception;
	
	/**
	 * 返回查询字符串结果
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getValue(String sql,Object[] params) throws Exception;
	
	/**
	 * 根据where条件返回总数
	 * @param <T>
	 * @param whereSql	语句: where id>?
	 * @param params    参数: Object[]{"1"}
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> int getCount(String whereSql,Object[] params,Class<T> clazz) throws Exception;
	
	/**
	 * 根据传入sql语句以及分页条件返回实体集合
	 * queryPageForSql(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param sql    sql语句 select * from tableName where id = ?
	 * @param params where条件的值的数组
	 * @param page   页码
	 * @param size	  每页数据的数量
	 * @param clazz  实体类
	 * @return
	 * @throws Exception
	 *int
	 * @exception
	 * @since  1.0.0
	 */
	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz) throws Exception;
	
	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz, String zhjsql) throws Exception;
	
	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz, String zhjsql,Object[] zhjparams) throws Exception;
	
	/**
	 * 添加数据操作0行时
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean insertsqlcount(String sql, Object[] params) throws Exception;
	/**
	 * 修改数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean updatesql(String sql, Object[] params) throws Exception;
	
	/**
	 * 根据SQL获取数据，返回map
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 * @author        李庆国
	 * @Date 2016年4月1日 上午9:28:15
	 */
	public Map<String,Object> getDataForSql(String sql,Object... args) throws Exception;
	
	
	/**
	 * 调用存储过程
	 * @param proName
	 * @param params
	 * @return
	 * @throws Exception
	 * @author        李庆国
	 * @Date 2016年4月11日 下午4:56:07
	 */
	public String executeProcedure (String proName,List<Map<String,String>> params,int returnValueIndex) throws Exception;
	/**
	 * 
	 * @Title:        updatesqlcount 
	 * @Description:  返回修改条数
	 * @param:        @param sql
	 * @param:        @param params
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       boolean    
	 * @throws 
	 * @author        赵斌
	 * @Date          2016-5-3 下午03:35:24
	 */
	public int updatesqlcount(String sql, Object[] params) throws Exception;
	/**
	 * 根据传入sql语句以及分页条件返回Map集合
	 * queryPageForSql(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param sql    sql语句 select * from tableName where id = ?
	 * @param params where条件的值的数组
	 * @param page   页码
	 * @param size	  每页数据的数量
	 * @param clazz  实体类
	 * @return
	 * @throws Exception
	 *int
	 * @exception
	 * @since  1.0.0
	 */
	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size) throws Exception;
	
	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size, String zhjsql) throws Exception;
	
	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size, String zhjsql, Object[] hjparams) throws Exception;
	
	public <T> List<Map<String, Object>> queryForMapList(String sql, Object[] params) throws Exception;
	
	/**
	 * 返回参数值问号形式
	 * @Title:        asListw 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param ids
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<String>    
	 * @throws 
	 * @author        苏少轩
	 * @Date          2017-5-3 下午01:39:25
	 */
	public String asListw(String ids) throws Exception;
	
	/**
	 * 返回分割的参数值
	 * @Title:        asList 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param ids
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String
	 * @throws 
	 * @author        苏少轩
	 * @Date          2017-5-3 下午01:39:48
	 */
	public List<String> asList(String ids) throws Exception;
}
