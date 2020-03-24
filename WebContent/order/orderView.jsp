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
<link href="${conPath }/css/orderView.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.oName').val()==''){
		           alert("배송자이름을 입력하세요(필수)");
		           $('.oName').focus();
		           return false;
		       }
			if($('.oAddress').val()==''){
		           alert("배송자주소를 입력하세요(필수)");
		           $('.oAddress').focus();
		           return false;
		       }
			if($('.oTel').val()==''){
		           alert("배송자전화을 입력하세요(필수)");
		           $('.oTel').focus();
		           return false;
		       }
			
			
			
		});
	});
</script>		
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<form action="${conPath }/order.do" method="get">
			<div class="content">
				<h2>주문</h2>
				<div class="list">
						
					<table class="table">
						<tr>
							<th class="th1">
								이미지
							</th>
							<th class="th2" >
								상품정보
							</th>
							<th class="th1">
								판매가
							</th>
							<th class="th1">
								수량
							</th>
							<th class="th1">
								합계
							</th>
						</tr>
						<c:if test="${not empty cartList }">
						<c:forEach items="${cartList }" var="cartLists" >
						<tr>
							<td>
								<input type="hidden" name="pCode" value="${cartLists.pCode}">
								<input type="hidden" name="cost" value="${cartLists.cost}">
								<input type="hidden" name="cNt" value="${cartLists.cNt}">
								<img alt="상품이미지" src="${conPath }/productFileUp/${cartLists.pFilename}"> 
							</td>
							<td style="text-align: left;">
								${cartLists.pName}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.pPrice}" groupingUsed="true" /> 
								
							</td>
							<td >
								${cartLists.cNt}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.cost}" groupingUsed="true" /> 
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${not empty carSelectlist }">
						<c:forEach items="${carSelectlist }" var="cartLists" >
						<tr>
							<td>
								<input type="hidden" name="pCode" value="${cartLists.pCode}">
								<input type="hidden" name="cost" value="${cartLists.cost}">
								<input type="hidden" name="cNt" value="${cartLists.cNt}">
								<img alt="상품이미지" src="${conPath }/productFileUp/${cartLists.pFilename}"> 
							</td>
							<td style="text-align: left;">
								${cartLists.pName}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.pPrice}" groupingUsed="true" /> 
								
							</td>
							<td >
								${cartLists.cNt}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.cost}" groupingUsed="true" /> 
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<tr>
							<td colspan="6" style="border-top: 1px solid black;">
								<h2 style="text-align: right;">전체 금액 :<b>￦</b><fmt:formatNumber value="${Allsum }" groupingUsed="true" /> </h2>
							</td>
						</tr>
					</table>
					
					
					<div class="orderContent">
						
						<table class="table2">
							<tr>
								<td class="th1">
									주문 정보
								</td>
								<td class="th1">
									<p class="required">
								</td>
							</tr>
							<tr>
								<td class="th1">
									주문하시는 분
								</td>
								<td class="th2">
									${member.mName }
								</td>
							</tr>
							<tr>
								<td class="th1">
									주소 
								</td>
								<td class="th2">
									${member.mAddress }
								</td>
							</tr>
							<tr>
								<td class="th1">
									휴대전화 
								</td>
								<td class="th2">
									${member.mTel }
								</td>
							</tr>
							<tr>
								<td class="th1">
									이메일 
								</td>
								<td class="th2">
									${member.mEmail }
								</td>
							</tr>
						</table>
					</div>
					
					<div class="orderDetailContent">
						<table class="table2">
							<tr>
								<td class="th1">
									배송 정보
								</td>
								<td class="th1">
									<p class="required"><b>*</b>필수 입력사항</p>
								</td>
							</tr>
							<tr>
								<td class="th1">
									받으시는 분 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oName" class="oName" value="${member.mName }" style="width: 200px;">
								</td>
							</tr>
							<tr>
								<td class="th1">
									주소 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oAddress" class="oAddress" value="${member.mAddress }" style="width: 678px;">
								</td>
							</tr>
							<tr>
								<td class="th1">
									휴대전화 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oTel" class="oTel" value="${member.mTel }" style="width: 200px;">
								</td>
							</tr>
						</table>
					</div>
					<div class="payment">
						<br>
						<input type="hidden" name="Allsum" value="${Allsum }">
						<h2 style="text-align: center; color: darkblue">전체 금액 :<b>￦</b><fmt:formatNumber value="${Allsum }" groupingUsed="true" /> </h2>
						<br>
						<input type="submit" class="noticeWriteBtn" value="">
						<br>
					</div>
				</div>
			</div>
		</form>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>