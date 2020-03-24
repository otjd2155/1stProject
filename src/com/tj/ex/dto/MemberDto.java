package com.tj.ex.dto;

import java.sql.Date;

public class MemberDto {
	private String mId;
	private String mPw;
	private String mName;
	private String mAddress;
	private String mTel;
	private String mEmail;
	private Date mBirth;
	private Date mRdate;
	private int mState;
	
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MemberDto(String mId, String mPw, String mName, String mAddress, String mTel, String mEmail, Date mBirth,
			Date mRdate, int mState) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mAddress = mAddress;
		this.mTel = mTel;
		this.mEmail = mEmail;
		this.mBirth = mBirth;
		this.mRdate = mRdate;
		this.mState = mState;
	}


	public String getmId() {
		return mId;
	}


	public void setmId(String mId) {
		this.mId = mId;
	}


	public String getmPw() {
		return mPw;
	}


	public void setmPw(String mPw) {
		this.mPw = mPw;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getmAddress() {
		return mAddress;
	}


	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}


	public String getmTel() {
		return mTel;
	}


	public void setmTel(String mTel) {
		this.mTel = mTel;
	}


	public String getmEmail() {
		return mEmail;
	}


	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}


	public Date getmBirth() {
		return mBirth;
	}


	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}


	public Date getmRdate() {
		return mRdate;
	}


	public void setmRdate(Date mRdate) {
		this.mRdate = mRdate;
	}


	public int getmState() {
		return mState;
	}


	public void setmState(int mState) {
		this.mState = mState;
	}


	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mAddress=" + mAddress + ", mTel="
				+ mTel + ", mEmail=" + mEmail + ", mBirth=" + mBirth + ", mRdate=" + mRdate + ", mState=" + mState
				+ "]";
	}
	
	
}
