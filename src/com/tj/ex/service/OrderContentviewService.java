package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.OrderDetailDao;
import com.tj.ex.dao.OrdersDao;
import com.tj.ex.dto.OrderDetailDto;
import com.tj.ex.dto.OrderDto;

public class OrderContentviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String oNo = request.getParameter("oNo");
		OrderDetailDao orderDetailDao = OrderDetailDao.getInstance();
		OrdersDao oDao = OrdersDao.getInstance();
		OrderDto ordersMember = oDao.ordersMember(oNo);
		
		ArrayList<OrderDetailDto> orderDetailList = orderDetailDao.orderContentList(oNo);
		
		int sum = 0;
		for(int i=0; i<orderDetailList.size(); i++) {
			OrderDetailDto c = orderDetailList.get(i);
			sum += c.getCost();
		}
		
		
		
		request.setAttribute("orderDetailList", orderDetailList);
		request.setAttribute("ordersMember", ordersMember);
		request.setAttribute("Allsum", sum);
	}
}
