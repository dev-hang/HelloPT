<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>헬로피티트레이너지원</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/board/audition.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/board/slick.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/slick.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/audition.css">
</head>
<body>

    <div id="wrap">
        <div class="banner">
            <img class="bnimg" src="${pageContext.request.contextPath}/resources/images/audition/a01.jpg" alt="에프터 서비스 배너 이미지">
        </div>
        <div class="contents">
            <div class="textbox">
                <p class="txtbold">대한민국 No.1 그룹운동 플랫폼 헬로피티와 함께 할 트레이너를 모집합니다.</p>
                <p class="txt">헬로피티는 트레이너의 본질은 영업이 아니라 질 좋은 수업 제공이라 믿고,<br> 이를 위해 수준 높은 수업을 만들기 위해 끊임없이 연구합니다.</p>
                <p class="txt">헬로피티는 트레이너가 수업에만 집중할 수 있도록 수익 창출,<br>역량 강화, 브랜딩을 위해 노력합니다.</p>
                <div class="youtubebox">
                    <div id="player"></div>
                    <script>
                        // 2. 아이프레임api코드 비동기식으로 불러오는 코드?
                        var tag = document.createElement('script');

                        tag.src = "https://www.youtube.com/iframe_api";
                        var firstScriptTag = document.getElementsByTagName('script')[0];
                        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

                        // 3. api코드 다운로드후 이 함수는 만든다 하나의 아이프레임을    
                        var player;

                        function onYouTubeIframeAPIReady() {
                            player = new YT.Player('player', {
                                height: '285',
                                width: '540',
                                videoId: 'XdZWu0hqqTc', //원하는 유투브 동영상에서 오른쪽 클릭후 url 복사 누르면 id 확인가능
                                playerVars: {
                                    'controls': 1, //플레이어 컨드롤러 표시여부(0일때는 밑에 재생 버튼이 없고 1일때는 밑에 재생버튼을 클릭할 수 있다.)
                                    'rel': 0, //연관동영상 표시여부
                                    'playsinline': 1, //ios환경에서 전체화면으로 재생하지 않게하는 옵션
                                    'autoplay': 1, //자동재생 여부(모바일에서 작동하지 않습니다. mute설정을 하면 작동합니다.)
                                    'loop': 1,
                                    'playlist': 'rVpaz4XsKVc'
                                },
                                events: {
                                    'onReady': onPlayerReady,
                                    'onStateChange': onPlayerStateChange
                                }
                            });
                        }

                        // 4. api는 비디오 플리에어가 준비 됐을때 이 함수를 불러 올 것이다 .
                        function onPlayerReady(event) {
                            event.target.playVideo();
                        }

                        // 5. 플레이어의 상태가 변할 때 이 함수를 불러온다.
                        //    영상을 실행할때 이 함수가 실행된다.
                        var done = false;

                        function onPlayerStateChange(event) {
                            if (event.data == YT.PlayerState.PLAYING && !done) {
                                //setTimeout(stopVideo, 6000); -->6초만 재생하고 멈추는 코드
                                done = true;
                            }
                        }

                        function stopVideo() {
                            player.stopVideo();
                        }
                    </script>
                    </div>
                </div>
            </div>
            <div class="tabbox">
                <ul id="tab">
                    <li id="tab01" class="tab_li">주요 업무</li>
                    <li id="tab02" class="tab_li">트레이너 혜택</li>
                    <li id="tab03" class="tab_li">자격 요건</li>
                    <li id="tab04" class="tab_li">선발 과정</li>
                    <li id="tab05" class="tab_li">지원 하기</li>
                </ul>
            </div>
            <div class="viewbox">
                <div class="view" id="view01">
                    <div class="view01_inner1">
                        <h2 class="viewh2_white">주요업무<br>소개</h2>
                        <p class="viewtext_white">헬로피티<br>오프라인 수업에서는</p>
                        <img class="view_img" src="${pageContext.request.contextPath}/resources/images/audition/a02.jpg" alt="이미지">
                    </div>
                    <div class="view01_inner2">
                        <div class="inner2_box">
                            <p class="inner2_num">01</p>
                            <p class="inner2_txt">16명의 그룹 운동 수업을 진행합니다.<br><span class="innert2_small">(트레이너 2인 진행)</span></p>
                            <p class="inner2_gray">주말반 : 클래스 당 주 1회, 2시간 수업<br>평일반 : 클래스 당 주 2회, 1시간 30분 수업</p>
                            <p class="innert2_small">개인의 수업 가능 시간 및 역량을 고려하여, 1주 최소 3개, 최대 10개의<br>클래스를 담당하게 됩니다.</p>
                        </div>
                        <div class="inner2_box">
                            <p class="inner2_num">02</p>
                            <p class="inner2_txt">온라인 과제 체크 및 회원 피드백</p><br>
                            <p class="innert2_small">회원들의 운동 영상에 대한 피드백을 주 1회 제공함으로써,<br>회원들의 운동 학습 효율을 높이고 동기부여를 위한 응원과 지지를 보냅니다.</p>
                        </div>
                    </div>
                    <div class="view01_inner3">
                        <p class="viewtext_white">헬로피티<br>온라인 수업에서는</p>
                        <div class="slider single-item">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/Silding_1.jpg" alt="img">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/Silding_2.jpg" alt="img">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/Silding_3.jpg" alt="img">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/Silding_4.jpg" alt="img">
                        </div>
                        <script>
                            $('.single-item').slick({
                                autoplay: true,
                                autoplaySpeed: 5000,
                                fade: true,
                                cssEase: "ease",
                                easing: "ease",
                            });
                        </script>
                    </div>
                    <div class="view01_inner2">
                        <div class="inner3_box">
                            <p class="inner2_num">01</p>
                            <p class="inner2_txt">1명 ~ 4명의 그룹 운동<br>수업을 진행합니다.<br><span class="innert2_small">(트레이너 1인 진행)</span></p>
                            <p class="inner2_gray">클래스 당 주 2회, 1시간 수업</p>
                            <p class="innert2_small">개인의 수업 가능 시간 및 역량을 고려하여,<br>1주 최소 3개, 최대 10개의 클래스를<br>담당하게 됩니다.</p>
                        </div>
                        <div class="inner3_box">
                            <p class="inner2_num">02</p>
                            <p class="inner2_txt">회원 상담 진행</p><br>
                            <p class="innert2_small">신규 회원들의 올바른 목표 및 운동 방향성<br>설정을 위하여 온라인 상담을 진행합니다.</p>
                        </div>
                        <div class="inner3_box">
                            <p class="inner2_num">03</p>
                            <p class="inner2_txt">수업 외 과제 부여</p><br>
                            <p class="innert2_small">클래스 별로, 주차 별 목표에 적합한 과제를<br>부여하여, 회원들이 수업 시간 외에도 지속적으로 운동할 수 있는 환경을 만들어 줍니다.</p>
                        </div>
                    </div>
                </div>
                <div class="view" id="view02">
                    <div class="view02_inner1">
                        <h2 class="viewh2_black">헬로피티<br>트레이너가 되면,<br>이런 점이 좋아요!</h2>
                        <div class="view02_imgbox">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/view02_1.png" alt="이미지1">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/view02_2.png" alt="이미지1">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/view02_3.png" alt="이미지1">
                        </div>
                        <div>
                            <img src="${pageContext.request.contextPath}/resources/images/audition/view02_4.png" alt="이미지1">
                            <img src="${pageContext.request.contextPath}/resources/images/audition/view02_5.png" alt="이미지1">
                        </div>
                    </div>
                </div>
                <div class="view" id="view03">
                    <div class="view03_inner1">
                        <h2 class="viewh2_white">헬로피티는<br>이런 트레이너와<br>잘 어울려요!</h2>
                    </div>
                    <div class="view03_inner2">
                        <div class="view03_txtbox">
                            <p class="inner2_txt"><span class="txtline"></span>그룹 수업 능력과 주도력</p><br>
                            <p class="innert2_small">‘따르고 싶은 운동리더’ 모습에 걸맞는 수업 진행능력이 필수적입니다.</p>
                        </div>
                        <div class="view03_txtbox">
                            <p class="inner2_txt"><span class="txtline"></span>섬세하고 긍정적인 개별 커뮤니케이션 능력</p><br>
                            <p class="innert2_small">헬로피티는 그룹 형태의 수업을 진행하지만, 헬로피티는 트레이너는 회원 한명 한명을 소중하게 생각하고, 커뮤니케이션 해야 합니다.</p>
                        </div>
                        <div class="view03_txtbox">
                            <p class="inner2_txt"><span class="txtline"></span>다양한 형태의 수업을 기획, 진행할 수 있는 역량</p><br>
                            <p class="innert2_small">헬로피티는 자체적인 수업 콘텐츠를 지속적으로 개발하고 있습니다.<br>새로운 수업 콘텐츠를 만들고 받아들이는 과정이 즐거운 분들과 함께 하고 싶습니다.</p>
                        </div>
                        <div class="view03_txtbox">
                            <p class="inner2_txt"><span class="txtline"></span>자기 계발과 성장에 대한 의지</p><br>
                            <p c
                               lass="innert2_small">헬로피티는 트레이너가 ‘더 나은 수업’과 ‘회원들의 만족’을 위해 노력하고 시간을 투자하는 것이 그 무엇보다 중요하다고 생각합니다.</p>
                        </div>
                        <div class="view03_txtbox">
                            <p class="inner2_txt"><span class="txtline"></span>그룹 수업 능력과 주도력</p><br>
                            <p class="innert2_small">‘따르고 싶은 운동리더’ 모습에 걸맞는 수업 진행능력이 필수적입니다.</p><br>
                            <p class="innert2_small txtcolor">1. 생활 스포츠 지도사 자격증 혹은 그에 준하는 자격증을 보유하신 분 (NSCA-CPT, 건강 운동 관리사, NASM-CPT 등)<br>2. 4인 이상의 그룹 수업 경험을 보유하신 분</p><br>
                        </div>
                    </div>
                </div>
                <div class="view" id="view04">
                    <div class="view04_inner1">
                        <h2 class="viewh2_black">선발과정</h2>
                    </div>
                    <div class="view04_inner1">
                        <div class="dotbox">
                            <span class="dot">01. 지원서 접수</span>
                            <span class="dot">02. 1차 인터뷰</span>
                            <span class="dot">03. 2차 인터뷰</span>
                            <span class="dot">04. 합격자 발표</span>
                            <span class="dot">05. 교육 진행</span>
                        </div>
                    </div>
                </div>
                <div class="view" id="view05">
                    <p class="view05_txt">이젠, 헬로피티 트레이너로 업그레이드 할 때!</p><br><br>
                    <a href="https://docs.google.com/forms/d/e/1FAIpQLScpCaBc7VJXTcb0sn2ArRgoSOD9Haku3hNNCWowsLbPsCSVvw/viewform?usp=sf_link" class="view05_a">
                        <p class="view05_btn">트레이너 지원하기</p>
                    </a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>