package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
	public static void main(String[] args) throws IOException {
			
		// 문자열 전송하기
		
		String str = "어쩌구저쩌구 문자열 전송하기~~";
		
		
		// 내 클라이언트 어쩌구임
		ServerSocket server = new ServerSocket(8520);
		System.out.println("서버 준비 완료");
		
		Socket socket = server.accept(); // 이거 날리면 대기중인거임
		System.out.println("클라이언트 연결 완료");
		System.out.println(socket.getInetAddress());
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		byte[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		
		dos.writeUTF(str);
		dos.flush();
		dos.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버 종료");
	}

}
