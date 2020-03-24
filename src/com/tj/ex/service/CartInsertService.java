package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CartDao;
import com.tj.ex.dto.MemberDto;

public class CartInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		System.out.println(mId);
		String pCode = request.getParameter("pCode");
		int cNt = Integer.parseInt(request.getParameter("cNt"));
		
		CartDao cDao = CartDao.getInstance();
		int result = cDao.cartInsert(mId, pCode, cNt);
		if(result== CartDao.SUCCESS) {
			request.setAttribute("cartInsert", "카트담기 성공");
		}else {
			request.setAttribute("erroMsg", "카트등록 실패");
		}
		

	}

}
