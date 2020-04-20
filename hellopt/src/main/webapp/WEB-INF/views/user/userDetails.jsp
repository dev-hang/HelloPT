<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main/signup.css">
<title>유저 관리</title>
</head>
<body>
<div class="container">
<div class="row">
			<div class="col-xs-3 col-md-2"></div>
			<div class="col-xs-12 col-md-8">
	<h2>회원 정보 관리</h2>
	<form:form
		action="${pageContext.request.contextPath}/auth/update"
		method="POST" modelAttribute="user" enctype="multipart/form-data">
		<ul>
			<c:if test="${ not empty user.userProfile }">
				<li><img
					src="${pageContext.request.contextPath}/s3/profile/${ user.userProfile }"
					alt="profile"></li>
			</c:if>

			<li><form:label path="userId">아이디</form:label> <form:input
					path="userId" readonly="true" /> <label class="error">아이디는
					변경 불가능합니다.</label></li>
			<li><form:label path="userPw">비밀번호</form:label> <form:input
					path="userPw" type="password" placeholder="PASSWORD" /> <label
				id="pw-info"></label> <input type="password" id="userPwConfirm"
				placeholder="PASSWORD CONFIRM"> <label id="pw-cfm-info"></label>
				<form:errors path="userPw" cssClass="error"></form:errors></li>
			<li><form:label path="userName">이름</form:label> <form:input
					path="userName" placeholder="NAME" /> <form:errors path="userName"
					cssClass="error"></form:errors></li>
			<li>
				<form:label path="userEmail">이메일</form:label>
				<form:input path="userEmail" placeholder="Email" required="" type="email" />
				<form:errors path="userEmail" cssClass="error"></form:errors>
			</li>
			<li><form:label path="userGender">성별</form:label> <br /> <form:radiobutton
					path="userGender" value="M" /><span>남자</span><br /> <form:radiobutton
					path="userGender" value="F" /><span>여자</span><br /></li>
			<li><form:label path="userAddress">주소</form:label> <input
				type="button" onclick="getAddress()" value="우편번호 찾기"> <form:input
					path="userAddress" id="roadAddr" placeholder="ADDRESS" /></li>
			<li><form:label path="userBirth">생년월일</form:label> <form:input
					type="date" path="userBirth" value="${ user.userBirth }" /></li>
			<li><form:label path="userJob">직업</form:label> <form:input
					path="userJob" placeholder="JOB" /></li>
			<li><form:label path="userRoot">알게된 경로</form:label> <select
				name="userRoot">
					<c:choose>
						<c:when test="${user.userRoot eq 'friend' }">
							<option value="friend" selected="selected">친구를 통해서</option>
						</c:when>
						<c:otherwise>
							<option value="friend">친구를 통해서</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${user.userRoot eq 'advertise' }">
							<option value="advertise" selected="selected">광고를 보고</option>
						</c:when>
						<c:otherwise>
							<option value="advertise">광고를 보고</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${user.userRoot eq 'search' }">
							<option value="search" selected="selected">검색으로</option>
						</c:when>
						<c:otherwise>
							<option value="search">검색으로</option>
						</c:otherwise>
					</c:choose>
			</select></li>
			<li><form:label path="userHeight">키</form:label> <form:input
					path="userHeight" type="number" min="0" max="300" /></li>
			<li><form:label path="userWeight">몸무게</form:label> <form:input
					path="userWeight" type="number" min="0" max="300" /></li>
			<li><label for="file">프로필 사진</label> <input type="file"
				name="file" onchange="loadPreviewImg(this, 'preview-profile')" /> <img
				src="" id="preview-profile" alt=""></li>

		</ul>
		<input type="submit" value="회원 정보 수정">
		<input type="button" onclick="deleteUser()" value="회원 탈퇴">
	</form:form>
	</div>
			<div class="col-xs-3 col-md-2"></div>
		</div>
	</div>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function getAddress() {
			new daum.Postcode({
				oncomplete : function(data) {
					let roadAddr = data.roadAddress;
					document.getElementById('roadAddr').value = roadAddr;
				}
			}).open();
		}
	</script>
	<script>
		function deleteUser() {
			let result = confirm("정말로 탈퇴하시겠습니까?");
			if (result) {
				location
						.replace("${pageContext.request.contextPath}/auth/delete?userId=${user.userId}");
			}
		}
	</script>
	<script
		src="${pageContext.request.contextPath}/resources/js/user/idcheck.js"></script>
</body>
</html>