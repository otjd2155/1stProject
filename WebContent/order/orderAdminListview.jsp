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
<link href="${conPath }/css/orderAdminListview.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<form action="${conPath }/order.do" method="get">
			<div class="content">
				<h2>주문관리</h2>
				<div class="list">
						
					<table class="table">
						<tr>
							<th class="th1">
								[주문번호]<br>
								주문일자
							</th>
							<th class="th1">
								회원 ID
							</th>
							<th class="th2" >
								상품명
							</th>
							<th class="th1">
								주문처리
							</th>
							<th class="th1">
								수량
							</th>
							<th class="th1">
								주문 상태
							</th>
							<th class="th1" >
								주문처리 / 주문상세
							</th>
						</tr>
						
						<c:forEach items="${orderAdminList }" var="orderAdminLists" >
						<tr>
							<td >
								<a href="${conPath }/orderContentview.do?oNo=${orderAdminLists.oNo}">
									[${orderAdminLists.oNo}]
								</a>
								<br>
								${orderAdminLists.oRdate}
								
							</td>
							<td>
								${orderAdminLists.mId }
							</td>
							<td  style="text-align: left;">
								${orderAdminLists.pName}
							</td>
							
							<td  >
								<b>￦</b><fmt:formatNumber value="${orderAdminLists.cost}" groupingUsed="true" />
							</td>
							<td >
								 ${orderAdminLists.cNt}
							</td>
							<td >
								 ${orderAdminLists.oState}
							</td>
							<td>
								<input type="button" onclick="location.href='${conPath }/orderState.do?oNo=${orderAdminLists.oNo}'" value="배송완료" class="reviewBtn">
								<input type="button" onclick="location.href='${conPath }/orderContentview.do?oNo=${orderAdminLists.oNo}'" value="주문상세" class="reviewBtn">
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/orderAdminListview.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/orderAdminListview.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/orderAdminListview.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>
		</form>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>