<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>		
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event</title>

 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/calender/calendarPop.css">
	 <script src="//cdn.jsdelivr.net/npm/js-cookie@2.2.0/src/js.cookie.min.js"></script>
</head>

<body>

<form name=”form1″>
<div>
		<img src="${pageContext.request.contextPath}/resources/images/calendar/pop2.jpg" style="width: 617px; height: 716px;" />
		<input class="popinput" type="button" onclick="pageChange()" value="이벤트 페이지로 이동하기"/>

		<div class="popup_bottom">
			<span class="close"><input type="checkbox" name="todayPopup" OnClick="popup_closeToday()" />오늘은 이창을 다시 열지않음</span>
			<span class="close"><input type="checkbox" name="popup" OnClick="popup_close()" />닫기</span>
		</div>
		
</div>		
</form>
		
	   <script src="${pageContext.request.contextPath}/resources/js/calender/cookie.js"></script>
</body>
