<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/content.css">

<title>운동정보등록</title>
	
</head>

<body>
<div id="wrapper">
	<div class="con-wr contact">
		<div class="con-inner sub-sub">
		    <div class="tit-wr tit-wr-ani">
		      <h2>운동 정보 입력</h2>
			</div>

			<div class="sub-content">
			  <div class="basic-table">
			    <form:form class="open" method="post" action="${pageContext.request.contextPath}/insertexerciseinformation"
					enctype="multipart/form-data">
			        <ul>
			          <li>
			            <label for="className" class="sound_only">운동이름</label>
			            <input type="text" name="exerciseName" id="className" required value="" placeholder="운동이름">
			          </li>
			          <li>
			            <label for="tel" class="sound_only">운동영어이름</label>
			            <input type="text" name="exerciseEName" id="classType" required value="" placeholder="운동영어이름">
			          </li>
			          <li>
			            <label for="classLength" class="sound_only">운동부위</label>
			          	<select name="exerciseParts">
							<option value="가슴">가슴</option>
							<option value="등">등</option>
							<option value="어깨">어깨</option>
							<option value="팔">팔</option>
							<option value="복근">복근</option>
							<option value="하체">하체</option>
						</select>
			          </li>
			          <li>
			            <label for="classDay" class="sound_only">운동사진</label>
			            <input multiple="multiple" type="file" name="exerciseFile" id="classDay" required value="" placeholder="운동사진">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">운동동영상</label>
			            <input type="text" name="exerciseVideo" id="classTime" required value="" placeholder="운동동영상">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">운동주의사항</label>
			            <input type="text" name="caution" id="classTime" required value="" placeholder="주의사항">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">반복횟수</label>
			            <input type="text" name="repetition" id="classTime" required value="" placeholder="반복횟수">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">세트횟수</label>
			            <input type="text" name="setCount" id="classTime" required value="" placeholder="세트횟수">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">휴식시간</label>
			            <input type="text" name="restTime" id="classTime" required value="" placeholder="휴식시간">
			          </li>       
			          <li>
			            <label for="classTime" class="sound_only">운동방법1</label>
			            <input type="text" name="howtoExercise" id="classTime" required value="" placeholder="운동방법1">
			          </li>
			          <li>
			            <label for="classTime" class="sound_only">운동방법2</label>
			            <input type="text" name="howtoExercise2" id="classTime" required value="" placeholder="운동방법2">
			          </li>
			        </ul>
			        <input type="submit" name="" value="Send" class="send-btn">
			    </form:form>
			  </div>
		 	</div>
		</div>
	</div>
</div>
			
<%-- <c:if test="${not empty error }"> --%>
<%-- <h4 class=error>An error occurred :${error }</h4> --%>
<!-- <br> -->
<%-- </c:if> --%>

</body>
</html>