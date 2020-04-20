<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>트레이너정보 관리자페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/trainerinfo.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
    <div id="wrap">
        <header id="">

        </header>
        <div class="trainerbox_banner_admin">
        </div>
        <div class="trainerbox">

            <div class="trainer_inner">
            <c:forEach var="trainer" items="${trainerList }">
                <div class="trainer trainerAdmin">
                    <a href="${pageContext.request.contextPath}/admin/trainerupdate?trainerIdx=${trainer.trainerIdx }">
                        <img src="${pageContext.request.contextPath}/resources/images/trainer/${trainer.trainerProfile }" alt="트레이너" class="trainerImg_img">

                        <div class="trainer_caption">
                            <p class="captiontitle">
                            	${trainer.trainerNickname } ${trainer.trainerName }
                            </p>
                            <pre class="captiontxt">${trainer.trainerCareer }</pre>
                        </div>
                    </a>
                    <%-- <div class="trainer_btnbox">
	                    <a class="trainer_adminbtn" href="${pageContext.request.contextPath}/trainerupdate?trainerIdx=${trainer.trainerIdx }">수정</a>
	                    <span><a class="trainer_adminbtn" href="${pageContext.request.contextPath}/deletetrainer?trainerIdx=${trainer.trainerIdx }">삭제</a></span>
                    </div> --%>
                </div>
            </c:forEach>
            </div>
            
        </div>
    </div>
</body>
</html>