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
<link href="${conPath }/css/cartlist.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>

</style>			

<script>
	
$(document).ready(function(){
    $("#checkall").click(function(){
        if($("#checkall").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=cNo]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=cNo]").prop("checked",false);
        }
    })
    $('.delete').click(function(){
		var confirmChk = confirm("정말 삭제 하시겠습니까?");
			if(confirmChk == true){
				var cNoArray = [];
				$('input[name="cNo"]:checked').each(function(i){
					cNoArray.push($(this).val());
				});
				var cartParam = {
						"cNolist":cNoArray
				}
				location.href="${conPath}/cartDelete.do?"+$.param(cartParam);
				
			}
		});
    //selectbtn
    $('.selectbtn').click(function(){
		var confirmChk = confirm("선택 상품 구매할꺼얌?");
			if(confirmChk == true){
				var cNoArrays = [];
				$('input[name="cNo"]:checked').each(function(a){
					cNoArrays.push($(this).val());
				});
				var cartParams = {
						"cNolists":cNoArrays
				}
				if(typeof cNoArrays[0]  == "undefined"){
					alert('선택해');
				}else{
					
				location.href="${conPath}/orderSelectView.do?"+$.param(cartParams);
				}
				
			}
		});
})

</script>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
	
		<div id ="wrap">
			<div class="content">
				<h2>CART</h2>
				<div class="list">
					<table class="table">
					
						<tr>
							<th class="th3">
								<input type="checkbox" id="checkall">
							</th>
							<th class="th1">
								이미지
							</th>
							<th class="th2" >
								상품정보
							</th>
							<th class="th1">
								판매가
							</th>
							<th class="th1">
								수량
							</th>
							<th class="th1">
								합계
							</th>
						</tr>
						<c:if test="${empty cartList }">
							<tr>
								<td colspan="6">
									장바구니가 비었습니다.
								</td>
							</tr>
						</c:if>
						<c:forEach items="${cartList }" var="cartLists" >
						<form action="cartModify.do" method="get">
						<tr>
							<td>
								<input type="checkbox" name="cNo" class="cNo" value="${cartLists.cNo }" >
							</td>
							<td>
								<a href="${conPath }/productContentView.do?pCode=${cartLists.pCode}">
								<img alt="상품이미지" src="${conPath }/productFileUp/${cartLists.pFilename}">
								</a>
							</td>
							<td style="text-align: left;">
								${cartLists.pName}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.pPrice}" groupingUsed="true" /> 
								
							</td>
							<td >
								<input type="number" name="cNt" value="${cartLists.cNt}" min="1" max="10" style="width: 40px;">
								<input type="hidden" name="num" value="${cartLists.cNo }">
								<input type="submit" value="수정">
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${cartLists.cost}" groupingUsed="true" /> 
							</td>
						</tr>
						</form>
						</c:forEach>
						<tr>
							<td colspan="6" style="text-align: left; border-bottom: 0;">
								<img alt="delte" style="width: 80px;" src="${conPath }/img/cart/btn_delete2.gif" class="delete"> 						
								<h2 style="text-align: right;">전체 금액 :<b>￦</b><fmt:formatNumber value="${Allsum }" groupingUsed="true" /> </h2>
							</td>
						</tr>
					</table>
						
						<span>
							<a href="${conPath }/orderView.do"><img alt="전체" src="${conPath }/img/cart/btn_order_all.jpg" > </a>
							<img alt="선택" class="selectbtn" src="${conPath }/img/cart/btn_order_select.jpg" >
						</span>		
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>