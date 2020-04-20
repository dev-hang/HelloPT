<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>운동정보팁 상세보기~!</title>
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

<div id="container">
	<h1>운동정보팁 상세</h1>
	<hr>
	<form action="updateExerciseInformationTip" method="post">
		<input type="hidden" name="tipIdx" value="${exerciseInformationTip.tipIdx }">
	<table>
		<thead>
		<!-- 자바스크립트 99번 참조 -->
			<tr>
				<th>운동팁 글번호</th>
				<th>운동팁 제목</th>
				<th>운동팁 내용</th>
				<th>운동팁 작성자</th>
				<th>운동팁 사진</th>
				<th>게시일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>	
				<td>
					<input type="text" name="tipTitle"
						value="${exerciseInformationTip.tipTitle }">			
				</td>
				<td>
					<input type="text" name="tipContent"
						value="${exerciseInformationTip.tipContent }">
				</td>
				<td>
					<input type="text" name="tipWriter"
						value="${exerciseInformationTip.tipWriter }">
				</td>
				<td>
					<img src="resources/images/서정.jpg">
					<input type="file" name="tipPicture"
						value="${exerciseInformationTip.tipPicture }">
				</td>
				<td>
					<input type="date" name="regdate"
						value="${exerciseInformationTip.regdate }">
				</td>
				<td>
					<input type="text" name="hit"
						value="${exerciseInformationTip.hit }">
				</td>
				</tr>
		</tbody>
		<tfoot>
		
		</tfoot>
	</table>
	</form>
	<p>
		<a href="exercise/insertExerciseInformationTip">글등록</a>
		<a href="exercise/deleteExerciseInformationTip?tipIdx=${exerciseInformation.getTipIdx() }">글삭제</a>
		<a href="exercise/getExerciseInformationTipList">글목록</a>
	</p>
</div>

</body>
</html>