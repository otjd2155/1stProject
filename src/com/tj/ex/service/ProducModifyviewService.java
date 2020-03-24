package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProducModifyviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pCode = request.getParameter("pCode");
		System.out.println(pCode);
		ProductDao pDao = ProductDao.getInstance();
		ProductDto pDto = pDao.productContentView(pCode);
		request.setAttribute("productModifyview", pDto);
	}

}
