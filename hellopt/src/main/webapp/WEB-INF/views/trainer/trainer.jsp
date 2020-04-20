<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>트레이너 리스트</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/trainerinfo.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
    <div id="wrap">
        <div class="trainerbox_banner">
        <div class="${pageContext.request.contextPath}/resources/images/trainer/banner.png">

            <div class="bannertxtbox">
                <p class="bannertxt"><span class="bannertxt_big">헬로피티 트레이너</span><br>
            철저하게 검증된 트레이너 선생님들에 의한 수준 높은 트레이닝을 받아보세요!<br>
            연예인 트레이너, 보디빌더 출신 등 헬로피티만의 특별한 트레이너를 소개합니다.</p>
            </div>
        </div>
        </div>
        <div class="trainerbox">

            <div class="trainer_inner">
            <c:forEach var="trainer" items="${trainerList }">
                <div class="trainer">
                    <a href="trainerinfo?trainerIdx=${trainer.trainerIdx }">
                        <img src="${pageContext.request.contextPath}/resources/images/trainer/${trainer.trainerProfile }" alt="트레이너" class="trainerImg_img">
                        <div class="trainer_caption">
                            <p class="captiontitle">${trainer.trainerNickname } ${trainer.trainerName }</p>
                            <pre class="captiontxt">${trainer.trainerCareer }</pre>
                        </div>
                    </a>
                </div>
            </c:forEach>
            </div>
            
        </div>
    </div>
</body>
</html>