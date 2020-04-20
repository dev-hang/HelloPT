"use strict";

// 자기자신 카메라
const localVideo = document.getElementById("localVideo");
// 다른 사람 카메라
const remoteVideos = document.getElementsByClassName("remoteVideo");

// 자기자신 미디어 스트림
let localStream = new MediaStream();

// 다른 사람의 socket.id를 key로 사용해서
// 소켓마다 RTCPeerConnection과 MediaStream을 할당
let connections = {};

// 로컬 미디어 설정
const localMediaConstraints = {
  video: true,
  audio: true
};

// 방의 고유 번호
const uniqueToken = document.getElementById("uniqueToken");

// 방 ID
let roomId;
// 유저 ID
let userId;
// 방에 입장한 유저의 socket.id와 userId
let users;

const socket = io.connect("//hellopt-signal.herokuapp.com", {secure: true});

/*const startButton = document.getElementById("startButton");*/
const callButton = document.getElementById("callButton");

//startButton.addEventListener("click", startAction);
callButton.addEventListener("click", callAction);

// 사용자의 카메라, 마이크 미디어 얻기
function startAction() {
  /*startButton.disabled = true;*/
  callButton.disabled = false;
  navigator.mediaDevices
    .getUserMedia(localMediaConstraints)
    .then(setLocalMediaStream)
    .catch(error => {
      console.log("Error getLocalMedia :", error);
    });
}

function callAction() {
  callButton.disabled = true;
  // 다른 사람과 WebRTC 연결 요청
  makeCall();
  // 다른 유저에게서 얻은 Stream을 카메라에 할당
  setRemoteVidoes();
}

function hangupAction() {}

function makeCall() {
  // 자기 자신을 제외한 나머지 유저의 socket.id 획득
  let otherSockets = getOtherSockets();
  
  console.log("otherSockets: " + otherSockets);
  
  // connections : JSON 객체
  // 다른 유저의 socket.id를 key로 사용해서 socket.id마다
  // RTCPeerConnection과 MediaStream을 할당
  for (const otherSocket of otherSockets) {
    connections[otherSocket] = {};
    connections[otherSocket]["peer"] = new RTCPeerConnection();
    connections[otherSocket]["stream"] = new MediaStream();

    const peer = connections[otherSocket]["peer"];

    // 다른 유저에게 전송할 자기자신의 미디어 스트림을 PeerConnection에 추가
    for (const track of localStream.getTracks()) {
      peer.addTrack(track);
    }

    // offer를 생성해서 localDescription에 할당하고
    // 자기 자신을 제외한 방에 입장한 모든 유저에게 
    // offer를 송신
    peer
      .createOffer()
      .then(offer => {
        peer.setLocalDescription(offer);
        console.log("create offer: set localDescription");
        socket.emit("multioffer", {
          offer: offer,
          room: roomId,
          to: otherSocket,
          from: socket.id
        });
        console.log(`send offer to user[${otherSocket}] in room[${roomId}].`);
      })
      .catch(error => {
        console.log("Error creating offer :", error);
      });

      // icecandidate 이벤트 처리
      peer.addEventListener("icecandidate", handleConnection);
      // connction 상태가 변경될 때마다 로그 출력
      peer.addEventListener(
        "connectionstatechange",
        handleConnectionChange
      );
      // 다른 유저의 Stream 정보를 획득
      peer.addEventListener("track", event => {
        console.log("get remote Stream");
        connections[otherSocket]["stream"].addTrack(event.track);
      });
  }
}

// 로컬 비디오에 스트림 할당
function setLocalMediaStream(stream) {
  localStream = stream;
  localVideo.srcObject = localStream;

  console.log("setLocalMedia success");
}

// 다른 유저에게 icecandidate 정보 송신
function handleConnection(event) {
  const candidate = event.candidate;
  socket.emit("icecandidate", {
	candidate: candidate,
    to: roomId,
    from: socket.id
  });
  console.log("sent candidate to signaling server");
}

// 연결 상태 로그 출력
function handleConnectionChange(event) {
  console.log("handleConnectionChange: ", event.target);
  if (event.target.connectionState === "connected") {
    console.log("peers connected!");
  }
}

// 무작위 고유 방ID 생성 -> classIdx로 대체
function setRoomToken() {
  const hashValue = (Math.random() * new Date().getTime())
    .toString(32)
    .toUpperCase()
    .replace(/\./g, "-");

  if (location.hash.length > 2) {
    uniqueToken.setAttribute("href", location.href);
  } else {
    location.hash = "#" + hashValue;
  }
}

// PeerConnection의 다른 유저에게서 얻은 Stream을 비디오에 할당
function setRemoteVidoes() {
  const streams = [];
  for(const key in connections) {
    streams.push(connections[key]["stream"]);
  }
  for(let i = 0; i < streams.length; i++) {
    remoteVideos[i].srcObject = streams[i];
  }
}

// iceServer 설정
// STUN 서버 설정
const configuration = {
  iceServers: [{ urls: "stun:stun.l.google.com:19302" }]
};

// 사용자를 제외한 나머지 유저의 소켓ID를 획득
function getOtherSockets() {
  const sockets = [];
  for (const key in users) {
    if (key !== socket.id) {
      sockets.push(key);
    }
  }
  return sockets;
}

function initialize() {
  //roomId = 65;
  userId = $("#userid").val();
  roomId = $("#roomId").val();

  callButton.disabled = true;
  socketProcess();
}

function socketProcess() {
  // 방 입장
  socket.emit("joinroom", {roomId: roomId, userId: userId});

  // 임의의 유저가 방에 입장 시 방에 있는 유저 정보를 수신
  socket.on("join", rooms => {
    users = rooms;
    console.log("join user: ", rooms);
  });

  // 임의의 유저가 방에서 퇴장 시 퇴장한 유저 정보를 수신
  socket.on("leave", userSocket => {
    console.log(`a user[${userSocket}] leaved.`);
    delete users[userSocket];
    console.log(`left users[${JSON.stringify(users)}]`);
  });

  // offer sdp를 수신
  // offer를 보낸 사람의 socket.id로 PeerConnection을 찾아서
  // RemoteDescription을 설정하고
  // 보낸 사람에게 answer를 송신
  socket.on("multioffer", data => {
    console.log("received multioffer:", data);

    if (data.to === socket.id) {
      connections[data.from] = {};
      connections[data.from]["peer"] = new RTCPeerConnection();
      connections[data.from]["stream"] = new MediaStream();

      const peer = connections[data.from]["peer"];

    for (const track of localStream.getTracks()) {
      peer.addTrack(track);
    }

    peer.addEventListener("icecandidate", handleConnection);
      peer.addEventListener(
        "connectionstatechange",
        handleConnectionChange
      );
      peer.addEventListener("track", event => {
        console.log("get remote Stream");
        connections[data.from]["stream"].addTrack(event.track);
      });
    peer
      .setRemoteDescription(new RTCSessionDescription(data.offer))
      .then(console.log("received offer: set remoteDescription success."))
      .catch(error => {
        console.log("received offer: set remoteDescription failure.", error);
      });
    peer
      .createAnswer()
      .then(answer => {
    	console.log("나와나와 " + answer);  
        peer.setLocalDescription(answer);
        console.log("create answer: set localDescription");

        socket.emit("multianswer", {
          answer: answer,
          room: roomId,
          to: data.from,
          from: socket.id
        });
      })
      .catch(error => {
        console.log("Error creating answer:", error);
      });
      setRemoteVidoes();
    } // end if
  });

  // answer를 수신
  // answer를 보낸 사람의 socket.id로 PeerConnection을 찾아서
  // RemoteDescription을 설정
  socket.on("multianswer", data => {
    console.log("received answer from", data.from);
    if (data.to === socket.id) {
      const peer = connections[data.from]["peer"];
      peer.setRemoteDescription(new RTCSessionDescription(data.answer));
      console.log("answer: set remoteDescription");
      
      console.log("peer : " + peer);
    }
  });

  // candidate 정보를 보낸 사람의 socket.id를 사용해서
  // PeerConnection을 찾아서 candidate 정보 추가
  socket.on("icecandidate", data => {
    if (connections[data.from]["peer"]) {
      console.log("received icecandidte from ", data.from);
      const peer = connections[data.from]["peer"];
      console.log("peer : " + peer);
      console.log("connections[data.from]['peer'] : " + connections[data.from]["peer"]);
      try {
        peer.addIceCandidate(data.candidate);
        console.log("received new candidate");
      } catch (error) {
        console.log("Error add received ice candidate :", error);
      }
    }
  });
}

initialize();
startAction();
