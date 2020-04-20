<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>다대다 스트리밍</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/multi.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/class/style.css">
<script src="${pageContext.request.contextPath }/resources/js/live/DetectRTC.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/live/adapter-latest.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/live/icon.js"></script>
<style>
	video {
		border: 1px solid white;
		width: 49.7%;
		height: 400px;
	}
	
	#class-name {
		padding: 20px;
		background: #fff;
		margin: 3px 0;
		width: 99.9%;
	}

	#class-name p {
		color: black;
	}
	
	.video-info {
		width: 70%;
		margin: auto;
	}
</style>
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
			       		<a rel="nofollow" class="btn-call" id="callButton">
							<div class="btn-call__ico">
						  		<i class="fas fa-video"></i>
							</div>
							<!-- //btn-call__ico -->
						</a>
			        	<div id="class-name"><p>${className } 강의입니다. 연결을 위해 <i class="fas fa-video"></i> 버튼을 클릭해주세요.</p></div>
			        	<div id="videos-container">
			        		<video id="localVideo" autoplay ></video>
							<video id="remoteVideo1" class="remoteVideo" autoplay></video>
							<video id="remoteVideo2" class="remoteVideo" autoplay></video>
							<video id="remoteVideo3" class="remoteVideo" autoplay></video>
			        	</div>
			        	<!-- //videos-container -->
			        </div>
			        <!-- //video-info -->
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
	<input type="hidden" value="${classIdx }" id="roomId">
	
<script src="${pageContext.request.contextPath }/resources/js/socket.io.js"></script>	
<!-- <script src="socket.io/socket.io.js"></script> -->	
<!-- <script src="http://localhost:3000/socket.io/socket.io.js"></script> -->
<script src="${pageContext.request.contextPath }/resources/js/live/multi.js"></script>
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
</body>
</html>