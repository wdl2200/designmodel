package com.cmts.xm.service.member;

import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;

/**
 * 
 * @Description: 查询账号
 * @ClassName: QueryAccontService
 * @author: wdl
 *
 */
public interface QueryAccontService {

	C0001_MEM_ACCOUNT getAccountData(String acccountno, String cardno, String memoryid,String placeno)throws Exception;

}
