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
<link href="${conPath }/css/qnaModifyview.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
	<form action="${conPath }/qnaModify.do" method="post" enctype="multipart/form-data">
		<div id ="wrap">
			<div class="content">
				<h2>Q&A MODIFY</h2>
				<div class="list">
					<table class="table">
						
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								<input type="hidden" name="qNo" value="${qnaModifyview.qNo}">
								${qnaModifyview.qNo}
								<input type="text" name="qtitle" class="nTitle" style="width: 650px;" value="${qnaModifyview.qtitle}">
							</td>
						</tr>
						<tr>
							<td class="th1">
								파일첨부
							</td>
							<td class="th2">
								<input type="file" name="qFilename1" >
								원파일이름 : ${qnaModifyview.qFilename1}
								<input type="hidden" name="orFilename1" value="${qnaModifyview.qFilename1}">
							</td>
						</tr>
						<tr>
							<td class="th1">
								파일첨부
							</td>
							<td class="th2">
								<input type="file" name="qFilename2" >
								원파일이름 : ${qnaModifyview.qFilename2}
								<input type="hidden" name="orFilename2" value="${qnaModifyview.qFilename2}">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="25" cols="45" name="rContent" style="margin-left:20px; width: 670px; text-align: left; resize: none;">${qnaModifyview.qContent}</textarea>
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