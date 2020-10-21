<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.ContractsDiv{
	text-align : center;
}
.checkDiv{
	display: inline-block;
	margin-top : 10px;
	margin-bottom : 10px;
	width: 800px;
	text-align :left;
}

.joinContract01Div {
	display: inline-block;
	margin-top : 10px;
	margin-bottom : 10px;
	border : solid 1px;
	width: 800px;
	height: 300px;
	overflow: auto;
	text-align:left;
}

.joinContract02Div {
	display: inline-block;
	margin-top : 10px;
	margin-bottom : 10px;
	border : solid 1px;
	width: 800px;
	height: 300px;
	overflow: auto;
	text-align:left;
}

.isJoinAgreeDiv {
	margin-top : 10px;
	margin-bottom : 10px;
	padding-top : 10px;
	display: inline-block;
	width: 800px;
	height: 100px;
	border-top: solid 1px;
}

</style>
<title>회원가입 약관 동의</title>
<script>

	function backAction(){
		if(confirm("정말 회원가입을 취소 하시겠습니까?") == true){
			history.back();	
		}
	}
	
	function NextInfo(){
		var frm = document.joinContractForm;
		var contract01 = $("#isContract01");
		var contract02 = $("#isContract02");
		var advertis = $("#isAdvertis");
		
		if(contract01.is(":checked") && contract02.is(":checked")){
		 	if(advertis.is(":checked")){
				$("#paramIsAdvertis").val(advertis.val());	
			}else{
				$("#paramIsAdvertis").val("N");
			}
			frm.submit();
		}else{
			alert("약관에 동의 하지 않으셧습니다.");
		}
		
		
	}
</script>
</head>
<body>
<form  name="joinContractForm" method="post" action="/common/joinUserInfo.do">
	<input type="hidden" id="paramIsAdvertis" name="paramIsAdvertis" value="N">
	
	<div class="direcortyDiv">
	 - 회원가입 > 약관동의
	</div>
	<div class="ContractsDiv">	
		<div class="joinContract01Div">
			${result.contractTxt01}
		</div>
		<div class="checkDiv">
			<input type="checkbox" id="isContract01">동의
		</div>
		
		<div class="joinContract02Div">
			${result.contractTxt02}
		</div>
		<div class="checkDiv">
			<input type="checkbox" id="isContract02">동의
		</div>
		<div class="checkDiv">
			<input type="checkbox" id="isAdvertis" value="Y">광고 수신여부 동의
		</div>
		<div class="isJoinAgreeDiv">
			<input type="button" value="취소" onclick="backAction();">
			
			<input type="button" value="다음" onclick="NextInfo();">
		</div>
	</div>
</form>
</body>
</html>