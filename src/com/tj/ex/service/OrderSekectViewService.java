package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.CartDao;
import com.tj.ex.dto.CartDto;

public class OrderSekectViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String[] cNolists = request.getParameterValues("cNolists[]");
		CartDao cDao = CartDao.getInstance();
		ArrayList<CartDto> carSelectlist = new ArrayList<CartDto>();
		for(String cNo : cNolists) {
			carSelectlist.add(cDao.cDtos(Integer.parseInt(cNo)));
		}
		//ArrayList<CartDto> carSelectlist = cDao.cartSelectList(cNo);
		
		
		int sum = 0;
		for(int i=0; i<carSelectlist.size(); i++) {
			CartDto c = carSelectlist.get(i);
			sum += c.getCost();
		}
		
		request.setAttribute("carSelectlist", carSelectlist);
		request.setAttribute("Allsum", sum);
	}

}
