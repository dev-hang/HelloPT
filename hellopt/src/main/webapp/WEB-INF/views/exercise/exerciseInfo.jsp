<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>운동정보 상세보기~!</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/exercise.css">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrap">
		<div class="bannertxtbox">
			<p class="bannertxt"><span class="bannertxt_big">대한민국 No.1 그룹운동 플랫폼 헬로피티</span><br>
			헬로피티에서 제공하는 운동방법을 통해 건강하고 아름다운 몸을 가꾸세요.</p>
		</div>
			<div class="exercisebox">
				<div class="exerciseImg">
					<c:choose>
						<c:when test="${exerciseInformation.exerciseParts eq '가슴'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseC.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
						<c:when test="${exerciseInformation.exerciseParts eq '등'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseB.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
						<c:when test="${exerciseInformation.exerciseParts eq '어깨'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseS.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
						<c:when test="${exerciseInformation.exerciseParts eq '팔'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseA.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
						<c:when test="${exerciseInformation.exerciseParts eq '복근'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseABS.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
						<c:when test="${exerciseInformation.exerciseParts eq '하체'}">
							<img src="${pageContext.request.contextPath}/resources/images/exercise/exerciseL.jpg" alt="운동부위사진" class="exerciseImg_img">
						</c:when>
					</c:choose>
				</div>
				<div class="exerciseinfo">
					<h2 class="exercisetitle">${exerciseInformation.exerciseName }<span class="nick">${exerciseInformation.exerciseEName }</span></h2>
					<div>
					<br><br>
						<h2 class="exerhow">운동방법</h2>
						<br>
						<h2 id="exerhow">${exerciseInformation.howtoExercise }</h2>
            			<h2 id="exerhow">${exerciseInformation.howtoExercise2 }</h2>
            		</div>
            		<br>
					<br>
					<div>
						<h2 class="exercau">주의사항</h2>
						<br><h2 id="exercau">${exerciseInformation.caution }</h2>
					</div>
				<div class="exercise_video">
				<br><br>
					<h3 class="exercise_video_txt">운동 동영상</h3>
					<div class="exercise_video_box">
					<iframe width="592" height="450"
							src="https://www.youtube.com/embed/${exerciseInformation.exerciseVideo }" frameborder="0"
							allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
							allowfullscreen></iframe>
					</div>
        	<br><br>
            
            <div class="exercise_dd">
            	<c:forEach var="file" items="${pageContext.request.contextPath}/s3/exercise/${exerciseInformation.exercisePictures }">
            		<img src="${file }" width="250" height="250">
            		<br><br>
            	</c:forEach>
            
				<h2>권장반복횟수 : ${exerciseInformation.repetition }회</h2>
				<h2>권장세트횟수 : ${exerciseInformation.setCount }회</h2>
				<h2>권장휴식시간 : ${exerciseInformation.restTime }초</h2>
				
				<hr>
				</div>
			<p class="cente">
				<a href="deleteexerciseinfo?exerciseIdx=${exerciseInformation.exerciseIdx }">글 삭제</a>
				<a href="updateexerciseinfo?exerciseIdx=${exerciseInformation.exerciseIdx }">글 수정</a>
			</p>
			<br><br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>