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
		action="${pageContext.request.contextPath}/admin/user/update"
		method="POST" modelAttribute="user" enctype="multipart/form-data">
		<ul>
			<c:if test="${ not empty user.userProfile }">
				<li><img src="${pageContext.request.contextPath}/s3/profile/${ user.userProfile }" alt="profile"></li>
			</c:if>
			
			<li><form:label path="userId">아이디</form:label> <form:input
					path="userId" readonly="true" /> <label class="error">아이디는
					변경 불가능합니다.</label></li>
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
			<li><form:label path="userRole">회원 등급</form:label> <select
				name="userRole">
					<c:choose>
						<c:when test="${user.userRole eq 'ROLE_ADMIN'}">
							<option value="ROLE_ADMIN" selected="selected">관리자</option>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${user.userRole eq 'ROLE_USER' }">
									<option value="ROLE_USER" selected="selected">일반 회원</option>
								</c:when>
								<c:otherwise>
									<option value="ROLE_USER">일반 회원</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${user.userRole eq 'ROLE_TRAINER' }">
									<option value="ROLE_TRAINER" selected="selected">트레이너</option>
								</c:when>
								<c:otherwise>
									<option value="ROLE_TRAINER">트레이너</option>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
			</select></li>
			<li>
				<form:label path="userEnable">회원 상태</form:label>
				<c:choose>
					<c:when test="${user.userEnable == 1 }">
					<span>정상</span>
					</c:when>
					<c:otherwise>
					<span>회원 정지</span>
					</c:otherwise>
				</c:choose>
			</li>
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
			<li><label for="file">프로필 사진</label> 
				<input type="file" name="file" onchange="loadPreviewImg(this, 'preview-profile')" />
				<img src="" id="preview-profile" alt=""></li>

		</ul>
		<input type="submit" value="회원 정보 수정">
		<input type="button" onclick="deleteUser()" value="회원 삭제">
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
			let result = confirm("정말로 유저를 삭제하시겠습니까?");
			if (result) {
				location
						.replace("${pageContext.request.contextPath}/admin/user/delete?userId=${user.userId}");
			}
		}
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/user/idcheck.js"></script>
</body>
</html>