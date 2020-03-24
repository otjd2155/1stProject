package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MemberModifyService implements Service {

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
		if(!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		MemberDao mDao = MemberDao.getInstance();
		MemberDto member = new MemberDto(mId, mPw, mName, mAddress, mTel, mEmail, mBirth, null, 0);
		int result = mDao.modifyMember(member);
		if(result == MemberDao.SUCCESS) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("member", member);
			request.setAttribute("memberModifyResult", "회원정보 수정 성공");
		}else {
			request.setAttribute("errorMsg", "회원정보 수정 실패");
		}

	}

}
