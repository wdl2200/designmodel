package com.cmts.xm.service.member;

import java.util.List;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;


/**
 * 
 * @Description:会员卡销户接口Service
 * @ClassName: EliminateAccountService
 * @author: liuc
 * @date: 2018年05月19日 上午10:52:21  
 *
 */
public interface EliminateAccountService {
	//会员卡销户
	public ResultVo eliminateAccount(JsonBean jsonBean) throws Exception;
	
}
