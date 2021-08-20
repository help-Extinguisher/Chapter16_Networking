package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {	
		

		ServerSocket server = new ServerSocket(8520);
		System.out.println("서버 준비 완료");
		
		Socket socket = server.accept(); // 이거 날리면 대기중인거임
		System.out.println("클라이언트 연결 완료");
		
		// 클라이언트 IP 주소
		System.out.println(socket.getInetAddress());
		
		
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		dis.close();
		in.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버 종료");
	}

}
