package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.CartDao;

public class CartDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String[] cNolist = request.getParameterValues("cNolist[]");
		System.out.println(cNolist[0]);
		CartDao cDao = CartDao.getInstance();
//		 for(int i=0; i<cNolist.length; i++) { 
//			 System.out.println(cNolist[i]);
//			 cDao.carttDelte(Integer.parseInt(cNolist[i])); 
//		 }
		 for(String cNo : cNolist) {
			 cDao.carttDelte(Integer.parseInt(cNo));
		 }
	}
}
