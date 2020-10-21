<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.importSp{
		color : red;
	}
	
	.checkImg{
	 height: 15px;
	}
</style>
<title>회원가입 정보 입력</title>
<script>
	var isCheckId = false;
	var isCheckPw = false;

	//아이디 확인
	function cehckId(){
		 var checkId= $.trim($("#userId").val());
		 
		 if(checkId==""){
			 alert("아이디를 입력 해 주세요.");
		 }else{
			 if(/^[a-zA-Z0-9_-]{4,12}$/.test(checkId)){
				 $.ajax({
					  url: "/common/checkId.do", 
					  dataType:'json',
					  type : "POST", 
					  data:{"paramUserId":checkId}, 
					  success : function(data){
						  if(data.check==true){
							  isCheckId = true;
							  $("#isCehckIdImg").css('display','inline-block')
							  $("#isCehckId").css('display','none');
						  }else{
							  alert("해당계정은 이미 사용하고 있습니다.");
							  isCheckId = false;
							  $("#isCehckIdImg").css('display','none')
							  $("#isCehckId").css('display','inline-block');
						  }
					  }
				 }); 
			 }else{
				 alert("아이디 형식에 어긋납니다.");
			 }
		 }
	}
	
	//아이디 변경 시
	function changeId(){
		 isCheckId = false;
		  $("#isCehckIdImg").css('display','none');
		  $("#isCehckId").css('display','inline-block');
	}
	
	//패스워드 확인
	function cehckPw(){
		var pw = $.trim($("#userPw").val());
		var pwRe = $.trim($("#userPwRe").val());
		
		if(pw == ""||pwRe == ""){
			alert('패스워드를 입력 해 주세요.');
		}else{
			if(!/^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/.test(pw)){
				alert('비밀번호 규칙에 어긋납니다.');			
			}else{
				if(pw==pwRe){
					isCheckPw = true;
					$("#isCehckPwImg").css('display','inline-block');
					$("#isCehckPw").css('display','none');
					
				} else{
					isCheckPw = false;
					$("#isCehckPwImg").css('display','none');
					$("#isCehckPw").css('display','inline');
					alert('패스워드가 다릅니다.');
				}
			}
		}
	}
	
	// 패스워드 변경 시
	function changePw(){
		isCheckPw = false;
		$("#isCehckPwImg").css('display','none');
		$("#isCehckPw").css('display','inline-block');
	}
	
	
	//이메일 형식 선택
	function selectedEmail(){
		emailaddr = $("#emailaddr").val();
		if(emailaddr == ""){
			$("#eMail2").prop('readonly', false);
			$("#eMail2").val("");
		}else{
			$("#eMail2").prop('readonly', true);
			$("#eMail2").val(emailaddr);
		}
	}
	
	//회원가입
	function joinUserAction(){
		
		var userNm = $("#userNm");
		
		if(userNm.val() == ""){
			alert('이름이 입력되지 않았습니다');
			userNm.focus();
			return false;
		}else if(!/^[가-힣]+$/.test(userNm.val())){
			alert("한글이 아닙니다.");
			return false;
		}
		
		var userNicNm = $("#userNicNm"); 
		if(userNicNm.val() == ""){
			alert('닉네임이 입력되지 않았습니다');
			userNicNm.focus();
			return false;
		}
		
		var residentNum1 = $("#residentNum1");
		var residentNum2 = $("#residentNum2");
		if(residentNum1.val() == "" || residentNum2.val() == ""){
			alert('주민등록 번호가 입력되지 않았습니다');
			residentNum1.focus();
			return false;
		}else{
			$("#residentNum").val(residentNum1.val()+'-'+residentNum2.val());
			if(!/^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/.test($("#residentNum").val())){
				alert('주민등록 번호 형식이 아닙니다.');
				return false;
			}
		}
		
		
		if(!isCheckId){
			alert('아이디 중복검사를 해주세요.');
			$("#userId").focus();
			return false;
		}
		
		
		if(!isCheckPw){
			alert('패스워드 확인을 해주세요.');
			$("#userPw").focus();
			return false;
		}
		
	 	
		var pwQCd = $("#pwQCd"); 
		if(pwQCd.val() == ""){
			alert('패스워드 질문을 선택 해 주세요.');
			pwQCd.focus();
			return false;
		}
		
		var pwA = $("#pwA"); 
		if(pwA.val() == ""){
			alert('질문의 답변을 입력 해 주세요.');
			pwA.focus();
			return false;
		}
		
		var eMail1 = $("#eMail1");
		var eMail2 = $("#eMail2");
		if(eMail1.val() == "" || eMail2.val() == ""){
			alert('이메일이 입력되지 않았습니다.');
			return false;
		}else{
			var eMail =  eMail1.val()+"@"+eMail2.val();
			$("#eMail").val(eMail);
			 if(!/[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/.test($("#eMail").val())){
				alert('이메일 형식에 어긋납니다.');
				return false;
			} 
		}
		
	
		var phoneNumber1 = $("#phoneNumber1");
		var phoneNumber2 = $("#phoneNumber2");
		var phoneNumber3 = $("#phoneNumber3");
		
		if(phoneNumber1.val() == "" || phoneNumber2.val() == "" || phoneNumber3.val() == ""){
			alert('핸드폰 번호가 입력되지 않았습니다');
			phoneNumber1.focus();
			return false;
		}else{
			var phoneNumber =  phoneNumber1.val()+'-'+phoneNumber2.val()+'-'+phoneNumber3.val();
			$("#phoneNumber").val(phoneNumber);
			
			if(!/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/.test($("#phoneNumber").val())){
				alert('핸드폰 번호 형식이 아닙니다.');
				return false;
			}
		}
		
		
		var jobCd = $("#jobCd"); 
		if(jobCd.val() == ""){
			alert('직업을 선택 해 주세요.');
			jobCd.focus();
			return false;
		}
		
		var interCdGroup="";
		var interCdSize = $("input[id='interCd']:checked").length
		if(interCdSize != 0){
			$("input[id='interCd']:checked").each(function(i){
				 /* alert($(this).val()); */
				 if(i!=interCdSize-1){
					 interCdGroup = interCdGroup+$(this).val()+","; 
				 }else{
					 interCdGroup = interCdGroup+$(this).val();
					 $("#interCdGroup").val(interCdGroup);
				 }
			})	
		}
		
		var joinPathCd = $("#joinPathCd"); 
		if(joinPathCd.val() == ""){
			alert('가입 경로를 선택 해 주세요.');
			joinPathCd.focus();
			return false;
		}
		
		var showInfoLvCd = $("#showInfoLvCd"); 
		if(showInfoLvCd.val() == ""){
			alert('정보 공개여부를 선택 해 주세요.');
			showInfoLvCd.focus();
			return false;
		}
		
		 // serialize() : 입력된 모든Element(을)를 문자열의 데이터에 serialize 한다.
		 var params = jQuery("form[name=joinUserForm]").serializeArray();
		    jQuery.ajax({
		        url: '/common/joinUserAction.do',
		        type: 'POST',
		        data:params,
		        dataType:'json',
		        success: function (isSucess) {
		        	if(isSucess){
		        		alert("축하합니다 가입이 완료되었습니다.");
		        		location.href="/start/home.do";
		        	}else{
		        		alert("가입중 문제가 발생하여 정상적으로 완료되지 않았습니다.");
		        		
		        		$("#residentNum2").val("");
		        		$("#residentNum").val("");
		        		$("#userPwRe").val("");
		        		
		        		isCheckId = false;
		        		$("#isCehckIdImg").css('display','none');
		      		  	$("#isCehckId").css('display','inline-block');
		      		  	
		      		  	isCheckPw = false;
		      		  	$("#isCehckPwImg").css('display','none');
		      			$("#isCehckPw").css('display','inline-block');
		        	}
		        }
		    });
	}
</script>
</head>
<body>

<div class="direcortyDiv">
 - 회원가입 > 약관동의 > 회원가입
</div>
<form name="joinUserForm" method="post" action="/common/joinUserAction.do">
<input type="hidden" id="residentNum" name="residentNum">
<input type="hidden" id="phoneNumber" name="phoneNumber">
<input type="hidden" id="eMail" name="eMail">
<input type="hidden" id="interCdGroup" name="interCdGroup">
<input type="hidden" id="snsReceiveYn" name="snsReceiveYn" value="${snsReceiveYn}">



	<div>
	 	<table width="940" style="padding:5px 0 5px 0; ">
	      <tr>
	         <th> 이름<span class="importSp">*</span></th>
	         <td><input type="text" id="userNm" name="userNm" value="테스터" maxlength="10"  ></td>
	      </tr>
	      <tr>
	      	 <th> 닉네임<span class="importSp">*</span></th>
	         <td><input type="text" id="userNicNm" name="userNicNm"  value="테스터" maxlength="10"></td>
	      </tr>
	      <tr>
	         <th>주민등록번호<span class="importSp">*</span></th>
	         <td><input type="text" id="residentNum1"  value="111111" maxlength="6"> -
	        <input type="password" id="residentNum2"  value="1111111" maxlength="7"></td>
	       </tr>
	       <tr>
	         <th>아이디<span class="importSp">*</span></th>
	         <td>
	         	<input type="text" id="userId" name="userId" onchange="changeId();" maxlength="12">
	         	<span id="isCehckId"><a href="#" onclick="javascript:cehckId();">중복확인</a></span>
	         	<span id="isCehckIdImg" style="display: none;"><img class="checkImg" src="/resources/img/check.jpg" alt ="checkImg"></span>
	         	영문 혹은 숫자 4~12자
	         </td>
	       </tr>
	       <tr>
	         <th>비밀번호<span class="importSp">*</span></th>
	         <td><input type="password" id="userPw" name=userPw onchange="changePw();" value="1q2w3e4r" maxlength="20"> 영문/숫자포함 8~20자</td>
	       </tr>
	       <tr>
	         <th>비밀번호 확인<span class="importSp">*</span></th>
	         <td><input type="password" id="userPwRe" id="userPwRe"  onchange="changePw();" value="1q2w3e4r" maxlength="20">
	         <span id="isCehckPw"><a href="#" onclick="javascript:cehckPw();">확인</a></span>
	         <span id="isCehckPwImg" style="display: none;"><img class="checkImg" src="/resources/img/check.jpg" alt ="checkImg"></span>
	         </td>
	         
	         
	       </tr>
	       <tr>
	          <th>비밀번호 힌트/답변<span class="importSp">*</span></th>
	          <td><select id="pwQCd" name='pwQCd' size='1' class='select'>
	          <option value=''>선택하세요</option>
	          <c:forEach var="list" items="${compMap.pwQCompList}" varStatus="status">
		          <option value='${list.cd}'>${list.cdNm}</option>
	          </c:forEach>	
	        </select>
	        </tr>
	        <tr>
	           <th>답변<span class="importSp">*</span></th>
	           <td><input type='text' id="pwA" name='pwA' value="111" maxlength="25"></td>
	        </tr>
	        <tr>
	          <th>이메일<span class="importSp">*</span></th>
	          <td>
	            <input type='text' id="eMail1" value="test" maxlength="20">@
	            <input type='text' id="eMail2">
	              <select id="emailaddr" onchange="selectedEmail();" style="vertical-align: middle;">
	                 <option value="">직접입력</option>
	                 <option value="daum.net">daum.net</option>
	                 <option value="empal.com">empal.com</option>
	                 <option value="gmail.com">gmail.com</option>
	                 <option value="hanmail.net">hanmail.net</option>
	                 <option value="msn.com">msn.com</option>
	                 <option value="naver.com">naver.com</option>
	                 <option value="nate.com">nate.com</option>
	              </select>
	            </td>
	         </tr>
	         <tr>
	           <th>주소</th>
	           <td>
		           <table>
						<tr>
			        		<th>우편번호:</th>
			         		<td>
			             	<input type="text" id="zipCd" name="zipCd" maxlength="5" size="5"> 
				           </td>
			         	</tr>
			         	<tr>
			         		<th>상세주소:</th>
			           	<td>
			            	<input type="text" name="address1"><br/>
			             	<input type="text" name="address2" maxlength="50">
			           	</td>
			        	</tr>           
		           </table>
	           </td>
	         </tr>
	         <tr>
	         <th>전화번호</th>
	           <td>
	           		<select id="phoneNumber1" id="homeNumber1">
		           		<option value="02">02
		           		<option value="051">051
		           		<option value="053">053
		           		<option value="032">032
		           		<option value="042">042
		           		<option value="052">052
		           		<option value="044">044
		           		<option value="031">031
		           		<option value="033">033
		           		<option value="043">043
		           		<option value="041">041
		           		<option value="063">063
		           		<option value="061">061
		           		<option value="054">054
		           		<option value="055">055
		           		<option value="064">064
		           </select> - 
	               <input type="text" id="homeNumber2" title="전화번호" maxlength="4"> -
	               <input type="text" id="homeNumber3" maxlength="4">
	            </td>
	        </tr>
	        <tr>
	          <th>핸드폰 번호<span class="importSp">*</span></th>
	           <td>
		           <select id="phoneNumber1" >
		           		<option value="010">010
		           		<option value="011">011
		           		<option value="016">016
		           		<option value="019">019
		           </select> - 
	               <input type="text" id="phoneNumber2" value="2222" maxlength="4"> -
	               <input type="text" id="phoneNumber3" value="2222" maxlength="4">
	           </td>
	          </tr>
	         <tr>
	           <th>직업<span class="importSp">*</span></th>
	           <td>
	           <select id="jobCd" name='jobCd' size='1'>
	           	 <option value=''>선택하세요</option>
	           	   <c:forEach var="list" items="${compMap.jobCompList}" varStatus="status">
		             <option value='${list.cd}'>${list.cdNm}</option>
	          	   </c:forEach>	
	           </select>
	          </td>
	        </tr>
	         <tr>
	           <th> 관심분야 </th>
	           <td>
	            	<c:forEach var="list" items="${compMap.joinPathCompList}" varStatus="status">
	              		<input type="checkbox" id='interCd' value='${list.cd}'> ${list.cdNm}
	            	</c:forEach>	 
	            </td>
	         </tr>
	         <tr>
	           <th>가입경로<span class="importSp">*</span></th>
	           <td>
	             <select id="joinPathCd" name='joinPathCd' size='1'>
	                 <option value=''>선택하세요</option>
	                 <c:forEach var="list" items="${compMap.joinPathCompList}" varStatus="status">
		            	 <option value='${list.cd}'>${list.cdNm}</option>
	          	 	 </c:forEach>	
	             </select>
	           </td>
	         </tr>
	         <tr>
	           <th>정보공개여부<span class="importSp">*</span></th>
	           <td>
	           <select id="showInfoLvCd" name='showInfoLvCd' size='0'>
	              <option value=''>선택하세요</option>
	              <c:forEach var="list" items="${compMap.showInfoCompList}" varStatus="status">
		             <option value='${list.cd}'>${list.cdExpl}</option>
	          	  </c:forEach>	
	             </select>
	            </td>
	           </tr>
	           <tr>
	             <td colspan="2" align="center">
	               <input type="reset" value="취소">
	               <input type="button" value="회원가입" onclick="joinUserAction();">
	            </td>
	           </tr>
	 	</table>
	</div>
</form>
</body>
</html>