<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.pieChart.js"></script>
<style>
.logTb{
	margin-top : 10px;
	border-collapse: collapse;
	width: 800px;
}

.logTb td,th{
	border: solid 1px;
}

.logPageDiv{
	width: 800px;
	text-align : center;
}

</style>
<script>
$(document).ready(function(){
	var firstPage = ${pageInfo.firstPage};
	var lastPage = ${pageInfo.lastPage};
	var pageSp= $("#pageSp");
	var pageTags="";
	for(var i=firstPage ;i <= lastPage; i++){
		var tagA = "<a href='/admin/log.do?crntPage="+i+"'>"+i+"<a/>&nbsp;";
		pageTags = pageTags + tagA;
		pageSp.html(pageTags);
	}
});


function excelDownload(){
	
	 var form = document.createElement("form"); // 폼 생성
	 form.setAttribute("method", "POST"); // 전송방식
	 form.setAttribute("action", "/admin/logExcel.do"); // 주소
	 document.body.appendChild(form);
	
	 form.submit();
}
</script>

<title>LOG</title>
</head>
<body>
	<div>
		<table class="logTb">
		<tr>
				<th>ID</th>
				<th>닉네임</th>
				<th>이름</th>
				<th>유저타입</th>
				<th>로그내용</th>
				<th>접속IP</th>
				<th>BROWSER</th>
				<th>접근시간</th>
			</tr>
			 <c:if test="${not empty result}">
					<c:forEach var="list" items="${result}" varStatus="status">
					<tr>
						<td>${list.userId}</td>
						<td>${list.userNm}</td>
						<td>${list.userNicNm}</td>
						<td>${list.userTypeNm}</td>
						<td>${list.accessNm}</td>
						<td>${list.accessIp}</td>
						<td>${list.accessBrwsr}</td>
						<td>${list.accessDt}</td>
					</tr>
					</c:forEach>
			</c:if>
		</table>
	</div>
	<div class="logPageDiv">
	<div class="pageDiv">
	 	<c:if test="${pageInfo.isPrewPage == true}">
			<a href="/admin/log.do?crntPage=1">앞</a>&nbsp;
			<a href="/admin/log.do?crntPage=${pageInfo.firstPage-1}">이전</a>&nbsp;
		</c:if>
		<span id="pageSp">
		 </span>
		 <c:if test="${pageInfo.isNextPage == true}">
			 &nbsp;<a href="/admin/log.do?crntPage=${pageInfo.lastPage+1}">다음</a>
			 &nbsp;<a href="/admin/log.do?crntPage=${pageInfo.maxPageCnt}">끝</a>
		 </c:if>
	</div>
	</div>
	<input type="button" value="엑셀다운로드" onclick="excelDownload();">
</body>
</html>