/*global io*/
/** @type {RTCConfiguration} */
const config = { // eslint-disable-line no-unused-vars
  'iceServers': [{
    'urls': [
        'stun:stun.l.google.com:19302',
        'stun:stun1.l.google.com:19302',
        'stun:stun2.l.google.com:19302',
        'stun:stun.l.google.com:19302?transport=udp',
    ]
  }]
};

const socket = io.connect("//hellopt-signal.herokuapp.com", {secure: true});
const video = document.querySelector('video'); // eslint-disable-line no-unused-vars

window.onunload = window.onbeforeunload = function() {
	socket.close();
};


/*
방송하는 사람 - 카메라, 오디오 접근 필요
여러명이 보는 방송 -> 여러 peerConnection을 받아야 함
*/

//peerConnection을 담아줄 배열 생성
let peerConnections = {};

//오디오, 비디오 관련 설정
let constraints = {
	audio: true,
	video: true
};

//룸아이디로 룸에 조인하기
socket.emit('joinroom', {roomId: roomId, userId: userId});
console.log("룸에 조인");

//오디오, 비디오 접근
navigator.mediaDevices.getUserMedia(constraints)
.then(function(stream) {
	video.srcObject = stream;
	socket.emit('broadcaster', roomId);
	console.log(roomId + "번방에 broadcaster 이벤트 전달");
}).catch(function(err) {
	console.log(err);
});

socket.on('connect', function() {
	socket.emit('broadcaster', roomId);
	console.log("broadcaster 이벤트를 보내자");
})

socket.on('answer', function(id, description) {
	console.log("answer이벤트로 받은 des : " + description);
	peerConnections[id].setRemoteDescription(description);
	console.log("answer 이벤트 받음");
});

//서버에서 viewer이벤트를 보내주면 broadcaster가 offer를 생성해서 보냄
//id는 서버에서 보내는 소켓아이디
socket.on('viewer', function(id) {
	let peerConnection = new RTCPeerConnection(config);
	peerConnections[id] = peerConnection;
	peerConnection.addStream(video.srcObject);
	
	console.log("viewer 이벤트 받음");
	
	peerConnection.createOffer()
	.then(sdp => peerConnection.setLocalDescription(sdp))
	.then(function () {
		socket.emit('offer', id, peerConnection.localDescription);
	});
	
	peerConnection.onicecandidate = function(event) {
		if (event.candidate) {
			socket.emit('candidate', id, event.candidate);
			console.log("candidate 이벤트 보냄 id : " + id + " candidate : " + event.candidate);
		}
	};
});

socket.on('candidate', function(id, candidate) {
	peerConnections[id].addIceCandidate(new RTCIceCandidate(candidate));
	console.log("candidate 이벤트 받음");
	console.log("id : " + id + " , candidate : " + candidate);
});


socket.on('out', function(id) {
	peerConnections[id] && peerConnections[id].close();
	delete peerConnections[id];
	console.log("peerCnn 삭제");
})


