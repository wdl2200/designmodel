package member;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.utils.Md5;
import com.cmts.xm.web.member.ConsumptionAndRechargeController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @Description: 消费充值测试
 * @ClassName: ConsumptionAndRechargeTest
 * @author: 王淼 
 * @date: 2018年5月19日 下午2:31:30  
 *
 */
@SuppressWarnings({ "all" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConsumptionAndRechargeTest {
	
	@Autowired
	private ConsumptionAndRechargeController consumptionAndRechargeController;
	
	/**
	 * 
	 * @Description: 消费
	 * @Title: consumptionTest  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 下午2:31:52
	 *
	 * @throws Exception
	 *   
	 * @throws
	 */
	@Test
	public void consumptionTest() throws Exception{
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr("99999999" + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("placeno", "99999999");
		hashMap.put("price", "-10");
		hashMap.put("score", "-10");
		hashMap.put("checkcount", "-10");
		hashMap.put("posamt", "-10");
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("accountno", "999");
		List<C0002_MEM_DAYBOOK> list = new ArrayList<C0002_MEM_DAYBOOK>();
		C0002_MEM_DAYBOOK memBayBook = new C0002_MEM_DAYBOOK();
		memBayBook.setTraceno("9000000033");
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
		System.out.println(JSONObject.fromObject(hashMap).toString());
		
		System.out.println(consumptionAndRechargeController.consumption(URLEncoder.encode(JSONObject.fromObject(hashMap).toString(), "UTF-8"), null, null));
	}
	
	/**
	 * 
	 * @Description: 充值
	 * @Title: rechargeTest  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 下午2:32:00
	 *
	 * @throws Exception
	 *   
	 * @throws
	 */
	//@Test
	public void rechargeTest() throws Exception{
		Md5 md5 = new Md5();
		String verifyInfo = md5.getMD5ofStr("99999999" + DateUtil.simpleDateFormat("yyyy-MM-dd") + "cmts2016clkey").substring(8, 24).toLowerCase();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("placeno", "99999999");
		hashMap.put("price", "10");
		hashMap.put("score", "10");
		hashMap.put("membertypeno", "03");
		hashMap.put("membertypename", "测试03");
		hashMap.put("verifyInfo", verifyInfo);
		hashMap.put("accountno", "999");
		List<C0002_MEM_DAYBOOK> list = new ArrayList<C0002_MEM_DAYBOOK>();
		C0002_MEM_DAYBOOK memBayBook = new C0002_MEM_DAYBOOK();
		memBayBook.setTraceno("9000000010");
		memBayBook.setAccountno("999");
		memBayBook.setTracetypeno("2");
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
		System.out.println(JSONObject.fromObject(hashMap).toString());
		
		System.out.println(consumptionAndRechargeController.recharge(JSONObject.fromObject(hashMap).toString(), null, null));
	}

}
