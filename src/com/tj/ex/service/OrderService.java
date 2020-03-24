package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CartDao;
import com.tj.ex.dao.OrderDetailDao;
import com.tj.ex.dao.OrdersDao;
import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.MemberDto;

public class OrderService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		String[] pCode = request.getParameterValues("pCode");
		String[] cNt = request.getParameterValues("cNt");
		String[] cost = request.getParameterValues("cost");
		
		String oName = request.getParameter("oName");
	    String oAddress = request.getParameter("oAddress");
	    String oTel = request.getParameter("oTel");
	    
	    OrdersDao oDao = OrdersDao.getInstance();
	    OrderDetailDao oDetailDao = OrderDetailDao.getInstance();
	    ProductDao pDao = ProductDao.getInstance();
	    CartDao cDao = CartDao.getInstance();
	    oDao.orderInsert(mId, oName, oAddress, oTel);
	    for(int i=0; i<pCode.length; i++) {
	    	System.out.println(pCode[i]);
	    	System.out.println(Integer.parseInt(cNt[i]));
	    	System.out.println(Integer.parseInt(cost[i]));
	    	oDetailDao.orderDetailInsert(pCode[i], Integer.parseInt(cNt[i]), Integer.parseInt(cost[i]));
	    	pDao.productStockUpdate(Integer.parseInt(cNt[i]), pCode[i]);
	    }
	    System.out.println(mId);
	    cDao.cartOrderDelte(mId);
	    
	}
}
