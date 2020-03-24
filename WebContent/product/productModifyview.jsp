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
<link href="${conPath }/css/productModifyview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.pName').val()==''){
		           alert("상품명을입력하세요");
		           $('.pName').focus();
		           return false;
		       }
			if($('.pOrign').val()==''){
		           alert("원산지를 입력하세요");
		           $('.pOrign').focus();
		           return false;
		       }
			if($('.pWeight').val()==''){
		           alert("중량을 입력하세요");
		           $('.pWeight').focus();
		           return false;
		       }
			if($('.pPart').val()==''){
		           alert("부위를 입력하세요");
		           $('.pPart').focus();
		           return false;
		       }
			if($('.pUse').val()==''){
		           alert("용도를 입력하세요");
		           $('.pUse').focus();
		           return false;
		       }
			if($('.pType').val()==''){
		           alert("상품타입 선택");
		           $('.pType').focus();
		           return false;
		       }
			if($('.pPrice').val()==''){
		           alert("가격 입력");
		           $('.pPrice').focus();
		           return false;
		       }
			if($('.pStock').val()==''){
		           alert("재고 입력");
		           $('.pStock').focus();
		           return false;
		       }
			if($('.pFilename').val()==''){
		           alert("상품이미지 입력바람");
		           $('.pFilename').focus();
		           return false;
		       }
		});
	});
</script>	
<style>

</style>
			
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
			<form action="${conPath }/productModify.do?pCode=${productModifyview.pCode}" method="post" enctype="multipart/form-data">
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								상품명
							</td>
							<td class="th2">
								<input type="text" name="pName" class="pName" value="${productModifyview.pName }" style="width: 678px;" >
							</td>
						</tr>
						<tr>
							<td class="th1">
								원산지
							</td>
							<td class="th2">
								<input type="text" name="pOrign" class="pOrign" value="${productModifyview.pOrign }" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								중량
							</td>
							<td class="th2">
								<input type="text" name="pWeight" class="pWeight" value="${productModifyview.pWeight }" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								부위
							</td>
							<td class="th2">
								<input type="text" name="pPart" class="pPart" value="${productModifyview.pPart }" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								용도
							</td>
							<td class="th2">
								<input type="text" name="pUse" class="pUse" value="${productModifyview.pUse }" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								상품타입
							</td>
							
							<td class="th2">
								<input type="radio" name="pType" 
									checked="<c:if test="${productModifyview.pType eq pork }"> checked </c:if>"    
								 value="pork" />돼지고기
								<input type="radio" name="pType"
									checked="<c:if test="${productModifyview.pType eq beep }"> checked </c:if>" 
								 value="beef" />소고기
							</td>
						</tr>
						<tr>
							<td class="th1">
								가격
							</td>
							<td class="th2">
								<input type="text" name="pPrice" class="pPrice" value="${productModifyview.pPrice }" style="width: 100px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								재고
							</td>
							<td class="th2">
								<input type="text" name="pStock" class="pStock" value="${productModifyview.pStock }" style="width: 100px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								상품이미지
							</td>
							<td class="th2">
								<input type="file" name="pFilename" class="pFilename"  style="width: 200px; ">
								원 파일 이미지 :${productModifyview.pFilename }
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