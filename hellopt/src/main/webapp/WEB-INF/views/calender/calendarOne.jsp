<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/calender/calenderOne.css">
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>


<body class="calBody">

	<div class="hello_body">

		<form action="" method="post">
			<div class="calHd">
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="user" />
				<div class="calName">작성자</div> <input type="text" class="calUser" id="" name="" value="${oneCalendar.fkUserId}" readonly>
				</sec:authorize>
			
			<!-- 이건 현재 날짜 뽑는거 -->	
			<c:set var="now" value="<%=new java.util.Date()%>" />	
			<!--  현재날짜를 00-00-00 형식으로 만들어주고 변수명 지어주기 -->
			<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyyMMdd" /></c:set> 
			${today}/${oneCalendar.yymmdd}
			<c:if test="${today eq oneCalendar.yymmdd}">
			<div class="icon">
				<a href="${pageContext.request.contextPath}/calendarUpdate?calendarIdx=${oneCalendar.calendarIdx }"><i class='fas fa-pen' style='font-size:18px;color:white'></i></a>
				<a class="delCal" href="#" onclick="delCal()" ><i class='far fa-trash-alt edit' style='font-size:18px;color:white'></i></a>
			</div>	
			</c:if>
			</div>

			<div class="calHd2">
				<div class="calName">동영상</div>
				<video class="fileUpload" id="video" controls height="280"  src="${pageContext.request.contextPath}/s3/calendar/${oneCalendar.cSysVideo}" width="525" controls="controls"></video>
			</div>

			<div class="calHd3">
				<div class="calName">내용</div>
				<textarea cols="67" rows="10" class="calContent" id="mComment" name="mComment"	readonly>${oneCalendar.content}</textarea>
			</div>
			<input type="hidden" id="delIdx" value="${oneCalendar.calendarIdx}">
			<input type="button" id="calInsert" class="calInsert" value="닫기" onclick="window.close()">
		</form>



	</div>

	<script	src="${pageContext.request.contextPath}/resources/js/calender/calenderOne.js"></script>
</body>