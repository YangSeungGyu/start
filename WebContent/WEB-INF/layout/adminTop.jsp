<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>

</script>
<div class="topLineDiv">
	<div class="topLineSubDiv">
		<c:if test="${empty sessionScope.userId}">
			<a href="/common/joinContract.do">회원가입</a> | 
			<a href="/common/login.do">login</a>
		</c:if>
		<c:if test="${not empty sessionScope.userId}">
			<span>${sessionScope.userNicNm}(<a href="#">${sessionScope.userId}</a>)님께서 로그인 하셧습니다.</span>
			 | <span><a href="/common/logoutAction.do">logout</a></span>
		</c:if>
		 <c:if test="${sessionScope.userType != 'U' && not empty sessionScope.userType }">
		 | <span><a href="/start/home.do">Home</a></span>
		</c:if>
	</div>
</div>

<div class="topImg" >
	<img src="/resources/img/admin_top_image-1.jpg" alt ="topImg">
</div>	
<div class="topMenu"></div>
