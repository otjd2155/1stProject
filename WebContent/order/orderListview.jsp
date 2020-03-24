<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="${conPath }/css/main.css" rel="stylesheet">
<link href="${conPath }/css/orderListview.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<form action="${conPath }/order.do" method="get">
			<div class="content">
				<h2>주문리스트</h2>
				<div class="list">
						
					<table class="table">
						<tr>
							<th class="th1">
								[주문번호]<br>
								주문일자
							</th>
							<th class="th1">
								이미지
							</th>
							<th class="th2" >
								상품명
							</th>
							<th class="th1">
								주문금액
							</th>
							<th class="th1">
								수량
							</th>
							<th class="th1">
								주문 상태
							</th>
							<th class="th1" >
								주문상세보기 / 구매후기
							</th>
						</tr>
						
						<c:forEach items="${orderDetailList }" var="orderDetailLists" >
						<tr>
							<td >
								<a href="${conPath }/orderContentview.do?oNo=${orderDetailLists.oNo}">
									[${orderDetailLists.oNo}]
								</a>
								<br>
								${orderDetailLists.oRdate}
								
							</td>
							<td >
								<a href="${conPath }/productContentView.do?pCode=${orderDetailLists.pCode}">
									<img alt="상품이미지" src="${conPath }/productFileUp/${orderDetailLists.pFilename}"> 
								</a>
							</td>
							<td  style="text-align: left;">
								${orderDetailLists.pName}
							</td>
							
							<td  >
								<b>￦</b><fmt:formatNumber value="${orderDetailLists.cost}" groupingUsed="true" />
							</td>
							<td >
								 ${orderDetailLists.cNt}
							</td>
							<td >
								 ${orderDetailLists.oState}
							</td>
							<td>
								<input type="button" onclick="location.href='${conPath }/orderContentview.do?oNo=${orderDetailLists.oNo}'" value="상세보기" class="reviewBtn">
								<input type="button" onclick="location.href='${conPath }/reviewWriteview.do?pCode=${orderDetailLists.pCode}'" value="구매후기" class="reviewBtn">
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/orderListView.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/orderListView.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/orderListView.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>
		</form>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>