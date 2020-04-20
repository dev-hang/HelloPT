<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/faq.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/board/faqservice.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1cf06b214747f08132bda9cf27da5cb9"></script>
<title>상담실안내</title>
</head>
<body>

    <div class="all_view">
        <div class="banner">
            <img class="bnimg" src="${pageContext.request.contextPath}/resources/images/faq/FAQ.png" alt="afterservice">
        </div>
        <div class="tabbox">
            <ul class="tabbox_inner">
                <a href="faq1" class="tab_qnaover">
                    <li class="his">자주하는 질문</li>
                </a>
                <a href="#" class="tab_qna">
                    <li class="active">상담실 안내</li>
                </a>
            </ul>
        </div>
        <div class="opinion">
                헬로피티는 전문 상담요원이 상주하여 인터넷, 전화로 접수된<br> 
                컴플레인에 대해 권한을 가지고 회원정책 기준에 따라 신속하고 공정하게<br> 
                처리해드리기 위해 노력하고 있습니다. 
        </div>
        <div class="areabox">
            <div class="areatitle">헬로피티</div>
            <div class="areainfo">
                <p>서울 서초구 강남대로 459 6~7층</p>
                <p>수신자부담 : 080-111-2222<br>직통전화 : 02-111-2222~3</p>
            </div>
        </div>
        <div class="mapbox">
            <div id="map" style="width:100%;height:350px;"></div>

            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1cf06b214747f08132bda9cf27da5cb9"></script>
            <script>
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = {
                        center: new kakao.maps.LatLng(37.503328, 127.024243), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };

                var map = new kakao.maps.Map(mapContainer, mapOption);

                // 마커가 표시될 위치입니다 
                var markerPosition = new kakao.maps.LatLng(37.503328, 127.024243);

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    position: markerPosition
                });

                // 마커가 지도 위에 표시되도록 설정합니다
                marker.setMap(map);

                var iwContent = '<div style="padding:5px; color: black;">HelloPT 본사! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">백암빌딩</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">6~7층</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

                // 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    position: iwPosition,
                    content: iwContent
                });

                // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
                infowindow.open(map, marker);

            </script>
        </div>

</body>
</html>