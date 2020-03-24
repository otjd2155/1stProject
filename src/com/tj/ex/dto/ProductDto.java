package com.tj.ex.dto;

import java.sql.Date;

public class ProductDto {
	private String pCode;
	private String pName;
	private String pType;
	private String pFilename;
	private String pOrign;
	private String pWeight;
	private String pPart;
	private String pUse;
	private int    pPrice;
	private int    pStock;
	private Date   pRdate;
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(String pCode, String pName, String pType, String pFilename, String pOrign, String pWeight,
			String pPart, String pUse, int pPrice, int pStock, Date pRdate) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.pType = pType;
		this.pFilename = pFilename;
		this.pOrign = pOrign;
		this.pWeight = pWeight;
		this.pPart = pPart;
		this.pUse = pUse;
		this.pPrice = pPrice;
		this.pStock = pStock;
		this.pRdate = pRdate;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpFilename() {
		return pFilename;
	}
	public void setpFilename(String pFilename) {
		this.pFilename = pFilename;
	}
	public String getpOrign() {
		return pOrign;
	}
	public void setpOrign(String pOrign) {
		this.pOrign = pOrign;
	}
	public String getpWeight() {
		return pWeight;
	}
	public void setpWeight(String pWeight) {
		this.pWeight = pWeight;
	}
	public String getpPart() {
		return pPart;
	}
	public void setpPart(String pPart) {
		this.pPart = pPart;
	}
	public String getpUse() {
		return pUse;
	}
	public void setpUse(String pUse) {
		this.pUse = pUse;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpStock() {
		return pStock;
	}
	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	public Date getpRdate() {
		return pRdate;
	}
	public void setpRdate(Date pRdate) {
		this.pRdate = pRdate;
	}
	@Override
	public String toString() {
		return "ProductDto [pCode=" + pCode + ", pName=" + pName + ", pType=" + pType + ", pFilename=" + pFilename
				+ ", pOrign=" + pOrign + ", pWeight=" + pWeight + ", pPart=" + pPart + ", pUse=" + pUse + ", pPrice="
				+ pPrice + ", pStock=" + pStock + ", pRdate=" + pRdate + "]";
	}
	
	
}
