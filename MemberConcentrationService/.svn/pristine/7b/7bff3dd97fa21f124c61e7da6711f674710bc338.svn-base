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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.MemberAccountHlService;
import com.cmts.xm.utils.ErrorCode;
import com.cmts.xm.utils.Md5;
/**
 * 
 * @Description: 积分换礼
 * @ClassName: MemberIntegralGift
 * @author: 王海明
 * @date: 2018年5月19日 
 *
 */
@SuppressWarnings({ "all" })
@Controller
@RequestMapping(value = "/")
public class MemberIntegralGift {
	
	private static Logger log = Logger.getLogger(MemberIntegralGift.class);
	
	@Autowired
	private MemberAccountHlService memberAccountHlService;
	
	@Autowired
	private CommonService  commonService;
	
	@RequestMapping(value = "/memberIntegralGift", produces = "application/json; charset=utf-8")
	public @ResponseBody String memberIntegralGift(HttpServletRequest request,HttpServletResponse response){
		ResultVo vo = new ResultVo();
		String postdata = request.getParameter("postdata");
		
		try {
			JsonBean jsonbean = DateUtil.procJson(postdata);
			
			log.info("接口必要参数名：acccountno----daybook----placeno----price----score----verifyInfo********传入参数：" + jsonbean);
			if(null == jsonbean.getAccountno() || "".equals(jsonbean.getAccountno()) || 
			   null == jsonbean.getDaybook() || "".equals(jsonbean.getDaybook()) ||
			   null == jsonbean.getPlaceno() || "".equals(jsonbean.getPassword()) ||
			   null == jsonbean.getPrice() || "".equals(jsonbean.getPrice()) || 
//			   null == jsonbean.getMembertypeno() || "".equals(jsonbean.getMembertypeno()) ||
//			   null == jsonbean.getMembertypename() || "".equals(jsonbean.getMembertypename()) ||
			   null == jsonbean.getScore() || "".equals(jsonbean.getScore()) ){
				log.info("缺少相应参数：acccountno=" + jsonbean.getAccountno() + 
						  " daybook=" + jsonbean.getDaybook() +
						  " placeno=" + jsonbean.getPlaceno() +
						  " price=" + jsonbean.getPrice() +
//						  " membertypeno=" + jsonbean.getMembertypeno() +
//						  " membertypename=" + jsonbean.getMembertypename() +
						  " score=" + jsonbean.getScore());
				log.info("缺少相应参数:"+jsonbean.toString());
				vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
				vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
				return vo.toChangeResultJson();

			}
			
			if(DateUtil.validationDate(jsonbean.getPlaceno(), jsonbean.getVerifyInfo())){
				vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
				log.info("验证错误:"+vo.toString());
				return vo.toChangeResultJson();
			}
			
			if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
				vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
				log.info("影院不存在"+vo.toString());
				return vo.toChangeResultJson();
			}	
									
			C0001_MEM_ACCOUNT memberAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());

			if(null == memberAccount){
				vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
				log.info("没有该会员数据"+vo.toString());
				return vo.toChangeResultJson();
			}
			if(memberAccount.getBalance() + jsonbean.getPrice() < 0 ){
				vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
				log.info("账号金额不足"+vo.toString());
				return vo.toChangeResultJson();
			}
			if(memberAccount.getScore() + jsonbean.getScore() < 0 ){
				vo.setResultcode(ErrorCode.SCORE_NOTENOUGH_ERROR.getValues());
				vo.setResultmsg(ErrorCode.SCORE_NOTENOUGH_ERROR.getMsg());
				log.info("账号积分不足"+vo.toString());
				return vo.toChangeResultJson();
			}	
			
			if(StringUtils.isNotBlank(memberAccount.getUsable()) && memberAccount.getUsable().equals("1") ){
				vo.setResultcode(ErrorCode.ACCOUNT_USABLE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACCOUNT_USABLE_ERROR.getMsg());
				log.info("会员账户禁用"+vo.toString());
				return vo.toChangeResultJson();
			}
			
			vo = memberAccountHlService.addMemberAccountHl(jsonbean);
			log.info(vo.toString());
			return vo.toChangeResultJson();	
		} catch (Exception e) {
			vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
			log.info("程序内部错误"+vo.toString());
			log.info(e);
			e.printStackTrace();
			return vo.toChangeResultJson();
		}
		
	}

	
}
