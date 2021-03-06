package com.cmts.xm.service.member.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.DaoFactory;
import com.cmts.xm.dao.exception.NoColumnAnnotationFoundException;
import com.cmts.xm.dao.exception.NoIdAnnotationFoundException;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.MemcardService;
import com.cmts.xm.utils.ErrorCode;

/** 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 *公司：哈尔滨富力通(研发二部)             			 
 *                                        	 
 *项目： MemberConcentrationService	              	 	   
 *作者：周贵雨     			              		 
 *时间：2018年5月19日 上午11:09:32                 		 
 *功能：                                                                                                  	 	 
 *___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
 */
@SuppressWarnings({ "all" })
@Service
public class MencardServiceImpl  implements MemcardService{
	@Autowired
	private DaoFactory dao;
	@Autowired
	private CommonService commonservice;
	private static Logger log = Logger.getLogger(MencardServiceImpl.class);	
	//换卡： 更改卡号,芯片号,续期，余额,积分
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo updateCardno(JsonBean jsonbean) throws Exception {
		ResultVo vo = new ResultVo();
		String sql="";
		String accountno= jsonbean.getAccountno();//账户号
		String cardno=	  jsonbean.getCardno();    //卡面号
		String memoryid=  jsonbean.getMemoryid();  //芯片号 
		String placeno=	  jsonbean.getPlaceno();   //影城编码
		String lifedate=  jsonbean.getLeftdate();  //续期
		Double score=	  jsonbean.getScore();     //积分
		Double balance=	  jsonbean.getTraceprice();//余额
		try {
		
		 String status = commonservice.procMemdaybook(jsonbean);
		 if(status!="" && status.equals("-1")){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
				return vo;	
		 }
		// double newbalance = account.getBalance() - jsonbean.getTraceprice(); 
		// double newscore   = account.getScore()   + jsonbean.getScore();
		 sql="update C0001_MEM_ACCOUNT "
		 	+ "set cardno="+cardno+", memoryid="+memoryid+",lifedate='"+lifedate+"',balance = balance+'"+jsonbean.getTraceprice()+"', score =score+'"+jsonbean.getScore()+"'"
		 	+ "where accountno = "+accountno+" and placeno = "+placeno+"";
		  System.err.println("换卡 ："+sql);
		   if(dao.updatesql(sql, null)){
			    vo.setResultcode("0");
				vo.setResultmsg("换卡_修改成功");
				vo.getResultMap().put("serialnumber", status);
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.CHANGECARD_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.CHANGECARD_PROC_ERROR.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}


   //补卡 更改 卡号，芯片号，余额，积分
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo updatebucard(JsonBean jsonbean) {
		ResultVo vo = new ResultVo();
		String sql="";
		String accountno=jsonbean.getAccountno();
		String cardno=	 jsonbean.getCardno();
		String memoryid= jsonbean.getMemoryid();
		String placeno=	 jsonbean.getPlaceno();
		Double score=	 jsonbean.getScore();
		Double balance=	 jsonbean.getTraceprice();
		try {

		 String status = commonservice.procMemdaybook(jsonbean);
		 if(status!="" && status.equals("-1")){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
				return vo;	
		 }
		// double newbalance = account.getBalance() - jsonbean.getTraceprice(); 
		// double newscore   = account.getScore() + jsonbean.getScore();
		 sql="update C0001_MEM_ACCOUNT "
		 	+ "set cardno="+cardno+", memoryid="+memoryid+",balance = balance+'"+jsonbean.getTraceprice()+"', score = score+'"+jsonbean.getScore()+"'"
		 	+ "where accountno = "+accountno+" and placeno = "+placeno+"";
		   System.err.println("补卡 ："+sql);
			if(dao.updatesql(sql, null)){
				vo.setResultcode("0");
				vo.setResultmsg("补卡_修改成功");
				vo.getResultMap().put("serialnumber", status);
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.BUCARD_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.BUCARD_PROC_ERROR.getMsg());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
   //激活 更改续期，芯片号，余额，积分
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo updateActivateCard(JsonBean jsonbean) {
		ResultVo vo = new ResultVo();
		String sql="";
		String accountno=	jsonbean.getAccountno();
		String cardno=	jsonbean.getCardno();
		String memoryid=	jsonbean.getMemoryid();
		String placeno=	jsonbean.getPlaceno();
		Double score=	jsonbean.getScore();
		Double balance=	jsonbean.getTraceprice();
		String lifeDate =jsonbean.getLeftdate();
		try {

		 String status = commonservice.procMemdaybook(jsonbean);
		 if(status!="" && status.equals("-1")){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
				return vo;	
			}
		// double newbalance = account.getBalance() - jsonbean.getTraceprice(); 
		// double newscore   = account.getScore()   + jsonbean.getScore();
		 sql="update C0001_MEM_ACCOUNT "
		 	+ "set lifeDate='"+lifeDate+"', memoryid="+memoryid+",balance =balance+'"+jsonbean.getPrice()+"', score =score+'"+jsonbean.getScore()+"'"
		 	+ "where accountno = "+accountno+" and placeno = "+placeno+"";
		 System.err.println("激活 ："+sql);
		 if(dao.updatesql(sql, null)){
			    vo.setResultcode("0");
				vo.setResultmsg("激活_修改成功");
				vo.getResultMap().put("serialnumber", status);
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				vo.setResultcode(ErrorCode.ACTIVATECARD_PROC_ERROR.getValues());
				vo.setResultmsg(ErrorCode.ACTIVATECARD_PROC_ERROR.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}


}
