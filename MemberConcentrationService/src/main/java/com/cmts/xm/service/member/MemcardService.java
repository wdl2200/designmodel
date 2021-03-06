package com.cmts.xm.service.member;

import org.springframework.stereotype.Service;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.exception.NoColumnAnnotationFoundException;
import com.cmts.xm.dao.exception.NoIdAnnotationFoundException;

/** 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 *公司：哈尔滨富力通(研发二部)             			 
 *                                        	 
 *项目： MemberConcentrationService	              	 	   
 *作者：周贵雨     			              		 
 *时间：2018年5月19日 上午9:58:55                 		 
 *功能：                                                                                                  	 	 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 */
@Service
public interface MemcardService {

	
	//换卡
	ResultVo updateCardno(JsonBean jsonbean) throws Exception;
	//补卡
	ResultVo updatebucard(JsonBean jsonbean) throws Exception;
	//激活
	ResultVo updateActivateCard(JsonBean jsonbean) throws Exception;
}
