<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	

<head>
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/resources/css/calender/calendarUpdate.css">
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	

</head>


<body class="calBody">

	<div class="hello_body">

		<form action="calendarUpdateOk" method="post" id="formId" name="calInsert"  enctype="multipart/form-data" >
			<div class="calHd">
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="user" />
				<div class="calName">작성자</div> <input type="text" class="calUser" id="" name="" value="${user.username}" readonly>
				</sec:authorize>
			</div>

			<div class="calHd2">
				<div class="calName">동영상</div>
				<input type="file" class="fileUpload" id="uploadFile" name="uploadFile" >
				
				<video class="fileUpload" id="video" controls height="280"  src="${pageContext.request.contextPath}/s3/calendar/${oneCalendar.cSysVideo}" width="505" controls="controls"></video>
				<p class="fileName">첨부파일 : ${oneCalendar.cSysVideo}</p>
			</div>

			<div class="calHd3">
				<div class="calName">내용</div>
				<textarea cols="66" rows="10" class="calContent" id="mComment" name="content" >${oneCalendar.content}</textarea>
			</div>
			
			<input type="hidden" name="calendarIdx" value="${oneCalendar.calendarIdx}">
			<input type="button" id="calInsert" class="calInsert "value="수정">
		</form>

	</div>

	<script	src="${pageContext.request.contextPath}/resources/js/calender/calenderUpdate.js"></script>
</body>