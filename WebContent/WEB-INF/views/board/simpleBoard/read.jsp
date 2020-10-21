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
	.readDiv{
		margin-top : 10px;
		display: inline-block;
		width: 800px;
		padding: 0 0 0 0;
	}
	.readTb{
		border-collapse: collapse;
		width: 800px;
	}
	
	.infoTr {
		background-color: #F2FFED;
		border-top: 2px solid #BDBDBD;
	}
	.infoTr td{
		text-align: left;
	}
	
	.titleTr {
		background-color: #F2FFED;
		border-bottom:  1px solid #BDBDBD;
		text-align: left;
	}
	.contensTr{
		border-bottom: 2px solid #BDBDBD;
	}
	
	.titleTh{
		width: 70px;
		text-align: center;
	}
	
	.infoTh {
		width: 70px;	
		text-align: center;
	}
	
	.contents{
		position : relative;
		width: 100%;
		padding: 0 0 0 0;
		border-left: 0px;
		border-right: 0px; 
		text-align: left;
		min-height: 200px;
		
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
	
	function deleteAction(){
		var no= "${result.no}";
		
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="/board/simpleBoard/deleteAction.do?no="+no;
		}
	}
	
	function modify(){
		var frm = document.modifyForm;
		frm.submit();
		
	}
</script>
<title>simpleBoardRead</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 기본 게시판 > 글상세
</div>
<div class="simpleBoardPageDiv">
<form name="modifyForm" method="post" action="/board/simpleBoard/modify.do">
	<input type="hidden" name="no" value="${result.no}">
	<input type="hidden" name="userNicNm" value="${result.userNicNm}">
	<input type="hidden" name="upDt" value="${result.upDt}">
	<input type="hidden" name="title" value="${result.title}">
	<input type="hidden" name="contents" value="${result.contents}">
</form>

	<div class="readDiv">
		<table class="readTb">
			<tbody>
			<tr class="infoTr">
				<th class="infoTh" >NO : </th>
				<td>${result.no}</td>
				<th class="infoTh">작성자 : </th>
				<td>${result.userNicNm}</td>
				<th class="infoTh">작성일 : </th>
				<td>${result.upDt}</td>
				<th class="infoTh">조회수 : </th>
				<td>${result.viewCount}</td>
			</tr>
			<tr class="titleTr">
				<th class="titleTh">제목 : </th>
				<td colspan="7">${result.title}</td>
			</tr>
			<tr class="contensTr">
				<td colspan="8">
					<div class="contents" >
						${result.contents}
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	
	<div class="btDiv">
		<div class="btDivLeft" >
			<input type="button" value="목록" onclick="javascript:location.href='/board/simpleBoard/list.do';">
		</div>
		<div class="btDivRight" >
		 	<c:if test="${result.userId == sessionScope.userId}">
				<input type="button" value="삭제" onclick="deleteAction()">
				<input type="button" value="수정" onclick="modify()">
			</c:if>
		</div>
	</div>
 </div>
 
</body>
</html>