package com.cmts.xm.web.member;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.ConsumptionAndRechargeService;
import com.cmts.xm.utils.ErrorCode;
import com.cmts.xm.utils.HttpUtils;
import com.cmts.xm.utils.Md5;

import net.sf.json.JSONObject;

/**
 * 
 * @Description: 消费与充值
 * @ClassName: ConsumptionAndTopUpAction
 * @author: 王淼 
 * @date: 2018年5月19日 上午10:48:21  
 *
 */
@SuppressWarnings({ "all" })
@Controller
@RequestMapping(value = "/")
public class ConsumptionAndRechargeController {
	
	private static Logger log = Logger.getLogger(ConsumptionAndRechargeController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ConsumptionAndRechargeService consumptionAndRechargeService;
	
	/**
	 * 
	 * @Description: 消费接口（售与退）
	 * @Title: consumption  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 上午11:02:06
	 *
	 * @param postdata
	 * @param request
	 * @param response
	 * @return
	 *   
	 * @throws
	 */
	@RequestMapping(value = "consumption" , produces = "application/json; charset=utf-8")
	public @ResponseBody String consumption(String postdata, HttpServletRequest request, HttpServletResponse response){
		ResultVo vo = new ResultVo();
		log.info("接口处理开始：");
		
		try {
			JsonBean jsonbean = DateUtil.procJson(postdata);
			log.info("接口必要参数名：acccountno----daybook----placeno----pirce----score----checkcount----posamt----verifyInfo********传入参数：" + jsonbean);
			if(null == jsonbean.getAccountno() || "".equals(jsonbean.getAccountno()) || 
			   null == jsonbean.getDaybook() || 
			   null == jsonbean.getPrice() || "".equals(jsonbean.getPrice()) || 
			   null == jsonbean.getScore() || "".equals(jsonbean.getScore()) || 
			   null == jsonbean.getCheckcount() || "".equals(jsonbean.getCheckcount()) || 
			   null == jsonbean.getPosamt() || "".equals(jsonbean.getPosamt()) ||
			   null == jsonbean.getVerifyInfo() || "".equals(jsonbean.getVerifyInfo())){
				log.info("缺少相应参数");
				vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(DateUtil.validationDate(jsonbean.getPlaceno(), jsonbean.getVerifyInfo())){
				vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
				log.info("电影院不存在");
				vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());
			
			if("1".equals(memAccount.getUsable())){
				log.info("账户禁用");
				vo.setResultcode(ErrorCode.ACCOUNT_USABLE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_USABLE_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(null == memAccount){
				log.info("会员不存在");
				vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if((memAccount.getBalance() + jsonbean.getPrice()) < 0){
				log.info("会员账户余额不足");
				vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if((memAccount.getPosamt() + jsonbean.getPosamt()) < 0){
				log.info("礼包金额不足");
				vo.setResultcode(ErrorCode.ACCOUNT_POSAMT_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_POSAMT_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if((memAccount.getCkcount() + jsonbean.getCheckcount()) < 0){
				log.info("兑换次数不足");
				vo.setResultcode(ErrorCode.ACCOUNT_CKCOUNT_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_CKCOUNT_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			vo = consumptionAndRechargeService.consumptionInformation(jsonbean);
			return vo.toChangeResultJson();
		} catch (Exception e) {
			log.error("系统异常" + e);
			vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
			return vo.toChangeResultJson();
		}
	}
	
	/**
	 * 
	 * @Description: 充值接口（正负充值）
	 * @Title: recharge  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 上午11:02:46
	 *
	 * @param postdata
	 * @param request
	 * @param response
	 * @return
	 *   
	 * @throws
	 */
	@RequestMapping(value = "recharge" , produces = "application/json; charset=utf-8")
	public @ResponseBody String recharge(String postdata, HttpServletRequest request, HttpServletResponse response){
		ResultVo vo = new ResultVo();
		log.info("接口处理开始：");
		
		try {
			JsonBean jsonbean = DateUtil.procJson(postdata);
			log.info("接口必要参数名：acccountno----daybook----placeno----pirce----score----membertypeno----membertypename----verifyInfo********传入参数：" + jsonbean);
			if(null == jsonbean.getAccountno() || "".equals(jsonbean.getAccountno()) || 
			   null == jsonbean.getDaybook() || 
			   null == jsonbean.getPrice() || "".equals(jsonbean.getPrice()) || 
			   null == jsonbean.getScore() || "".equals(jsonbean.getScore()) || 
			   null == jsonbean.getMembertypeno() || "".equals(jsonbean.getMembertypeno()) ||
			   null == jsonbean.getVerifyInfo() || "".equals(jsonbean.getVerifyInfo()) ||
			   null == jsonbean.getMembertypename() || "".equals(jsonbean.getMembertypename())){
				log.info("缺少相应参数");
				vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(DateUtil.validationDate(jsonbean.getPlaceno(), jsonbean.getVerifyInfo())){
				vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
				log.info("电影院不存在");
				vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());
			
			if("1".equals(memAccount.getUsable())){
				log.info("账户禁用");
				vo.setResultcode(ErrorCode.ACCOUNT_USABLE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_USABLE_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if(null == memAccount){
				log.info("会员不存在");
				vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			if((memAccount.getBalance() + jsonbean.getPrice()) < 0){
				log.info("会员账户余额不足");
				vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			vo = consumptionAndRechargeService.rechargeInformation(jsonbean);
			return vo.toChangeResultJson();
		} catch (Exception e) {
			log.error("系统异常" + e);
			vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
			return vo.toChangeResultJson();
		}
	}
	
}
