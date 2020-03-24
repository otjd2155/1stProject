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
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link href="${conPath }/css/main.css" rel="stylesheet">
			
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
		</script>
	</c:if> 
	<c:if test="${not empty loginFail}">
		<script>
			alert('${loginFail}');
			location.href='${conPath}/loginView.do'
		</script>
	</c:if> 	
	<c:if test="${not empty memberModifyResult}">
		<script>
			alert('${memberModifyResult}');
		</script>
	</c:if> 
	<c:if test="${not empty memberUnsubscribeResult}">
		<script>
			alert('${memberUnsubscribeResult}');
		</script>
	</c:if> 
	<c:if test="${not empty adminResult}">
		<script>
			alert('${adminResult}');
		</script>
	</c:if>
	<c:if test="${not empty cartInsert}">
		<script>
			alert('${cartInsert}');
		</script>
	</c:if>
	
	 
	<div id="wrap">
		<div class="visual">
			<div >
				<a href="${conPath }/producTypeList.do?pType=pork"><img src="${conPath }/img/main01.jpg" ></a>
			</div>
			<div>
				<a href="${conPath }/producTypeList.do?pType=beep"><img src="${conPath }/img/main03.jpg" ></a>
			</div>
		</div>
		<script type="text/javascript">
		$('.visual').slick({
			autoplay: true,
			autoplaySpeed: 1000
			
		});
		</script>
		<div class="main_about">
		<h1>g o g i t g a n</h1>
		<br>
			<p>스마트폰으로 바로 주문하고 다음날 받아보는 프리미엄 온라인 정육점 고깃간</p>
			<p>고깃간의 모든 제품은 등급과 육질, 육량 등을 체크해 매일 경매를 받습니다.</p>
			<br><br>
			<p>그리고 그것들 중 또 한번 전문가들의 선별 작업을 거쳐 통과된 고기만이 고객님의 집으로 배달됩니다.</p>
		<br>
		<h3><a href="${conPath }/producList.do">shop now -></a></h3>
		</div>
		<div class="main_banner">
			<div class="banner1">
				<img src="${conPath }/img/banner04.jpg" >
					<a href="${conPath }/producTypeList.do?pType=pork" >
						<p>
							<h2>한우 선물세트</h2>
							<hr>
							<span>한우</span>
						</p>
					</a>	
			</div>
			<div class="banner2">
				<img src="${conPath }/img/banner05.jpg" >
					<a href="${conPath }/producTypeList.do?pType=beep">
						<p>
							<h2>돼지고기 선물세트</h2>
							<hr>
							<span>한우 </span>
						</p>
					</a>	
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>	
</body>
</html>