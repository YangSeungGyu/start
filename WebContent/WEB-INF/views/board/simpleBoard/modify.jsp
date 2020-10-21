<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/common/common.css">
<style>
	.simpleBoardPageDiv{
		text-align : center;
	}
	.modifyDiv{
		margin-top : 10px;
		display: inline-block;
		width: 800px;
		padding: 0 0 0 0;
	}
	.modifyTb{
		border-collapse: collapse;
		width: 800px;
	}
	
	.titleTr {
		border-top: 2px solid #BDBDBD;
		background-color: #F2FFED;
	}
	.contensTr{
		border-bottom: 2px solid #BDBDBD;
		background-color: #F2FFED;
	}
	
	.titleTh{
		width: 50px;
	}
	
	.title{
		width: 700px;;
	}
	
	.contents{
		width: 100%;
		padding: 0 0 0 0;
		border-left: 0px;
		border-right: 0px; 
		text-align: left;
		
	}
	
	
	.btDiv{
		display: inline-block;
		width: 800px;
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
</style>
<script>
	
	function modifyAction(){
		var frm = document.modifyForm;
		if($("#title")==""){
			alert("제목을 입력 해 주세요");
			return null;
		}
		if( $("#contents")==""){
			alert("내용을 입력 해 주세요");
			return null;
		}
		if(confirm("해당 내용으로 수정 하시겠습니까?")){
			frm.submit();
		}
	}
</script>
<title>simpleBoardModify</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 기본 게시판 > 글수정
</div>
<div class="simpleBoardPageDiv">

<form  name="modifyForm" method="post" action="/board/simpleBoard/modifyAction.do">
<input type="hidden" name="no" value="${result.no}">
<input type="hidden" name="upDt" value="${result.upDt}">
	<div class="modifyDiv">
		<table class="modifyTb">
			<tbody>
			<tr class="titleTr">
				<th class="titleTh">제목 : </th>
				<td><input class="title" id="title" name="title" type="text" maxlength="30" value="${result.title}"></td>
			</tr>
			<tr class="contensTr">
				<td colspan="2">
				<textarea class="contents" name="contents" id="contents" rows="20">${result.contents}</textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
</form>
	
	<div class="btDiv">
		<div class="btDivLeft" >
			<input type="button" value="목록" onclick="javascript:location.href='/board/simpleBoard/list.do';">
		</div>
		<div class="btDivRight" >
			<input type="button" value="취소" onclick="javascript:history.back();">
			<input type="button" value="수정" onclick="modifyAction();">
		</div>
	</div>
 </div>
 
</body>
</html>