package com.hk.dtos;

public class LookDto {
	//냉장고
	private String rname;
	private int seq;
	private int minprice;
	private int maxprice;
	private int ramount;
	private int renergy;
	private int ryearprice;
	private String rplus;
	public LookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LookDto(String rname, int seq, int minprice, int maxprice, int ramount, int renergy, int ryearprice,
			String rplus) {
		super();
		this.rname = rname;
		this.seq = seq;
		this.minprice = minprice;
		this.maxprice = maxprice;
		this.ramount = ramount;
		this.renergy = renergy;
		this.ryearprice = ryearprice;
		this.rplus = rplus;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getMinprice() {
		return minprice;
	}
	public void setMinprice(int minprice) {
		this.minprice = minprice;
	}
	public int getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(int maxprice) {
		this.maxprice = maxprice;
	}
	public int getRamount() {
		return ramount;
	}
	public void setRamount(int ramount) {
		this.ramount = ramount;
	}
	public int getRenergy() {
		return renergy;
	}
	public void setRenergy(int renergy) {
		this.renergy = renergy;
	}
	public int getRyearprice() {
		return ryearprice;
	}
	public void setRyearprice(int ryearprice) {
		this.ryearprice = ryearprice;
	}
	public String getRplus() {
		return rplus;
	}
	public void setRplus(String rplus) {
		this.rplus = rplus;
	}
	
	//티비
	private String tname;
	//private int seq;
	private int tprice;
	private int tsize;
	private String tsound;
	private String tquality;
	private String tdisplay;
	private String tenergy;
	private String tplus;
	
	public LookDto(int seq, String tname, int tprice, int tsize, String tsound, String tquality, String tdisplay,
			String tenergy, String tplus) {
		super();
		this.seq = seq;
		this.tname = tname;
		this.tprice = tprice;
		this.tsize = tsize;
		this.tsound = tsound;
		this.tquality = tquality;
		this.tdisplay = tdisplay;
		this.tenergy = tenergy;
		this.tplus = tplus;
	}
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getTprice() {
		return tprice;
	}
	public void setTprice(int tprice) {
		this.tprice = tprice;
	}
	public int getTsize() {
		return tsize;
	}
	public void setTsize(int tsize) {
		this.tsize = tsize;
	}
	public String getTsound() {
		return tsound;
	}
	public void setTsound(String tsound) {
		this.tsound = tsound;
	}
	public String getTquality() {
		return tquality;
	}
	public void setTquality(String tquality) {
		this.tquality = tquality;
	}
	public String getTdisplay() {
		return tdisplay;
	}
	public void setTdisplay(String tdisplay) {
		this.tdisplay = tdisplay;
	}
	public String getTenergy() {
		return tenergy;
	}
	public void setTenergy(String tenergy) {
		this.tenergy = tenergy;
	}
	public String getTplus() {
		return tplus;
	}
	public void setTplus(String tplus) {
		this.tplus = tplus;
	}
	//청소기
	private String vname;
	//private int seq;
	private int vprice;
	private int vsuction;
	private int vkg;
	private int vtime;
	private int vdegree;
	private String vplus;
	private String vlink;
	
	public LookDto(int seq, String vname, int vprice, int vsuction, int vkg, int vtime, int vdegree, String vplus,
			String vlink) {
		super();
		this.seq = seq;
		this.vname = vname;
		this.vprice = vprice;
		this.vsuction = vsuction;
		this.vkg = vkg;
		this.vtime = vtime;
		this.vdegree = vdegree;
		this.vplus = vplus;
		this.vlink = vlink;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public int getVprice() {
		return vprice;
	}
	public void setVprice(int vprice) {
		this.vprice = vprice;
	}
	public int getVsuction() {
		return vsuction;
	}
	public void setVsuction(int vsuction) {
		this.vsuction = vsuction;
	}
	public int getVkg() {
		return vkg;
	}
	public void setVkg(int vkg) {
		this.vkg = vkg;
	}
	public int getVtime() {
		return vtime;
	}
	public void setVtime(int vtime) {
		this.vtime = vtime;
	}
	public int getVdegree() {
		return vdegree;
	}
	public void setVdegree(int vdegree) {
		this.vdegree = vdegree;
	}
	public String getVplus() {
		return vplus;
	}
	public void setVplus(String vplus) {
		this.vplus = vplus;
	}
	public String getVlink() {
		return vlink;
	}
	public void setVlink(String vlink) {
		this.vlink = vlink;
	}
	
	//세탁기
	private String wname;
	//private int seq;
	private int wprice;
	private int wamount;
	private String wtype;
	private String woption;
	private String wplus;
	
	public LookDto(int seq, String wname, int wprice, int wamount, String wtype, String woption, String wplus) {
		super();
		this.seq = seq;
		this.wname = wname;
		this.wprice = wprice;
		this.wamount = wamount;
		this.wtype = wtype;
		this.woption = woption;
		this.wplus = wplus;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public int getWprice() {
		return wprice;
	}
	public void setWprice(int wprice) {
		this.wprice = wprice;
	}
	public int getWamount() {
		return wamount;
	}
	public void setWamount(int wamount) {
		this.wamount = wamount;
	}
	public String getWtype() {
		return wtype;
	}
	public void setWtype(String wtype) {
		this.wtype = wtype;
	}
	public String getWoption() {
		return woption;
	}
	public void setWoption(String woption) {
		this.woption = woption;
	}
	public String getWplus() {
		return wplus;
	}
	public void setWplus(String wplus) {
		this.wplus = wplus;
	}
	
	@Override
	public String toString() {
		return "LookDto [rname=" + rname + ", seq=" + seq + ", minprice=" + minprice + ", maxprice=" + maxprice
				+ ", ramount=" + ramount + ", renergy=" + renergy + ", ryearprice=" + ryearprice + ", rplus=" + rplus
				+ ", tname=" + tname + ", tprice=" + tprice + ", tsize=" + tsize + ", tsound=" + tsound + ", tquality="
				+ tquality + ", tdisplay=" + tdisplay + ", tenergy=" + tenergy + ", tplus=" + tplus + ", vname=" + vname
				+ ", vprice=" + vprice + ", vsuction=" + vsuction + ", vkg=" + vkg + ", vtime=" + vtime + ", vdegree="
				+ vdegree + ", vplus=" + vplus + ", vlink=" + vlink + ", wname=" + wname + ", wprice=" + wprice
				+ ", wamount=" + wamount + ", wtype=" + wtype + ", woption=" + woption + ", wplus=" + wplus + "]";
	}
}
