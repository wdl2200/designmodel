/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2018年5月19日
* 功 能：
* 作 者：李丹丹
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.service.member;


import org.springframework.stereotype.Service;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.vo.ResultVo;

@Service
public interface AdjustAccountService {
	
	
	
	/** 
	 * @Title:        insertDaybookUpdateAccount 
	 * @Description:  TODO会员调级-插入流水表更新会员表
	 * @param:        @param daybook
	 * @param:        @param account
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Boolean    
	 * @throws 
	 * @author        李丹丹
	 * @Date          2018年5月19日 上午11:45:44 
	 */
	public ResultVo insertDaybookUpdateAccount(JsonBean jsonbean,C0001_MEM_ACCOUNT account) throws Exception;
	
	/** 
	 * @Title:        insertDaybookUpdateScore 
	 * @Description:  TODO会员调分-插入流水表更新会员表
	 * @param:        @param jsonbean
	 * @param:        @param account
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       ResultVo    
	 * @throws 
	 * @author        李丹丹
	 * @Date          2018年5月19日 下午1:52:27 
	 */
	public ResultVo insertDaybookUpdateScore(JsonBean jsonbean,C0001_MEM_ACCOUNT account) throws Exception;
	
	/** 
	 * @Title:        insertDaybookBuyGiftPackage 
	 * @Description:  TODO购买礼包-插入流水表更新会员表
	 * @param:        @param jsonbean
	 * @param:        @param account
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       ResultVo    
	 * @throws 
	 * @author        李丹丹
	 * @Date          2018年5月19日 下午2:08:02 
	 */
	public ResultVo insertDaybookBuyGiftPackage(JsonBean jsonbean,C0001_MEM_ACCOUNT account) throws Exception;
}

