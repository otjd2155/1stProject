package com.tj.ex.dto;

import java.sql.Date;

public class OrderDetailDto {
	private String oDno;
	private String oNo;
	private String pCode;
	private int cNt;
	private int cost;
	
	private String mId;
	private String pFilename;
	private String pName;
	private Date oRdate;
	private String oState;
	public OrderDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetailDto(String oDno, String oNo, String pCode, int cNt, int cost, String mId, String pFilename,
			String pName, Date oRdate, String oState) {
		super();
		this.oDno = oDno;
		this.oNo = oNo;
		this.pCode = pCode;
		this.cNt = cNt;
		this.cost = cost;
		this.mId = mId;
		this.pFilename = pFilename;
		this.pName = pName;
		this.oRdate = oRdate;
		this.oState = oState;
	}
	public String getoDno() {
		return oDno;
	}
	public void setoDno(String oDno) {
		this.oDno = oDno;
	}
	public String getoNo() {
		return oNo;
	}
	public void setoNo(String oNo) {
		this.oNo = oNo;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public int getcNt() {
		return cNt;
	}
	public void setcNt(int cNt) {
		this.cNt = cNt;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
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
	public Date getoRdate() {
		return oRdate;
	}
	public void setoRdate(Date oRdate) {
		this.oRdate = oRdate;
	}
	public String getoState() {
		return oState;
	}
	public void setoState(String oState) {
		this.oState = oState;
	}
	@Override
	public String toString() {
		return "OrderDetailDto [oDno=" + oDno + ", oNo=" + oNo + ", pCode=" + pCode + ", cNt=" + cNt + ", cost=" + cost
				+ ", mId=" + mId + ", pFilename=" + pFilename + ", pName=" + pName + ", oRdate=" + oRdate + ", oState="
				+ oState + "]";
	}
	
	
}
