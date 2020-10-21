<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/smartEditor2.0/js/HuskyEZCreator.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/common/common.css">


<style>
	.pageDiv{
	}
	.readDiv{
		margin-top : 10px;
		display: inline-block;
		width: 800px;
		padding: 0 0 0 0;
	}
	
	.infoDiv {
		width: 800px;
		border-top: solid 2px gray;
		background-color: #F6F6F6;
		text-align: left;
	}
	.titleDiv {
		width: 800px;
		border-bottom: solid 1px gray;
		background-color: #F6F6F6;
		text-align: left;
	}

	
	.contentsDiv{
		display: inline-block;
		width: 800px;
		min-height: 300px;
		max-height: 1000px;
		border-bottom: solid 2px gray;
		overflow: auto;
	}
	
	.fileDiv {
		width: 800px;
		text-align: left;
		border-bottom: solid 1px gray;
		background-color: #F6F6F6;
	}
	
	.btDiv{
		display: inline-block;
		width: 800px;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	.btDivLeft{
		display: inline-block;
		width: 45%;
		text-align: left;
	}
	
	.btDivRight{
		display: inline-block;
		width: 45%;
		text-align: right;
	}
	
	.writeCommentDiv{
		display: inline-block;
		width: 800px;
		text-align: left;
		
		margin-top: 5px;
		margin-bottom: 5px; 
		
	}
	
	.commentDivArea{
		display: inline-block;
		width: 800px;
		border-top: solid 2px gray;
		margin-top: 10px;
		margin-bottom: 10px; 
	}
	
	.commentTa{
		width: 700px; height: 50px;
		background-color : #BDBDBD;
	}
	
	.commentModifyTa{
		width: 700px; height: 50px;
	}
	
	.commentDiv{
		border-bottom: solid 1px gray;text-align: left;
	}
	
	.commentDiv ul{
		list-style-type:none; margin: 0;padding: 0; overflow: auto;
	}
	
	.commentDiv ul li:first-child{
		display: inline; width: 70%; text-align: left; float: left;
	}
	.commentDiv ul li:last-child{
		display: inline; width: 30%; text-align: right; float: left;
		
	}
	
	.commentDiv ul li a{
		color: black;
		text-decoration: none;
		color: gray; 
	}
	
	.commentDiv ul li a:hover{
		text-decoration: underline;
		color: gray; 
	}
	
	.commnetBt{
		height: 55px; vertical-align: top;
	}
	.commnetModifyBt{
		margin-left : 5px;
		height: 55px; vertical-align: top;
		width: 60px;
	}
</style>
<script>

$(document).ready(function(){
	if("${sessionScope.userId}" != ""){
		$("#commentTa").attr("readonly", false);
		$("#commentTa").css("background-color",'white');
	}
});


//글 삭제
function deleteAction(){
	var form = document.modifyForm
	var action = "/board/smartBoard/smartDeleteAction.do";
	var name="deleteForm";
	form.setAttribute("action",action); 
	form.setAttribute("name", name); // 값
	
	if(confirm("정말 삭제하시겠습니까?")){
		form.submit();
	}
	
}
 //수정페이지 이동
 function modify(){
	var frm = document.modifyForm;
	frm.contents.value=$("#contents").html();
	frm.submit(); 
	
}	 
 
 //게시물 추천
 function rcmndAction(){
	 var no= "${result.no}";
	   jQuery.ajax({
	        url: '/board/smartBoard/smartRcmndCnt.do',
	        type: 'POST',
	        data:{'no':no},
	        dataType:'json',
	        success: function (data) {
	        	alert(data.msg);
	        }
	    });
 }
 
 
 //파일 다운로드
 function fileDownLoad(flNm,flId){
	
	   var form = document.createElement("form"); // 폼 생성
	    form.setAttribute("method", "POST"); // 전송방식
	    form.setAttribute("action", "/board/smartBoard/smartFileDownLoad.do"); // 주소
	    document.body.appendChild(form);

	    var fileNm = document.createElement("input"); // 입력창 생성
	    fileNm.setAttribute("type", "hidden"); // 타입
	    fileNm.setAttribute("name", "fileNm"); // name 속성
	    fileNm.setAttribute("value", flNm); // 값
	    form.appendChild(fileNm); // 추가한당
	    
	    var fileId = document.createElement("input"); // 입력창 생성
	    fileId.setAttribute("type", "hidden"); // 타입
	    fileId.setAttribute("name", "fileId"); // name 속성
	    fileId.setAttribute("value", flId); // 값
	    form.appendChild(fileId); // 추가한당

	    form.submit();

 }
 
 //-------------------------------------덧글start---------------------------------
 //덧글 입력
 function commentWriteAction(){
	 if("${sessionScope.userId}" == ""){
		 alert("로그인 후 이용이 가능합니다.");
	 }else{
		 var contents = $("#commentTa").val();
		 var boardNo = "${result.no}";
		 if(contents == ""){
			 alert("내용을 입력 해 주세요.");
			 return;
		 }
		 
		 $.ajax({
			  url: "/board/smartBoard/smartCommentWriteAction.do", 
			  dataType:'json',
			  type : "POST", 
			  data:{"boardNo":boardNo,"contents":contents}, 
			  success : function(data){
				  	if(data.result){
				  		alert("덧글이 정상적으로 작성되었습니다.");
						 //입력창 초기화
						 $("#commentTa").val("");
						  location.reload();	
				  	}else{
				  		alert("덧글이 작성 중 문제가 발생하였습니다.");
				  	}
			  }
		 });
	 }
 }

 //덧글 삭제
 function commentDeleteAction(idx){
	 if(isModify){
		 alert("다른 게시물이 수정 중 입니다.");
		 return false;
	 }
	 
	 if(confirm("해당 글을 정말 삭제하시겠습니까?")){
		 $.ajax({
			  url: "/board/smartBoard/smartCommentDeleteAction.do", 
			  dataType:'json',
			  type : "POST", 
			  data:{"idx":idx}, 
			  success : function(data){
				if(data.result){
					alert("정상적으로 삭제되었습니다.");
					location.reload();
				}else{
					alert("삭제중 문제가 발생하였습니다.");
				}
			  }
		 });
	 }
 }
 
 var isModify = false;
 //덧글 수정
 function commentModify(obj,contents,idx){
	 if(isModify){
		 alert("현제 다른 글을 수정중입니다.")
	 }else{
		 isModify = true;
		 taId = "commentTa"+idx;
		 var commentLi =  obj.parentNode;
		 var liObj = "<a href='javascript:;' onclick='commentModifyCancle(this,\""+contents+"\",\""+idx+"\")'>취소</a>";
		 
		 
		 var commentDiv =  obj.parentNode.parentNode.parentNode;
		 var insertDiv = commentDiv.getElementsByTagName("div")[0];
		 var modifyObj = "<textarea rows='3' id='"+taId+"' class='commentModifyTa'>"+contents+"</textarea>"+
		 "<input type=\'button\' class='commnetModifyBt' value='수정' onclick='commentModifyAction(this,\""+taId+"\",\""+idx+"\")'>";
		/*  "<input type='button' class='commnetModifyBt' value='수정' onclick='commentModifyAction("+obj+",\""+taId+"\",\""+idx+"\")'>"; */
		 
		
		
		 commentLi.innerHTML = liObj;
		 insertDiv.innerHTML = modifyObj;
	 }
 }

 // 덧글 수정 취소
 function commentModifyCancle(obj,contents,idx){
	 isModify = false;
	 
	 var commentLi =  obj.parentNode;
	 var liObj =  "<a href='javascript:;' onclick='commentModify(this,\""+contents+"\",\""+idx+"\");'>수정 </a>"+
	 "<a href='javascript:;' onclick='commentDeleteAction(\""+idx+"\");'>삭제</a>"
	 
	 var commentDiv =  obj.parentNode.parentNode.parentNode;
	 var insertDiv = commentDiv.getElementsByTagName("div")[0];
	 
	 commentLi.innerHTML = liObj;
	 insertDiv.innerHTML = contents;
	 //location.reload();
 }
 
 //덧글수정 액션
 function commentModifyAction(obj,taId,idx){
	 if(!isModify){
		 alert("수정 상태가 아닙니다.");
	 }else{
		 var contents =  $("#"+taId).val();
		 alert(contents);
		 
		  $.ajax({
			  url: "/board/smartBoard/smartCommentModifyAction.do", 
			  dataType:'json',
			  type : "POST", 
			  data:{"idx":idx,"contents":contents}, 
			  success : function(data){
				if(data.result){
					alert("정상적으로 수정 되었습니다.");
					
					 isModify = false;
					 
					 var commentUl =  obj.parentNode.parentNode.getElementsByTagName("ul")[0];
					 var commentLi =  commentUl.getElementsByTagName("li")[1];
					 var liObj =  "<a href='javascript:;' onclick='commentModify(this,\""+contents+"\",\""+idx+"\");'>수정 </a>"+
					 "<a href='javascript:;' onclick='commentDeleteAction(\""+idx+"\");'>삭제</a>"
					 
					 var commentDiv =  obj.parentNode.parentNode;
					 var insertDiv = obj.parentNode;
					 
					 commentLi.innerHTML = liObj;
					 insertDiv.innerHTML = contents;
				}else{
					alert("수정 중 문제가 발생하였습니다.");
				}
			  }
		 });
	 }
 }
 //-------------------------------------덧글end---------------------------------
 //답글 작성
 function reWriteBoard(){

	   var form = document.createElement("form"); // 폼 생성
	    form.setAttribute("method", "POST"); // 전송방식
	    form.setAttribute("action", "/board/smartBoard/smartWrite.do"); // 주소
	    document.body.appendChild(form);

	    
	    var topNo = document.createElement("input"); // 입력창 생성
	    topNo.setAttribute("type", "hidden"); // 타입
	    topNo.setAttribute("name", "topNo"); // name 속성
	    topNo.setAttribute("value", "${result.no}"); // 값
	    form.appendChild(topNo); // 추가한당
	    
	    var category = document.createElement("input"); // 입력창 생성
	    category.setAttribute("type", "hidden"); // 타입
	    category.setAttribute("name", "category"); // name 속성
	    category.setAttribute("value", "${result.category}"); // 값
	    form.appendChild(category); // 추가한당

	    form.submit();
	 
	 
/* 		location.href="/board/smartBoard/smartWrite.do"; */
	}
</script>
<title>smartBoardRead</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 기본 게시판 > 글읽기
</div>
<div class="pageDiv">
<form name="modifyForm" method="post" action="/board/smartBoard/smartModify.do">
	<input type="hidden" name="no" value="${result.no}">
	<input type="hidden" name="userNicNm" value="${result.userNicNm}">
	<input type="hidden" name="upDt" value="${result.upDt}">
	<input type="hidden" name="title" value="${result.title}">
	<!-- contents 값 미리 입력 시 사진으로인해 태그 깨짐 -->
	<input type="hidden" name="contents">
	<input type="hidden" name="category" value="${result.category}">
	<input type="hidden" name="publicLv" value="${result.publicLv}">
	<input type="hidden" name="reply" value="${result.reply}">
	<input type="hidden" name="topNo" value="${result.topNo}">
</form>
	<div class="readDiv">
		<div  class="infoDiv">
			NO : ${result.no}
			카테고리 :  ${result.categoryNm}
			작성자 : ${result.userNicNm}
			공개 레벨 : ${result.publicLvNm}
			작성일 : ${result.upDt}
		</div>
		<div class="titleDiv">
			제목 : ${result.title}
		</div>
		<div class="contentsDiv" id="contents">${result.contents}</div>
		<c:if test="${not empty fileList}">
			<c:forEach var="list" items="${fileList}" varStatus="status">
			<div class="fileDiv" >
				<div>
					첨부파일(1) :
				</div>
				<div>
					- <a href="javascript:;" onclick="fileDownLoad('${list.fileNm}','${list.fileId}');">${list.fileNm}</a>
					<img src="/resources/img/smartBoard/file_img.png" style="width: 20px;height: 20px;">
				</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
	
	<div class="btDiv">
		
			<div class="btDivLeft" >
			<input type="button" value="목록" onclick="javascript:location.href='/board/smartBoard/smartList.do';">
		</div>
		<div class="btDivRight" >
			<c:if test="${not empty sessionScope.userId && sessionScope.userId != result.userId}">
				<c:if test="${result.reply=='N'}">
					<input type="button" value="답글" onclick="reWriteBoard()">
				</c:if>
				<input type="button" value="추천" onclick="rcmndAction()">
			</c:if>
		 	<c:if test="${isModify}">
				<input type="button" value="삭제" onclick="deleteAction()">
				<input type="button" value="수정" onclick="modify()">
			</c:if>
		</div>
		
	</div>
	<div class="writeCommentDiv">
		<textarea rows="3" id="commentTa" class="commentTa" readonly="readonly"></textarea>
		<input type="button" class="commnetBt" value="덧글 입력" onclick="commentWriteAction();">
	</div>
	<div class="commentDivArea" id="commentDivArea">
	<c:if test="${not empty commentList}">
		<c:forEach var="list" items="${commentList}" varStatus="status">
			<div class="commentDiv">
				<ul>
					<li>┗ ${list.userNicNm} ${list.upDt}</li>
					<li>
						<c:if test="${sessionScope.userId != list.userId}">
							<a href="javascript:;">신고</a>
						</c:if> 
						<c:if test="${sessionScope.userId == list.userId || sessionScope.userType != 'U' && not empty sessionScope.userId}">
							<a href="javascript:;" onclick="commentModify(this,'${list.contents}','${list.idx}');">수정 </a>
							<a href="javascript:;" onclick="commentDeleteAction('${list.idx}');">삭제</a>
						</c:if>
					</li>
				</ul>
				<div style="padding-left: 20px;">
					${list.contents}
				</div>
			</div>
		</c:forEach>
	</c:if>
	</div>
 </div>
 
</body>
</html>