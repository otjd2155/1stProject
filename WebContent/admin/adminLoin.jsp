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
<link href="${conPath }/css/login.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.aId').val()==''){
		           alert("ID를 입력하세요");
		           $('.aId').focus();
		           return false;
		       }
			if($('.aPw').val()==''){
		           alert("PW를 입력하세요");
		           $('.aPw').focus();
		           return false;
		       }
		});
	});
</script>			
</head>
<body>
<c:if test="${not empty member }">
	<script>
		alert('고객로그아웃 후 로그인');
		location.href="${conPath}/mainView.do";
	</script>
</c:if>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="login">
				<form action="${conPath }/adminLogin.do" method="post">
					<h3>관리자 로그인</h3>
						<table>
							<tr>
								<th>
									ADMIN ID
								</th>
								<td>
									<input type="text" name="aId" placeholder="아이디" class="aId" >
								</td>
								<td rowspan="2">
									<input type="submit" value="로그인" class="lbtn"> 
								</td>	
							</tr>
							<tr>
								<th>
									ADMIN PW
								</th>
								<td>
									<input type="password" name="aPw" placeholder="비밀번호" class="aPw" >
								</td>
							</tr>
						</table>
										
				</form>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>