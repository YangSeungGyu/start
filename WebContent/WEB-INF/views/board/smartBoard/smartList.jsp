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
	.contensDiv {
		text-align : center;
	}
	
	.smartBoardDiv{
		display: inline-block;
		width: 800px;
	}
	
	.smartBoardTb {
		border-collapse: collapse;
		width: 800px;
		text-align: center;
	}
	
	.smartBoardTb caption {
		font-weight: bold;
		color: black;
	}
	
	.smartBoardTb thead {
		background-color: #F2FFED; 
		
	}	
	.smartBoardTb thead tr{
		border-top: 1px solid #EAEAEA;
		border-bottom: 1px solid #EAEAEA;
		background-image: url("/resources/img/smartBoard/smartBoardDoc.jpg");
	}
	
	.smartBoardTb thead th{
		border-right: 1px solid #D5D5D5;
		font-family: serif; 
		color : #5D5D5D;
	}
	
	.smartBoardTb tbody{
		font-size: 12px;
	}
	
	.smartBoardTb tbody tr{
		border-bottom: 1px solid #BDBDBD;
	}
	
	.smartBoardTb tbody tr:nth-child(even){
		background-color: #F6F6F6;
	}
	
	.smartBoardTb .noTh{
		width: 50px;
	}
	
	.smartBoardTb .titleTh, .titleTd{
		width: 450px;
		vertical-align: middle;
	}
	
	.smartBoardTb  .titleTd{
		text-align: left;
	}
	
	.smartBoardTb .titleTd div{
		position: relative;
		display : inline-block;
		max-width : 350px;
		
		text-overflow : ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	
	
	
	.smartBoardTb .writerTh, .writerTd{
		width: 100px;
		text-overflow : ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	
	
	.smartBoardTb .dtTh, .dtTd{
		width: 70px;
	}
	
	.smartBoardTb .viewCntTh, .viewCntTd{
		width: 70px;
	}
	
	.smartBoardTb .rcmndCntTh, .rcmndCntTd{
		width: 70px;
	}
	
	.smartBoardTb .fileTh, .fileTd{
		width: 70px;
	}
	
	

	.smartBoardTb a{
		color: black;
		text-decoration: none; 
	}
	
	.smartBoardTb a:hover {
		text-decoration: underline; 
	}
	
	.bottomDiv{
		display: inline-block;
		width: 800px;
	}
	
	.searchDiv{
		position:relative;
		display:inline-block;
		width: 65%;
		text-align: left;
	}
	.btDiv{
		position:relative;
		display:inline-block;
		width: 30%;
		text-align: right;
	}
</style>

<script>
	$(document).ready(function(){
		var firstPage = ${pageInfo.firstPage};
		var lastPage = ${pageInfo.lastPage};
		var pageSp= $("#pageSp");
		var pageTags="";
		for(var i=firstPage ;i <= lastPage; i++){
			var tagA = "<a href='javascript:;' onclick='pageAction("+i+");'>"+i+"<a/>&nbsp;";
			pageTags = pageTags + tagA;
			pageSp.html(pageTags);
		}
		
		//정렬 선택
		if("${pageInfo.searchOrder}"!=""){
			$("#searchOrder").val("${pageInfo.searchOrder}");
		}
		
		//검색 조건
		if("${pageInfo.searchPart}"!=""){
			$("#searchPart").val("${pageInfo.searchPart}");
		}
		
		//검색어
		if("${pageInfo.searchTerm}"!=""){
			$("#searchTerm").val("${pageInfo.searchTerm}");
		}
	});

	function writeBoard(){
		location.href="/board/smartBoard/smartWrite.do";
	}
	
	function readBoard(no,lv,writeId){
		var isAccess = false;
		//공개 레벨에따른 접근 확인
		if(lv == "1"){
			isAccess = true;
		}else if(lv == "2"){
	 		if("${sessionScope.userId}" != ""){
				isAccess = true;
			}else{
				alert("회원 공개 게시물입니다.");
				return false;
			}	 		
		}else if(lv == "3"){
	 		if('${sessionScope.userId}' == writeId || "${sessionScope.userId}" != "" && "${sessionScope.userType}" != 'U'){
				isAccess = true;
			}else{
				alert("비공개 게시물입니다.");
				return false;
			}
		}	
		
		if(no == "" && isAccess){
			alert("잘못된 접근 입니다.");			
		}else{
			location.href="/board/smartBoard/smartRead.do?no="+no;
		}
		
	}
	
	//페이지 검색
	function pageAction(page){
		if(page!=""){
			$("#crntPage").val(page);
		}
		form = document.smartSearchAction;
		form.searchOrder.value="${pageInfo.searchOrder}";  
		form.searchPart.value="${pageInfo.searchPart}"; 
		form.searchTerm.value="${pageInfo.searchTerm}";
		form.submit();
		
	}
	//조검 검색
	function searchAction(){
		form = document.smartSearchAction;
		form.submit();
		
	}
</script>
<title>smartBoardList</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 스마트 게시판
</div>
<div class="contensDiv">

	<div class="smartBoardDiv">
		<table class="smartBoardTb">
			<caption>< ${boardNm} ></caption>
			<thead>
				<tr>
					<th class="noTh" >NO</th>
					<th class="titleTh">제 목</th>
					<th class="writerTh">작성자</th>
					<th class="dtTh">작성일</th>
					<th class="viewCntTh">조회수</th>
					<th class="rcmndCntTh">추천수</th>
					<th class="fileTh">파일</th>
				</tr>
			</thead>
			<tbody>
			 <c:if test="${not empty result}">
				<c:forEach var="list" items="${result}" varStatus="status">
					<tr>
						<td class="noTd">
							${list.no}
						</td>
						<td  class="titleTd">
							<div>
								<c:if test="${list.status == 'N'}">
								<a href="javascript:;" onclick="readBoard('${list.no}','${list.publicLv}','${list.userId}');">${list.title}</a>
								</c:if>
								<c:if test="${list.status == 'D'}">
									<div style="color:gray;">(삭제된 게시물 입니다.)</div>
								</c:if>
							</div>
							<c:if test="${list.commentCnt != 0}">
							 	<span  style="vertical-align:3px; color: blue;" >(${list.commentCnt})</span>
							 </c:if>
							 
							<c:if test="${list.isNew == 'Y'}">
							 	<span  style="vertical-align:3px; color: red;" >-new-</span>
							 </c:if>
							 <c:if test="${list.isImg == 'Y'}">
							 	<img alt="img" src="/resources/img/smartBoard/isImg.jpg">
							 	
							 </c:if>		
						</td>
						<td class="writerTd">
							${list.userNicNm}
						</td>
						<td class="dtTd">
							${list.upDt}
						</td>
						<td class="viewCntTd">
							${list.viewCnt}
						</td>
						<td class="rcmndCntTd">
							${list.rcmndCnt}
						</td>
						<td class="fileTd">
							<c:if test="${list.isFile == 'Y'}">
								<img src="/resources/img/smartBoard/file_img.png" style="width: 20px;height: 20px;">(${list.fileCnt})
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
	<form name="smartSearchAction" action="/board/smartBoard/smartList.do" method="post">
		<input type="hidden" id="crntPage" name="crntPage" value="1">
		<div class="pageDiv">
		 	<c:if test="${pageInfo.isPrewPage == true}">
				<a href="javascript:;" onclick="pageAction(1);"><<</a>&nbsp;
				<a href="javascript:;" onclick="pageAction(${pageInfo.firstPage-1});"><</a>&nbsp;
			</c:if>
			<span id="pageSp">
			 </span>
			 <c:if test="${pageInfo.isNextPage == true}">
				 &nbsp;<a href="javascript:;" onclick="pageAction(${pageInfo.lastPage+1});">></a>
				 &nbsp;<a href="javascript:;" onclick="pageAction(${pageInfo.maxPageCnt});">>></a>
			 </c:if>
		</div>
		<div class="bottomDiv">
			<div class="searchDiv">
			<select name="searchOrder" id="searchOrder" style="vertical-align: middle;">
				<option value="inDt">작성순</option>
				<option value="viewCnt">조회순</option>
				<option value="rcmndCnt">추천순</option>
			</select>
			
			<select  name="searchPart" id="searchPart" style="vertical-align: middle;">
				<option value="title">제목</option>
				<option value="contents">내용</option>
				<option value="titlAndCont">제목+내용</option>
				<option value="nickName">작성자</option>
			</select>
			
			<input type="text" id="searchTerm" name="searchTerm">
			<input type="button" id="earchBt" value="검색" onclick="searchAction()">	
			</div>
			
			<div class="btDiv">
				<input type="button" value="글쓰기" onclick="writeBoard();">
			</div>
		</div>
	</form>
 </div>
</body>
</html>