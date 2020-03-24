package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;

public class ProductDeleteyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pCode = request.getParameter("pCode");
		ProductDao pDao = ProductDao.getInstance();
		int result = pDao.productDelte(pCode);

	}

}
