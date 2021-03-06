package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ReviewDao;
import com.tj.ex.dto.ReviewDto;

public class ReviewModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		ReviewDao rDao = ReviewDao.getInstance();
		ReviewDto rDto = rDao.reviewContentView(rNo);
		request.setAttribute("reviewModify", rDto);

	}

}
