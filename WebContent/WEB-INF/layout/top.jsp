<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
function openTopMenuArea(){
	var menuDiv=$("#topMenuArea")
	menuDiv.clearQueue().animate({marginTop:-29},'fast').parent().clearQueue().animate({height:329},'fast')
}


function closeTopMenuArea(){
	var menuDiv=$("#topMenuArea")
	menuDiv.clearQueue().animate({marginTop:-227},'fast').parent().clearQueue().animate({height:0},'fast');
}
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
		 | 문의하기
		 <c:if test="${sessionScope.userType != 'U' && not empty sessionScope.userType }">
		 | <span><a href="/admin/main.do">관리자</a></span>
		</c:if>
	</div>
</div>

<div class="topImg" >
	<img src="/resources/img/top_image-1.jpg" alt ="topImg">
</div>	
<div class="topMenu">
	<div class="topSubMenu">
		<ul>
			<li>
				<p>
					<a href="/start/home.do" onmouseover="openTopMenuArea();">HOME</a>
				</p>
			</li>
			<li>
				<p>
					<a href="/board/simpleBoard/list.do" onmouseover="openTopMenuArea();">게시판</a>
				</p>
			</li>
			<li>
				<p>
					<a href="#" onmouseover="openTopMenuArea();">UCC</a>
				</p>
			</li>
			<li>
				<p>
					<a href="#" onmouseover="openTopMenuArea();">테스트4</a>
				</p>	
			</li>
			<li>
				<p>
					<a href="#" onmouseover="openTopMenuArea();">테스트5</a>
				</p>
			</li>
		</ul>
		
		<div class="topMenuAreaHiddenDiv">
			<div class="topMenuAreaDiv" id="topMenuArea" onmouseout="closeTopMenuArea();" >

				 <ul onmouseover="openTopMenuArea();" style="margin-left : -50px;">
					<li>
						<a href="/start/home.do" >home</a>
					</li>
				</ul>
				
				<ul onmouseover="openTopMenuArea();">
					<li>
						<a href="/board/simpleBoard/list.do" >기본 게시판</a>
					</li>
					<li>
						<a href="/board/smartBoard/smartList.do?PageCategory=700001" >[SMT]자유게시판</a>
					</li>
					<li>
						<a href="/board/smartBoard/smartList.do?PageCategory=700002" >[SMT]Q&A</a>
					</li>
				</ul>
				
				<ul onmouseover="openTopMenuArea();">
					<li>
						<a href="#" >이미지 게시판</a>
					</li>
					<li>
						<a href="/ucc/linkVideo/list.do" >링크 동영상</a>
					</li>
					<li>
						<a href="/ucc/video/test.do" >동영상</a>
					</li>
				</ul>
				
				<ul onmouseover="openTopMenuArea();">
					<li>
						<a href="#" >test4</a>
					</li>
					<li>
						<a href="#" >test4</a>
					</li>
					<li>
						<a href="#" >test4</a>
					</li>
				</ul>
				
				<ul onmouseover="openTopMenuArea();">
					<li>
						<a href="#" >test4</a>
					</li>
					<li>
						<a href="#" >test4</a>
					</li>
					<li>
						<a href="#" >test4</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<br/>
</div>
