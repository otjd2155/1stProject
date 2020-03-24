<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="${conPath }/css/footer.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style>

</style>
<body>
	<footer>
		<div id="footer_conts">
			<p>COMPANY 주식회사 고깃간 | OWNER 오성현  | TEL 000-0000-0000 | E-MAIL aaa@aaaaaaa.co</p> 
			<p>BUSINESS NUMBER 312-88-00750 | MAIL-ORDER LICENSE 제2017-안양만안-0132호 </p>
			<p>서울특별시 종로구 수표로  육의전빌딩 8,9F | 
			<c:if test="${empty member && empty admin}">
				<a href="${conPath }/admin/adminLoin.jsp">관리자 모드</a>
			</c:if>
			</p>
			<br>
			<p>Copyright © 고깃간. All rights reserved.</p>
		</div>	
	</footer>
</body>
</html>