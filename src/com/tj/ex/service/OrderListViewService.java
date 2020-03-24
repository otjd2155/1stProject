package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.OrderDetailDao;
import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.MemberDto;
import com.tj.ex.dto.OrderDetailDto;
import com.tj.ex.dto.ProductDto;

public class OrderListViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
				pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=9, BLOCKSIZE=3;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		OrderDetailDao odDao = OrderDetailDao.getInstance();
		ArrayList<OrderDetailDto> orderDetailList = odDao.orderDetailList(mId, startRow, endRow);
		System.out.println();
		request.setAttribute("orderDetailList", orderDetailList);
		int totCnt = odDao.orderDetailTotCnt(mId);// 글갯수
		System.out.println(totCnt);
		
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 list.size()대용
		request.setAttribute("pageNum", currentPage);// pageNum 없으면 param.pageNum
		
	}
}
