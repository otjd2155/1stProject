<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/header.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style >

</style>
</head>
<body>
	<header>
	<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
		<div id="nav">
			<div id ="top_logo">
				<a href="${conPath }/main/main.jsp"><img alt="gogitgan_logo" src="${conPath }/img/logo.png"></a>
			</div>
			<div id="top_menu">
				<ul>
					<li><a href="${conPath }/joinView.do" >JOIN</a></li>
					<li><a href="${conPath }/loginView.do" >LOGIN</a></li>
					<li>
						<a href="${conPath }/noticeList.do" >COMMUNITY</a>
						<ul>
                            <li><a href="${conPath }/noticeList.do">공지사항</a></li>
                            <li><a href="${conPath }/reviewList.do">상품후기</a></li>
                            <li><a href="${conPath }/qnaList.do">Q&A</a></li>
                        </ul>
					</li>
					<li>
						<a href="${conPath }/producList.do" >SHOP</a>
						<ul>
                              <li><a href="${conPath }/producTypeList.do?pType=pork">돼지고기</a></li>
                            <li><a href="${conPath }/producTypeList.do?pType=beef">소고기</a></li>
                        </ul>
					</li>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty member and empty admin}"><%-- 사용자 모드 로그인 화면--%>
		<div id="nav">
			<div id ="top_logo">
				<a href="${conPath }/main/main.jsp"><img alt="gogitgan_logo" src="${conPath }/img/logo.png"></a>
			</div>
			<div id="top_menu">
				<ul>
					<li><a class="cartlogo" href="${conPath }/cartList.do" ><img alt="cart" src="${conPath }/img/btn_cart.png">   </a></li>
					<li>
						<a>${member.mId }</a>
						<ul>
							 <li><a href="${conPath }/memberModifyView.do" >MODIFY</a></li>
                    		 <li><a href="${conPath }/orderListView.do" >ORDER LIST</a></li>
						</ul>
					</li>
                        
					<li><a href="${conPath }/logout.do" >LOGOUT</a></li>
					<li>
						<a href="${conPath }/noticeList.do" >COMMUNITY</a>
						<ul>
                            <li><a href="${conPath }/noticeList.do">공지사항</a></li>
                            <li><a href="${conPath }/reviewList.do">상품후기</a></li>
                            <li><a href="${conPath }/qnaList.do">Q&A</a></li>
                        </ul>
					</li>
					<li>
						<a href="${conPath }/producList.do" >SHOP</a>
						<ul>
                             <li><a href="${conPath }/producTypeList.do?pType=pork">돼지고기</a></li>
                            <li><a href="${conPath }/producTypeList.do?pType=beef">소고기</a></li>
                        </ul>
					</li>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if test="${empty member and not empty admin}"><%-- 사용자 모드 로그인 화면--%>
		<div id="nav">
			<div id ="top_logo">
				<a href="${conPath }/main/main.jsp"><img alt="gogitgan_logo" src="${conPath }/img/logo.png"></a>
			</div>
			<div id="top_menu">
				<ul>
					<li><a href="${conPath }/memberAllView.do" >회원관리</a></li>
					<li><a href="${conPath }/orderAdminListview.do" >주문관리</a></li>
					<li><a href="${conPath }/logout.do" >LOGOUT</a></li>
					<li>
						<a href="${conPath }/noticeList.do" >COMMUNITY</a>
						<ul>
                            <li><a href="${conPath }/noticeList.do">공지사항</a></li>
                            <li><a href="${conPath }/reviewList.do">상품후기</a></li>
                            <li><a href="${conPath }/qnaList.do">Q&A</a></li>
                        </ul>
					</li>
					<li>
						<a href="${conPath }/producList.do" >SHOP</a>
						<ul>
                            <li><a href="${conPath }/producTypeList.do?pType=pork">돼지고기</a></li>
                            <li><a href="${conPath }/producTypeList.do?pType=beef">소고기</a></li>
                        </ul>
					</li>
				</ul>
			</div>
		</div>
	</c:if>
	</header>
</body>
</html>








