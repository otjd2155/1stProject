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
<link href="${conPath }/css/productContentview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$(document).ready(function(){
	
	$('.delete').click(function(){
		var confirmChk = confirm("정말 삭제 하시겠습니까?");
			if(confirmChk == true){
				location.href="${conPath }/productDelete.do?pCode=${productContentView.pCode}";
			}else{
				
			}
		});
	$('#cart').click(function(){
		if(${productContentView.pStock ne 0}){
			
				if(${not empty member}){
					var cNt = $('.cNt').val();
					var pStock = document.getElementById('pStock').innerHTML;
						if(parseInt(cNt) <= parseInt(pStock)){
							location.href='${conPath}/cartInsert.do?pCode=${productContentView.pCode }&cNt='+cNt
						}else{
							alert('재고 부족 재고:'+pStock);
						}
						
				}else{
					alert('로그인후 이용하세요');
					return false;
				}
		   }else{
			   alert('재고가 없습니다. 미안미안');
			   return false;	
		   }
		});
	});

</script>			
</head>
<body>
	
<jsp:include page="../main/header.jsp"></jsp:include>
	<form action="" >
		<div id ="wrap">
			<div class="content">
				<h2>${productContentView.pName }</h2>
					<div class="productContentLeft">
						<div class="leftImg">
							<img src="${conPath }/productFileUp/${productContentView.pFilename}" >
						</div>
					</div>
					
					<div class="productContentRight">
						<c:if test="${not empty admin }">
						<p class="rightbtn"><a href="${conPath }/producModifyview.do?pCode=${productContentView.pCode}"><img alt="modify" src="${conPath }/img/btnimg/btn_modify.gif"> </a></p>
						<p class="rightbtn"><img alt="delete" src="${conPath }/img/btnimg/btn_delete.gif" class="delete"></p>
						</c:if>
						<div class="rightContent">
							<ul>
								<li><h2>${productContentView.pName }</h2></li>
								<li><h2><b>￦</b> <fmt:formatNumber value="${productContentView.pPrice}" groupingUsed="true" /></h2> </li>
							</ul>
							<table>
								<tr>
									<th>
										원산지
									</th>
									<td>
										${productContentView.pOrign }
									</td>
								</tr>
								<tr>
									<th>
										중량
									</th>
									<td>
										${productContentView.pWeight }
									</td>
								</tr>
								<tr>
									<th>
										부위
									</th>
									<td>
										${productContentView.pPart }
									</td>
								</tr>
								<tr>
									<th>
										용도 
									</th>
									<td>
										${productContentView.pUse }
									</td>
								</tr>
								<tr><c:if test="${productContentView.pStock ne 0 }">
										<td >
											<select name="cNt" class="cNt">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
											</select>
										</td>
										<td >
											<span>재고 :</span> <div id="pStock" style="display: inline;">${productContentView.pStock}</div>
										</td>
									</c:if>
									<c:if test="${productContentView.pStock eq 0 }">
										<td colspan="2">
											재고없음
										</td>
									</c:if>
								</tr>
							</table>
								<br>
								<c:if test="${empty admin }">
									<p><input type="button" value="ADD CART" class="orderBtn" id="cart"></p>
								</c:if>
						</div>
					</div>		
				</div>
				<div class="list">
					<h2>REVIEW</h2>
					<br>
					<table class="table">
						<tr>
							<th class="th1">
								NO
							</th>
							<th class="th2" >
								SUBJECT
							</th>
							<th class="th1">
								NAME
							</th>
							<th class="th1">
								HIT
							</th>
							<th class="th1">
								DATE
							</th>
						</tr>
					<c:if test="${empty reviewList }">
						<tr>
							<td colspan="6">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${reviewList}" var="reviewLists">
						<tr>
							<td>
								${reviewLists.rNo }
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<a href="${conPath }/reviewContentview.do?rNo=${reviewLists.rNo}&pageNum=${pageNum}">
								${reviewLists.rTitle }</a>
							</td>
							<td>
								${reviewLists.mName}
							</td>
							<td>
								${reviewLists.rHit}
							</td>
							<td>
								${reviewLists.rRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/productContentView.do?pCode=${productContentView.pCode }&pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/productContentView.do?pCode=${productContentView.pCode }&pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/productContentView.do?pCode=${productContentView.pCode }&pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>			
				</div>
			</div>
		</form>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>