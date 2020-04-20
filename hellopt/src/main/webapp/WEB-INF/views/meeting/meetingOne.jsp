<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/meeting/meeting.css">
	
	<!-- 슬라이드 관련 -->
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
	<!-- Link Swiper's CSS -->
	<link rel="stylesheet" href="https://unpkg.com/swiper/css/swiper.css">
	<script src="https://unpkg.com/swiper/js/swiper.js"></script>
	
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
			<div class="boarder-oline">
			
				
				<p class="meeting-detailsb">${meetingOne.mSubject } </p>
				
				<!-- 여기는 작성자만 보인다 -->
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="user" />
					<c:if test="${user.username eq meetingOne.fkUserId }">
						<div class="mUD">
						<input type="button" value="수정" class="send-btn3 send-u">
						<input type="button" value="삭제" class="send-btn3 send-d">
						</div>
					</c:if>
				</sec:authorize>
				<hr class="meeting-line">
				<!-- 내용물 넣기 -->
				
			<div class="meeting-one">
				
				<!-- 깔끔한 슬라이드 보류 
				<div class="slidebody">	
					<div class="swiper-container">
					    <div class="swiper-wrapper">
					      <div class="swiper-slide">
					        <div class="swiper-zoom-container">
					          <img src="${pageContext.request.contextPath}/resources/images/meeting/nature-1.jpg">
					        </div>
					      </div>
					      <div class="swiper-slide">
					        <div class="swiper-zoom-container">
					          <img src="${pageContext.request.contextPath}/resources/images/meeting/nature-2.jpg">
					        </div>
					      </div>
					      <div class="swiper-slide">
					        <div class="swiper-zoom-container">
					          <img src="${pageContext.request.contextPath}/resources/images/meeting/nature-3.jpg">
					        </div>
					      </div>
					    </div>

					    <div class="swiper-pagination swiper-pagination-white"></div>
					    
					    <div class="swiper-button-prev"></div>
					  <div class="swiper-button-next"></div>
				    </div>
				</div> 
				 -->
				 
				  <!-- Swiper -->
				  <div class="swiper-container">
				    <div class="swiper-wrapper">
				    
				    <c:forEach var="file" items="${meetingOne.meetingFileVO}">
				    <!-- 
				      <div class="swiper-slide" style="background-image:url(${pageContext.request.contextPath}/downloadFile?mSysImg=${file.mSysImg} )"></div>
				     -->
				      <div class="swiper-slide" style="background-image:url(${pageContext.request.contextPath}/s3/meeting/${file.mSysImg} )"></div>
					</c:forEach>				   
				    </div>
				    
				    <!-- Add Pagination -->
				    <div class="swiper-pagination"></div>
				  </div>
				  
					<p class="meeting-detail"> 한줄메모 </p>
					<p class="one-border">
					${meetingOne.mMemo } 
					</p>
					
					<p class="meeting-detail"> 상세정보 </p>
					<textarea  cols="90" rows="10" class="onemeeting-textarea" readonly>${meetingOne.details }</textarea>
							
					<p class="meeting-detail"> 포함사항 </p>
					<textarea  cols="90" rows="5" class="onemeeting-include" readonly>${meetingOne.include }</textarea>
					
					
					<p class="meeting-detail"> 만나는 장소 </p>
					
					<div id="map" class="map"></div>
					<input type="hidden" id="mLocation" value="${meetingOne.mLocation }">
					<input type="hidden" id="search_name" disabled />
					<p class="one-border"> ${meetingOne.mLocationC }</p>

					<hr class="meeting-line">
					
					<p class="meeting-detail"> 개설자 정보 </p>
					<p class="meeting-profile"><img src="${pageContext.request.contextPath}/resources/images/meeting/profile.png"></p>
					<p class="one-border"> 
					${meetingOne.userName } 
					</p>
					
					<p class="meeting-detail"> 개설자 한마디 </p>
					<textarea  cols="50" rows="5" class="onemeeting-comment">${meetingOne.mComment }
					</textarea>
				
				<!-- 내용물 넣기 -->				
				
				<p class="meeting-detailsb"> 회원들이 많이 본 모임 </p>
					
					<c:forEach var="meetingCnt" items="${meetingCnt}">
					    <div class="m-profile">
					       	<img class="profile-thumbnail" src="${pageContext.request.contextPath}/s3/meeting/${meetingCnt.meetingFileVO[0].mSysImg }" class="thumbnail">
					        <h3 class="mname textLine"><a href="${pageContext.request.contextPath}/meetingOne?meetingIdx=${meetingCnt.meetingIdx }">${meetingCnt.mSubject }</a></h3>
					        <img class="mtitleimg" src="${pageContext.request.contextPath}/resources/images/meeting/location.png" width="20px" height="20px">
					        <p class="mtitle">
					        ${meetingCnt.local }</p>
					    </div>
					</c:forEach>
					
				
			</div> <!-- meeting-one의 끝 -->
			
				    
				<!-- 버튼 부분 -->
				<input type="button" name="" value="목록으로" class="send-btn2">
				
			</div> <!-- border-line의 끝 -->
			
			<div class="right-side">



			<form action="meetingRes" method="post">
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user" />
					<input type="hidden" name="fkUserId" value="${user.username}">
				</sec:authorize>
					<input type="hidden" id="meeting-idx" name="meetingIdx" value="${meetingOne.meetingIdx }">
					<input type="hidden" name="maxCount" value="${meetingOne.maxCount }">

				<p class="area"><img  src="${pageContext.request.contextPath}/resources/images/meeting/location.png"> ${meetingOne.local } </p>	
				<div  id="datePicker" class="">
					<input type="hidden" id="datePicker" value="${meetingOne.mDate }" disabled >
				</div>
				
				<div class="drop-rv">
				<input type="button" value="예약하기" class="r-btn">
					<c:choose>
						<c:when test="${!empty resUser.fkMeetingIdx  and !empty resUser.fkUserId}" >
							<div class="dropdown-content">
							<p class="m-condition"><a class="res" href="${pageContext.request.contextPath}/resCancle?meetingIdx=${meetingOne.meetingIdx }"> 예약 취소  </a></p>	
							</div>
						</c:when>
						
						<c:when test="${resCount.meetingIdx lt meetingOne.maxCount }">
							<div class="dropdown-content">
							<p class="m-condition"><button class="res" type="submit"> 신청현황 : ${resCount.meetingIdx }/${meetingOne.maxCount }명</button></p>	
							</div>
						</c:when>
						
						<c:when test="${resCount.meetingIdx eq meetingOne.maxCount }">
							<div class="dropdown-content">
							<p class="m-condition"> 예약 마감되었습니다  </p>	
							</div>
						</c:when>
					</c:choose>
				</div>
			</form>
				
			</div>	<!-- right-side의 끝 -->
				
				
	</div> <!-- hello_body의 끝 -->
</div> <!-- hello_top 의 끝 -->
				
	<!-- js에서 태그들을 찾고 있어서 여기다가 위치 시켜야 함 -->
	<script src="${pageContext.request.contextPath}/resources/js/meeting/meetingOne.js"></script>
	
</body>