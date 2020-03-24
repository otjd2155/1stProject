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
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
		
			$('.mId').keyup(function(){
				var mId = $('.mId').val();
				
				$.ajax({
					url : '${conPath}/idConfirm.do',
					dataType : 'html',
					data : "mId="+mId,
					success : function(data){
							$('#idConfirmResult').html(mId+data);
					}
				});
			});
			
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
				 var idPattern = /^[a-zA-Z0-9]{4,12}$/;
			     var pwPattern = /^[a-zA-Z0-9]{4,12}$/;
			     var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			     var phonePattern = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
			   
			       if($('.mId').val()==''){
			           alert("ID를 입력하세요");
			           $('.mId').focus();
			           return false;
			       }
			       if(!idPattern.test($('.mId').val())) {
			           alert("아이디는 4~12자의 영문 대소문자와 숫자로만 입력");
			           $('.mId').focus();
			           return false;
			       }
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
			       if($('.mName').val()==''){
				       alert("이름을 입력하세요");
				       return false;
				       $('.mName').focus();
				   }
			       
			});
	});
</script>			
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="join">
				<form action="${conPath }/join.do" method="post" >
					<h3>회원가입</h3>
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
									<input type="text" name="mId" class="mId" >
									<span id="idConfirmResult"></span>
									(4~12자의 영문 대소문자와 숫자로만 입력)
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
									<input type="text" name="mName" class="mName">
								</td>
							</tr>
							<tr>
								<th>
									주소 
								</th>
								<td>
									<input type="text" name="mAddress" class="mAddress">
									
								</td>
							</tr>
							<tr>
								<th>
									휴대폰<b>*</b>
								</th>
								<td>
									<input type="text" name="mTel" class="mTel" >
								</td>
							</tr>
							<tr>
								<th>
									이메일<b>*</b>
								</th>
								<td>
									<input type="tel" name="mEmail" class="mEmail" >
								</td>
							</tr>
							<tr>
								<th>
									생일
								</th>
								<td>
									<input type="text" name="mBirth" id="datepicker" >
								</td>
							</tr>
						</table>
						<br>
						<br>
						<br>
						<br>
						<p class="subtn">
							<input type="submit" value="회원가입" class="joinbtn">
							&nbsp;
							<input type="button" value="회원가입 취소" class="resetbtn"
								onclick="location.href='${conPath}/main/main.jsp'"
							>
						</p>			
				</form>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>