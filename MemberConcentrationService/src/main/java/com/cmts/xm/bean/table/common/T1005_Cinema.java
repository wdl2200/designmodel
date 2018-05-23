package com.cmts.xm.bean.table.common;

import com.cmts.xm.dao.annotation.Table;

/**
 * 
 * @Description: 影院信息表
 * @ClassName: T1005_Cinema
 * @author: 王淼 
 * @date: 2017年8月31日 下午2:48:53  
 *
 */
@Table(value = "T1005_CINEMA")
public class T1005_Cinema {
	
	/**
	 * 影院编码
	 */
	private String cinemano;
	
	/**
	 * 影院名称
	 */
	private String cinemaname;

	public String getCinemano() {
		return cinemano;
	}

	public void setCinemano(String cinemano) {
		this.cinemano = cinemano;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}
}
