<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/common/tag.css" />
<title>tag</title>
</head>
<body>
 <div class="tagdiv" >
  	<script src="/resources/js/comp/tag.js"></script>
    <script>
  		$(document).ready(function(){
  			init_tag();
  		});
 	</script>
 		<div id="layer1"> 
    	<div id="tab1" class="tab">포털사이트</div> 
    	<div class="content">
			네이버(http://www.naver.com) : 엔에이치엔 포털서비스<br/>
			야후(http://kr.yahoo.com) : 야후코리아 포털 서비스<br/>
			다음(http://www.daum.net) : 다음커뮤니케이션 포털서비스
		</div> 
	</div> 
	<div id="layer2"> 
	    <div id="tab2" class="tab">신문사이트</div> 
	    <div class="content">
			조선일보(http://www.chosun.com) : 디지털 조선일보<br/>
			동아일보(http://www.donga.com) : 동아일보<br/>
			조인스(http://www.joins.com) : 중앙일보 인터넷 신문	
		</div> 
	</div> 
	<div id="layer3"> 
	    <div id="tab3" class="tab">방송국사이트</div> 
	    <div class="content">
			KBS(http://www.kbs.co.kr) : 한국방송공사<br/>
			MBC(http://www.imbc.com) : 문화방송<br/>
			SBS(http://www.sbs.co.kr) : SBS
		</div> 
	</div>    
 </div>
</body>
</html>