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
<link href="${conPath }/css/producList.css" rel="stylesheet">
	
</head>
<body>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty productInsert}">
		<script>
			alert('${productInsert}');
		</script>
	</c:if>
	
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>SHOP</h2>
				<div class="list">
					<table class="table">
						<tr>
						<c:forEach items="${productList }" var="pLists" varStatus="idx">
							<td>
								<div class="product">
									<ul>
										<li><a href="${conPath }/productContentView.do?pCode=${pLists.pCode}"><img src="${conPath }/productFileUp/${pLists.pFilename}" ></a></li>
										
										<li>${pLists.pName}</li>
										
										<li><b>￦</b> <fmt:formatNumber value="${pLists.pPrice}" groupingUsed="true" /> </li>
									</ul>
								</div>
							</td>
							<c:if test="${(idx.index)%3 eq 2 }"></tr><tr></c:if>
						</c:forEach>
					</table>
					<c:if test="${not empty admin }">
						<p><a href="${conPath }/producWriteview.do"><img alt="write" src="${conPath }/img/btnimg/btn_register.gif"> </a></p>
					</c:if>
					
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/producList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/producList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/producList.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>
					<div class="prosearch">
					<form action="${conPath }/productSearch.do">
					<ul class="search">
						<li>
							<input type="text" name="pName" class="pNameSearch">
						</li>
						<li>
							<input type="submit" value="" class="searchBtn">
						</li>
					</ul>
					</form>
					</div>
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>