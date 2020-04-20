<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>운동정보팁 게시판~!</title>

<style>
	
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

<div id="container">
	<h1>운동정보팁 목록</h1>
	<h3>연문님 환영합니다~!!!</h3>

	<!-- 검색을 위한 폼 -->
	<form action="exerciseInfoTipList" method="post">
	<table class="border-none">
		<tr>
			<td>
				<select name="searchCondition">
				<c:forEach var="option" items="${conditionMap }">
					<option value="${option.value }">${option.key }</option>
				</c:forEach>
				</select>
				
				<input type="text" name="searchKeyword">
				<input type="submit" value="검색">
			</td>
		</tr>
	</table>
	</form>

	<!-- 데이터 표시 -->
	<form>
	<table>
		<tr>
			<th width="80">운동팁 글번호</th>
			<th width="80">운동팁 제목</th>
			<th width="80">운동팁 내용</th>
			<th width="80">운동팁 작성자</th>
			<th width="80">운동팁 사진</th>
			<th width="80">게시일자</th>
			<th width="80">조회수</th>
		</tr>
		
		<c:forEach var="exerciseInformationTip" items="${exerciseInformationTipList }">
		<tr>
			<td class="center">${exerciseInformationTip.tipIdx }</td>
			<td>
				<a href="exerciseInfoTip?tipIdx=${exerciseInformationTip.tipIdx }">
					${exerciseInformationTip.tipTitle }
				</a>
			</td>
			<td>${exerciseInformationTip.tipContent }</td>
			<td>${exerciseInformationTip.tipWriter }</td>
			<td>${exerciseInformationTip.tipPictures }</td>
			<td>${exerciseInformationTip.regdate }</td>
			<td>${exerciseInformationTip.hit }</td>
		</tr>
		</c:forEach>
	</table>
	</form>
	<p><a href="exercise/insertExerciseInformationTipform">운동정보팁글등록</a></p>
</div>


</body>
</html>