package com.cmts.xm.web.member;

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
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.QueryAccontService;
import com.cmts.xm.service.member.UpdatePasswordService;
import com.cmts.xm.utils.ErrorCode;

import net.sf.json.JSONObject;

/**
 * 
 * @Description: 修改密码接口
 * @ClassName: UpdatePlaceOrders
 * @author: wdl
 *
 */
@SuppressWarnings({ "all" })
@Controller
@RequestMapping(value = "/")
public class UpdatePassword {
	
	private static Logger log = Logger.getLogger(UpdatePassword.class);
	
	@Autowired
	private UpdatePasswordService updatePasswordService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/updatePassword" , produces = "application/json; charset=utf-8")
	public @ResponseBody String updatePassword(String postdata, HttpServletRequest request, HttpServletResponse response){
		ResultVo vo = new ResultVo();
		try {
			JsonBean jsonBean = DateUtil.procJson(postdata);
			log.info("updatePassword传入参数：acccountno:" + jsonBean.getAccountno()+",daybook:"+jsonBean.getDaybook()+",placeno:"+jsonBean.getPlaceno()+",password:"+jsonBean.getPassword()+",verifyInfo:"+jsonBean.getVerifyInfo());
			if(null == jsonBean.getAccountno() || "".equals(jsonBean.getAccountno()) || 
					   null == jsonBean.getDaybook() || 
					   null == jsonBean.getVerifyInfo() || "".equals(jsonBean.getVerifyInfo()) || 
					   null == jsonBean.getPlaceno() || "".equals(jsonBean.getPlaceno()) ||
					   null == jsonBean.getPassword() || "".equals(jsonBean.getPassword())){
						log.info("缺少相应参数:"+jsonBean.toString());
						vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
						vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
						return vo.toChangeResultJson();
					}
			boolean isCinemaOk =commonService.selectCinemaNo(jsonBean.getPlaceno());
			if(!isCinemaOk){
				vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
				return vo.toChangeResultJson();
			}
			
			boolean isOk = DateUtil.validationDate(jsonBean.getPlaceno(), jsonBean.getVerifyInfo());
			if(isOk){
				vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
				vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
				log.info("验证错误:"+vo.toString());
				return vo.toChangeResultJson();
			}
			vo= updatePasswordService.updatePassword(jsonBean);
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
	

}
