package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
	public static void main(String[] args) throws IOException {	
		

		ServerSocket server = new ServerSocket(8520);
		System.out.println("서버 준비 완료");
		
		Socket socket = server.accept(); // 이거 날리면 대기중인거임
		System.out.println("클라이언트 연결 완료");
		
		// 클라이언트 IP 주소
		System.out.println(socket.getInetAddress());
		
		// 읽어오는 스트림
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		// 쓰는 스트림
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		while (true) {
			String userMsg = dis.readUTF();
			System.out.println("사용자 메세지 : " + userMsg);
			
			if (userMsg.equalsIgnoreCase("exit"))
				break;
			
			// 받은 메시지를 다시 전송
			dos.writeUTF(userMsg);
			dos.flush();
			
			
		}
		
		dis.close();
		in.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버 종료");
	}

}
