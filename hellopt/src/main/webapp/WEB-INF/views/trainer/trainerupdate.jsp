<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>트레이너업데이트상세정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/trainerinfo.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
    <div id="wrap">
        <header id="">

        </header>
        <!-- <form action="updatetrainer" method="post"> -->
        <%-- <input name="trainerIdx" type="hidden" value=${trainerinfo.trainerIdx }> --%>
        <div class="trainerbox">
            <div class="trainerImg">
                <img src="${pageContext.request.contextPath}/resources/images/trainer/${trainerinfo.trainerProfile }" alt="트레이너" class="trainerImg_img">
            </div>
            <div class="trainerinfo">
                <h2 class="trainertitle">${trainerinfo.trainerName }<span class="nick">${trainerinfo.trainerNickname }</span></h2>
                <div class="career">
                    <h3 class="h3_font">경력</h3>
                    <pre class="trainertxt">${trainerinfo.trainerCareer }</pre>
                </div>
                <div class="word">
                    <h3 class="h3_font">트레이너 한마디</h3>
                    <pre class="trainertxt">${trainerinfo.trainerTalk }</pre>
                </div>
                <div class="trainer_media">
                    <h3 class="trainer_media_txt">트레이너 영상</h3>
                    <div class="trainer_media_box">
                    <iframe width="792" height="300"
							src="https://www.youtube.com/embed/${trainerinfo.trainerMedia }" frameborder="0"
							allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
							allowfullscreen></iframe>
                    </div>
                </div>
                <div class="trainer_btnbox">
	        	<!-- <input type="submit" value="수정페이지" name="" class="trainer_adminbtn"> -->
	        	<a class="trainer_adminbtn" href="${pageContext.request.contextPath}/admin/trainerupdatepage?trainerIdx=${trainerinfo.trainerIdx }">수정</a>
	            <span><a class="trainer_adminbtn" href="${pageContext.request.contextPath}/admin/deletetrainer?trainerIdx=${trainerinfo.trainerIdx }">삭제</a></span>
                </div>
            </div>

        </div>
        </form>
    </div>

</body>
</html>