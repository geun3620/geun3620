package com.hk.dtos;

import java.util.Date;

public class CalDto {

	private int cseq;
	private String id;
	private String caltitle;
	private String calcontent;
	private String mdate;
	private Date calregdate;
	
	//조인 할 경우 
	private LoginDto loginDto;
	
	public CalDto() {
		super();
	}
	
	public CalDto(int cseq, String id, String caltitle, String calcontent, String mdate, Date calregdate) {
		super();
		this.cseq = cseq;
		this.id = id;
		this.caltitle = caltitle;
		this.calcontent = calcontent;
		this.mdate = mdate;
		this.calregdate = calregdate;
	}
	//-----------------------------
	//loginDto객체에 대한 set/get 메서드 추가
	public LoginDto getLoginDto() {
		return loginDto;
	}

	public void setLoginDto(LoginDto loginDto) {
		this.loginDto = loginDto;
	}
	//---------------------------------
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaltitle() {
		return caltitle;
	}
	public void setCaltitle(String caltitle) {
		this.caltitle = caltitle;
	}
	public String getCalcontent() {
		return calcontent;
	}
	public void setCalcontent(String calcontent) {
		this.calcontent = calcontent;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public Date getCalregdate() {
		return calregdate;
	}
	public void setCalregdate(Date calregdate) {
		this.calregdate = calregdate;
	}
	
	@Override
	public String toString() {
		return "CalDto [cseq=" + cseq + ", id=" + id + ", caltitle=" + caltitle + ", calcontent=" + calcontent
				+ ", mdate=" + mdate + ", calregdate=" + calregdate + "]";
	}
}
