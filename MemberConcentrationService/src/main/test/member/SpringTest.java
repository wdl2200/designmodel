package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.utils.Md5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest   extends AbstractJUnit4SpringContextTests{
	@Autowired
	private com.cmts.xm.web.member.QueryAccont queryAccont;
	

	public void commonTest() throws Exception{
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr("99999999" + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("placeno", "99999999");
		hashMap.put("cardno", "0");
		hashMap.put("memoryid", "3");
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("acccountno", "999");
		System.out.println(JSONObject.fromObject(hashMap).toString());
		
		System.out.println(queryAccont.queryAccont(JSONObject.fromObject(hashMap).toString(), null, null));
	}
	
	@Test
	public void oneTest() throws Exception{
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr("99999999" + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("placeno", "99999999");
		hashMap.put("cardno", "0");
		hashMap.put("memoryid", "3");
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("acccountno", "999");
		System.out.println(JSONObject.fromObject(hashMap).toString());
		
		System.out.println(queryAccont.queryAccont(JSONObject.fromObject(hashMap).toString(), null, null));
	}

	public com.cmts.xm.web.member.QueryAccont getQueryAccont() {
		return queryAccont;
	}

	public void setQueryAccont(com.cmts.xm.web.member.QueryAccont queryAccont) {
		this.queryAccont = queryAccont;
	}
	
	

}
