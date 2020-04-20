<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 상세정보</title>
<meta charset="utf-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/classdetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/content.css">
</head>
<body>
	<!-- 콘텐츠 시작 { -->
	<div id="wrapper">
		<div id="live">
			<div class="con-wr">
				<div class="con-inner sub-sub ani-adj">
					<div class="tit-wr tit-wr-ani">
						<h2>강의 상세정보</h2>
					</div>
					<!-- //tit-wr -->
					<div class="sub-content">
						<div class="content-area clearfix">
							<!-- 썸네일 -->
							<div class="live_img_area">
								<!-- <iframe width="560" height="315"
									src="https://www.youtube.com/embed/azxahQi6vFo" frameborder="0"
									allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
									allowfullscreen></iframe> -->
								<img src="${pageContext.request.contextPath}/resources/images/class/classimg.jpg">
							</div>
							<!--//썸네일-->
						</div>
						<!-- //sub-content -->
						<div class="basic-table">
							<table>
								<colgroup>
									<col width="22.5%;">
									<col width="*">
								</colgroup>

								<tr>
									<th>수업 이름</th>
									<td>${classDetail.className }</td>
								</tr>
								<tr>
									<th>수업 유형</th>
									<td>${classDetail.classType }</td>
								</tr>
								<tr>
									<th>수업 기간</th>
									<td>${classDetail.classLength }주</td>
								</tr>
								<tr>
									<th>수업 시작일</th>
									<td>
										<fmt:formatDate value="${classDetail.classStartDate }" pattern="yy/MM/dd"/>
									</td>
								</tr>
								<tr>
									<th>수업 요일</th>
									<td>${classDetail.classDay }</td>
								</tr>
								<tr>
									<th>수업 시간</th>
									<td>${classDetail.classTime }</td>
								</tr>
								<tr>
									<th>수업 인원</th>
									<td>${classDetail.totalMembers }명</td>
								</tr>
								<tr>
									<th>가격</th>
									<td>${classDetail.price }원</td>
								</tr>
							</table>
						</div>
						<!-- //basic-table -->
						<div class="basic-caption">
							<p>※ 강의 신청 인원이 초과된 강의는 신청할 수 없습니다.</p>
							<p>※ 이미 신청이 완료된 강의는 다시 신청할 수 없으며, 신청 취소만 가능합니다.</p>
							<p>※ 강의 신청과 관련된 문의사항은 hellopt@email.com으로 연락 바랍니다.</p>
						</div>
						<!-- //basic-caption -->
						
						<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="user" />
							<c:if test="${result eq 0 and user.username ne classDetail.fkUserId}">
								<button style="width: 100%" class="detail-btn" id="reg-class">강의 신청</button>
							</c:if>
							<c:if test="${result eq 1 and user.username ne classDetail.fkUserId}">
								<button style="width: 100%" class="detail-btn" id="drop-class">강의 신청 취소</button>
							</c:if>
							<c:if test="${user.username eq classDetail.fkUserId}">
								<button style="float: right" class="detail-btn" id="del-class">강의 삭제</button>
								<button class="detail-btn"
									onclick="location.href='updateclass?classIdx=${classDetail.classIdx }'">강의 수정</button>
							</c:if>
						</sec:authorize>						
						<%-- <button class="detail-btn"
							onclick="location.href='myclass?fkUserId=${ }'">강의 신청 내역 보기</button> --%>
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
	var regClass = document.getElementById("reg-class");

	if (${cnt} >= ${total}) {
		alert("강의 신청 인원이 초과된 강의입니다!");
		regClass.style.backgroundColor = "gray";
		regClass.disabled = true;
	} 
	
	$("#reg-class").click(function() {
		var regOk = confirm("${classDetail.className} 강의를 신청하시겠습니까?");
		if (regOk) {
			location.href='regclass?fkClassIdx=' + ${classDetail.classIdx };
			alert("강의 신청이 완료되었습니다.");
		} else {
			alert("강의 신청이 취소되었습니다.");	
			history.go(1);
		}
	});
	$("#del-class").click(function() {
		var delOk = confirm("${classDetail.className} 강의를 정말 삭제하시겠습니까?");
		if (delOk) {
			location.href='deleteclass?classIdx=' + ${classDetail.classIdx };
			alert("삭제가 완료되었습니다.");
		} else {
			alert("삭제가 취소되었습니다.");	
			history.go(1);
		}
	});
	$("#update-class").click(function() {
		var updateOk = confirm("강의를 수정하시겠습니까?");
		if (updateOk) {
			alert("수정이 완료되었습니다.");
			location.href='updateclassok?classIdx=' + ${classDetail.classIdx };
		} else {
			alert("수정이 취소되었습니다.");	
			history.go(1);
		}
	});
	$("#drop-class").click(function() {
		var dropOk = confirm("${classDetail.className} 강의 신청을 취소하시겠습니까?");
		if (dropOk) {
			location.href='dropclass?fkClassIdx=' + ${classDetail.classIdx };
			alert("강의 신청 취소가 완료되었습니다.");
		} else {
			alert("강의 신청 취소가 취소되었습니다.");	
			history.go(1);
		}
	});
</script>	
</body>
</html>