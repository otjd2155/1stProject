package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.MemberDto;
import com.tj.ex.dto.NoticeDto;

public class memberAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String requestPageNum = (String)request.getAttribute("pageNum");
		if(pageNum==null) {
			if(requestPageNum==null)
				pageNum = "1";
			else
				pageNum = requestPageNum;
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=10, BLOCKSIZE=2;
		
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		MemberDao mDao = MemberDao.getInstance();
		ArrayList<MemberDto> members = mDao.allMember(startRow, endRow);
		request.setAttribute("members", members);
		int totCnt = mDao.getMemberTotCnt();
		
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