package com.cmts.xm.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.vo.ResultVo;
@Service
public interface MemberAccountHlService {
	/**
	 * 积分换礼、退礼
	 * @param jsonbean
	 * @return
	 * @throws Exception
	 */
	public ResultVo addMemberAccountHl(JsonBean jsonbean) throws Exception;

	
	
}
