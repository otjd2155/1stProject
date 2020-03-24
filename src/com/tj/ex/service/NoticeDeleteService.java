package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.NoticeDto;

public class NoticeDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.notice_delete(nNo);
		
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("noticeDeleteResult", "글삭제 성공");
		}else {
			request.setAttribute("errorMsg", "글삭제 실패");
		}
	}
}
