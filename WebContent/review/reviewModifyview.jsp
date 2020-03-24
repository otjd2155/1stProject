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
<link href="${conPath }/css/reviewModifyview.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
	<form action="${conPath }/reviewModify.do" method="post" enctype="multipart/form-data">
		<div id ="wrap">
			<div class="content">
				<h2>MODIFY</h2>
				<div class="productContent">
					<div class="productLeft">
						<img alt="상품이미지" src="${conPath }/productFileUp/${reviewModify.pFilename}">
					</div>
					<div class="productright">
							<p>
								${reviewModify.pName}
							</p>
							<p class="p1">
								<b>￦</b> <fmt:formatNumber value="${reviewModify.pPrice}" groupingUsed="true" /> 
							</p>
							<hr>
							<p>
								<a href="${conPath }/productContentView.do?pCode=${reviewModify.pCode}"><img alt="상세보기" src="${conPath }/img/btnimg/btn_prd_detail.gif"></a>
							</p>
					</div>
				</div>
				<div class="list">
					<table class="table">
						
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								<input type="hidden" name="rNo" value="${reviewModify.rNo}">
								<input type="text" name="rTitle" class="nTitle" style="width: 650px;" value="${reviewModify.rTitle}">
							</td>
						</tr>
						<tr>
							<td class="th1">
								파일첨부
							</td>
							<td class="th2">
								<input type="file" name="rFilename" >
								원파일이름 : ${reviewModify.rFilename}
								<input type="hidden" name="orFilename" value="${reviewModify.rFilename}">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="25" cols="45" name="rContent" style="margin-left:20px; width: 670px; text-align: left; resize: none;">${reviewModify.rContent}</textarea>
							</td>
						</tr>
					</table>
					
					<p>
						<input type="submit" class="noticeWriteBtn" value="" >
						<a href="${conPath }/reviewList.do"><img alt="cancel" src="${conPath }/img/btnimg/btn_cancel.gif"> </a>					
					</p>
						
				</div>
			</div>
	</div>
	</form>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>