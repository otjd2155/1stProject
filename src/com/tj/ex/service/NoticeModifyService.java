package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;

public class NoticeModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.modify(nNo, nTitle, nContent);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("noticeModiFyResult", "글수정 성공");
		}else {
			request.setAttribute("erroMsg", "글수정 실패");
		}
	}
}
