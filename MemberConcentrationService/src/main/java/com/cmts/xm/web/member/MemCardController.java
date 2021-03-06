package com.cmts.xm.web.member;
/** 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 *公司：哈尔滨富力通(研发二部)             			 
 *                                        	 
 *项目： MemberConcentrationService	              	 	   
 *作者：周贵雨     			              		 
 *时间：2018年5月19日 上午9:26:59                 		 
 *功能：     换卡 补卡 激活 接口                                                                                             	 	 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 */
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.annotation.NoCheckPermission;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.MemcardService;
import com.cmts.xm.utils.ErrorCode;
import com.cmts.xm.utils.HttpUtils;

	@SuppressWarnings({ "all" })
	@Controller
	@RequestMapping(value = "/")
	public class MemCardController {
		
		private static Logger log = Logger.getLogger(MemCardController.class);		
		@Autowired
		private MemcardService  memcardService;
		@Autowired
		private CommonService  commonService;
		
		//换卡接口
		@RequestMapping(value = "/changeCard" , produces = "application/json; charset=utf-8")
		public @ResponseBody String changeCard(HttpServletRequest request, HttpServletResponse response) throws Exception{
			 ResultVo vo = new ResultVo();
		     String postdata= request.getParameter("postdata");
		     log.info("传入参数__postdata=" + postdata);
			
		     JsonBean jsonbean = DateUtil.procJson(postdata);

			
			try {
   
			    log.info("接口必要参数名：acccountno----daybook----placeno----cardno----memory----tracepirce----score---leftdate----verifyInfo********传入参数：" + jsonbean);
				if(null == jsonbean.getAccountno()|| "".equals(jsonbean.getAccountno())|| 
				   null == jsonbean.getDaybook()   || 
				   null == jsonbean.getPlaceno()   || "".equals(jsonbean.getPlaceno())   ||
				   null == jsonbean.getTraceprice()|| "".equals(jsonbean.getTraceprice())|| 
				   null == jsonbean.getScore() 	   || "".equals(jsonbean.getScore())     ||
				   null == jsonbean.getCardno()    || "".equals(jsonbean.getCardno())    ||
				   null == jsonbean.getMemoryid()  || "".equals(jsonbean.getMemoryid())  || 
				   null == jsonbean.getLeftdate()  || "".equals(jsonbean.getLeftdate() ))
				{ 
					log.info("缺少相应参数");
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
				
				C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());
				if(null == memAccount){
					vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
					log.info("账号错误:"+vo.toString());
					return vo.toChangeResultJson();
				}				
				
				if(jsonbean.getTraceprice()>0){
					if(memAccount.getBalance()<jsonbean.getTraceprice()){
						vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
						vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
						log.info("账号余额不足:"+vo.toString());
						return vo.toChangeResultJson();
					}
				}
				
				if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
					vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
					return vo.toChangeResultJson();
				}

				vo = memcardService.updateCardno(jsonbean);//换卡 更改 卡号
				log.info(vo.toString());
				return vo.toChangeResultJson();
			} catch (Exception e) {
				vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
				vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
				log.info("程序内部错误:"+vo.toString());
				log.info(e);
				e.printStackTrace();
				return vo.toChangeResultJson();
			}
		}
		
	   // 补卡接口
		@RequestMapping(value = "/buCard" , produces = "application/json; charset=utf-8")
		public @ResponseBody String buCard(HttpServletRequest request, HttpServletResponse response) throws Exception{
			 ResultVo vo = new ResultVo();
		     String postdata= request.getParameter("postdata");
		     log.info("传入参数__postdata=" + postdata);
		     JsonBean jsonbean = DateUtil.procJson(postdata);

			
			try {
			    log.info("接口必要参数名：acccountno----daybook----placeno----cardno----memory----tracepirce----score----verifyInfo********传入参数：" + jsonbean);
				if(null == jsonbean.getAccountno() || "".equals(jsonbean.getAccountno())|| 
				   null == jsonbean.getDaybook()    || 
				   null == jsonbean.getPlaceno() || "".equals(jsonbean.getPlaceno()) ||
				   null == jsonbean.getTraceprice() || "".equals(jsonbean.getTraceprice())|| 
				   null == jsonbean.getScore()      || "".equals(jsonbean.getScore()) ||
				   null == jsonbean.getCardno()     || "".equals(jsonbean.getCardno())||
				   null == jsonbean.getMemoryid()   || "".equals(jsonbean.getMemoryid())  )
				{
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
				
				C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());
				if(null == memAccount){
					vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
					log.info("账号错误:"+vo.toString());
					return vo.toChangeResultJson();
					
				}
				if(jsonbean.getTraceprice()>0){
					if(memAccount.getBalance()<jsonbean.getTraceprice()){
						vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
						vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
						log.info("账号余额不足:"+vo.toString());
						return vo.toChangeResultJson();
					}
				}

				if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
					vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
					return vo.toChangeResultJson();
				}

			 vo=memcardService.updatebucard(jsonbean);//补卡 更新卡号 芯片好 续期
			 log.info(vo.toString());
			 return vo.toChangeResultJson();
			} catch (Exception e) {
				vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
				vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
				log.info("程序内部错误:"+vo.toString());
				log.info(e);
				e.printStackTrace();
				return vo.toChangeResultJson();
			}
			
		}
		
		//激活接口
		@RequestMapping(value = "/activateCard" , produces = "application/json; charset=utf-8")
		public @ResponseBody String activateCard(HttpServletRequest request, HttpServletResponse response) throws Exception{
			 ResultVo vo = new ResultVo();
		     String postdata= request.getParameter("postdata");
		     log.info("传入参数__postdata=" + postdata);
		     JsonBean jsonbean = DateUtil.procJson(postdata);

			
			try {
			    log.info("接口必要参数名：acccountno----daybook----placeno----memoryid----lifedate---pirce----score----verifyInfo********传入参数：" + jsonbean);
				if(null == jsonbean.getAccountno() || "".equals(jsonbean.getAccountno()) || 
				   null == jsonbean.getDaybook()    || 
				   null == jsonbean.getPlaceno() || "".equals(jsonbean.getPlaceno()) ||
				   null == jsonbean.getPrice()	 	|| "".equals(jsonbean.getPrice())   || 
				   null == jsonbean.getScore()  	|| "".equals(jsonbean.getScore())   ||
				   null == jsonbean.getMemoryid() 	|| "".equals(jsonbean.getMemoryid())|| 
				   null == jsonbean.getLeftdate() 	|| "".equals(jsonbean.getLeftdate() ))
				{
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
			
				C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(jsonbean.getAccountno(), jsonbean.getPlaceno());
				if(null == memAccount){
					vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
					log.info("账号错误:"+vo.toString());
					return vo.toChangeResultJson();
				}
				
				if(jsonbean.getPrice()>0){
					if(memAccount.getBalance()<jsonbean.getPrice()){
						vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
						vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
						log.info("账号余额不足:"+vo.toString());
						return vo.toChangeResultJson();
					}
				}

				if(!commonService.selectCinemaNo(jsonbean.getPlaceno())){
					vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
					vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
					return vo.toChangeResultJson();
				}
	
				vo= memcardService.updateActivateCard(jsonbean);//激活    更新续期 积分 余额
				log.info(vo.toString());
				return vo.toChangeResultJson();
			} catch (Exception e) {
				vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
				vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
				log.info("程序内部错误:"+vo.toString());
				log.info(e);
				e.printStackTrace();
				return vo.toChangeResultJson();
			}
		}
		
		
/*		换卡：
		{"acccountno":"",daybook:[],placeno:"","cardno":"","memoryid":"",traceprice:"","score":""};
		返回：{"resultcode":"","resultmsg":"","serialnumber":"中心流水号_地面流水号"};

		补卡：
		{"acccountno":"",daybook:[],placeno:"","cardno":"","memoryid":"","traceprice":"","score":""};
		返回：{"resultcode":"","resultmsg":"","serialnumber":"中心流水号_地面流水号"};

		激活：
		{"acccountno":"",daybook:[],placeno:"","memoryid":"","pirce":"",traceprice:"","score":""};
		返回：{"resultcode":"","resultmsg":"","serialnumber":"中心流水号_地面流水号"};*/

		
}

