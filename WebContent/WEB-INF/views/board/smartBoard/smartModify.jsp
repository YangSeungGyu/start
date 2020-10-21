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
		text-align : center;
	}
	.modifyDiv{
		margin-top : 10px;
		display: inline-block;
		width: 800px;
		padding: 0 0 0 0;
	}
	
	.titleDiv select{
		width: 130px;
		vertical-align: middle;
	}
	.titleDiv input{
		width: 650px;
	}
	
	.publicDiv{
		display: inline-block;
		width: 800px;
		text-align: left;
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
	
	#upload_wrap #upload_list {
		margin-top : 0;
		float:left;  
		list-style-image: url('/resources/img/smartBoard/disk.png');
	}
	#upload_wrap #upload_list li{
		text-align : left;
		margin:2px 0; 
		border-bottom:dotted 1px #d6d6d6;
	}
	#upload_wrap #delete_list {
		margin-bottom : 0;
		list-style-image: url('/resources/img/smartBoard/disk.png');
	}
	#upload_wrap #delete_list li{
		text-align : left;
		margin:2px 0; 
		border-bottom:dotted 1px #d6d6d6;
	}
	#upload_wrap {
		position:relative; float:left; 
	}
</style>
<script>




	var oEditors = [];
	window.onload=function(){
		nhn.husky.EZCreator.createInIFrame({
			 oAppRef: oEditors,
			 elPlaceHolder: "contents",
			 sSkinURI: "/resources/smartEditor2.0/SmartEditor2Skin.html",
			 fCreator: "createSEditorInIFrame"
			});
		function insertIMG(irid,fileame){
		  var sHTML = "<img src='" + imagepath + "/" + fileame + "' border='0'>";
		  oEditors.getById[irid].exec("PASTE_HTML", [sHTML]);
		}
		
		
		
		//공개레벨 check
		$("input:radio[name='publicLv']").removeAttr('checked');
		$("input:radio[name='publicLv']:radio[value='${result.publicLv}']").attr('checked',true);
		
		//카테고리 check
		$("#category").val("${result.category}");
		//답글이 있을경우
		if("${result.reply}"=="Y"){
			$("#category").attr("disabled",true);
		}
	};
	
	
	function modifyAction(){
		var frm = document.modifyForm;
		
		if($("#category").val() == ""){
			alert("카테고리를 입력 해 주세요");
			$("#category_target").val("${result.category}");
			return null;
		}else{
			$("#category_target").val($("#category").val());
			
			if($("#category_target").val() != "${result.category}"){
				$("#changeCacategory").val("Y");
			}
			
		}
		
		if($("#title").val() == ""){
			alert("제목을 입력 해 주세요");
			return null;
		}
		if( $("#contents").val() == ""){
			alert("내용을 입력 해 주세요");
			return null;
		}
		
		
		if(confirm("해당 내용으로 수정 하시겠습니까?")){
			oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
			frm.submit();
		}
	}
	$(function(){
		var boardFileLength=${result.fileCnt};
		limitFileCount = (5 - boardFileLength);
		$('#del_target').hide();
		
	});
	
	function fileAdd(){
		var ullist = document.getElementById("upload_list");
		var li = document.createElement("li");
		var num =ullist.getElementsByTagName("li").length;
		
		var countnum=num+1;
		if(num>=limitFileCount){
			alert("파일은 최대 5개까지 첨부할 수 있습니다.");
			return false;
		}else{
			li.innerHTML='첨부파일 #'+countnum+' <input type="file" size="40" name="smartFileBeanList['+num+'].files" id="file'+countnum+'" />';
			ullist.appendChild(li);	
		}
		if(countnum == 2){
			$("#del_target").show();
		}
	}
	

	function fileDel(){
	 	var ullist = document.getElementById("upload_list");
		var num =ullist.getElementsByTagName("li").length;
		
		var countnum=num-1;
		
		if(num == 1){		
			return false;
		}
		else{
			$('#file'+num).parent().remove();
		}
		
		if(countnum == 1){
			$('#del_target').hide();
		}
	}
	
	function deleteFileFun(file){
		limitFileCount = limitFileCount+1;
		var deleteFile = $(file);
		
		var ullist = document.getElementById("delete_list");
		var num =ullist.getElementsByTagName("li").length;
		
		var deleteCnt = 0;
		for(var i=0 ; i < num; i++){
			if(ullist.getElementsByTagName("li")[i].style.display=="none"){
				deleteCnt= deleteCnt+1;
			}
		}
		
		deleteFile.parent().css("display","none");
		var deleteFileSq= deleteFile.find("input");
		deleteFileSq.attr("name","smartFileDeleteList["+deleteCnt+"].deleteFile");
		
		//name적용 확인
	/*	var testLis= deleteFile.parent().parent().children();
		
		for(var i=0 ;i<testLis.length;i++){
			alert(testLis.eq(i).find("a").find("input").attr("name"));
		}*/
	}
</script>
<title>smartBoardModify</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 기본 게시판 > 글쓰기
</div>
<div class="pageDiv">

<form  name="modifyForm" method="post" enctype="multipart/form-data" action="/board/smartBoard/smartModifyAction.do">
<input type="hidden" id="changeCacategory" name="changeCacategory" value="N">
<input type="hidden" name="no" value="${result.no}">
<input type="hidden" id="category_target" name="category">

	<div class="modifyDiv">
		<div class="titleDiv">
			<select id="category">
				<option value="" >선택</option>
				<c:forEach var="list" items="${categoryList}" varStatus="status">
		          <option value='${list.cd}'>${list.cdNm}</option>
	          	</c:forEach>
			</select>
			<input type="text" id="title" name="title" value="${result.title}">
		</div>
		<div class="smartDiv">
		  	<textarea name="contents" id="contents" style=" width:780px; height:300px; display:none;" >${result.contents}</textarea>
		</div>
		<!-- 파일 첨부  -->
	<div class="fileDiv">
		<div id="upload_wrap">
		<!--첨부된 파일 삭제 목록  -->
			<c:if test="${!empty fileList}">
			<div>
				<ul id="delete_list">
					<c:forEach var="file" items="${fileList}" varStatus="i">
						<li>
							${file.fileNm}
							<a href="javascript:;" onclick="deleteFileFun(this);">
								<input type="hidden" value="${file.idx}">
								<img src="/resources/img/smartBoard/img_minus_btn01.gif" alt="첨부파일 삭제"/>
							</a>
						</li>
					<c:set var="listCnt" value="${i.count+1}" />
					</c:forEach>
				</ul>
			</div>
			</c:if>
			
				<div>
					<ul id="upload_list"><!--id="upload_list"  -->
						<li >첨부파일 #1 <input name="smartFileBeanList[0].files" id="file1" type="file" size="40" />
							<a id="add_target" href="javascript:fileAdd();">
								<img src="/resources/img/smartBoard/img_plus_btn01.gif" alt="첨부파일 추가" />
							</a>
							<a id="del_target" href="javascript:fileDel();">
								<img src="/resources/img/smartBoard/img_minus_btn01.gif" alt="첨부파일 삭제" />
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="publicDiv">
			공개여부  : 
			<input type="radio" name="publicLv" checked="checked" value="1">전체 공개
			<input type="radio" name="publicLv" value="2">회원공개
			<input type="radio" name="publicLv" value="3">비공개
		</div>
	</div>
</form>
	
	<div class="btDiv">
		<div class="btDivLeft" >
			<input type="button" value="목록" onclick="javascript:location.href='/board/smartBoard/smartList.do';">
		</div>
		<div class="btDivRight" >
			<input type="button" value="취소" onclick="javascript:history.back();">
			<input type="button" value="수정" onclick="modifyAction();">
		</div>
	</div>
 </div>
</body>
</html>