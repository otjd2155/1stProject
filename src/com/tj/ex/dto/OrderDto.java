package com.tj.ex.dto;

import java.sql.Date;

public class OrderDto {
	private String oNo;
	private String mId;
	private String oName;
	private String oAddress;
	private String oTel;
	private Date oRdate;
	private String oState;
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDto(String oNo, String mId, String oName, String oAddress, String oTel, Date oRdate, String oState) {
		super();
		this.oNo = oNo;
		this.mId = mId;
		this.oName = oName;
		this.oAddress = oAddress;
		this.oTel = oTel;
		this.oRdate = oRdate;
		this.oState = oState;
	}
	public String getoNo() {
		return oNo;
	}
	public void setoNo(String oNo) {
		this.oNo = oNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public String getoTel() {
		return oTel;
	}
	public void setoTel(String oTel) {
		this.oTel = oTel;
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
		return "OrderDto [oNo=" + oNo + ", mId=" + mId + ", oName=" + oName + ", oAddress=" + oAddress + ", oTel="
				+ oTel + ", oRdate=" + oRdate + ", oState=" + oState + "]";
	}
	
	
	
}
