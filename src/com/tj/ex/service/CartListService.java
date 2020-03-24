package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CartDao;
import com.tj.ex.dto.CartDto;
import com.tj.ex.dto.MemberDto;

public class CartListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		
		CartDao cDao = CartDao.getInstance();
		ArrayList<CartDto> cartList = cDao.cartList(mId);
		int sum = 0;
		for(int i=0; i<cartList.size(); i++) {
			CartDto c = cartList.get(i);
			sum += c.getCost();
		}
		
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("Allsum", sum); //전체금액
	}
}
