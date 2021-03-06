package com.cmts.xm.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.dao.DaoFactory;
import com.cmts.xm.service.member.QueryAccontService;

/**
 * 
 * @Description: 查询账号
 * @ClassName: QueryAccontService
 * @author: wdl
 *
 */
@Service
public class QueryAccontServiceImpl implements QueryAccontService {
	@Autowired
	private DaoFactory dao;

	@Override
	public C0001_MEM_ACCOUNT getAccountData(String acccountno, String cardno, String memoryid,String placeno) throws Exception {
		StringBuffer sql = new StringBuffer("select * from C0001_MEM_ACCOUNT t where t.placeno=? and (t.accountno=? or t.cardno=? or t.memoryid=?) and rownum=1");
		System.out.println(sql.toString());
		System.out.println(placeno+","+acccountno+","+cardno+","+memoryid);
		return dao.findByWhere(sql.toString(), new Object[]{placeno,acccountno, cardno, memoryid}, C0001_MEM_ACCOUNT.class);
	}
	
	
	
	
}
