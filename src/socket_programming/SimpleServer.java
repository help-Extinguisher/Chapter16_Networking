package socket_programming;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args) throws IOException {
		// 내 클라이언트 어쩌구임
		ServerSocket server = new ServerSocket(8520);
		System.out.println("서버 준비 완료");
		
		Socket socket = server.accept(); // 이거 날리면 대기중인거임
		System.out.println("클라이언트 연결 완료");
		System.out.println(socket.getInetAddress());
		
		byte[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		OutputStream out = socket.getOutputStream();
		
		out.write(arr);
		out.flush();
		out.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버 종료");
	}

}
