/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2018年5月19日
* 功能：
* 作 者：李庆国
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.web.member;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.pool.DruidDataSource;
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;
import com.cmts.xm.bean.vo.InitDataAccount;
import com.cmts.xm.bean.vo.InitDataVo;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.service.init.InitDataService;
import com.cmts.xm.utils.HttpUtils;

/**
 * @FileName InitDataController.java
 * @Package com.cmts.xm.web.member
 * @Author 李庆国
 * @date 2018年5月19日 下午2:15:35 
 * @Version V1.0.1
 */
@Controller
public class InitDataController {
	
	private static Logger log = Logger.getLogger(InitDataController.class);
	
	static{
		
		System.setProperty("org.mortbay.jetty.Request.maxFormContentSize", "900000");

	}
	
	@Autowired
	private InitDataService service;
	
	@RequestMapping(value = "/initData" , produces = "application/json; charset=utf-8")
	public @ResponseBody String initData(String postdata, HttpServletRequest request, HttpServletResponse response){
		
		
		log.info("初始化数据接口处理开始：");
		log.info("初始化数据传入参数：" + postdata);
		ResultVo vo = new ResultVo();
		Logger myTest = null;
		try {
			
			
			
			Map<String, Class> classMap = new HashMap<String, Class>();  
			  
			classMap.put("account", InitDataAccount.class);
			classMap.put("member", C0003_MEM_MEMBER.class);
			
			InitDataVo jsonbean = (InitDataVo) JSONObject.toBean(JSONObject.fromObject(postdata), InitDataVo.class,classMap);
		
			myTest = Logger.getLogger(InitDataController.class);  
			
			Layout layout = new PatternLayout("%d %p [%c] - %m%n");  
			
			Appender appender = new FileAppender(layout, "c:/logs/MemberConcent/" + jsonbean.getPlaceno() + "/" + jsonbean.getPlaceno() + ".log");  
			
			myTest.addAppender(appender);
			
			service.initData(jsonbean);
			
			vo.setResultcode("0");
			vo.setResultmsg("操作成功");
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("pagecount", jsonbean.getPagecount());
			vo.setResultMap(returnMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			myTest.info(e.getMessage());
			vo.setResultcode("100500");
			vo.setResultmsg(e.getMessage());
			return vo.toChangeResultJson();
		}
		return vo.toChangeResultJson();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String a = "{\"account\":[{\"placeno\":\"888888\",\"member\":{\"placeno\":\"666666\"}},{\"placeno\":\"999999\"}],\"pagecount\":\"1\"}".toLowerCase();
//		Map<String, Class> classMap = new HashMap<String, Class>();  
//		  
//		classMap.put("account", InitDataAccount.class);
//		classMap.put("member", C0003_MEM_MEMBER.class);
//		
//		InitDataVo jsonbean = (InitDataVo) JSONObject.toBean(JSONObject.fromObject(a), InitDataVo.class,classMap);
//		
//		System.out.println(jsonbean);
//		Md5 md5 = new Md5();
//		String myverifyInfo = md5.getMD5ofStr("8888882018-05-22cmts2016clkey").substring(8, 24).toLowerCase();
//		System.out.println(myverifyInfo);
		
		DruidDataSource basicDataSource = new DruidDataSource(); 
		basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver"); 
		basicDataSource.setUrl("jdbc:oracle:thin:@cmtsgzxxx.vicp.cc:1521:orcl"); 
		basicDataSource.setUsername("gzmovie");
		basicDataSource.setPassword("mtx3cmts4mtx3"); 
		
		JdbcTemplate jt = new JdbcTemplate(basicDataSource);
		
		List<Map<String,Object>> cinemaMaps = jt.queryForList("select a.*,b.* ,c.membertypename from mem_account a,mem_member b,mem_membertype c where a.memberno = b.memberno and a.membertypeno = c.membertypeno and rownum = 1");
		Map<String,Object> a = new HashMap<String, Object>();
		a.put("placeno", "19191919");
		a.put("pagecount", "1");
		List<Map<String,Object>> b = new ArrayList<Map<String,Object>>();
		for (Map<String,Object> map : cinemaMaps){
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("ACCOUNTNO", map.get("ACCOUNTNO"));
			m.put("MEMBERNO", map.get("MEMBERNO"));
			m.put("CARDNO", map.get("CARDNO"));
			m.put("MEMORYID", map.get("MEMORYID"));
			m.put("MEMBERTYPENO", map.get("MEMBERTYPENO"));
			m.put("PASSWORD", map.get("PASSWORD"));
			m.put("LIFEDATE", map.get("LIFEDATE"));
			m.put("BALANCE", map.get("BALANCE")==null?"0":map.get("BALANCE"));
			m.put("SCORE", map.get("SCORE")==null?"0":map.get("SCORE"));
			m.put("SCORESUM", map.get("SCORESUM")==null?"0":map.get("SCORESUM"));
			m.put("USABLE", map.get("USABLE"));
			m.put("GMEMBERNO", map.get("GMEMBERNO"));
			m.put("MONEYADD", map.get("MONEYADD")==null?"0":map.get("MONEYADD"));
			m.put("CINEMANAME", map.get("CINEMANAME"));
			m.put("CKCOUNT", map.get("CKCOUNT"));
			m.put("POSAMT", map.get("POSAMT")==null?"0":map.get("POSAMT"));
			m.put("MEMBERTYPENAME", map.get("MEMBERTYPENAME"));
			m.put("placeno", "19191919");
			
			Map<String,Object> mm = new HashMap<String, Object>();
			
			mm.put("MEMBERNO", map.get("MEMBERNO"));
			mm.put("MEMBERNAME", map.get("MEMBERNAME"));
			mm.put("CONNECTNAME", map.get("CONNECTNAME"));
			mm.put("IDNUM", map.get("IDNUM"));
			mm.put("SEX", map.get("SEX"));
			mm.put("BIRTHDAY", map.get("BIRTHDAY"));
			mm.put("EMAIL", map.get("EMAIL"));
			mm.put("MOBILE", map.get("MOBILE"));
			mm.put("PHONE", map.get("PHONE"));
			mm.put("FAX", map.get("FAX"));
			mm.put("ADDRESS", map.get("ADDRESS"));
			mm.put("POSTCODE", map.get("POSTCODE"));
			mm.put("MARRIED", map.get("MARRIED"));
			mm.put("EDUCATION", map.get("EDUCATION"));
			mm.put("COUNTRY", map.get("COUNTRY"));
			mm.put("PROVINCE", map.get("PROVINCE"));
			mm.put("CAREER", map.get("CAREER"));
			mm.put("SALARY", map.get("SALARY"));
			mm.put("KIND", map.get("KIND"));
			mm.put("REGDATE", map.get("REGDATE"));
			mm.put("REGTIME", map.get("REGTIME"));
			mm.put("MEMBERMEMO", map.get("MEMBERMEMO"));
			mm.put("LASTUPDATE", map.get("LASTUPDATE"));
			mm.put("placeno", "19191919");
			
			m.put("member", mm);
			
			b.add(m);
		}
		a.put("account", b);
		System.out.println(JSONObject.fromObject(a).toString().toLowerCase());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postdata", URLEncoder.encode(JSONObject.fromObject(a).toString().toLowerCase(), "UTF-8"));
		String aaaa = HttpUtils.http("http://119.254.34.202:59220/MemberConcentrationService/initData", params);
		
		System.out.println(aaaa);
	}

}

