<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/content.css">

<!-- datepicker -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.standalone.min.css'>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.13/css/all.css'>
<script src='${pageContext.request.contextPath}/resources/js/live/bootstrap-datepicker.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/live/datepicker.js'></script>

<title>스트리밍 강의 개설</title>
</head>

<body>
<!-- 콘텐츠 시작  -->
<div id="wrapper">
	<div class="con-wr contact">
		<div class="con-inner sub-sub">
		    <div class="tit-wr tit-wr-ani">
		      <h2>스트리밍 강의 개설</h2>
		    </div>
		    <!-- //tit-wr -->
		
			<div class="sub-content">
			  <div class="basic-table">
			    <form class="opens" action="openclass" method="post">
			        <ul>
			          <li>
			            <label for="className" class="sound_only">수업 이름</label>
			            <input type="text" name="className" id="className" required value="" placeholder="수업 이름">
			          </li>
			          <li>
			            <label for="classType" class="sound_only">수업 유형</label>
			            <select class="class-select" name="classType" id="classType" required>
			            	<option value="" hidden>수업 유형</option>
			            	<option value="일대다">일대다</option>
			            	<option value="다대다">다대다</option>
			            </select>
			           <!--  <input type="text" name="classType" id="classType" required value="" placeholder="수업 유형"> -->
			          </li>
			          <li>
			            <label for="classLength" class="sound_only">수업 기간</label>
			            <input style="width:95%; display: inline-block;" type="number" name="classLength" id="classLength" required value="" placeholder="수업 기간(주 단위로 입력 가능합니다.)" min="1" max="12">
			            <span class="week">주</span>
			          </li>
			          <li>
			            <label for="classStartDate" class="sound_only">수업 시작일</label>
			            <!-- <input type="text" name="classStartDate" id="classStartDate" required value="" placeholder="수업 시작일"> -->
						<div class="input-group date datepicker">
							<input class="form-control" name="classStartDate" id="classStartDate" required value="" placeholder="수업 시작일">
							<button class="btn btn-danger rounded-0" type="button">
								<i class="fas fa-calendar-alt"></i>
							</button>
						</div>
						<!-- //input-group -->
					  </li>
			          <li>
			            <label for="classDay" class="sound_only">수업 요일</label>
			            <select class="class-select" name="classDay" required>
			            	<option value="" hidden>수업 요일</option>
			            	<option value="월/수/금">월/수/금</option>
			            	<option value="화/목">화/목</option>
			            	<option value="토/일">토/일</option>
			            </select>
			            <!-- <input type="text" name="classDay" id="classDay" required value="" placeholder="수업 요일"> -->
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">수업 시간</label>
			            <select class="class-select" name="classTime" required>
			            	<option value="" hidden>수업 시간</option>
			            	<option value="9:00 ~ 9:50">9:00 ~ 9:50</option>
			            	<option value="10:00 ~ 10:50">10:00 ~ 10:50</option>
			            	<option value="11:00 ~ 11:50">11:00 ~ 11:50</option>
			            	<option value="12:00 ~ 12:50">12:00 ~ 12:50</option>
			            	<option value="13:00 ~ 13:50">13:00 ~ 13:50</option>
			            	<option value="14:00 ~ 14:50">14:00 ~ 14:50</option>
			            	<option value="15:00 ~ 15:50">15:00 ~ 15:50</option>
			            	<option value="16:00 ~ 16:50">16:00 ~ 16:50</option>
			            	<option value="17:00 ~ 17:50">17:00 ~ 17:50</option>
			            	<option value="18:00 ~ 18:50">18:00 ~ 18:50</option>
			            	<option value="19:00 ~ 19:50">19:00 ~ 19:50</option>
			            	<option value="20:00 ~ 20:50">20:00 ~ 20:50</option>
			            	<option value="21:00 ~ 21:50">21:00 ~ 21:50</option>
			            	<option value="22:00 ~ 22:50">22:00 ~ 22:50</option>
			            	<option value="23:00 ~ 23:50">23:00 ~ 23:50</option>
			            </select>
			            <!-- <input type="text" name="classTime" id="classTime" required value="" placeholder="수업 시간"> -->
			          </li>
			          <li>
			            <label for="totalMembers" class="sound_only">수업 인원</label>
			       		<select class="class-select" name="totalMembers" id="totalMembers" required>
			       			<option value="" hidden>수업 인원</option>
			       			<option value="4">4</option>
			       			<option value="100">100</option>
			       		</select>
			           <!--  <input type="number" name="totalMembers" id="totalMembers" required value="" placeholder="수업 인원"> -->
			          </li>
			          <li>
			            <label for="price" class="sound_only">가격</label>
			            <input type="number" name="price" id="price" required value="" placeholder="가격">
			          </li>
			        </ul>
			        <sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="user" />
						<input type="hidden" value="${user.username }" name="fkUserId">
					</sec:authorize>
			        <input type="submit" name="" value="Send" class="send-btn">
			    </form>
			  </div>
			  <!-- //basic-table -->
		 	</div>
		 	<!-- //sub-content -->
		</div>
		<!-- //con-inner-->
	</div>
	<!-- //con-wr-->
</div>
<!-- //wrapper -->
<!-- 하단 끝 -->
<script>
	$("#classType").change(function() {
		if ($("#classType").val() == '일대다') {
			/* alert("일대다"); */
			$("select option[value='100']").attr("selected", true);
		} 
		if ($("#classType").val() == '다대다') {
			/* alert("다대다"); */
			$("select option[value='4']").attr("selected", true);
		};
	});
</script>
</body>
</html>
