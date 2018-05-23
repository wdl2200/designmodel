/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2016-1-14
* 功能：
* 作 者：苏少轩
* 版 本：1.0
* =========================================================
*/
package com.cmts.xm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.cmts.xm.utils.code.DESUtil;

@SuppressWarnings("all")
public class DecryptDruidSource extends DruidDataSource{
	@Override
	public void setUsername(String username) {
		try {
			username = DESUtil.decode(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setUsername(username);
	}
	@Override
	public void setPassword(String password) {
		try {
			if (password.equals("127.0.0.1")) {
				String key = "hxcmtscn"; 
				String pw = "-1";
				while (pw.equals("-1")) {
					pw = HttpUtils.http("http://" + password + ":8888/CinemaControl/service_queryDBinfo.action", null);
				}
				password = java.net.URLDecoder.decode(DES3.decrypt(pw,key), "utf-8") ; 
			}else{
				password = DESUtil.decode(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setPassword(password);
	}
}
