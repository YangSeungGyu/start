<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.pieChart.js"></script>
<style>
.homePageInfo {
	margin-top : 10px;
	border: solid 1px;
	border-collapse:collapse;
}

.homePageInfo caption{
	font-weight: bold;
}

.homePageInfo caption,th,td{
	border: solid 1px;
	border-collapse:collapse;
}

.homePageInfo tr td span{
	font-weight: bold;
}

.titleTr {
	background-color : black;
	color : white;
}
</style>
<script>
$(document).ready(function(){
	var simpleTotalCnt = ${result.simpleTotalCnt};
	var smartCate1Cnt = ${result.smartCate1Cnt};
	var smartCate2Cnt = ${result.smartCate2Cnt};
/* 	var catntuna = [
	    ["기본 게시판",100],
	    ["[smart]자유 게시판", 550],
	    ["[smart]Q&A 게시판", 200]
	   ]; */
	var catntuna = [
	    ["기본 게시판",simpleTotalCnt], 
	    ["[smart]자유 게시판", smartCate1Cnt], 
	    ["[smart]Q&A 게시판", smartCate2Cnt] 
	   ];
	   
	$('#piegraph').pieChart( catntuna, 200, "pie");
	
});

</script>

<title>Admin</title>
</head>
<body>

<table  class="homePageInfo">
	<caption> 홈페이지 정보</caption>
	<tr class="titleTr"><th>접속 정보</th><th>회원 정보</th><th style="width: 250px;">게시판별 이용 현황</th></tr>
	<tr>
		<td>
			<span>총 접속 수 :</span> ${result.totalLoginCnt}<br/>
			<span>오늘 접속 수 :</span> ${result.todayLoginCnt}<br/>
		</td>
		<td>
			<span>총 회원 :</span> ${result.totalUserCnt}<br/>
			<span>관리자 :</span> ${result.mUserCnt}<br/>
			<span>운영진 :</span> ${result.sUserCnt}<br/>
			<span>일반 회원 :</span> ${result.uUserCnt}<br/>
		</td>
		<td rowspan="3" style="padding-left: 25px;">
			<div id = "piegraph"></div>
		</td>
	</tr>
	<tr class="titleTr"> 
		<th>기본 게시판 정보</th>
		<th>스마트 게시판 정보</th>
	</tr>		
	<tr>
		<td>
			<span>총 게시물 수 :</span> ${result.simpleTotalCnt}<br/>
			<span>오늘 게시물 수 </span>: ${result.simpleTodayCnt}<br/>
			<span>이번달 게시물 수 :</span> ${result.simpleMCnt}<br/>
		</td>
		<td>
			<span>총 게시물 수 :</span> ${result.smartTotalCnt}<br/>
			<span>오늘 게시물 수 :</span> ${result.smartTodayCnt}<br/>
			<span>이번달 게시물 수 :</span> ${result.smartMCnt}<br/>
			<span>총 게시물 수 :</span> ${result.commentTotalCnt}<br/>
			<span>오늘 게시물 수 :</span> ${result.commentTodayCnt}<br/>
			<span>이번달 게시물 수 :</span> ${result.commentMCnt}<br/>
		</td>
	</tr>
</table>


</body>
</html>