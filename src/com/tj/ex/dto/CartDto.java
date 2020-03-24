package com.tj.ex.dto;

public class CartDto {
	private int cNo;
	private String mId;
	private String pCode;
	private int cNt;
	private String pName;
	private int pPrice;
	private int cost;
	private String pFilename;
	public CartDto() {
		super();
		
	}
	public CartDto(int cNo, String mId, String pCode, int cNt, String pName, int pPrice, int cost, String pFilename) {
		super();
		this.cNo = cNo;
		this.mId = mId;
		this.pCode = pCode;
		this.cNt = cNt;
		this.pName = pName;
		this.pPrice = pPrice;
		this.cost = cost;
		this.pFilename = pFilename;
	}
	public int getcNo() {
		return cNo;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
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
	public int getcNt() {
		return cNt;
	}
	public void setcNt(int cNt) {
		this.cNt = cNt;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getpFilename() {
		return pFilename;
	}
	public void setpFilename(String pFilename) {
		this.pFilename = pFilename;
	}
	@Override
	public String toString() {
		return "CartDto [cNo=" + cNo + ", mId=" + mId + ", pCode=" + pCode + ", cNt=" + cNt + ", pName=" + pName
				+ ", pPrice=" + pPrice + ", cost=" + cost + ", pFilename=" + pFilename + "]";
	}
	
}
