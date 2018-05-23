package com.cmts.xm.bean.table.common;

import java.util.List;

import com.cmts.xm.bean.table.member.C0001_MEM_ACCOUNT;
import com.cmts.xm.bean.table.member.C0002_MEM_DAYBOOK;
import com.cmts.xm.bean.table.member.C0003_MEM_MEMBER;

/**
 * 
 * @Description: json实体
 * @ClassName: JsonBean
 * @author: 王淼 
 * @date: 2018年5月19日 上午10:13:48  
 *
 */
public class JsonBean {
	
	private C0003_MEM_MEMBER member;
	
	private C0001_MEM_ACCOUNT account;
	
	private List<C0002_MEM_DAYBOOK> daybook;
	
	private String placeno;
	
	private String opttype;
	
	private String accountno;
	
	private String cardno;
	
	private String memoryid;
	
	private Double traceprice;
	
	private Double score;
	
	private Double price;
	
	private Long checkcount;
	
	private Double posamt;
	
	private String membertypeno;
	
	private String membertypename;
	
	private String password;
	
	private String leftdate;
	
	private String verifyInfo;

	public C0003_MEM_MEMBER getMember() {
		return member;
	}

	public void setMember(C0003_MEM_MEMBER member) {
		this.member = member;
	}

	public C0001_MEM_ACCOUNT getAccount() {
		return account;
	}

	public void setAccount(C0001_MEM_ACCOUNT account) {
		this.account = account;
	}

	public List<C0002_MEM_DAYBOOK> getDaybook() {
		return daybook;
	}

	public void setDaybook(List<C0002_MEM_DAYBOOK> daybook) {
		this.daybook = daybook;
	}

	public String getPlaceno() {
		return placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

	public String getOpttype() {
		return opttype;
	}

	public void setOpttype(String opttype) {
		this.opttype = opttype;
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

	public Double getTraceprice() {
		return traceprice;
	}

	public void setTraceprice(Double traceprice) {
		this.traceprice = traceprice;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Long getCheckcount() {
		return checkcount;
	}

	public void setCheckcount(Long checkcount) {
		this.checkcount = checkcount;
	}

	public Double getPosamt() {
		return posamt;
	}

	public void setPosamt(Double posamt) {
		this.posamt = posamt;
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

	public String getLeftdate() {
		return leftdate;
	}

	public void setLeftdate(String leftdate) {
		this.leftdate = leftdate;
	}

	public String getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(String verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public String getMembertypename() {
		return membertypename;
	}

	public void setMembertypename(String membertypename) {
		this.membertypename = membertypename;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "JsonBean [member=" + member + ", account=" + account + ", daybook=" + daybook + ", placeno=" + placeno
				+ ", opttype=" + opttype + ", accountno=" + accountno + ", cardno=" + cardno + ", memoryid=" + memoryid
				+ ", traceprice=" + traceprice + ", score=" + score + ", price=" + price + ", checkcount=" + checkcount
				+ ", posamt=" + posamt + ", membertypeno=" + membertypeno + ", membertypename=" + membertypename
				+ ", password=" + password + ", leftdate=" + leftdate + ", verifyInfo=" + verifyInfo + "]";
	}

}
