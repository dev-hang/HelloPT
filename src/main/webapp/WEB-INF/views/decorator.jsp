<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	
	<title>
		<sitemesh:write property="title" />
	</title>
	
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	
	<!-- 부트스트랩 4.4.1 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	
	<!-- decorator에 있어야 함 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main/default.css">
	
	<script src="${pageContext.request.contextPath}/resources/js/main/script.js"></script>

	<!-- 오류 없는지 지켜보기 -->
	<%--<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/main/common.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/main/jquery-ui.min.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/main/moment.min.js"></script> --%>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/main/placeholders.min.js"></script> --%>

	<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/content.css"> --%>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/font-awesome.min.css"> --%>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/font.css"> --%>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/responsive.css"> --%>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/xeicon.min.css"> --%>

	
	

	<!-- <head></head> 태그 내용이 들어올 자리 -->
	<sitemesh:write property="head" />

</head>

<body>

	<!-- 상단 메뉴 고정 -->
	<div class="header">
		<c:import url="/WEB-INF/views/header.jsp" />
	</div>

	<!-- <body></body> 태그 내용이 들어올 자리 -->
	<div class="content">
		<sitemesh:write property="body" />
	</div>

	<div class="footer">
		<c:import url="/WEB-INF/views/footer.jsp" />
	</div>

</body>

</html>