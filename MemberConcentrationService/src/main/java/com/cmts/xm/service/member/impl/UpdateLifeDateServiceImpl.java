package com.cmts.xm.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.vo.ResultVo;
import com.cmts.xm.dao.DaoFactory;
import com.cmts.xm.service.common.CommonService;
import com.cmts.xm.service.member.UpdateLifeDateService;
import com.cmts.xm.service.member.UpdatePasswordService;
import com.cmts.xm.utils.ErrorCode;

/**
 * 
 * @Description: 修改密码
 * @ClassName: QueryAccontService
 * @author: wdl
 *
 */
@Service
public class UpdateLifeDateServiceImpl implements UpdateLifeDateService {
	@Autowired
	private DaoFactory dao;
	
	@Autowired
	CommonService commonService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo updateLifeDate(JsonBean jsonBean) throws Exception {
		// TODO Auto-generated method stub
		//处理daybook
		String result=commonService.procMemdaybook(jsonBean);
		if(result.equals("-1")){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
			vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
			return vo;
		}
		
		StringBuffer sql = new StringBuffer("update C0001_MEM_ACCOUNT set lifedate=?");
		if(jsonBean.getScore()!=0){
			sql.append(",score=score+"+String.valueOf(jsonBean.getScore()));
		}
		if(jsonBean.getTraceprice()!=0){
			sql.append(",balance=balance+"+String.valueOf(jsonBean.getTraceprice()));
		}
		sql.append(" where placeno=? and accountno=?");
		boolean isOk =dao.updatesql(sql.toString(), new Object[]{jsonBean.getLeftdate(),jsonBean.getPlaceno(),jsonBean.getAccountno()});
		if(isOk){
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.SUCCESS.getValues());
			vo.setResultmsg(ErrorCode.SUCCESS.getMsg());
			vo.getResultMap().put("serialnumber", result);
			return vo;
		}else{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.LIFEDATE_PROC_ERROR.getValues());
			vo.setResultmsg(ErrorCode.LIFEDATE_PROC_ERROR.getMsg());
			return vo;
		}
		
		
	}
	
}
