/* ========================================================
 * 哈尔滨富利通科技有限公司研发二部
 * 日 期：2015-11-13
 * 功能：模块授权表
 * 作 者：赵斌
 * 版 本：1.0
 * =========================================================
 */
package com.cmts.xm.bean.table.member;

import com.cmts.xm.dao.annotation.Id;
import com.cmts.xm.dao.annotation.Table;
/**
 * 会员交易表
* @FileName Mem_DayBook.java
* @Package com.cmts.bean.member
* @Author 赵斌
* @date 2017-12-12 上午10:14:12 
* @Version V1.0.1
 */
@Table(value = "C0002_MEM_DAYBOOK")
public class C0002_MEM_DAYBOOK {

	@Id
	// 交易流水号
	private String traceno;
	// 会员账户编码
	private String accountno;
	// 交易类型编号
	private String tracetypeno;
	// 交易日期
	private String tracedate;
	// 交易日期
	private String tracetime;
	// 记前会员账户级别编号
	private String membertypeno;
	// 记前余额
	private Double oldbalance;
	// 记前积分
	private Double oldscore;
	// 剩余次数
	private Integer oldckcount;
	// 礼包余额
	private Double oldposamt;
	// 次数
	private Integer ckcount;
	// 礼包金额
	private Double posamt;
	// 服务费
	private Double feeprice;
	// 服务费支付方式
	private String feetracetypeno;
	// 折后金额
	private Double price;
	// 积分
	private Double score;
	// 票数
	private Integer ticketsum;
	// 操作员
	private String username;
	// 交易地点
	private String cinemaname;
	// 交易备注
	private String tracememo;
	// 折前金额
	private Double oldprice;
	//费用
	private Double traceprice;
	// 礼包编号
	private String giftno;
	// 排期编号
	private String featureno;
	// 影片编码
	private String filmno;
	// 补现
	private Double bxamt;
	// 补现支付方式
	private String bxtracetypeno;
	// 补现支付方式
	private String paytraceno;
	//中心流水号
	private String centertraceno;
	//影院编码
	private String placeno;

	public String getTraceno() {
		return traceno;
	}

	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getTracetypeno() {
		return tracetypeno;
	}

	public void setTracetypeno(String tracetypeno) {
		this.tracetypeno = tracetypeno;
	}

	public String getTracedate() {
		return tracedate;
	}

	public void setTracedate(String tracedate) {
		this.tracedate = tracedate;
	}

	public String getTracetime() {
		return tracetime;
	}

	public void setTracetime(String tracetime) {
		this.tracetime = tracetime;
	}

	public String getMembertypeno() {
		return membertypeno;
	}

	public void setMembertypeno(String membertypeno) {
		this.membertypeno = membertypeno;
	}

	public Double getOldbalance() {
		return oldbalance;
	}

	public void setOldbalance(Double oldbalance) {
		this.oldbalance = oldbalance;
	}

	public Double getOldscore() {
		return oldscore;
	}

	public void setOldscore(Double oldscore) {
		this.oldscore = oldscore;
	}

	public Integer getOldckcount() {
		return oldckcount;
	}

	public void setOldckcount(Integer oldckcount) {
		this.oldckcount = oldckcount;
	}

	public Double getOldposamt() {
		return oldposamt;
	}

	public void setOldposamt(Double oldposamt) {
		this.oldposamt = oldposamt;
	}

	public Integer getCkcount() {
		return ckcount;
	}

	public void setCkcount(Integer ckcount) {
		this.ckcount = ckcount;
	}

	public Double getPosamt() {
		return posamt;
	}

	public void setPosamt(Double posamt) {
		this.posamt = posamt;
	}

	public Double getFeeprice() {
		return feeprice;
	}

	public void setFeeprice(Double feeprice) {
		this.feeprice = feeprice;
	}

	public String getFeetracetypeno() {
		return feetracetypeno;
	}

	public void setFeetracetypeno(String feetracetypeno) {
		this.feetracetypeno = feetracetypeno;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getTicketsum() {
		return ticketsum;
	}

	public void setTicketsum(Integer ticketsum) {
		this.ticketsum = ticketsum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}

	public String getTracememo() {
		return tracememo;
	}

	public void setTracememo(String tracememo) {
		this.tracememo = tracememo;
	}

	public Double getOldprice() {
		return oldprice;
	}

	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}

	public Double getTraceprice() {
		return traceprice;
	}

	public void setTraceprice(Double traceprice) {
		this.traceprice = traceprice;
	}

	public String getGiftno() {
		return giftno;
	}

	public void setGiftno(String giftno) {
		this.giftno = giftno;
	}

	public String getFeatureno() {
		return featureno;
	}

	public void setFeatureno(String featureno) {
		this.featureno = featureno;
	}

	public String getFilmno() {
		return filmno;
	}

	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}

	public Double getBxamt() {
		return bxamt;
	}

	public void setBxamt(Double bxamt) {
		this.bxamt = bxamt;
	}

	public String getBxtracetypeno() {
		return bxtracetypeno;
	}

	public void setBxtracetypeno(String bxtracetypeno) {
		this.bxtracetypeno = bxtracetypeno;
	}

	public String getPaytraceno() {
		return paytraceno;
	}

	public void setPaytraceno(String paytraceno) {
		this.paytraceno = paytraceno;
	}

	public String getCentertraceno() {
		return centertraceno;
	}

	public void setCentertraceno(String centertraceno) {
		this.centertraceno = centertraceno;
	}

	public String getPlaceno() {
		return placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

}
