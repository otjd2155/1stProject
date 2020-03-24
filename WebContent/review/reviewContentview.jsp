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
<link href="${conPath }/css/reviewContentview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$(document).ready(function(){
	 $('.delete').click(function(){
			var confirmChk = confirm("글을 삭제하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/reviewDelete.do?rNo=${reviewContent.rNo}";
				}
			});
	})
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
			<c:if test="${not empty reviewContent }">
				<div class="productContent">
					<div class="productLeft">
						<img alt="상품이미지" src="${conPath }/productFileUp/${reviewContent.pFilename}">
					</div>
					<div class="productright">
							<p>
								${reviewContent.pName}
							</p>
							<p class="p1">
								<b>￦</b> <fmt:formatNumber value="${reviewContent.pPrice}" groupingUsed="true" /> 
							</p>
							<hr>
							<p>
								<a href="${conPath }/productContentView.do?pCode=${reviewContent.pCode}"><img alt="상세보기" src="${conPath }/img/btnimg/btn_prd_detail.gif"></a>
							</p>
					</div>
				</div>
			</c:if>	
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								${reviewContent.rTitle }
							</td>
						</tr>
						<tr>
							<td class="th1">
								작성자
							</td>
							<td class="th2">
								${reviewContent.mName }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: left; padding-left: 30px;">
								작성일  <span>${reviewContent.rRdate }</span> &nbsp; &nbsp; &nbsp;&nbsp;  조회수 <span>${reviewContent.rHit }</span>
							</td>
						</tr>
						<c:if test="${not empty reviewContent.rFilename }">
						<tr>
							<td colspan="2">
									<img class="rFilenameImg" alt="파일첨부" src="${conPath }/reviewFileUp/${reviewContent.rFilename }">
							</td>
						</tr>
						</c:if>
						<tr>
							<td colspan="2">
								<div class="noticeText">
									<pre>${reviewContent.rContent }</pre>
								</div>
							</td>
						</tr>
					</table>
					
					<p>
					<c:if test="${member.mId eq reviewContent.mId || not empty admin}">
						<a href="${conPath }/reviewModifyview.do?rNo=${reviewContent.rNo}"><img alt="mdify" src="${conPath }/img/btnimg/btn_modify.gif"> </a>
						<img class="delete"  alt="delte" src="${conPath }/img/btnimg/btn_delete.gif">
					</c:if>
						<a href="${conPath }/reviewList.do?pageNum=${param.pageNum}"><img alt="list" src="${conPath }/img/btnimg/btn_list.gif"> </a>
					</p>
						
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>