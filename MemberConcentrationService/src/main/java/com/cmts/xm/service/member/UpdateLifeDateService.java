package com.cmts.xm.service.member;

import com.cmts.xm.bean.table.common.JsonBean;
import com.cmts.xm.bean.vo.ResultVo;

/**
 * 
 * @Description: 修改续期
 * @ClassName: QueryAccontService
 * @author: wdl
 *
 */
public interface UpdateLifeDateService {

	public ResultVo updateLifeDate(JsonBean jsonBean)throws Exception;

}
