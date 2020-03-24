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
<link href="${conPath }/css/orderContentview.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>주문상세</h2>
				<div class="list">
					주문번호 :${ordersMember.oNo } / 주문날짜 : ${ordersMember.oRdate }
					<table class="table">
						<tr>
							<th class="th1">
								이미지
							</th>
							<th class="th2" >
								상품정보
							</th>
							
							<th class="th1">
								수량
							</th>
							<th class="th1">
								합계
							</th>
							<th class="th1">
								배송 상태
							</th>
						</tr>
						<c:forEach items="${orderDetailList }" var="orderDetailLists" >
						<tr>
							<td>
								<a href="${conPath }/productContentView.do?pCode=${orderDetailLists.pCode}">
									<img alt="상품이미지" src="${conPath }/productFileUp/${orderDetailLists.pFilename}">
								</a>	
							</td>
							<td style="text-align: left;">
								${orderDetailLists.pName}
							</td>
							<td>
								${orderDetailLists.cNt}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${orderDetailLists.cost}" groupingUsed="true" /> 
							</td>
							<td>
								${orderDetailLists.oState }
							</td>
						</tr>
						</c:forEach>
						<tr>
							<th colspan="6" style="text-align: left;">
								<h2 style="text-align: right;">전체 금액 :<b>￦</b><fmt:formatNumber value="${Allsum }" groupingUsed="true" /> </h2>
							</th>
						</tr>
					</table>
					<br>
					<div class="orderDetailContent">
						<table class="table2">
							<tr>
								<th style="font-size: 1.5em" colspan="2">
									배송 정보
								</th>
							</tr>
							<tr>
								<th class="th1">
									받으시는 분 
								</th>
								<td class="th2">
									${ordersMember.oName }
								</td>
							</tr>
							<tr>
								<th class="th1">
									주소 
								</th>
								<td class="th2">
									${ordersMember.oAddress }
								</td>
							</tr>
							<tr>
								<th class="th1">
									휴대전화 
								</th>
								<td class="th2">
									${ordersMember.oTel }
								</td>
							</tr>
						</table>
					</div>
					<div class="payment">
						
					</div>
				</div>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>