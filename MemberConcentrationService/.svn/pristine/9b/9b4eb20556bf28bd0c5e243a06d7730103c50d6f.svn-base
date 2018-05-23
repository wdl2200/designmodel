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
public class UpdatePasswordServiceImpl implements UpdatePasswordService {
	@Autowired
	private DaoFactory dao;
	
	@Autowired
	CommonService commonService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVo updatePassword(JsonBean jsonBean) throws Exception {
		// TODO Auto-generated method stub
		C0001_MEM_ACCOUNT accountOk =commonService.selectMemberAccount(jsonBean.getAccountno(),jsonBean.getPlaceno());
		if(accountOk==null){
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.ACCOUNT_LOST_ERROR.getValues());
			vo.setResultmsg(ErrorCode.ACCOUNT_LOST_ERROR.getMsg());
			return vo;
		}
		if("1".equals(accountOk.getUsable())){
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.ACCOUNT_USABLE_ERROR.getValues());
			vo.setResultmsg(ErrorCode.ACCOUNT_USABLE_ERROR.getMsg());
			return vo;
		}
		String result=commonService.procMemdaybook(jsonBean);
		if(result.equals("-1")){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.PAYBOOK_PROC_ERROR.getValues());
			vo.setResultmsg(ErrorCode.PAYBOOK_PROC_ERROR.getMsg());
			return vo;
		}
		
		StringBuffer sql = new StringBuffer("update C0001_MEM_ACCOUNT set password=? where placeno=? and accountno=?");
		boolean isOk =dao.updatesql(sql.toString(), new Object[]{jsonBean.getPassword(),jsonBean.getPlaceno(),jsonBean.getAccountno()});
		if(isOk){
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.SUCCESS.getValues());
			vo.setResultmsg(ErrorCode.SUCCESS.getMsg());
			vo.getResultMap().put("serialnumber", result);
			return vo;
		}else{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResultVo vo = new ResultVo();
			vo.setResultcode(ErrorCode.PASSWORD_PROC_ERROR.getValues());
			vo.setResultmsg(ErrorCode.PASSWORD_PROC_ERROR.getMsg());
			return vo;
		}
		
	}
	
}
