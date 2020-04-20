<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/meeting/meeting.css">
	<!-- 지도  -->
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
		
		<form class="auto" action="meetingWriteOk" method="post" enctype="multipart/form-data" autocomplete="off">
			<div class="boarder-line">
				
				<h3 class="meeting-main">모임 개설 신청하기</h3>
				<hr class="meeting-line">
				<!-- 내용물 넣기 -->
					<div class="meeting-title">개 설 자
						<div class="meeting-content">
							<p class="meeting-profile"><img src="${pageContext.request.contextPath}/resources/images/meeting/profile.png"></p>
							
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal" var="user" />
								<input class="username" id="fkUserId" name="fkUserId" type="text" value="${user.username}" readonly>
							</sec:authorize>
							<div class="profile-detail">
								<textarea  cols="50" rows="5" class="meeting-comment" id="mComment" name="mComment" placeholder="개설자 한마디를 입력해주세요. "></textarea>
							</div>
							
						</div>
					</div>
					
					<div class="meeting-title">모임유형
						<div class="profile-detail2">
							<select class="m-select" name="fkMCategoryNo">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.mCategoryNo }">${category.mCategory}</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="meeting-title">모임지역
						<div class="profile-detail2">
							<select class="m-select" name="fkLocalNo">
							<c:forEach var="local" items="${localList}">
								<option value="${local.localNo }">${local.local }</option>
							</c:forEach>	
							</select>
						</div>
					</div>

					<div class="meeting-title">최대인원
						<div class="max-count">
						    <input class="max-detail" id="maxCount" name="maxCount" type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="00"> 명
						</div>
					</div>

					<div class="meeting-title">모임일자
						<div class="meeting-content ">
							<input class="form-control" type="text" id="datePicker" name="mDate" placeholder="click" >
						</div>
					</div>
					
					<div class="meeting-title">모임제목
						<div class="meeting-content">
							<input class="meeting-sub" id="mSubject" name="mSubject" type="text" placeholder="제목">						
						</div>
					</div>
					
					<div class="meeting-title">한줄소개
						<div class="meeting-content">
							<input class="meeting-sub" id="mMemo" name="mMemo" type="text" placeholder="8시간 소요, 자차 이동, 최대 8명">						
						</div>
					</div>
					
					<div class="meeting-title">상세정보
						<div class="meeting-content2">
							<textarea  cols="90" rows="10" class="meeting-textarea" id="details" name="details"  placeholder="모임소개  &#13;&#10; 1. 최소인원 : &#13;&#10; 2. 출발시간 : &#13;&#10; 3. 도착시간 : &#13;&#10; 4. 점       심 : &#13;&#10; 5. 준  비  물 : "></textarea>
						</div>
					</div>
					
					<div class="meeting-title">포함사항
						<div class="meeting-content2">
							<textarea  cols="90" rows="5" class="meeting-include" id="include" name="include" placeholder="점심식사, 간식"></textarea>					
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
								<input type="hidden" class="meeting-mcomment" id="mLocation" name="mLocation" placeholder="위치">
								<input class="meeting-mcomment" id="mLocationC" name="mLocationC" placeholder="상세위치 ">
							</div>
						</div>
					</div>
					
					<div class="meeting-title">회　　비
						<div class="meeting-content">
							<input class="meeting-sub" id="mPrice" name="mPrice" type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="30,000원  (숫자만 입력하세요)">						
						</div>
					</div>
					
					<div class="meeting-title">파　　일
					<input type="file" class="file-btn" id="uploadFile" name="uploadFile" multiple="multiple">
					</div>
					
				<!-- 내용물 넣기 -->				
				<hr class="meeting-line">
				
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="user" />
					<c:if test="${user.username != null }">
					<input type="submit" id="submit" value="개설 신청하기" class="send-btn2" onclick="webSocket()">
					</c:if>
				</sec:authorize>
				
			</div>	
	    </form>
			
			
	</div>
</div>
	
	<!-- js에서 태그들을 찾고 있어서 여기다가 위치 시켜야 함 -->
	<script src="${pageContext.request.contextPath}/resources/js/meeting/meetingWrite.js"></script>
	
	
	
	
</body>