/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2018年5月19日
* 功能：
* 作 者：李庆国
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.service.init.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;
import com.cmts.xm.bean.vo.InitDataAccount;
import com.cmts.xm.bean.vo.InitDataVo;
import com.cmts.xm.dao.DaoFactory;
import com.cmts.xm.service.init.InitDataService;

/**
 * @FileName InitDataServiceImpl.java
 * @Package com.cmts.xm.service.init.impl
 * @Description TODO【用一句话描述该文件做什么】
 * @Author 李庆国
 * @date 2018年5月19日 下午2:40:41 
 * @Version V1.0.1
 */
@Service
public class InitDataServiceImpl implements InitDataService {
	
	@Autowired
	private DaoFactory dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void initData(InitDataVo initData) throws Exception {
		
		for (InitDataAccount account : initData.getAccount()) {
			account.setPlaceno(initData.getPlaceno());
			dao.insert(account);
			
			C0003_MEM_MEMBER member = account.getMember();
			int count = dao.getCount("select count(0) from C0003_MEM_MEMBER where placeno = ? and memberno = ?", new Object[]{initData.getPlaceno(),member.getMemberno()}); 
			if (count == 0) {
				member.setPlaceno(initData.getPlaceno());
				dao.insert(member);
			}
		}
	}
}

