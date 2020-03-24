package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.AdminDao;
import com.tj.ex.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		System.out.println(aId+"/"+aPw);
		int result = aDao.adminLoginCheck(aId, aPw);
		if(result==AdminDao.ADMIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			AdminDto admin = aDao.getAdmin(aId);
			session.setAttribute("admin", admin);
			request.setAttribute("adminResult", "관리자 계정으로 들어오셨습니다.");
			
		}else { // 로그인 실패
			request.setAttribute("errorMsg", "관리자 계정으로 로그인이 실패되었습니다.");
		}
	}
}
