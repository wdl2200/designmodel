package com.cmts.xm.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.cmts.xm.dao.annotation.Table;
import com.cmts.xm.dao.exception.NoColumnAnnotationFoundException;
import com.cmts.xm.dao.exception.NoIdAnnotationFoundException;
import com.cmts.xm.dao.util.ModelSqlUtils;
import com.cmts.xm.dao.util.PageView;
import com.cmts.xm.dao.util.SqlParamsPairs;

import oracle.jdbc.driver.OracleTypes;

@Component
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class DaoFactoryImpl extends JdbcDaoSupport implements DaoFactory {


	private static Logger log = Logger.getLogger(DaoFactoryImpl.class);
	
	@Autowired
	public DaoFactoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public <T> T find(Class clazz, Serializable id)
			throws NoIdAnnotationFoundException,
			NoColumnAnnotationFoundException {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getGetFromObject(clazz, id);

		List<T> list = this.queryForList(sqlAndParams.getSql(),
				sqlAndParams.getParams(), clazz);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public <T> T findByWhere(String sql,Object[] params,Class clazz) throws NoIdAnnotationFoundException, NoColumnAnnotationFoundException
	{
		List<T> list = this.queryForList(sql, params, clazz);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else{
			return null;
		}
	}

	public <T> List<T> queryForList(String sql, Object[] params, Class<T> clazz) {
		List<T> list = null;
		if (params == null || params.length == 0) {
			list = this.getJdbcTemplate().query(sql,
					new BeanPropertyRowMapper(clazz));
		} else {
			list = this.getJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper(clazz));
		}
		return list;
	}
	
	public <T> List<T> queryForList(String sql, Object[] params, Class<T> clazz,String zhjsql,Object[] zhjparams) {
		List<T> list = null;
		if (params == null || params.length == 0) {
			list = this.getJdbcTemplate().query(sql,
					new BeanPropertyRowMapper(clazz));
		} else {
			list = this.getJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper(clazz));
		}
		if(list!=null && list.size()>0){
			List<T> olist2 = queryForList(zhjsql, zhjparams, clazz);
			if(olist2!=null && olist2.size()>0){
				list.add(olist2.get(0));
			}
		}
		return list;
	}
	
	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//mysql 分页
//			String countSql = "SELECT COUNT(0) FROM (" + sql + ") ttt";
//			int count = getCount(countSql, params);
//			if (count > 0) {
//				sql = sql + " limit " + pageView.getStartIndex() + "," + size;
//				pageView.setTotalRecords(count);
//				pageView.setRecords(queryForList(sql, params, clazz));
//			}else{
//				pageView.setTotalRecords(0);//总数
//				pageView.setRecords(null);
//			}
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				pageView.setRecords(queryForList(newsql, params, clazz));
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}
	
	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz,String zhjsql) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				List<T> olist = queryForList(newsql, params, clazz);
				if(olist!=null && olist.size()>0){
					List<T> olist2 = queryForList(zhjsql, params, clazz);
					if(olist2!=null && olist2.size()>0){
						olist.add(olist2.get(0));
					}
				}
				pageView.setRecords(olist);
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}

	public <T> PageView<T> queryPageForSql(String sql,Object[] params,int page,int size,Class<T> clazz,String zhjsql,Object[] zhjparams) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				List<T> olist = queryForList(newsql, params, clazz);
				if(olist!=null && olist.size()>0){
					List<T> olist2 = queryForList(zhjsql, zhjparams, clazz);
					if(olist2!=null && olist2.size()>0){
						olist.add(olist2.get(0));
					}
				}
				pageView.setRecords(olist);
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}

	@Override
	public <T> boolean insert(T t) throws Exception {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getInsertFromObject(t);
		try {
			return this.getJdbcTemplate().update(sqlAndParams.getSql(),
					sqlAndParams.getParams()) > 0 ? true : false;
		} catch (DataAccessException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (Object p : sqlAndParams.getParams()) {
				sb.append(p + " | ");
			}
			sb.append("]");
			log.error("Error SQL: " + sqlAndParams.getSql() + " Params: "
					+ sb.toString());
			throw e;
		}
	}
	
	@Override
	public boolean insertsql(String sql, Object[] params) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql,
					params) > 0 ? true : false;
		} catch (DataAccessException e) {
			logger.error("Error SQL: " + sql + " Params: "
					+ params.toString());
			throw e;
		}
	}

	@Override
	public <T> boolean update(T t) throws Exception {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getUpdateFromObject(t);
		try {
			return this.getJdbcTemplate().update(sqlAndParams.getSql(),
					sqlAndParams.getParams()) > 0 ? true : false;
		} catch (DataAccessException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (Object p : sqlAndParams.getParams()) {
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sqlAndParams.getSql() + " Params: "
					+ sb.toString());
			throw e;
		}
	}

	public boolean delete(Object po) throws Exception {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getDeleteFromObject(po);
		try {
			return this.getJdbcTemplate().update(sqlAndParams.getSql(),
					sqlAndParams.getParams()) > 0 ? true : false;
		} catch (DataAccessException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (Object p : sqlAndParams.getParams()) {
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sqlAndParams.getSql() + " Params: "
					+ sb.toString());
			throw e;
		}
	}

	protected static <T> String getTableName(Class<T> clazz) {
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			String tableName = table.value();
			return tableName;
		} else {
			return clazz.getSimpleName();
		}
	}

	public boolean delete(Class clazz, Serializable id) throws Exception
	{
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getDeleteById(clazz,id);
		try {
			return this.getJdbcTemplate().update(sqlAndParams.getSql(),
					sqlAndParams.getParams()) > 0 ? true : false;
		} catch (DataAccessException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (Object p : sqlAndParams.getParams()) {
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sqlAndParams.getSql() + " Params: "
					+ sb.toString());
			throw e;
		}
	}
	
	public boolean deletesql(String sql,Object[] params) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql,
					params) > 0 ? true : false;
		} catch (DataAccessException e) {
			logger.error("Error SQL: " + sql + " Params: "
					+ params.toString());
			throw e;
		}
	}
	

	@Override
	public int getCount(String sql, Object[] params)  throws Exception{
		int count = 0;
		try {
			count = this.getJdbcTemplate().queryForInt(sql,params);
		} catch (Exception e) {
			throw e;
		}
		return count;
	}
	
	@Override
	public String getValue(String sql, Object[] params) throws Exception {
		String val = "";
		try {
			val = (String)this.getJdbcTemplate().queryForObject(sql, params, String.class);
		} catch (Exception e) {
			throw e;
		}
		return val;
	}

	@Override
	public <T> int getCount(String whereSql, Object[] params, Class<T> clazz) throws Exception {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getCountByWhere(whereSql, clazz, params);
		int count = 0;
		try {
			count = this.getJdbcTemplate().queryForInt(sqlAndParams.getSql(),sqlAndParams.getParams());
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	@Override
	public <T> List<T> queryForList(T t) throws Exception {
		SqlParamsPairs sqlAndParams = ModelSqlUtils.getSelectSqlFromObject(t);
		List<T> list = (List<T>) this.queryForList(sqlAndParams.getSql(), sqlAndParams.getParams(), t.getClass());
		return list;
	}

	@Override
	public <T> PageView<T> queryForList(T t, int page, int size)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertsqlcount(String sql, Object[] params) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql,
					params) >= 0 ? true : false;
		} catch (DataAccessException e) {
			logger.error("Error SQL: " + sql + " Params: "
					+ params.toString());
			throw e;
		}
	}
	@Override
	public boolean updatesql(String sql, Object[] params) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql,
					params) > 0 ? true : false;
		} catch (DataAccessException e) {
			logger.error("Error SQL: " + sql + " Params: "
					+ params.toString());
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.cmts.cmts10.dao.DaoFactory#getDataForSql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Map<String, Object> getDataForSql(String sql, Object... args)
			throws Exception {
		return this.getJdbcTemplate().queryForMap(sql,args);
	}

	public String executeProcedure (final String sql,List<Map<String,String>> params,final int returnValueIndex) throws Exception
	{
		final List<Map<String,String>> p  = params;
		String param2Value = (String) this.getJdbcTemplate().execute(   
			     new CallableStatementCreator() {   
			        public CallableStatement createCallableStatement(Connection con) throws SQLException {
			           CallableStatement cs = con.prepareCall(sql);
			           for(int i = 0;i < p.size();i++)
			           {
			        	   Map<String,String> m = p.get(i);
			        	   if (m.containsKey("isReturn")) {
			        		   cs.registerOutParameter(i + 1, OracleTypes.VARCHAR);// 注册输出参数的类型  
						}else{
							cs.setString(i + 1, m.get("value"));// 设置输入参数的值
						}
			           }
			           return cs;   
			        }   
			     }, new CallableStatementCallback() {   
			         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
			           cs.execute();
			           if (returnValueIndex != -1) {
			        	   return cs.getString(returnValueIndex);// 获取输出参数的值  
					}else{
						return  "0";
					}
			           
			     }   
			  });  
		return param2Value;
	}
	@Override
	public int updatesqlcount(String sql, Object[] params) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql,
					params) ;
		} catch (DataAccessException e) {
			logger.error("Error SQL: " + sql + " Params: "
					+ params.toString());
			throw e;
		}
	}

	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//mysql 分页
//			String countSql = "SELECT COUNT(0) FROM (" + sql + ") ttt";
//			int count = getCount(countSql, params);
//			if (count > 0) {
//				sql = sql + " limit " + pageView.getStartIndex() + "," + size;
//				pageView.setTotalRecords(count);
//				pageView.setRecords(queryForList(sql, params, clazz));
//			}else{
//				pageView.setTotalRecords(0);//总数
//				pageView.setRecords(null);
//			}
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				pageView.setRecords(queryForMapList(newsql, params));
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}
	
	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size,String zhjsql) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				List<Map<String, Object>> olist = queryForMapList(newsql, params);
				if(olist!=null && olist.size()>0){
					List<Map<String, Object>> olist2 = queryForMapList(zhjsql, params);
					if(olist2!=null && olist2.size()>0){
						olist.add(olist2.get(0));
					}
				}
				pageView.setRecords(olist);
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}
	
	public <T> PageView<Map<String, Object>> queryMapPageForSql(String sql,Object[] params,int page,int size,String zhjsql,Object[] hjparams) throws Exception
	{
		PageView pageView = new PageView(size, page);
		try {
			//oracle 分页
			String countSql = "SELECT COUNT("+System.currentTimeMillis()+") FROM (" + sql + ") ttt";
			int count = getCount(countSql, params);
			if (count > 0) {
				String newsql = "select * from (select '"+System.currentTimeMillis()+"' sqlseq,joinTable.*,rownum r from(";
				newsql = newsql + sql;
				newsql = newsql + ") joinTable where rownum <= "+pageView.getPageResult()+") outerTable where outerTable.r > "+pageView.getStartIndex()+"";
				log.info("queryPageForSql：" + newsql);
				pageView.setTotalRecords(count);
				List<Map<String, Object>> olist = queryForMapList(newsql, params);
				if(olist!=null && olist.size()>0){
					List<Map<String, Object>> olist2 = queryForMapList(zhjsql, hjparams);
					if(olist2!=null && olist2.size()>0){
						olist.add(olist2.get(0));
					}
				}
				pageView.setRecords(olist);
			}else{
				pageView.setTotalRecords(0);//总数
				pageView.setRecords(null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return pageView;
	}
	
	public <T> List<Map<String, Object>> queryForMapList(String sql, Object[] params) {
		List<Map<String, Object>> list = null;
		if (params == null || params.length == 0) {
			list = this.getJdbcTemplate().query(sql,
					new ColumnMapRowMapper());
		} else {
			list = this.getJdbcTemplate().query(sql, params,
					new ColumnMapRowMapper());
		}
		return list;
	}
	
	@Override
	public String asListw(String ids) {
		String spw = "";
		if (ids != null && !"".equals(ids)) {
			String idGroup[] = ids.split(",");
			for (String id : idGroup) {
				spw += "?,";
			}
			if(!"".equals(spw)){
				spw = spw.substring(0,spw.length()-1);
			}
		}
		return spw;
	}
	
	@Override
	public List<String> asList(String ids) {
		List<String> idList = new ArrayList<String>();
		if (ids != null && !"".equals(ids)) {
			String idGroup[] = ids.split(",");
			for (String id : idGroup) {
				idList.add(id);
			}
		}
		return idList;
	}
}
