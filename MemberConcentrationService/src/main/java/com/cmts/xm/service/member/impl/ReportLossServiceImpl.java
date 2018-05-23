package com.cmts.xm.service.member.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.DaoFactory;
import com.cmts.xm.dao.util.DateUtil;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.ReportLossService;
import com.cmts.xm.utils.ErrorCode;

/**
 * 
 * @Description: 挂失
 * @ClassName: CommonServiceImpl
 * @author: liuc
 * @date: 2018年05月19日 下午13:56:14  
 *
 */
@Service
public class ReportLossServiceImpl implements ReportLossService {
	
	@Autowired
	private DaoFactory dao;

	@Autowired
	private CommonService commonService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo reportLoss(JsonBean jsonBean)throws Exception {
		ResultVo vo = new ResultVo();
		String acccountno = jsonBean.getAccountno();
		List<C0002_MEM_DAYBOOK> daybook = jsonBean.getDaybook();
		String placeno = jsonBean.getPlaceno();
		Double traceprice =jsonBean.getTraceprice();
		Double score = jsonBean.getScore();
		String verifyInfo = jsonBean.getVerifyInfo();
		//判断是否有该影院
		boolean cinemaCount = commonService.selectCinemaNo(placeno);
		if( !cinemaCount){
			Log.info("影院不存在");
			vo.setResultcode(ErrorCode.CINEMA_LOST_ERROR.getValues());
			vo.setResultmsg(ErrorCode.CINEMA_LOST_ERROR.getMsg());
			return vo;
		}
		//判断加密
		if(DateUtil.validationDate(placeno,verifyInfo)){
			Log.info("验证码错误");
			vo.setResultcode(ErrorCode.VALICATE_CODE_ERROR.getValues());
			vo.setResultmsg(ErrorCode.VALICATE_CODE_ERROR.getMsg());
			return vo;
		}
		//判断传入参数
		if(null == acccountno || "".equals(acccountno) || 
		   null == daybook    || 
		   null == placeno 	  || "".equals(placeno)    || 
		   null == traceprice || "".equals(traceprice) || 
		   null == score      || "".equals(score)      || 
		   null == verifyInfo || "".equals(verifyInfo) ){
		   Log.info("缺少参数");
		   vo.setResultcode(ErrorCode.PARAM_LOST_ERROR.getValues());
		   vo.setResultmsg(ErrorCode.PARAM_LOST_ERROR.getMsg());
		   return vo;
		}
		//判断是否有该会员
		C0001_MEM_ACCOUNT memAccount = commonService.selectMemberAccount(acccountno, placeno);
		if(null == memAccount){
			Log.info("账号不存在");
			vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
			vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
			return vo;
		}
		//该帐户已挂失或未开卡
		//if(StringUtils.isBlank(memAccount.getMemoryid()) || "1".equals(memAccount.getUsable())){
			//Log.info(该帐户已挂失或未开卡");	
			//vo.setResultcode(ErrorCode.ACCOUNT_LOSS_NOACTIVATE_ERROR.getValues());31
			//vo.setResultmsg(ErrorCode.ACCOUNT_LOSS_NOACTIVATE_ERROR.getMsg());该帐户已挂失或未开卡
			//return vo;
		//}
		//判断余额
		if((memAccount.getBalance()+traceprice)<0){
			Log.info("账号金额不足");
			vo.setResultcode(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getValues());
			vo.setResultmsg(ErrorCode.ACCOUNT_NOTENOUGH_ERROR.getMsg());
			return vo;
		}
		//判断积分余额
		if((memAccount.getScore()+score)<0){
			Log.info("账号积分不足");
			vo.setResultcode(ErrorCode.SCORE_NOTENOUGH_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SCORE_NOTENOUGH_ERROR.getMsg());
			return vo;
		}
		//判断流水是否存在,不存在则插入
		String result=commonService.procMemdaybook(jsonBean);
		if(result.equals("-1")){
			Log.info("流水订单已存在");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
			vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
			return vo;
		}
		StringBuffer sql=  new StringBuffer(  " update c0001_mem_account t  set t.cardno = '', t.memoryid='', t.usable='1', t.balance=t.balance+?,  "
											+ " t.score=t.score + ?  "
											+ " where placeno =?  and accountno = ?");
		Log.info(sql);
		boolean boo= dao.updatesql(sql.toString(), new Object[]{traceprice,score,placeno,acccountno});
		if(boo){
			Log.info("挂失成功");
			vo.getResultMap().put("serialnumber", result);
			vo.setResultcode(ErrorCode.SUCCESS.getValues());
			vo.setResultmsg(ErrorCode.SUCCESS.getMsg());
			return vo;
		}
		else{
			Log.info("程序内部错误");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			vo.setResultcode(ErrorCode.SYSTEM_ERROR.getValues());
			vo.setResultmsg(ErrorCode.SYSTEM_ERROR.getMsg());
			return vo;
		}
	}

}