package member;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.utils.Md5;
import com.cmts.xm.web.member.QueryAccont;

import net.sf.json.JSONObject;

//使用Junit4 Parameterized注册测试器
@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountParameterTest {
	
	private String resultCode;
	private String postdata;
	@Autowired  
    private QueryAccont queryAccont ; 
	
	
	private TestContextManager testContextManager ;  
    
    //每次运行都会执行  与 @BeforeClass的区别  
    @Before    
    public void setUp(){    
        //自动注解与@RunWith(SpringJUnit4ClassRunner.class) 效果一样  
        testContextManager = new TestContextManager(getClass()) ;  
        try {  
            testContextManager.prepareTestInstance(this);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }   
	
	@Parameters
	public static Collection<Object[]> t() {
		String jsonStr = getJson("00000006","","","8888888");  //只传accountno
		String jsonStr1 = getJson("","2018052107","","888888"); //只传cardno
		String jsonStr2 = getJson("","","918198988","23052801");  //只传memoryid
		String jsonStr3 = getJson("123456","00005","100001","654321");  //只传memoryid
		String jsonStr4 = getJson("","","","8888888");  //只传placeno
		String jsonStr5 = getJson("123","","","55");  //只传不存在的accountno
		return Arrays.asList(new Object[][]{ {"0",jsonStr}, {"0",jsonStr1},{"0",jsonStr2},{"0",jsonStr3},{"001",jsonStr4},{"005",jsonStr5} });
	}

	
	public AccountParameterTest(String resultCode, String postdata) {
		this.resultCode = resultCode;
		this.postdata = postdata;
	}


	public static String getJson(String accountno,String cardno,String memoryid,String placeno){
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr(placeno + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("accountno", accountno);
		hashMap.put("cardno", cardno);
		hashMap.put("memoryid", memoryid);
		hashMap.put("placeno", placeno);
		return JSONObject.fromObject(hashMap).toString();
	}

	@Test
	public void testAdd() {
		String result = queryAccont.queryAccont(postdata, null, null);
		JSONObject rObject = JSONObject.fromObject(result);
		String procResult = (String) rObject.get("resultcode");
		assertEquals(resultCode,procResult);
	}
}
