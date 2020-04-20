<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Class</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/class/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/class/classlist.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/class/content.css">
</head>
<body>
	<!-- 콘텐츠 시작 { -->
	<div id="wrapper">
		<div class="con-wr">
			<div class="con-inner sub-sub">
				<div class="tit-wr tit-wr-ani">
					<h2>내 스트리밍 강의 리스트</h2>
				</div>

				<div class="sub-content">
					<!--콘탠츠 내용 시작. 내용 불러오기-->
					<!--content-area-->

					<!-- 게시판 목록 시작 , 썸네일 이미지 -->
					<div id="bo_gall" style="width: 100%">
						<div class="thum-tit-wr">
							<h3 class="thum-tit">All CLASS</h3>
						</div>

						<ul id="gall_ul" class="gall_row">
							<c:forEach var="liveClass" items="${liveClassList }">
								<li class="gall_li col-gn-3">
									<div class="gall_box">
										<a
											href="${pageContext.request.contextPath}/classdetail?classIdx=${liveClass.classIdx }">
											<div class="thum_hover">
												<div style="color: #ef0000;">${liveClass.classLength } 주간<br>${liveClass.classDay }<br>${liveClass.liveStatus }</div>
											</div>
										</a>
										<div class="gall_con">
											<div class="gall_img">
												<img
													src="${pageContext.request.contextPath }/resources/images/class/thumbnail.jpg">
											</div>
											<div class="class_info">
												<p class="class_info1">
													<span>${liveClass.classType }</span>
												</p>
												<p class="class_info2">${liveClass.className }</p>
												<p class="class_info3">${liveClass.classTime }</p>
											</div>
										</div>
										<c:choose>
											<c:when test="${liveClass.classType eq '일대다' }">
												<button class="broadcast"
													onclick="location.href='${pageContext.request.contextPath}/broadcaster?classIdx=${liveClass.classIdx }'"
													style="float: left;">START</button>
												<c:forEach var="member" items="${classMember }">
													<c:if test="${member.fkClassIdx eq liveClass.classIdx }">
														<button class="broadcast"
															onclick="location.href='${pageContext.request.contextPath}/viewer?classIdx=${liveClass.classIdx }'"
															style="float: right;">VIEW</button>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<button class="broadcast"
													onclick="location.href='${pageContext.request.contextPath}/multi?classIdx=${liveClass.classIdx }'"
													style="float: left;">MULTI</button>
												<c:forEach var="member" items="${classMember }">
													<c:if test="${member.fkClassIdx eq liveClass.classIdx}">
														<button class="broadcast"
															onclick="location.href='${pageContext.request.contextPath}/multi?classIdx=${liveClass.classIdx }'"
															style="float: right;">MULTI</button>
													</c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
							</c:forEach>
							<c:forEach var="viwerLiveClass" items="${ viewerClassList }">
								<li class="gall_li col-gn-3">
									<div class="gall_box">
										<a
											href="${pageContext.request.contextPath}/classdetail?classIdx=${viwerLiveClass.classIdx }">
											<div class="thum_hover">
												<div style="color: #ef0000;">${viwerLiveClass.classLength } 주간<br>${viwerLiveClass.classDay }<br>${viwerLiveClass.liveStatus }</div>
											</div>
										</a>
										<div class="gall_con">
											<div class="gall_img">
												<img
													src="${pageContext.request.contextPath }/resources/images/class/thumbnail.jpg">
											</div>
											<div id="rec_class_info">
												<p id="rec_class_info1">
													<span>${liveClass.classType }</span>
												</p>
												<p id="rec_class_info2">${liveClass.className }</p>
												<p id="rec_class_info3">${liveClass.classTime }</p>
											</div>
										</div>
										<c:choose>
											<c:when test="${liveclass.classType eq '일대다' }">
												<button class="broadcast"
													onclick="location.href='${pageContext.request.contextPath}/viewer?classIdx=${viwerLiveClass.classIdx }'"
													style="float: right;">VIEW</button>
											</c:when>
											<c:otherwise>
												<button class="broadcast"
													onclick="location.href='${pageContext.request.contextPath}/multi?classIdx=${viwerLiveClass.classIdx }'"
													style="float: right;">MULTI</button>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
							</c:forEach>
						</ul>

						<!--강의 개설 버튼-->
						<button type="button" class="open-class"
							onclick="location.href='${pageContext.request.contextPath}/openClassForm'">
							New Class <i class="xi-plus-thin"></i>
						</button>
					</div>
				</div>
				<!--con-inner-->
			</div>
			<!--con-wr-->
		</div>
		<!-- } 게시판 목록 끝 -->
	</div>
	<!-- } 하단 끝 -->
</body>
</html>