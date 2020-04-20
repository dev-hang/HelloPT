<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/meeting/meeting.css">
	<script src="${pageContext.request.contextPath}/resources/js/meeting/meetingWrite.js"></script>
	
	<!-- 카카오 map -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=***&libraries=services,clusterer,drawing"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<!-- 데이트피커 사용하기 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/meeting/bootstrap-datepicker3.css">
	
	<!-- css파일 가져오는어 성공하면 지우기
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
	 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<!-- 한글 사용시 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.kr.min.js"></script>
	-->
	
</head>


<body>

<div class="hello_top">

	<div class="hello_body center">
	
		<h2><a class="meeting-header" href="${pageContext.request.contextPath}/meeting">Meeting</a></h2>
		
		<form  action="meetingUpdateOk" method="post">
			<div class="boarder-line">
				<h3 class="meeting-main">모임 개설 신청하기</h3>
				<hr class="meeting-line">
				<!-- 내용물 넣기 -->
					<div class="meeting-title">개 설 자
						<div class="meeting-content">
							<p class="meeting-profile"><img src="${pageContext.request.contextPath}/resources/images/meeting/profile.png"></p>
								
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal" var="user" />
								<input class="username" name="fkUserId" type="text" value="${user.username} " disabled>
							</sec:authorize>
							<div class="profile-detail">
								<textarea  cols="50" rows="5" class="meeting-comment" name="mComment" >${meetingOne.mComment }</textarea>
							</div>
							
						</div>
					</div>
					
					<div class="meeting-title">모임유형
						<div class="profile-detail">
							<select class="m-select" name="fkMCategoryNo">
							<c:forEach var="category" items="${categoryList}">
									<option value="${category.mCategoryNo }">${category.mCategory}</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="meeting-title">모임지역
						<div class="profile-detail">
							<select class="m-select" name="fkLocalNo">
							<c:forEach var="local" items="${localList}">
								<option value="${local.localNo }">${local.local }</option>
							</c:forEach>	
							</select>	
						</div>
					</div>
					
					<div class="meeting-title">최대인원
						<div class="max-count">
						    <input class="max-detail" name="maxCount" type="text" value="${meetingOne.maxCount }" /> 명
						</div>
					</div>

					<div class="meeting-title">모임일자
						<div class="meeting-content">
							<input class="form-control" type="text" id="datePicker" name="mDate" value="${meetingOne.mDate }" >
						</div>
					</div>
					
					<div class="meeting-title">모임제목
						<div class="meeting-content">
							<input class="meeting-sub" name="mSubject" type="text" value="${meetingOne.mSubject }" >						
						</div>
					</div>
					
					<div class="meeting-title">한줄소개
						<div class="meeting-content">
							<input class="meeting-sub" name="mMemo" type="text" value="${meetingOne.mMemo}">						
						</div>
					</div>
					
					<div class="meeting-title">상세정보
						<div class="meeting-content2">
							<textarea  cols="90" rows="10" class="meeting-textarea" name="details" >${meetingOne.details }</textarea>
						</div>
					</div>
					
					<div class="meeting-title">포함사항
						<div class="meeting-content2">
							<textarea  cols="90" rows="5" class="meeting-include" name="include" >${meetingOne.include }</textarea>					
						</div>
					</div>
					
					<div class="meeting-title">모임위치
						<div class="meeting-content">
						
							<!-- 지도 api 부분 입니다  시작-->
							<input type="button" class="address-btn" onclick="getAddress()" value="우편번호 찾기"> 
												
							<div id="map" style="width:300px;height:300px;"></div>
							<div>
								<input type="hidden" id="search_name" disabled />
							</div>
							<!-- 지도 api 부분 입니다 끝 -->
							
							<div class="meeting-content2">
								<input type="hidden" class="meeting-mcomment" id="mLocation" name="mLocation" value="${meetingOne.mLocation }">
								<input class="meeting-mcomment" name="mLocationC" value="${meetingOne.mLocationC }">
							</div>
						</div>
					</div>
					
					<div class="meeting-title">회　　비
						<div class="meeting-content">
							<input class="meeting-sub" name="mPrice" type="text" value="${meetingOne.mPrice }">						
						</div>
					</div>
					
					<input name="meetingIdx" type="hidden" value="${meetingOne.meetingIdx }">
					
					<div class="meeting-title">파　　일
					<input type="file" class="file-btn" name="uploadFile" multiple="multiple">
					<c:forEach var="file" items="${meetingOneFile}">
						<p>${file.mOriImg}</p>
					</c:forEach>
					</div>
				
				
				
					
				<!-- 내용물 넣기 -->				
				<hr class="meeting-line">
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="user" />
					<c:if test="${user.username eq meetingOne.fkUserId }">
						 <input type="submit" name="" value="수정하기" class="send-btn2">
					</c:if>
				</sec:authorize>
				
			</div>	
	    </form>
			
	</div>
</div>
	
	<!-- js에서 태그들을 찾고 있어서 여기다가 위치 시켜야 함 -->
	<script src="${pageContext.request.contextPath}/resources/js/meeting/meetingUpdate.js"></script>
	
</body>