<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>	

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/meeting/meeting.css">
	<script src="${pageContext.request.contextPath}/resources/js/meeting/meeting.js"></script>
</head>


<body>

<div class="hello_top">

	<div class="hello_body center">
	
		<h2>내 모임</h2>
		
			<div class="boarder-line">
			
				<div class="meeting-category">
					<p class="meeting-main">함께 하는 즐거움과 시너지를 통해 운동하는</p>
					<p class="meeting-main">오프라인 모임!</p>
	
				</div>
	
				
			<div class="container">
			
			<c:forEach var="meeting" items="${meetingList}"> <!--  반복문의 시작 -->
			 <c:if test="${meeting.progressSt == '진행중'}">
			  <div class="service-details">
			    <img src="${pageContext.request.contextPath}/s3/meeting/${meeting.meetingFileVO[0].mSysImg }">
			    <div class="service-hover-text">
			      <h3 class="msub">${meeting.mSubject}</h3>
			      <h4 class="mlocal">${meeting.fkLocalNo }</h4>
			      
					<!-- 이건 현재 날짜 뽑는거 -->
			      <c:set var="now" value="<%=new java.util.Date()%>" />
			      	<!--  현재날짜를 00-00-00 형식으로 만들어주고 변수명 지어주기 -->
				  <c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 
				  
				   <!-- 이건 기존 미팅데이트가 위의 pattern과 일치 하니까 면수명만 지어준다 -->
				  <c:set var="mday" value="${meeting.mDate}"/> 
				  
				  <!--  이건 시작 날짜랑 종료날짜를 date형식으로 변환해주고  -->
				  <fmt:parseDate value="${today}" var="strPlanDate" pattern="yyyy-MM-dd"/>
				  <fmt:parseDate value="${mday }" var="endPlanDate" pattern="yyyy-MM-dd"/>

				  <!--  밀리세컨드값에서부터 일자로 변환하기 위해서  1,000 * 60(초) * 60(분) * 24(시간)을 나누어 준 것 -->
				  <fmt:parseNumber value="${strPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
				  <fmt:parseNumber value="${endPlanDate.time / (1000*60*60*24) }" integerOnly="true" var="endDate"></fmt:parseNumber>
				  
				  <p class=mday><c:out value="${endDate - strDate } 일 남음 " /></p>
				
				  
				 
				 
			    </div>
			    <div class="service-white service-text">
			      <div class="minner">
				      <a class="mcategory">
					      <img class="categoryimg" src="${pageContext.request.contextPath}/resources/images/meeting/localtag.png" >
					         ${meeting.mCategory}
				      </a>
				      <a class="lcategory">
					      <img class="localimg" src="${pageContext.request.contextPath}/resources/images/meeting/location.png">
					      ${meeting.local }
				      </a>
				      <a class="mpro">${meeting.progressSt}</a>
				      
				      <p class="msub"><a href="${pageContext.request.contextPath}/meetingOne?meetingIdx=${meeting.meetingIdx }">${meeting.mSubject}</a></p>
				      <a class="mprice">회비 ${meeting.mPrice }원</a>
			      </div>
			    </div>
			  </div>
			</c:if>
			</c:forEach> <!-- 반복문의 끝 -->
			  
			</div>
			
		
		</div>
	</div>
</div>
	
	<!-- js에서 태그들을 찾고 있어서 여기다가 위치 시켜야 함 -->

</body>