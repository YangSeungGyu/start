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
	.simpleBoardDiv{
		display: inline-block;
		width: 800px;
	}

	.simpleBoardTb {
		border-collapse: collapse;
		width: 800px;
		text-align: center;
	}
	
	.simpleBoardTb caption {
		font-weight: bold;
		color: #22741C;
	}
	
	.simpleBoardTb thead {
		background-color: #F2FFED; 
		
	}	
	.simpleBoardTb thead tr{
		border-top: 2px solid #74D36D;
		border-bottom: 2px solid #74D36D;
	}
	
	.simpleBoardTb tbody tr{
		border-bottom: 1px solid #BDBDBD;
	}
	
	.simpleBoardTb .noTh{
		width: 50px;
	}
	
	.simpleBoardTb .titleTh, .titleTd{
		width: 450px;
		vertical-align: middle;
	}
	
	.simpleBoardTb  .titleTd{
		text-align: left;
	}
	
	.simpleBoardTb .titleTd div{
		position: relative;
		display : inline-block;
		max-width : 350px;
		
		text-overflow : ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	.simpleBoardTb .titleTd span{
		color: red;
	}
	
	
	
	.simpleBoardTb .writerTh, .writerTd{
		width: 100px;
		text-overflow : ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	
	
	.simpleBoardTb .dtTh, .dtTd{
		width: 100px;
	}
	
	.simpleBoardTb .cntTh, .cntTd{
		width: 100px;
	}
	
	
	.btDiv{
		display: inline-block;
		width: 800px;
		text-align: right;
	}
	.simpleBoardTb a{
		color: black;
		text-decoration: none; 
	}
	
	.simpleBoardTb a:hover {
		text-decoration: underline; 
	}
</style>
<script>
	$(document).ready(function(){
		var firstPage = ${pageInfo.firstPage};
		var lastPage = ${pageInfo.lastPage};
		var pageSp= $("#pageSp");
		var pageTags="";
		for(var i=firstPage ;i <= lastPage; i++){
			var tagA = "<a href='/board/simpleBoard/list.do?crntPage="+i+"'>"+i+"<a/>&nbsp;";
			pageTags = pageTags + tagA;
			pageSp.html(pageTags);
		}
	});

	function writeBorad(){
		location.href="/board/simpleBoard/write.do";
	}
</script>
<title>simpleBoardList</title>
</head>
<body>
<div class="direcortyDiv">
 - 게시판 > 기본 게시판
</div>
<div class="simpleBoardPageDiv">

	<div class="simpleBoardDiv">
		<table class="simpleBoardTb">
			<caption> 기본 게시판 </caption>
			<thead>
				<tr>
					<th class="noTh" >NO</th>
					<th class="titleTh">제 목</th>
					<th class="writerTh">작성자</th>
					<th class="dtTh">작성일</th>
					<th class="cntTh">조회수</th>
				</tr>
			</thead>
			<tbody>
			 <c:if test="${not empty result}">
				<c:forEach var="list" items="${result}" varStatus="status">
					<tr>
						<td  class="noTd">
							${list.no}
						</td>
						<td  class="titleTd">
							
							 <c:if test="${list.useYn == 'Y'}">
							 	<div>
									<a href="/board/simpleBoard/read.do?no=${list.no}" >${list.title}</a>
								</div>
								<c:if test="${list.isNew == 'Y'}">
								 	<span style="vertical-align:4px;">
								 		-new-
								 	</span>
							 </c:if>	
							 </c:if>
							 <c:if test="${list.useYn == 'N'}">
									<a href="" class="del">(삭제된 게시물 입니다.)</a>
							 </c:if>
							 
						</td>
						<td class="writerTd">
							${list.userNicNm}
						</td>
						<td class="dtTd">
							${list.upDt}
						</td>
						<td class="cntTd">
							${list.viewCount}
						</td>
					</tr>
				</c:forEach>	
			</c:if>
			</tbody>
		</table>
	</div>
	<div class="pageDiv">
	 	<c:if test="${pageInfo.isPrewPage == true}">
			<a href="/board/simpleBoard/list.do?crntPage=1"><<</a>&nbsp;
			<a href="/board/simpleBoard/list.do?crntPage=${pageInfo.firstPage-1}"><</a>&nbsp;
		</c:if>
		<span id="pageSp">
		 </span>
		 <c:if test="${pageInfo.isNextPage == true}">
			 &nbsp;<a href="/board/simpleBoard/list.do?crntPage=${pageInfo.lastPage+1}">></a>
			 &nbsp;<a href="/board/simpleBoard/list.do?crntPage=${pageInfo.maxPageCnt}">>></a>
		 </c:if>
	</div>
	<div class="btDiv">
		<input type="button" value="글쓰기" onclick="writeBorad();">
	</div>
 </div>
</body>
</html>