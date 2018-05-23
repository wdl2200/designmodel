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
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.member.ReportLossService;

/**
 * 
 * @Description: 会员卡挂失
 * @ClassName: ReportLoss
 * @author: liuc
 * @date: 2018年05月19日 上午10:31:59  
 *
 */
@SuppressWarnings({ "all" })
@Controller
@RequestMapping(value = "/")
public class ReportLoss {
	
	private static Logger log = Logger.getLogger(ReportLoss.class);
	@Autowired
	private ReportLossService reportLossService;
	
	//挂失 
	@RequestMapping(value = "/reportLoss" , produces = "application/json; charset=utf-8")
	public @ResponseBody String reportLoss(String postdata,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Class<C0002_MEM_DAYBOOK>> retMap = new HashMap<String, Class<C0002_MEM_DAYBOOK>>();  
		retMap.put("daybook", C0002_MEM_DAYBOOK.class);
		//JsonBean jsonBean = (JsonBean) JSONObject.toBean(JSONObject.fromObject(postdata), JsonBean.class, retMap);
		JsonBean jsonBean = DateUtil.procJson(postdata);
		ResultVo vo = new ResultVo();
		try {
			log.info("接口必要参数名：acccountno----daybook----placeno----traceprice----score----verifyInfo********传入参数：" + jsonBean);
			vo=reportLossService.reportLoss( jsonBean);
			log.info(vo.toString());
			System.out.println("调用成功--返回为"+vo.toString());
			return vo.toChangeResultJson();
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setResultcode("1");
			vo.setResultmsg("接口异常:" + e.getMessage());
			log.info(e);
		}
		return vo.toChangeResultJson();
	}
	
}
