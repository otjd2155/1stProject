package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnaDao;
import com.tj.ex.dto.QnaDto;

public class QnaModifyviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		QnaDao qDao = QnaDao.getInstance();
		QnaDto qDto = qDao.qNaContentView(qNo);
		request.setAttribute("qnaModifyview", qDto);

	}

}
