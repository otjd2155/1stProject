package com.tj.ex.dto;

import java.sql.Date;

public class ReviewDto {
	private int rNo;
	private String mId;
	private String pCode;
	private String rTitle;
	private String rContent;
	private String rFilename;
	private int rHit;
	private Date rRdate;
	
	private String mName;
	private String pFilename;
	private String pName;
	private int pPrice;
	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewDto(int rNo, String mId, String pCode, String rTitle, String rContent, String rFilename, int rHit,
			Date rRdate, String mName, String pFilename, String pName, int pPrice) {
		super();
		this.rNo = rNo;
		this.mId = mId;
		this.pCode = pCode;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rFilename = rFilename;
		this.rHit = rHit;
		this.rRdate = rRdate;
		this.mName = mName;
		this.pFilename = pFilename;
		this.pName = pName;
		this.pPrice = pPrice;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrFilename() {
		return rFilename;
	}
	public void setrFilename(String rFilename) {
		this.rFilename = rFilename;
	}
	public int getrHit() {
		return rHit;
	}
	public void setrHit(int rHit) {
		this.rHit = rHit;
	}
	public Date getrRdate() {
		return rRdate;
	}
	public void setrRdate(Date rRdate) {
		this.rRdate = rRdate;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getpFilename() {
		return pFilename;
	}
	public void setpFilename(String pFilename) {
		this.pFilename = pFilename;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	@Override
	public String toString() {
		return "ReviewDto [rNo=" + rNo + ", mId=" + mId + ", pCode=" + pCode + ", rTitle=" + rTitle + ", rContent="
				+ rContent + ", rFilename=" + rFilename + ", rHit=" + rHit + ", rRdate=" + rRdate + ", mName=" + mName
				+ ", pFilename=" + pFilename + ", pName=" + pName + ", pPrice=" + pPrice + "]";
	}
	
	
}
