package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MemberUnsubscribe implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String mId = null;
		if(session.getAttribute("member")!=null) {
			mId = member.getmId();
			MemberDao mDao = MemberDao.getInstance();
			int result = mDao.delteMember(mId);
			if(result==mDao.SUCCESS) {
				request.setAttribute("memberUnsubscribeResult", "회원탈퇴 성공");
				session.invalidate();
			}else {
				request.setAttribute("errorMsg", "회원탈퇴 실패");
			}
		}
		String mIdChk = request.getParameter("mId");
		if(mId==null) {
			mId = mIdChk;
			MemberDao mDao = MemberDao.getInstance();
			int result = mDao.delteMember(mId);
			if(result==mDao.SUCCESS) {
				request.setAttribute("memberUnsubscribeResult", "회원탈퇴 성공");
			}else {
				request.setAttribute("errorMsg", "회원탈퇴 실패");
			}
		}
		

	}

}
