package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.ALoginService;
import com.tj.ex.service.CartDeleteService;
import com.tj.ex.service.CartInsertService;
import com.tj.ex.service.CartListService;
import com.tj.ex.service.CartModifyService;
import com.tj.ex.service.MJoinService;
import com.tj.ex.service.MLoginService;
import com.tj.ex.service.MLogoutService;
import com.tj.ex.service.MemberModifyService;
import com.tj.ex.service.MemberUnsubscribe;
import com.tj.ex.service.MidConfirmService;
import com.tj.ex.service.NoticeContentService;
import com.tj.ex.service.NoticeDeleteService;
import com.tj.ex.service.NoticeListService;
import com.tj.ex.service.NoticeModifyService;
import com.tj.ex.service.NoticeModifyViewService;
import com.tj.ex.service.NoticeWriteService;
import com.tj.ex.service.OrderAdminListService;
import com.tj.ex.service.OrderContentviewService;
import com.tj.ex.service.OrderListViewService;
import com.tj.ex.service.OrderSekectViewService;
import com.tj.ex.service.OrderService;
import com.tj.ex.service.OrderStateService;
import com.tj.ex.service.OrderViewService;
import com.tj.ex.service.ProducListService;
import com.tj.ex.service.ProducModifyService;
import com.tj.ex.service.ProducModifyviewService;
import com.tj.ex.service.ProducTypeListService;
import com.tj.ex.service.ProducWriteService;
import com.tj.ex.service.ProductContentViewService;
import com.tj.ex.service.ProductDeleteyService;
import com.tj.ex.service.ProductSearchService;
import com.tj.ex.service.QnaContentviewService;
import com.tj.ex.service.QnaDeleteService;
import com.tj.ex.service.QnaListService;
import com.tj.ex.service.QnaModifyService;
import com.tj.ex.service.QnaModifyviewService;
import com.tj.ex.service.QnaReplyWriteService;
import com.tj.ex.service.QnaReplyWriteviewService;
import com.tj.ex.service.QnaWriteService;
import com.tj.ex.service.ReviewContentviewService;
import com.tj.ex.service.ReviewDeleteService;
import com.tj.ex.service.ReviewListService;
import com.tj.ex.service.ReviewModifyService;
import com.tj.ex.service.ReviewModifyViewService;
import com.tj.ex.service.ReviewWriteService;
import com.tj.ex.service.ReviewWriteviewService;
import com.tj.ex.service.Service;
import com.tj.ex.service.memberAllViewService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;

		// 회원
		if (command.equals("/mainView.do")) { // 메인페이지
			viewPage = "main/main.jsp";
		} else if (command.equals("/loginView.do")) { // 로그인 페이지
			viewPage = "member/login.jsp";
		} else if (command.equals("/login.do")) { // 로그인 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp"; // joinView.do
		} else if (command.equals("/joinView.do")) { // 회원가입 페이지
			viewPage = "member/join.jsp";
		} else if (command.equals("/idConfirm.do")) { // id 중복체크
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if (command.equals("/join.do")) { // 회원가입
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		} else if (command.equals("/logout.do")) { // 로그아웃
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "mainView.do";
		} else if (command.equals("/memberModifyView.do")) { // 회원정보수정 페이지
			viewPage = "member/memberModify.jsp";
		} else if (command.equals("/memberModify.do")) { // 회원 정보 수정
			service = new MemberModifyService();
			service.execute(request, response);
			viewPage = "mainView.do";
		} else if (command.equals("/memberUnsubscribe.do")) { // 회원 탈퇴
			service = new MemberUnsubscribe();
			service.execute(request, response);
			viewPage = "mainView.do";
		} else if (command.equals("/memberAllView.do")) { // 회원 리스트 보기
			service = new memberAllViewService();
			service.execute(request, response);
			viewPage = "member/memberAllView.jsp"; //
		}

		
		
		
		// 관리자
		if (command.equals("/adminLogin.do")) { // 관리자 로그인
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}

		
		
		
		
		//// 공지사항
		if (command.equals("/noticeList.do")) { // 공지사항 페이지 리스트
			service = new NoticeListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		} else if (command.equals("/noticeContent_view.do")) { // 공지사항 상세보기
			service = new NoticeContentService();
			service.execute(request, response);
			viewPage = "notice/noticeContentview.jsp";
		} else if (command.equals("/noticeWrite_view.do")) { // 공지사항 글 쓰기 페이지
			viewPage = "notice/noticeWriteview.jsp"; // noticewrite.do
		} else if (command.equals("/noticewrite.do")) { // 공지사항 글 쓰기
			service = new NoticeWriteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if (command.equals("/noticeModifyview.do")) { // 공지사항 글 수정 페이지
			service = new NoticeModifyViewService();
			service.execute(request, response);
			viewPage = "notice/noticeModifyview.jsp";
		} else if (command.equals("/noticeModify.do")) { // 공지사항 글 수정
			service = new NoticeModifyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if (command.equals("/noticeDelete.do")) { // 공지사항 글 삭제
			service = new NoticeDeleteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
			// noticeDelete.do
		}
		
		
		// 상품 producTypeList.do
		if(command.equals("/producList.do")) { // 전체리스트
			 service = new ProducListService(); 
			 service.execute(request, response); 
			 viewPage ="product/producList.jsp"; 
		}else if (command.equals("/producTypeList.do")) { // 타입별리스트
			service = new ProducTypeListService();
			service.execute(request, response);
			viewPage = "product/productTypeList.jsp";
		}else if (command.equals("/productContentView.do")) { // 상품 상세 보기
			service = new ProductContentViewService();
			service.execute(request, response);
			viewPage = "product/productContentview.jsp";
		}else if (command.equals("/producWriteview.do")) { // 상품 등록
			viewPage = "product/productWriteview.jsp";
		}else if (command.equals("/productWrite.do")) { // 상품 등록
			service = new ProducWriteService();
			service.execute(request, response);
			viewPage = "producList.do";
		}else if (command.equals("/producModifyview.do")) { // 상품 수정전 dto 가져오기
			service = new ProducModifyviewService();
			service.execute(request, response);
			viewPage = "product/productModifyview.jsp";
		}else if (command.equals("/productModify.do")) { // 상품 수정
			service = new ProducModifyService();
			service.execute(request, response);
			viewPage = "producList.do";
		}else if (command.equals("/productDelete.do")) { // 상품 삭제
			service = new ProductDeleteyService();
			service.execute(request, response);
			viewPage = "producList.do";
		}else if (command.equals("/productSearch.do")) { // 상품검색
			service = new ProductSearchService();
			service.execute(request, response);
			viewPage = "product/productSearchList.jsp";
		}


		
		
		
		///카트/////////////////////////////
		
		if(command.equals("/cartInsert.do")) { // 카트에 물건 담기 
			 service = new CartInsertService(); 
			 service.execute(request, response); 
			 viewPage ="cartList.do"; 
		}else if (command.equals("/cartList.do")) { // 카트 리스트 보기
			service = new CartListService();
			service.execute(request, response);
			viewPage = "cart/cartlist.jsp";
		}else if (command.equals("/cartDelete.do")) { // 카트 삭제
			service = new CartDeleteService();
			service.execute(request, response);
			viewPage = "cartList.do";
		}else if (command.equals("/cartModify.do")) { // 카트 수량 변경
			service = new CartModifyService();
			service.execute(request, response);
			viewPage = "cartList.do";
		}
		
		
		
		//주문 //////////////
		
		if(command.equals("/orderView.do")) { // 주문하기전 뷰단
			service = new OrderViewService();
			service.execute(request, response);
			viewPage = "order/orderView.jsp";
		}else if(command.equals("/orderSelectView.do")) { // 선택주문하기전 뷰단
			service = new OrderSekectViewService();
			service.execute(request, response);
			viewPage = "order/orderView.jsp";
		}else if(command.equals("/order.do")) { // 주문하기 
			service = new OrderService();
			service.execute(request, response);
			viewPage = "mainView.do";
		}else if(command.equals("/orderListView.do")) { // 내 주문리스트 보기
			service = new OrderListViewService();
			service.execute(request, response);
			viewPage = "order/orderListview.jsp";
		}else if(command.equals("/orderContentview.do")) { // 내 주문리스트 상세보기
			service = new OrderContentviewService();
			service.execute(request, response);
			viewPage = "order/orderContentview.jsp";
		}else if(command.equals("/orderAdminListview.do")) { // 관리자 주문 관리 페이지
			service = new OrderAdminListService();
			service.execute(request, response);
			viewPage = "order/orderAdminListview.jsp";
		}else if(command.equals("/orderState.do")) {
			service = new OrderStateService();
			service.execute(request, response);
			viewPage = "orderAdminListview.do";
		}
		
		
		
		
		
		
		
		//리뷰 게시판 reviewList.do / reviewContentview.do 
		
		if(command.equals("/reviewList.do")) { //  리뷰 글 목록
			service = new ReviewListService();
			service.execute(request, response);
			viewPage = "review/reviewList.jsp";
		}else if(command.equals("/reviewContentview.do")) { //  리뷰 글 상세
			service = new ReviewContentviewService();
			service.execute(request, response);
			viewPage = "review/reviewContentview.jsp";
		}else if(command.equals("/reviewWriteview.do")) { //  리뷰 글 쓸기 페이지
			service = new ReviewWriteviewService();
			service.execute(request, response);
			viewPage = "review/reviewWriteview.jsp";
		}else if(command.equals("/reviewWrite.do")) { //  리뷰 글 쓸기 
			service = new ReviewWriteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewModifyview.do")) { //  리뷰 글 수정 전 뷰단
			service = new ReviewModifyViewService();
			service.execute(request, response);
			viewPage = "review/reviewModifyview.jsp";
		}else if(command.equals("/reviewModify.do")) { //  리뷰 글 수정 
			service = new ReviewModifyService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewDelete.do")) { //  리뷰 글 삭제  
			service = new ReviewDeleteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}
		
		
		
		
		// Q&A 게시판 (관리자 글작성/ 글수정/ 글삭제가능 & 고객  (로그인후) 글작성 수정 삭제 가능)
		if(command.equals("/qnaList.do")) { //  Q&A 글 목록
			service = new QnaListService();
			service.execute(request, response);
			viewPage = "qna/qnaList.jsp";
		}else if(command.equals("/qnaContentview.do")) { //Q&A 글 상세
			service = new QnaContentviewService();
			service.execute(request, response);
			viewPage = "qna/qnaContentview.jsp";
		}else if(command.equals("/qnaWriteview.do")) { //Q&A 글 쓰기 뷰페이지
			viewPage = "qna/qnaWriteview.jsp";
		}else if(command.equals("/qnaWrite.do")) { //Q&A 글 
			service = new QnaWriteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaModifyview.do")) { //Q&A 글수정 뷰단
			service = new QnaModifyviewService();
			service.execute(request, response);
			viewPage = "qna/qnaModifyview.jsp";
		}else if(command.equals("/qnaModify.do")) { //Q&A 글수정 
			service = new QnaModifyService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaReplyWriteview.do")) { //Q&A 글 쓰기 뷰페이지
			service = new QnaReplyWriteviewService();
			service.execute(request, response);
			viewPage = "qna/qnaReplyWriteview.jsp";
		}else if(command.equals("/qnaReplyWrite.do")) { //Q&A 글 쓰기 뷰페이지
			service = new QnaReplyWriteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaDelete.do")) { //Q&A 글 쓰기 뷰페이지
			service = new QnaDeleteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
