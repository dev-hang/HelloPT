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
		<div class="boarder-line">
				
			<div class="mAdminCenter">
					
				
				<table class="meetingAdminTb ">
					<thead>
						<tr>
						<th class="adminTh" colspan="3">오프라인 모임 신청 대기 목록</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="mAdmin" items="${meetingList}">
						<tr>
					 	<td class="adminTd" >
					 	<a class="mBtn" href="${pageContext.request.contextPath}/meetingOne?meetingIdx=${mAdmin.meetingIdx }">${mAdmin.mSubject}</a>
					 	<c:if test="${mAdmin.progressSt == '진행중' }">
					 	<span class="problue">${mAdmin.progressSt}</span>
					 	</c:if>
					 	<c:if test="${mAdmin.progressSt == '대기중' }">
					 	<span class="progray">${mAdmin.progressSt}</span>
					 	</c:if>
					 	<c:if test="${mAdmin.progressSt == '거절됨' }">
					 	<span class="prored">${mAdmin.progressSt}</span>
					 	</c:if>
					 	</td>
					 	
					 	<c:if test="${mAdmin.progressSt == '진행중' }">
					 	<td class="adminTd2"><a class="mBtn red" href="${pageContext.request.contextPath}/admin/progressY?meetingIdx=${mAdmin.meetingIdx }">승낙</a></td>
					 	</c:if>
					 	<c:if test="${mAdmin.progressSt == '거절됨' }">
					 	<td class="adminTd2"><a class="mBtn white" href="${pageContext.request.contextPath}/admin/progressY?meetingIdx=${mAdmin.meetingIdx }">승낙</a></td>
					 	</c:if>
					 	<c:if test="${mAdmin.progressSt == '대기중' }">
					 	<td class="adminTd2"><a class="mBtn white" href="${pageContext.request.contextPath}/admin/progressY?meetingIdx=${mAdmin.meetingIdx }">승낙</a></td>
					 	</c:if>
					 	
					 	<c:if test="${mAdmin.progressSt == '거절됨' }">
					 	<td class="adminTd2"><a class="mBtn red" href="${pageContext.request.contextPath}/admin/progressN?meetingIdx=${mAdmin.meetingIdx }">거절</a></td>
						</c:if>
						<c:if test="${mAdmin.progressSt == '진행중' }">
					 	<td class="adminTd2"><a class="mBtn white" href="${pageContext.request.contextPath}/admin/progressN?meetingIdx=${mAdmin.meetingIdx }">거절 </a></td>
						</c:if>
						<c:if test="${mAdmin.progressSt == '대기중' }">
					 	<td class="adminTd2"><a class="mBtn white" href="${pageContext.request.contextPath}/admin/progressY?meetingIdx=${mAdmin.meetingIdx }">거절</a></td>
					 	</c:if>
					 	
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			
			
		</div>
	</div>
</div>
	
	
</body>