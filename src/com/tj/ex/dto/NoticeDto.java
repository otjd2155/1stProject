package com.tj.ex.dto;

import java.sql.Date;

public class NoticeDto {
	private int nNo;
	private String aId;
	private String aName;
	private String nTitle;
	private String nContent;
	private Date   nRdate;
	private int nHit;
	public NoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeDto(int nNo, String aId, String aName, String nTitle, String nContent, Date nRdate, int nHit) {
		super();
		this.nNo = nNo;
		this.aId = aId;
		this.aName = aName;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nRdate = nRdate;
		this.nHit = nHit;
	}
	public int getnNo() {
		return nNo;
	}
	public void setnNo(int nNo) {
		this.nNo = nNo;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public Date getnRdate() {
		return nRdate;
	}
	public void setnRdate(Date nRdate) {
		this.nRdate = nRdate;
	}
	public int getnHit() {
		return nHit;
	}
	public void setnHit(int nHit) {
		this.nHit = nHit;
	}
	@Override
	public String toString() {
		return "NoticeDto [nNo=" + nNo + ", aId=" + aId + ", aName=" + aName + ", nTitle=" + nTitle + ", nContent="
				+ nContent + ", nRdate=" + nRdate + ", nHit=" + nHit + "]";
	}
	
}
