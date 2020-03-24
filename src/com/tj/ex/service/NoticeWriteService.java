package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.AdminDto;

public class NoticeWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String aId = ((AdminDto)session.getAttribute("admin")).getaId();
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.notice_write(aId, nTitle, nContent);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("noticewriteResult", "글쓰기 성공");
		}else {
			request.setAttribute("erroMsg", "글쓰기 실패");
		}
	}
}
