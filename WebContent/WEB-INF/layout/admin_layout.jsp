<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/admin/admin.css?ver=4">
<title>Layout</title>
</head>
<body>
	<div class="startDiv">
		<div class="topDiv">
			<tiles:insertAttribute name="adminTop"/> 
		</div>
		<div class="bodyDiv">
			
			<div class="bodySubDiv">
				<div class="bodyLeftMenu">
					<tiles:insertAttribute name="adminLeftMenu"/>
				</div>
				<div class="bodyContents">
					<tiles:insertAttribute name="body"/>
				</div>
			</div>
		</div>		
	</div>
</body>
<footer>
	<div class="footerDiv">
		<tiles:insertAttribute name="adminFooter"/>
	</div>
</footer>

</html>

