package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {

	// 이슈1. 뭔가 메시지를 주고 받고 싶어요!
	// 이슈2. 하기전에 먼저 GUI를 만들도록 하겠습니다.
	
	ServerSocket serverSocket;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	
	public void setting() {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+"에서 접속했습니다.");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			String msg = in.readUTF();
			System.out.println("클라이언트로의 메시지 : " + msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerBackground serverBackground = new ServerBackground();
		serverBackground.setting();
	}
	
	
	
}
