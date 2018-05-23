package com.cmts.xm.service.common;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;

/**
 * 
 * @Description: 公共方法Service
 * @ClassName: commonService
 * @author: 王淼 
 * @date: 2017年11月17日 上午9:53:26  
 *
 */
public interface CommonService {

	/**
	 * 
	 * @Description: 根据影院上报编码查询一组影院编码
	 * @Title: selectCinemaNos  
	 * @author: 王淼 
	 * @Date: 2017年11月20日 下午2:51:17
	 *
	 * @param placeNo
	 * @return
	 * @throws Exception
	 *   
	 * @throws
	 */
	public boolean selectCinemaNo(String placeNo) throws Exception;
	/**
	 * 
	 * @param placeno 
	 * @Title:        selectMember 
	 * @Description:  根据会员编号查询会员信息
	 * @param:        @param memberno
	 * @param:        @param placeno
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       C0003_MEM_MEMBER    
	 * @throws 
	 * @author        赵斌
	 * @Date          2018-5-19 上午09:57:27
	 */
	public C0003_MEM_MEMBER selectMember(String memberno, String placeno) throws Exception;
	/**
	 * 
	 * @Title:        selectMemberAccount 
	 * @Description:  根据会员账户编号查询会员账户信息
	 * @param:        @param accountno
	 * @param:        @param placeno
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       C0001_MEM_ACCOUNT    
	 * @throws 
	 * @author        赵斌
	 * @Date          2018-5-19 上午09:57:30
	 */
	public C0001_MEM_ACCOUNT selectMemberAccount(String accountno,String placeno) throws Exception;
	/**
	 * 
	 * @Title:        selectMemdaybook 
	 * @Description:  查询流水是否存在 
	 * @param:        @param traceno
	 * @param:        @param placeno
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       C0002_MEM_DAYBOOK    
	 * @throws 
	 * @author        赵斌
	 * @Date          2018-5-19 下午12:03:06
	 */
	public String procMemdaybook(JsonBean jsonbean) throws Exception;
	/**
	 * 
	 * @Title:        getNewMemdaybookTraceno 
	 * @Description:  获取新流水号
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @throws 
	 * @author        赵斌
	 * @Date          2018-5-19 下午12:03:28
	 */
	public String getNewMemdaybookTraceno() throws Exception;
	public Object selectMemberAdd(String memberno, String idnum, String placeno) throws Exception;
	public Object selectMemberUpdate(String memberno, String idnum, String placeno) throws Exception;
	public C0001_MEM_ACCOUNT selectMemberAccountCardno(String cardno,
			String placeno) throws Exception;
	public C0001_MEM_ACCOUNT selectMemberAccountMemoryid(String memoryid,
			String placeno) throws Exception;
}
