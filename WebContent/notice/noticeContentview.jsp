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
<link href="${conPath }/css/noticeContentview.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								${noticeContentView.nTitle }
							</td>
						</tr>
						<tr>
							<td class="th1">
								작성자
							</td>
							<td class="th2">
								${noticeContentView.aName }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: left; padding-left: 30px;">
								작성일  <span>${noticeContentView.nRdate }</span> &nbsp; &nbsp; &nbsp;&nbsp;  조회수 <span>${noticeContentView.nHit }</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="noticeText">
									<pre>${noticeContentView.nContent }</pre>
								</div>
							</td>
						</tr>
					</table>
					
					<p>
					<c:if test="${not empty admin }">
						<a href="${conPath }/noticeModifyview.do?nNo=${noticeContentView.nNo}"><img alt="mdify" src="${conPath }/img/btnimg/btn_modify.gif"> </a>
						<a href="${conPath }/noticeDelete.do?nNo=${noticeContentView.nNo}"><img alt="delte" src="${conPath }/img/btnimg/btn_delete.gif"> </a>
					</c:if>
						<a href="${conPath }/noticeList.do?pageNum=${param.pageNum}"><img alt="list" src="${conPath }/img/btnimg/btn_list.gif"> </a>
					</p>
						
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>