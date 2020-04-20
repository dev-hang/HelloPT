<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>     
<!DOCTYPE html>
<html>
<head>
<title>일대다 스트리밍</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/live.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/style.css">
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
	
	<!-- 다양한 플랫폼에서 WebRTC 구현 간의 다양한 차이점을 없애주는 라이브러리 -->
	<script src="${pageContext.request.contextPath }/resources/js/live/adapter-latest.js"></script>
	
	<!-- 캠, 마이크 있는지 확인하는 라이브러리 -->
	<script src="${pageContext.request.contextPath }/resources/js/live/DetectRTC.js"></script>
	
	<script src="${pageContext.request.contextPath }/resources/js/socket.io.js"></script>
	
	<style>
	    video {
	        vertical-align: top;
	        width: 100%;
	    }
	</style>
	
	<script>
	    document.createElement('article');
	</script>
</head>
<body>
	<div id="wrapper">
		<div class="tit-wr tit-wr-ani">
			<h2>라이브 스트리밍</h2>
		</div>
		<!-- //tit-wr -->

		<div class="sub-content">
			<article>
			    <section class="experiment">
			        <div class="video-info">
			        	<div id="videos-container">
			        		<video playsinline autoplay="autoplay"></video>
			        	</div>
			        	<!-- //videos-container -->
			        	<div id="class-name"><p>${className } 강의입니다.</p></div>
			        </div>
			        <!-- //video-info -->
			        <div id="chat-container">
						<iframe id="chat" src="//hellopt-signal.herokuapp.com/" scrolling="no"></iframe>
			        </div>
			        <!-- //chat-container -->
			    </section>
			</article>
		</div>
		<!-- // sub-content -->
	</div>
	<!-- // wrapper -->

	<!-- 로그인한 userId 얻어오기 -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<input type="hidden" value="${user.username }" id="userid">
	</sec:authorize>
	
	<script>
		var confirm = confirm("방송을 시작하려면 확인을 눌러주세요");
		
		if (!confirm) {
			alert("이전 페이지로 이동합니다.")
			history.back();
		} else {
			DetectRTC.load(function() {
				if (DetectRTC.hasWebcam !== true) {
	                alert('카메라를 찾을 수 없습니다. 카메라가 시스템 상에 존재하지 않거나 카메라 접근을 허용하지 않았을 수 있습니다. 혹은 다른 앱에서 카메라를 사용중인지 확인해주세요.');
	                history.back();
	            } 
			});
		}
    </script>
	
    <!-- <script src="/socket.io/socket.io.js"></script> -->
    <script>
		var userId = $("#userid").val();
		var roomId = ${classIdx };
		
		console.log("userId: " + userId);
		console.log("roomId: " + roomId);
		
		//채팅서버로 룸아이디, 유저아이디 전송
		setTimeout(function () {
			document.querySelector("#chat").contentWindow.postMessage(JSON.stringify({"roomId": roomId, "userId": userId}), "*");	
		}, 1000);
	</script>    
	<script src="${pageContext.request.contextPath }/resources/js/live/broadcaster.js"></script>
</body>
</html>
