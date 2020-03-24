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
<link href="${conPath }/css/noticeModifyview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.nTitle').val()==''){
		           alert("제목을 입력하세요");
		           $('.nTitle').focus();
		           return false;
		       }
		});
	});
</script>	
<style>
.noticeWriteBtn{
	width: 100px;
	height: 30px;
	background-image: url("${conPath }/img/btnimg/btn_modify.gif");
	border: 0;
}
</style>			
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
			<form action="${conPath }/noticeModify.do?nNo=${notice_modify_view.nNo}" method="post">
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								<input type="text" name="nTitle" class="nTitle" style="width: 678px;" value="${notice_modify_view.nTitle }">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="25" cols="45" name="nContent" style="width: 678px; text-align: left; resize: none;">${notice_modify_view.nContent }
								</textarea>
							</td>
						</tr>
					</table>
					<p>
						<input type="submit" class="noticeWriteBtn" value="" >
						<a href="${conPath }/noticeList.do"><img alt="cancel" src="${conPath }/img/btnimg/btn_cancel.gif"> </a>					
					</p>
						
				</div>
				</form>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>