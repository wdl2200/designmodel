package com.cmts.xm.bean.table.member;

import com.cmts.xm.dao.annotation.Id;
import com.cmts.xm.dao.annotation.Table;

@Table(value = "C0001_MEM_ACCOUNT")
public class C0001_MEM_ACCOUNT {
@Id
private String placeno ;// 影城编码
@Id
private String accountno;//帐户号
private String  memberno;// 会员号
private String  cardno;//当前会员卡号
private String memoryid;//会员卡内部号
private String  membertypeno;//帐户级别编号
private String  password;//会员密码
private String  lifedate;//有效期限
private double  balance;//余额
private double  score;//积分
private double  scoresum;//期内积分
private String usable;//可用标志
private String  gmemberno;//集体会员编号
private double moneyadd;//账户总充值额
private String cinemaname;//会员账户影院
private Integer  ckcount;//享有兑换次数
private double  posamt;//卖品礼包金额 
private String membertypename;//级别编码名称

public String getPlaceno() {
	return placeno;
}
public void setPlaceno(String placeno) {
	this.placeno = placeno;
}
public String getAccountno() {
	return accountno;
}
public void setAccountno(String accountno) {
	this.accountno = accountno;
}
public String getMemberno() {
	return memberno;
}
public void setMemberno(String memberno) {
	this.memberno = memberno;
}
public String getCardno() {
	return cardno;
}
public void setCardno(String cardno) {
	this.cardno = cardno;
}
public String getMemoryid() {
	return memoryid;
}
public void setMemoryid(String memoryid) {
	this.memoryid = memoryid;
}
public String getMembertypeno() {
	return membertypeno;
}
public void setMembertypeno(String membertypeno) {
	this.membertypeno = membertypeno;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getLifedate() {
	return lifedate;
}
public void setLifedate(String lifedate) {
	this.lifedate = lifedate;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public double getScoresum() {
	return scoresum;
}
public void setScoresum(double scoresum) {
	this.scoresum = scoresum;
}
public String getUsable() {
	return usable;
}
public void setUsable(String usable) {
	this.usable = usable;
}
public String getGmemberno() {
	return gmemberno;
}
public void setGmemberno(String gmemberno) {
	this.gmemberno = gmemberno;
}
public double getMoneyadd() {
	return moneyadd;
}
public void setMoneyadd(double moneyadd) {
	this.moneyadd = moneyadd;
}
public String getCinemaname() {
	return cinemaname;
}
public void setCinemaname(String cinemaname) {
	this.cinemaname = cinemaname;
}
public Integer getCkcount() {
	return ckcount;
}
public void setCkcount(Integer ckcount) {
	this.ckcount = ckcount;
}
public double getPosamt() {
	return posamt;
}
public void setPosamt(double posamt) {
	this.posamt = posamt;
}
public String getMembertypename() {
	return membertypename;
}
public void setMembertypename(String membertypename) {
	this.membertypename = membertypename;
}


}
