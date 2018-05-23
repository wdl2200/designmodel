/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2018-5-19
* 功能：
* 作 者：赵斌
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.web.member;

import java.util.HashMap;
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
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.MemberRegisterService;
import com.cmts.xm.utils.ErrorCode;
/**
 * 
* @FileName MemberRegister.java
* @Package com.cmts.xm.web.member
* @Description 会员拆集体接口
* @Author 赵斌
* @date 2018-5-19 上午10:19:12 
* @Version V1.0.1
 */
@SuppressWarnings({ "all" })
@Controller
@RequestMapping(value = "/")
public class MemberRegisterSplitGroup {
	private static Logger log = Logger.getLogger(MemberRegisterSplitGroup.class);
	@Autowired
	private CommonService commonservice;
	@Autowired
	private MemberRegisterService memberregisterservice;
	/**
	 * 
	 * @Title:        memberregistersplitgroup 
	 * @Description:  会员拆集体接口 
	 * @param:        @param request
	 * @param:        @param response
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        赵斌
	 * @Date          2018-5-19 上午10:50:15
	 */
	@RequestMapping(value = "/memberregistersplitgroup" , produces = "application/json; charset=utf-8")
	public @ResponseBody String memberregistersplitgroup(String postdata, HttpServletRequest request, HttpServletResponse response){
		ResultVo vo = new ResultVo();
		
		try {
			JsonBean jsonbean = DateUtil.procJson(postdata);
			String placeno = jsonbean.getPlaceno();
			String verifyInfo = jsonbean.getVerifyInfo();
			log.info("postdata====="+postdata);
			log.info("接口必要参数名：member----account----placeno----verifyInfo********传入参数：" + jsonbean);
			if(null == jsonbean.getMember() || null == jsonbean.getAccount()
					|| null == jsonbean.getPlaceno() || "".equals(jsonbean.getPlaceno())
					|| null == jsonbean.getVerifyInfo() || "".equals(jsonbean.getVerifyInfo())){
				log.error("缺少相应参数__postdata=" + postdata);
				vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			boolean check = DateUtil.validationDate(placeno, verifyInfo);
			if(check){
				log.error("验证失败");
				vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			boolean cinemacount = commonservice.selectCinemaNo(placeno);
			if(!cinemacount){
				log.error("电影院不存在");
				vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			vo = memberregisterservice.memberRegisterSplitGroup(jsonbean);
			log.info("操作成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
		}
		
		return vo.toChangeResultJson();
	}
}

