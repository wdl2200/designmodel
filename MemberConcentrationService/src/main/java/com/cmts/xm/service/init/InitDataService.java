/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2018年5月19日
* 功能：
* 作 者：李庆国
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.service.init;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cmts.xm.bean.vo.InitDataVo;

/**
 * @FileName InitDataService.java
 * @Package com.cmts.xm.service.init
 * @Author 李庆国
 * @date 2018年5月19日 下午2:38:35 
 * @Version V1.0.1
 */
@Service
public interface InitDataService {
	
	public void initData(InitDataVo initData) throws Exception;

}

