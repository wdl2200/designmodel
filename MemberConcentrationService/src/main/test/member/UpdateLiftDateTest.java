package member;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.utils.Md5;
import com.cmts.xm.web.member.UpdateLifeDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//使用Junit4 Parameterized注册测试器
@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UpdateLiftDateTest {
	
	private String resultCode;
	private String postdata;
	@Autowired  
    private UpdateLifeDate updateLifeDate ; 
	
	
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
		String jsonStr = getJson("00000006","2019-05-22","","1","1");  //缺少placeno
		String jsonStr1 = getJson("","2019-05-22","888888","-1","-1"); //缺少accountno
		String jsonStr2 = getJson("00000006","","23052801","","");  //缺少日期
		String jsonStr8 = getJson("00000005","123456","8888888","2","2");
		String jsonStr3 = getJson("00000006","2019-05-22","8888888","1","0");  
		String jsonStr4 = getJson("00000004","2019-05-22","8888888","0","1");  
		String jsonStr5 = getJson("00000003","2019-05-22","8888888","1","1");  
		String jsonStr6 = getJson("00000002","2019-05-23","8888888","0","-2");  
		String jsonStr7 = getJson("00000001","2019-05-24","8888888","-2","0");  
		return Arrays.asList(new Object[][]{ {"001",jsonStr}, {"001",jsonStr1},{"001",jsonStr2},{"001",jsonStr3},{"001",jsonStr4},{"0",jsonStr5},{"0",jsonStr6},{"0",jsonStr7},{"0",jsonStr8} });
	}

	
	public UpdateLiftDateTest(String resultCode, String postdata) {
		this.resultCode = resultCode;
		this.postdata = postdata;
	}


	public static String getJson(String accountno,String leftdate,String placeno,String traceprice,String score){
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr(placeno + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("accountno", accountno);
		hashMap.put("placeno", placeno);
		hashMap.put("leftdate", leftdate);
		hashMap.put("traceprice", traceprice);
		hashMap.put("score", score);
		List<C0002_MEM_DAYBOOK> list = new ArrayList<C0002_MEM_DAYBOOK>();
		C0002_MEM_DAYBOOK memBayBook = new C0002_MEM_DAYBOOK();
		
		Random ran = new Random();
		int ranTraceno = ran.nextInt(100000);
		String traceno =String.format("%09d",ranTraceno);
		memBayBook.setTraceno(traceno);
		
		memBayBook.setAccountno("999");
		memBayBook.setTracetypeno("1");
		memBayBook.setTracedate("1");
		memBayBook.setTracetime("1");
		memBayBook.setMembertypeno("1");
		memBayBook.setOldbalance(1D);
		memBayBook.setOldscore(1D);
		memBayBook.setOldckcount(1);
		memBayBook.setOldposamt(1D);
		memBayBook.setCkcount(1);
		memBayBook.setPosamt(1D);
		memBayBook.setFeeprice(1D);
		memBayBook.setFeetracetypeno("1");
		memBayBook.setPrice(1D);
		memBayBook.setScore(1D);
		memBayBook.setTicketsum(1);
		memBayBook.setUsername("1");
		memBayBook.setCinemaname("1");
		memBayBook.setTracememo("1");
		memBayBook.setOldprice(1D);
		memBayBook.setTraceprice(1D);
		memBayBook.setGiftno("1");
		memBayBook.setFeatureno("1");
		memBayBook.setFilmno("1");
		memBayBook.setBxamt(1D);
		memBayBook.setBxtracetypeno("1");
		memBayBook.setPaytraceno("1");
		memBayBook.setCentertraceno("1");
		memBayBook.setPlaceno("1");
		list.add(memBayBook);
		hashMap.put("daybook", JSONArray.fromObject(list));
		
		return JSONObject.fromObject(hashMap).toString();
	}

	@Test
	public void testAdd() {
		String result = updateLifeDate.updateLifeDate(postdata, null, null);
		JSONObject rObject = JSONObject.fromObject(result);
		String procResult = (String) rObject.get("resultcode");
		assertEquals(resultCode,procResult);
	}
}
