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
<link href="${conPath }/css/memberAllView.css" rel="stylesheet">
<style>

</style>			
</head>
<body>
<c:if test="${empty admin }">
	<script>
		location.href='${conPath}/mainView.do';
	</script>
</c:if>

<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<h2>회원 관리</h2>
			<div class="content">
				<div class="list">
					<table class="table">
						<tr>
							<th class="th1">
								ID
							</th>
							<th class="th1" >
								NAME
							</th>
							<th class="th2">
								ADDRESS
							</th>
							<th class="th1">
								TEl
							</th>
							<th class="th1">
								E-MAIL
							</th>
							<th class="th1">
								BIRTH
							</th>
							<th class="th1">
								DATE
							</th>
							<th class="th1">
								강제탈퇴
							</th>
						</tr>
					<c:if test="${empty members }">
						<tr>
							<td colspan="8">
								회원이 없습니다
							</td>
						</tr>
					</c:if>
					<c:forEach items="${members}" var="member">
						<tr>
							<td>
								${member.mId }
							</td>
							<td>
								${member.mName }
							</td>
							<td style="text-align: left;">
								${member.mAddress }
							</td>
							<td>
								${member.mTel }
							</td>
							<td>
								${member.mEmail }
							</td>
							<td>
								${member.mBirth }
							</td>
							<td>
								${member.mRdate }
							</td>
							<td>
								<a href="${conPath}/memberUnsubscribe.do?mId=${member.mId }">							
									<img alt="회원 탈퇴" src="${conPath }/img/btnimg/btn_modify_out.gif">
								</a>
							</td>
						</tr>
					</c:forEach>
					</table>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/memberAllView.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/memberAllView.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/memberAllView.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>			
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>