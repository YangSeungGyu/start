<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.LoginPageDiv{
		text-align: center;
	}
	.loginDiv{
		display: inline-block;
		margin-top : 10px;
		margin-bottom : 10px;
		border : solid 3px #AAD7A5;
		width: 600px;
		height: 150px;
		
	 	padding:1.0em;
	  	line-height: 2em;
	  	border-radius:1.0em;
	  	-moz-border-radius: 1.0em;
	  	-webkit-border-radius: 1.0em;
	}
	
	.loginSubDiv{
		display: inline-block;
		margin-top : 10px;
		margin-bottom : 10px;
		width: 600px;
		height: 150px;
	}	
	.loginBt{
		height: 80px; 
		width: 100px;
	}
	.resultMsg{
		font-size : 12px;
		color: red;
		display: none;
	}
</style>
<title>로그인</title>
<script>
	$(document).ready(function(){
	    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var userId = getCookie("userId");
	    $("#userId").val(userId); 
	     
	    if($("#userId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#saveId").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#saveId").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#saveId").is(":checked")){ // ID 저장하기 체크했을 때,
	            var userId = $("#userId").val();
	            setCookie("userId", userId, 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("userId");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#userId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#saveId").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            var userId = $("#userId").val();
	            setCookie("userId", userId, 7); // 7일 동안 쿠키 보관
	        }
	    });
	});
	
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}


	function loginAction(){
		 var params = jQuery("form[name=loginForm]").serializeArray();
		    jQuery.ajax({
		        url: '/common/loginAction.do',
		        type: 'POST',
		        data:params,
		        dataType:'json',
		        success: function (data) {
		        	if(data.isSucess){
		        		alert(data.msg);
		        		location.href="/start/home.do";
		        	}else{
		        		$("#resultMsg").html("* "+data.msg);
		        		 $("#resultMsg").css('display','block')
		        	}
		        }
		    });
	}
	
</script>
</head>
<body>
<div class="direcortyDiv">
	 - 로그인
</div>
<div class="LoginPageDiv">
	<div class="loginDiv">
		<form name="loginForm" method="post" action="/common/loginAction.do">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="userId" name="userId"></td>
					<td rowspan="2"><input type="button" class="loginBt" value="로그인" onclick="loginAction();"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="userPw"></td>
				</tr>
				<tr>
					<td><input type="checkbox" id="saveId"> 아이디 저장하기
						<div class="resultMsg" id="resultMsg"></div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="loginSubDiv">
		<a href="/common/joinContract.do">회원가입</a>
		 | 아이디 찾기 | 비밀번호 찾기
	</div>
</div>
</body>
</html>