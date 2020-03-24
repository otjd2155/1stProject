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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.mId').val()==''){
		           alert("ID를 입력하세요");
		           $('.mId').focus();
		           return false;
		       }
			if($('.mPw').val()==''){
		           alert("PW를 입력하세요");
		           $('.mPw').focus();
		           return false;
		       }
		});
	});
</script>			
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
		<div id ="wrap">
			<div class="login">
				<form action="${conPath }/login.do" method="post">
					<h3>회원로그인</h3>
						<table>
							<tr>
								<th>
									ID
								</th>
								<td>
									<input type="text" name="mId" placeholder="아이디" class="mId" >
								</td>
								<td rowspan="2">
									<input type="submit" value="로그인" class="lbtn"> 
								</td>	
							</tr>
							<tr>
								<th>
									PW
								</th>
								<td>
									<input type="password" name="mPw" placeholder="비밀번호" class="mPw" >
								</td>
							</tr>
						</table>
						<br>						
						<hr>
						<p><input type="button" value="회원가입" class="jbtn" onclick="location.href='${conPath}/joinView.do'">  </p>						
				</form>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>