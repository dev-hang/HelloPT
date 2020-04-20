<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>		

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/calender/calender.css">

</head>


<body>
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<input type="hidden" id="userName" value="${user.username} ">	
		</sec:authorize>
	

	<div class="hello_body">
	
	    <div class="center">
	    <!-- 
	     <img id="cal" class="calPop" src="${pageContext.request.contextPath}/resources/images/calendar/이벤트pop2.jpg">
	     -->
	    <div id="cal"></div>
	    <!-- 달력이 생성될 위치 -->
	    <h2>매일매일 홈트레이닝!</h2>
	    <p>한달동안 한번도 안빠지고 운동하면 당첨확률 up! up!</p>
		
		<div id="eventCalendar"></div>
	    </div>
	
	</div>
	

		
	    <script>
	    var userName = '${user.username}';
	    console.log("ㅜㅜ:" + userName);
	    </script>
	    
		<script src="${pageContext.request.contextPath}/resources/js/calender/moment.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/calender/calender.js"></script>
</body>
