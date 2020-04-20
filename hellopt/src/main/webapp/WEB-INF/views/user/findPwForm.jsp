<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main/login.css">
<title>HelloPT</title>
</head>
<body>
	<h2>비밀번호 찾기</h2>
	<form name='f' action="findpw" method='POST'>
		<ul>
			<li>
				<input type='text' name='username' value='' placeholder='ID' required minlength="5" maxlength="20" pattern="[0-9a-z-_]{5,20}"/>
			</li>
		</ul>
		<input name="submit" type="submit" value="비밀번호 찾기" />
	</form>
</body>
</html>