package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {

	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	
	public void connet() {
		try {
			socket = new Socket("112.169.196.125", 7777);
			System.out.println("서버 연결됨 >.<");
		
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("안녕하세여 나는 연무니에요");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClientBackground clientBackground = new ClientBackground();
		clientBackground.connet();
	}
	
}
