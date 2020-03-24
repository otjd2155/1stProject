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
<link href="${conPath }/css/qnaList.css" rel="stylesheet">
<style>

</style>			
</head>
<body>

<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>Q&A</h2>
				<div class="list">
					<table class="table">
						<tr>
							<th class="th1">
								NO
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
					<c:if test="${empty qnaList }">
						<tr>
							<td colspan="6">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${qnaList}" var="qnaLists">
						<tr>
							<td>
								${qnaLists.qNo }
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<c:forEach var="i" begin="1" end="${qnaLists.qIndent }">
								<c:if test="${i != qnaLists.qIndent }">
									&nbsp; &nbsp; &nbsp;
								</c:if>
								<c:if test="${i eq qnaLists.qIndent }">
									<img style="width: 30px;" alt="답변" src="${conPath }/img/reply.gif">
								</c:if>
							</c:forEach>
								<a href="${conPath }/qnaContentview.do?qNo=${qnaLists.qNo}&pageNum=${pageNum}">
								${qnaLists.qtitle }
								</a>
								<c:if test="${qnaLists.qHit >50}">
									&nbsp;<b style="color: red; font-size: 0.7em;"> HIT</b>
								</c:if>
							</td>
							<td>
								<c:if test="${not empty qnaLists.mId}">
									${qnaLists.writer}
								</c:if>
								<c:if test="${not empty qnaLists.aId}">
									<b style="color: red;">관리자</b>
								</c:if>
							</td>
							<td>
								${qnaLists.qHit}
							</td>
							<td>
								${qnaLists.qRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					<c:if test="${not empty member || not empty admin }">
						<p><a href="${conPath }/qnaWriteview.do?pageNum=${pageNum}"><img alt="write" src="${conPath }/img/btnimg/btn_board_write.jpg"> </a></p>
					</c:if>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/qnaList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/qnaList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/qnaList.do?pageNum=${endPage+1}">  
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