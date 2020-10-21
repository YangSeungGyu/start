<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(document).ready(function(){
    $(".leftMenuLi>a").click(function(){
        var submenu = $(this).next("ul");

        if( submenu.is(":visible") ){
            submenu.slideUp();
        }else{
            submenu.slideDown();
        }
    });
});
</script>
<div class="leftMenuTitle">
	<a href="/admin/main.do">M E N U</a>
</div>
<div>
    <ul class="leftMenuUl">
        <li class="leftMenuLi">
            <a href="javasccript:;">> 홈페이지 관리</a>
            <ul class="hide">
            	<li><a href="javasccript:;">공지사항</a></li>
                <li><a href="javasccript:;">공통 코드 관리</a></li>                
                <li><a href="/admin/log.do">로그 관리</a></li>

            </ul>
        </li>
 
        <li class="leftMenuLi">
            <a  href="javasccript:;">> 회원관리</a>
            <ul class="hide">
                <li><a href="javasccript:;">운영진 관리</a></li>
                <li><a href="javasccript:;">일반 회원 관리</a></li>
            </ul>
        </li>
        <li class="leftMenuLi">
            <a  href="javasccript:;">> 게시물 관리</a>
            <ul class="hide">
                <li><a href="javasccript:;">일반 게시판</a></li>
                <li><a href="javasccript:;">스마트 게시판</a></li>
                <li><a href="javasccript:;">이미지 게시판</a></li>
            </ul>
        </li>
        <li class="leftMenuLi">
            <a  href="javasccript:;">> 통계</a>
            <ul class="hide">
                <li><a href="javasccript:;">회원 통계</a></li>
                <li><a href="javasccript:;">게시물 통계</a></li>
            </ul>
        </li>
    </ul>
</div>



