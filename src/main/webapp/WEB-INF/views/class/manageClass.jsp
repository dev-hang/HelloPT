<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 관리</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/content.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/manage.css">
</head>
<body>
	<!-- 콘텐츠 시작 { -->
	<div id="wrapper">
		<div id="live">
			<div class="con-wr">
				<div class="con-inner sub-sub ani-adj">
					<div class="tit-wr tit-wr-ani">
						<h2>강의 정보 관리</h2>
					</div>
					<br><br>
					<div class="sub-content">
						<div class="basic-table">
							<table>
								<colgroup>
									<col width="10%;">
									<col width="*">
								</colgroup>
									
								<tr>
									<th>번호</th>
									<th>수업 이름</th>
									<th>트레이너 아이디</th>
									<th>수업 유형</th>
									<th>관리</th>
								</tr>
								<c:forEach var="classList" items="${liveClassList }" varStatus="idx">
									<tr>
										<td>${classList.classIdx }</td>
										<td id="class-name"><a href="classdetail?classIdx=${classList.classIdx }">${classList.className }</a></td>
										<td>${classList.fkUserId }</td>
										<td>${classList.classType }</td>
										<td>
											<button style="float: right" class="manage-btn" id="del-class" onclick="del_class(${classList.classIdx })">강의 삭제</button>
											<button class="manage-btn"
												onclick="location.href='updateclass?classIdx=${classList.classIdx }'">강의 수정</button>
										</td>
									</tr>
								</c:forEach>	
							</table>
						</div>
						<!-- //basic-table -->
					</div>
					<!--sub-content-->
					<!--top버튼-->
					<a href="#" class="top-btn"> <i class="xi-angle-up-thin"></i>
						<div>top</div>
					</a>
				</div>
				<!--con-inner-->
			</div>
			<!--con-wr-->
		</div>
		<!-- live -->
	</div>
	<!-- } 하단 끝 -->
	<script>
	function del_class(classIdx) {
		var delOk = confirm("정말 삭제하시겠습니까?");
		if (delOk) {
			location.href='deleteclass?classIdx=' + classIdx;
			alert("삭제가 완료되었습니다.");
		} else {
			alert("삭제가 취소되었습니다.");	
			history.go(1);
		}
	};
	</script>
</body>
</html>