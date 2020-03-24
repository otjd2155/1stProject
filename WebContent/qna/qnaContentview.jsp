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
<link href="${conPath }/css/qnaContentview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$(document).ready(function(){
	 $('.delete').click(function(){
			var confirmChk = confirm("글을 삭제하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/qnaDelete.do?qNo=${qnaContentview.qNo}";
				}
			});
	})
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>Q&A ${qnaContentview.qNo }</h2>
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								${qnaContentview.qtitle }
							</td>
						</tr>
						<tr>
							<td class="th1">
								작성자
							</td>
							<td class="th2">
								${qnaContentview.writer }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: left; padding-left: 30px;">
								작성일  <span>${qnaContentview.qRdate }</span> &nbsp; &nbsp; &nbsp;&nbsp;  조회수 <span>${qnaContentview.qHit }</span>
							</td>
						</tr>
						<c:if test="${not empty qnaContentview.qFilename1 }">
						<tr>
							<td colspan="2">
									<img class="rFilenameImg" alt="파일첨부" src="${conPath }/qnaFileUp/${qnaContentview.qFilename1 }">
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty qnaContentview.qFilename2 }">
						<tr>
							<td colspan="2">
									<img class="rFilenameImg" alt="파일첨부" src="${conPath }/qnaFileUp/${qnaContentview.qFilename2 }">
							</td>
						</tr>
						</c:if>
						<tr>
							<td colspan="2">
								<div class="noticeText">
									<pre>${qnaContentview.qContent }</pre>
								</div>
							</td>
						</tr>
					</table>
					<p>
					<c:if test="${not empty admin }">
						<a style="float: left; margin-left: 200px;" href="${conPath }/qnaReplyWriteview.do?qNo=${qnaContentview.qNo}">
							<img alt="list" src="${conPath }/img/btnimg/btn_reply.gif"> 
						</a>
					</c:if>
				
					<c:if test="${member.mId eq qnaContentview.mId || not empty admin}">
						<a href="${conPath }/qnaModifyview.do?qNo=${qnaContentview.qNo}">
							<img alt="mdify" src="${conPath }/img/btnimg/btn_modify.gif">
						 </a>
						<img class="delete"  alt="delte" src="${conPath }/img/btnimg/btn_delete.gif">
					</c:if>
						<a href="${conPath }/qnaList.do?pageNum=${param.pageNum}"><img alt="list" src="${conPath }/img/btnimg/btn_list.gif"> </a>
					</p>
						
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>