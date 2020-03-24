package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnaDao;

public class QnaDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNo  = Integer.parseInt(request.getParameter("qNo"));
		QnaDao qDao = QnaDao.getInstance();
		qDao.qnaDelete(qNo);

	}

}
