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
<link href="${conPath }/css/reviewList.css" rel="stylesheet">			
</head>
<body>

<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>REVIEW</h2>
				<div class="list">
					<table class="table">
						<tr>
							<th class="th1">
								NO
							</th>
							<th class="th3" colspan="2">
								PRODUCT
							</th>
							<th class="th2" >
								SUBJECT
							</th>
							<th class="th1">
								NAME
							</th>
							<th class="th1">
								HIT
							</th>
							<th class="th1">
								DATE
							</th>
						</tr>
					<c:if test="${empty reviewList }">
						<tr>
							<td colspan="7">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${reviewList}" var="reviewLists">
						<tr>
							<td>
								${reviewLists.rNo }
							</td>
							<td>
								<a href="${conPath }/productContentView.do?pCode=${reviewLists.pCode}">
								<img alt="product" src="${conPath }/productFileUp/${reviewLists.pFilename}">
								</a>
								
							</td>
							<td>
								${reviewLists.pName}
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<a href="${conPath }/reviewContentview.do?rNo=${reviewLists.rNo}&pageNum=${pageNum}">
								${reviewLists.rTitle }</a>
								<c:if test="${reviewLists.rHit >50}">
									&nbsp;<b style="color: red; font-size: 0.7em;"> HIT</b>
								</c:if>
							</td>
							<td>
								${reviewLists.mName}
							</td>
							<td>
								${reviewLists.rHit}
							</td>
							<td>
								${reviewLists.rRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/reviewList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/reviewList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/reviewList.do?pageNum=${endPage+1}">  
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