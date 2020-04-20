<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>운동정보팁등록</title>
<style>
	#container {
		width: 700px;
		margin: 0 auto;
	}
	h1, h3, p { text-align: center; }
	table { border-collapse: collapse; }
	table, th, td {
		border: 1px solid black;
		margin: 0 auto;
	}
	th { background-color: orange; }
	.center { text-align: center; }
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<c:if test="${not empty error }">
<h4 class=error>An error occurred :${error }</h4>
<br>
</c:if>

<div id="container">
	<h1>운동정보팁등록</h1>

	<hr>
	<form:form method="post" action="${pageContext.request.contextPath}/insertExerciseInformationTip"
			enctype="multipart/form-data">
	<table>
		<tr>
			<th>팁 제목</th>
			<td>
				<input type="text" name="tipTitle">
			</td>
		</tr>
		<tr>
			<th>팁 내용</th>
			<td>
				<input type="text" name="tipContent">
			</td>
		</tr>
		<tr>
			<th>팁 작성자</th>
			<td>
				<input type="text" name="tipWriter">
			</td>
		</tr>
		<tr>
			<th>팁 사진</th>
			<td>
				<input type="file" name="tipPictures">
				<!-- name="file" -->
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>작성일자</th> -->
<!-- 			<td> -->
<!-- 				<input type="text" name="regdate"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td colspan="2" class="center">
				<input type="submit" value="운동정보 등록">
			</td>			
		</tr>
	</table>		
	</form:form>
	<p><a href="exerciseInfoList">운동 정보 목록 가기</a></p>
</div>




</body>
</html>