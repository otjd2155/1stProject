package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mAddress = request.getParameter("mAddress");
		String mTel = request.getParameter("mTel");
		String mEmail = request.getParameter("mEmail");
		String mBirthStr = request.getParameter("mBirth");
		Date mBirth = null;
		System.out.println(mBirthStr);
		System.out.println(mTel);
		if(!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		MemberDao mDao = MemberDao.getInstance();
		MemberDto member = new MemberDto(mId, mPw, mName, mAddress, mTel, mEmail, mBirth, null, 0);
		int result = mDao.joinMember(member);
		if(result==MemberDao.SUCCESS) {
			request.setAttribute("joinResult", "회원가입이 완료되었습니다");
		}else {
			request.setAttribute("errorMsg", "가입이 실패되었습니다.");
		}
	}
}
