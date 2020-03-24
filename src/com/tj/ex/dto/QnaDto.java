package com.tj.ex.dto;

import java.sql.Date;

public class QnaDto {
	private int qNo;
	private String mId;
	private String aId;
	private String writer;
	
	private String qtitle;
	private String qContent;
	private String qFilename1;
	private String qFilename2;
	private int    qGroup;
	private int    qStep;
	private int    qIndent;
	private int    qHit;
	private Date   qRdate;
	public QnaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaDto(int qNo, String mId, String aId, String writer, String qtitle, String qContent, String qFilename1,
			String qFilename2, int qGroup, int qStep, int qIndent, int qHit, Date qRdate) {
		super();
		this.qNo = qNo;
		this.mId = mId;
		this.aId = aId;
		this.writer = writer;
		this.qtitle = qtitle;
		this.qContent = qContent;
		this.qFilename1 = qFilename1;
		this.qFilename2 = qFilename2;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qHit = qHit;
		this.qRdate = qRdate;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getqFilename1() {
		return qFilename1;
	}
	public void setqFilename1(String qFilename1) {
		this.qFilename1 = qFilename1;
	}
	public String getqFilename2() {
		return qFilename2;
	}
	public void setqFilename2(String qFilename2) {
		this.qFilename2 = qFilename2;
	}
	public int getqGroup() {
		return qGroup;
	}
	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}
	public int getqStep() {
		return qStep;
	}
	public void setqStep(int qStep) {
		this.qStep = qStep;
	}
	public int getqIndent() {
		return qIndent;
	}
	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}
	public int getqHit() {
		return qHit;
	}
	public void setqHit(int qHit) {
		this.qHit = qHit;
	}
	public Date getqRdate() {
		return qRdate;
	}
	public void setqRdate(Date qRdate) {
		this.qRdate = qRdate;
	}
	@Override
	public String toString() {
		return "QnaDto [qNo=" + qNo + ", mId=" + mId + ", aId=" + aId + ", writer=" + writer + ", qtitle=" + qtitle
				+ ", qContent=" + qContent + ", qFilename1=" + qFilename1 + ", qFilename2=" + qFilename2 + ", qGroup="
				+ qGroup + ", qStep=" + qStep + ", qIndent=" + qIndent + ", qHit=" + qHit + ", qRdate=" + qRdate + "]";
	}
	
	
	
}
