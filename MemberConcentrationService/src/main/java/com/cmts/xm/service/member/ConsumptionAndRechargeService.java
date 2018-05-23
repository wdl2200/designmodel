package com.cmts.xm.service.member;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.vo.ResultVo;

/**
 * 
 * @Description: 消费与充值Service
 * @ClassName: ConsumptionAndTopUpService
 * @author: 王淼 
 * @date: 2018年5月19日 上午11:31:24  
 *
 */
public interface ConsumptionAndRechargeService {
	
	/**
	 * 
	 * @Description: 消费
	 * @Title: consumptionInformation  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 下午12:30:08
	 *
	 * @param jsonbean
	 * @return
	 * @throws Exception
	 *   
	 * @throws
	 */
	public ResultVo consumptionInformation(JsonBean jsonbean) throws Exception;
	
	/**
	 * 
	 * @Description: 充值
	 * @Title: topUpInformation  
	 * @author: 王淼 
	 * @Date: 2018年5月19日 下午1:57:18
	 *
	 * @param jsonbean
	 * @return
	 * @throws Exception
	 *   
	 * @throws
	 */
	public ResultVo rechargeInformation(JsonBean jsonbean) throws Exception;

}
