package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.OrderDetailDao;
import com.tj.ex.dao.OrdersDao;

public class OrderStateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String oNo = request.getParameter("oNo");
		OrdersDao oDao = OrdersDao.getInstance();
		oDao.orderState(oNo);

	}

}
