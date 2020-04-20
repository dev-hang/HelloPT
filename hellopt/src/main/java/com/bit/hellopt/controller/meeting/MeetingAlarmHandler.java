package com.bit.hellopt.controller.meeting;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bit.hellopt.controller.MainController;
import com.bit.hellopt.vo.meeting.MeetingVO;

//https://bloodfinger.tistory.com/40?category=366386
@Component("meetingAlarmHandler")
public class MeetingAlarmHandler extends TextWebSocketHandler {

	// 로그인 한 전체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	// 1대 1
	//Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
	// 접속한 사용자에 대한 정보를 담을 map
	private Map<String, WebSocketSession> users = new HashMap<>();
	// 로그
	private static Logger logger = LoggerFactory.getLogger(MeetingAlarmHandler.class);
	
	Principal principal;
	
	// ※클라이언트 연결 된 후
	// WebSocketSession을 매개 변수로 받고 클라이언트가 연결된 후
	// 해당 클라이언트의 정보를 가져와 연결확인 작업을한다.
	// 클라이언트의 세션을 세션 저장 리스트에 add()로 추가 한다.
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		
		System.out.println("핸들러 페이지 소켓연결 : " + session.getId());
		
		users.put(session.getId(), session);
		
	}

	// ※클라이언트와 연결이 끊어진 경우
	// add()와 반대로 remove()를 이용해서 세션리스트에서 제거한다.
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		
		System.out.println("핸들러 페이지 소켓연결 끝 : " + session.getId());

		Map<String, Object> attrs = session.getAttributes();
		
		MeetingVO m = (MeetingVO) attrs.get("username");
		
		users.remove(m.getUserName());
	}

	// 웹 소켓 서버로 데이터를 전송했을 경우
	// 연결된 모든 클라이언트에게 메시지 전송 : 리스트
	// 연결된 모든 사용자에게 보내야 하므로 for문으로 세션리스트에 있는 모든 세션들을 돌면서
	// sendMessage()를 이용해 데이터를 주고 받는다.
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		sessions.add(session);
		System.out.println("핸들러 페이지 아이디 소켓 정보 : " + session.getId());
		System.out.println("핸들러 페이지 메시지 소켓 정보 : " + message.getPayload());
		
		// getpayload는 js에서 보낸 메시지 읽는거
		String userRole = message.getPayload();
		//String sendMsg = "";
		//String[] arr = msg.split(":");
		//sendMsg = arr[0] + " : " + arr[1] ;
		users.put(session.getId()+userRole, session);
		
		Set<String> keys = users.keySet();
		for (String key : keys) {
			WebSocketSession wSession = users.get(key);
			try {
				wSession.sendMessage(new TextMessage(MainController.mService.progressCnt() + ""));
			} catch (Exception ex) {
				synchronized (sessions) {
					sessions.remove(session);
				}
			}
			// }
			// msg = service.progressCnt("대기");
			// System.out.println(service == null ? "널" : "있음");
			// int progressCnt = service.progressCnt();

			// Aytowired는 만능이 아니다.. 잘 찾아보고 사용할 것
			// 지금은 service 메소드를 사용할 수 있는 클래스를 가져옴 (static으로 선언하면 바로 가져올 수 있음)
			// MainController.service.progressCnt();

		}

	}
}