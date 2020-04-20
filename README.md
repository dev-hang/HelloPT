# HelloPT
## 소개
언제 어디서나 운동하고 싶은 사람들을 위한 온-오프라인 운동 플랫폼 HelloPT입니다.

https://hellopt.info

## 사용 기술
- Front-end
    - HTML5
    - CSS3
    - Javascript
    - AJAX
    - Bootstrap
    - JQuery
- Back-end
    - Spring framework
    - Oracle database
    - MyBatis
    - JSP
    - Node.js
    - express
    - sitemesh
    - AWS (EC2, RDS, S3, Route53)
- Open source API
    - [socket.io](https://socket.io)
    - [WebRTC](https://webrtc.org/)

## 기능
1. [로그인-회원가입](#로그인-회원가입)
2. [트레이너 게시판](#트레이너-게시판)
3. [운동 정보 게시판](#운동-정보-게시판)
4. [영양 계산기](#영양-계산기)
5. [PT 온라인 수업](#PT-온라인-수업)
6. [리뷰 게시판](#리뷰-게시판)
7. [FAQ 게시판](#FAQ-게시판)
8. [오프라인 모임 게시판](#오프라인-모임-게시판)
9. [일일 운동 체크](#일일-운동-체크)

### 로그인-회원가입
[로그인](https://hellopt.info/login)

Spring security authentication를 이용하여 로그인을 구현했습니다.  
로그인 기능 확인 시 다음의 아이디를 사용해주세요.

    
    ID: githubtest
    password: githubtest
    

[회원가입](https://hellopt.info/user/registrationform)

HelloPT에 회원으로 등록하기 위해 정보를 입력받는 페이지입니다.

유저가 입력한 정보를 client측에서는 내장 from validation을 사용해 데이터 유효성을 검사합니다. server측에는 java bean validation을 사용해 데이터 유효성을 검사합니다.

### 트레이너 게시판
[트레이너 정보](https://hellopt.info/trainer)

헬로피티에 등록된 트레이너의 정보를 보여주는 페이지입니다. 각각의 트레이너를 선택하면 상세 정보를 볼 수 있습니다.

[트레이너 신청](https://hellopt.info/audition)  

트레이너 지원에 필요한 안내사항과 설명을 통해 트레이너를 지원할 수 있는 페이지입니다.  
트레이너 지원 시 Google 설문지를 이용하여 관리자 계정으로 이력서가 전송됩니다.

### 운동 정보 게시판
[운동 정보](https://hellopt.info/exerciseinfolist)

운동에 대한 설명 및 동영상을 통해 운동법을 배울 수 있는 페이지입니다.

### 영양 계산기
[영양 계산기](https://hellopt.info/meal)

신체 정보를 입력한 후 음식을 선택하면 식사를 평가 할 수 있습니다.  
평가는 총 3가지로 일반식사, 다이어트식사, 벌크업 즉 살찌우는 식사로 평가를 나눴습니다. 각 평가마다 다른 계산식이 적용되어 구글차트를 사용 시각적으로 보기 쉽게 구현하였습니다.

### 리뷰 게시판
[리뷰 게시판](https://hellopt.info/review)

후기를 남길 수 있는 게시판입니다. 로그인 시 작성자만 수정, 삭제가 가능합니다.  
다중 파일 업로드를 이용해 여러 개의 이미지를 업로드 할 수 있습니다. 이미지 파일만 업로드 할 수 있습니다.  
댓글은 AJAX를 이용하여 구현했기 때문에 페이지 이동 없이 CRUD 동작을 수행할 수 있습니다.

### PT 온라인 수업
[PT 온라인 수업](https://hellopt.info/classlist)

화상 통화를 이용하여 온라인으로 PT 수업을 할 수 있는 기능입니다.  
화상 통화는 [WebRTC](https://webrtc.org/)를 이용하여 구현했습니다.

#### 일대다 PT 온라인 수업

트레이너가 방송하기로 방송을 시작하면 수강생이 방송보기로 방송을 보면서 채팅(로그인 시 가능)을 할 수 있습니다.

[일대다 PT 수업 - 방송하기](https://hellopt.info/broadcaster?classIdx=182)

[일대다 PT 수업 - 방송보기](https://hellopt.info/viewer?classIdx=182)

[일대다 PT 수업 - 방송하기 source code](https://github.com/bbangaro/hellopt/blob/master/src/main/webapp/resources/js/live/broadcaster.js)

[일대다 PT 수업 - 방송보기 source code](https://github.com/bbangaro/hellopt/blob/master/src/main/webapp/resources/js/live/viewer.js)

#### 다대다 PT 온라인 수업

각자가 방송을 시작해서 다중 화상 통화 방식으로 서로 운동하는 모습을 볼 수 있습니다.

[다대다 PT 수업](https://hellopt.info/multi?classIdx=146)

※로그인 후 HTTPS 연결로 접속해주세요.

[다대다 온라인 수업 데모 영상](https://youtu.be/Rb0j7aHKvl8)

[다대다 온라인 수업 클라이언트측 source code](https://github.com/bbangaro/hellopt/blob/master/src/main/webapp/resources/js/live/multi.js)


#### 시그널링 서버

[signaling server GitHub](https://github.com/DanHoBakMaCha/public_hellopt_live/blob/master/signal.js)

WebRTC는 P2P 기반의 통신이기 때문에 사용자간의 연결 정보를 연결해주는 시그널링 서버가 필요합니다.  
signaling server를 통해 사용자간의 [SDP](https://tools.ietf.org/html/rfc2327)를 주고 받아서 RTCPeerConnection을 설정합니다.

#### 어려웠던 점

WebRTC를 이용해서 스트리밍을 구현한 후 실제로 배포를 해보니 로컬호스트 환경과는 다르게 사용자의 카메라, 오디오 미디어를 얻게 해주는 getUserMedia() 함수가 secure context에서만 동작한다는 사실을 알게 되었습니다.

getUserMedia() 함수를 사용하기 위해서는 https가 필요하다는 것을 알게되었고 AWS Route 53에서 도메인을 구입한 후 AWS Certificate Manager를 이용하여 Amazon에서 발급해주는 인증서를 통해 https 통신이 가능하도록 하여 문제를 해결했습니다.

다대다 스트리밍 경우 P2P 연결로 인해 각각의 유저가 다른 모든 유저와 RTCPeerConnection을 가지고 있어야합니다. 다른 유저와 통신 요청을 할 때 이미 연결된 유저에게는 통신 요청을 하지 않도록 socket.id마다 RTCPeerConnction을 할당하여 불필요하게 이미 연결된 유저와 또 RTCPeerConnection을 생성하지 않도록 했습니다.

### FAQ 게시판
[FAQ 게시판](https://hellopt.info/faq1)

HelloPT에 궁금하실 수 있는 몇가지 질문에 대답해드리는 게시판입니다.

### 오프라인 모임 게시판
[오프라인 모임 게시판](https://hellopt.info/meeting)

### 일일 운동 체크
[일일 운동 체크](https://hellopt.info/calender)






