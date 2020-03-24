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
	<link href="${conPath }/css/main.css" rel="stylesheet">
	<link href="${conPath }/css/join.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$( function() {
		   $( "#datepicker" ).datepicker({
			   dateFormat : 'yy-mm-dd',
			   monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			   showMonthAferYear : true,
		   	   yearSuffix : "년", //2020년 3월
		   	   showOtherMonths : true,
		   	   dayNamesMin : ['일','월','화','수','목','금','토']
		   });
		} );
	$(document).ready(function(){
				$('.mPwchk').keyup(function(){
					var mPw = $('.mPw').val();
					var mPwchk = $(this).val();
					if(mPw == mPwchk){
						$('#pwChkResult').html('비밀번호 일치');
					}else{
						$('#pwChkResult').html('비밀번호 불일치');
					}
				});
			$('form').submit(function(){
			     var pwPattern = /^[a-zA-Z0-9]{4,12}$/;
			     var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			     var phonePattern = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
			      if($('.mPw').val()==''){
				       alert("비밀번호를 입력하세요");
				       $('.mPw').focus();
				       return false;
				   }
			       if($('.mPwchk').val()==''){
				       alert("비밀번호를 입력하세요");
				       $('.mPwchk').focus();
				       return false;
				   }
			       if($('.mPw').val()!=$('.mPwchk').val()){
			    	   alert("비밀번호가 다릅니다.");
			    	   $('.mPwchk').focus();
				       return false;
			       }
			       if(!pwPattern.test($('.mPw').val())){
			    	   alert("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력 ");
			    	   $('.mPw').focus();
				       return false;
			       }
			       if($('.mEmail').val()==''){
				       alert("e-mail 를 입력하세요");
				       $('.mEmail').focus();
				       return false;
				   }
			       if(!emailPattern.test($('.mEmail').val())){
			    	   alert("e-mail을 잘못입력하셨습니다 ");
			    	   $('.mEmail').focus();
				       return false;
			       }
			       if($('.mTel').val()==''){
				       alert("전화번호를 입력하세요");
				       return false;
				       $('.mTel').focus();
				   }
			       if(!phonePattern.test($('.mTel').val())){
			    	   alert("전화번호(-)빼고 입력 ");
			    	   $('.mTel').focus();
				       return false;
			       }
			       
			});
			
			$('#delete').click(function(){
				var confirmChk = confirm("정말 삭제 하시겠습니까?");
					if(confirmChk == true){
						location.href='${conPath}/memberUnsubscribe.do'
					}else{
						alert('삭제 실패');
					}
				});	
	});
</script>			
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="join">
				<form action="${conPath }/memberModify.do" method="post" >
					<h3>회원정보수정</h3>
					<hr>
					<br>
						<table>
							<tr>
								<th>
									<p class="info">기본정보</p>
								</th>
								<td>
									<p class="required"><b>*</b>필수 입력사항</p>
								</td>
							</tr>
							<tr>
								<th>
									아이디<b>*</b>
								</th>
								<td>
									<input type="text" name="mId" class="mId" readonly="readonly" value="${member.mId }">
									
								</td>

							</tr>
							<tr>
								<th>
									비밀번호<b>*</b>
								</th>
								<td>
									<input type="password" name="mPw" class="mPw">
									(4~12자의 영문 대소문자와 숫자로만 입력)
								</td>
							</tr>
							<tr>
								<th>
									비밀번호 확인<b>*</b>
								</th>
								<td>
									<input type="password" name="mPwchk" class="mPwchk">
									<span id="pwChkResult"></span>
								</td>
							</tr>
							<tr>
								<th>
									이름<b>*</b>
								</th>
								<td>
									<input type="text" name="mName" class="mName" readonly="readonly" value="${member.mName }">
								</td>
							</tr>
							<tr>
								<th>
									주소 
								</th>
								<td>
									<input type="text" name="mAddress" class="mAddress" value="${member.mAddress }">
								</td>
							</tr>
							<tr>
								<th>
									휴대폰<b>*</b>
								</th>
								<td>
									<input type="text" name="mTel" class="mTel" value="${member.mTel }">
								</td>
							</tr>
							<tr>
								<th>
									이메일<b>*</b>
								</th>
								<td>
									<input type="tel" name="mEmail" class="mEmail" value="${member.mEmail }">
								</td>
							</tr>
							<tr>
								<th>
									생일
								</th>
								<td>
									<input type="text" name="mBirth" id="datepicker" value="${member.mBirth }">
								</td>
							</tr>
						</table>
						<br>
						<br>
						<br>
						<br>
						<p class="subtn">
							<input type="submit" value="회원정보수정" class="joinbtn">
							&nbsp;
							<input type="button" value="취소" class="resetbtn"
								onclick="location.href='${conPath}/main/main.jsp'"
							>
							<input type="button" id="delete" value="회원탈퇴" class="resetbtn">
						</p>			
				</form>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>