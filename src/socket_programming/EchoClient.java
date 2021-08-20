package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// 그사람쪽의 아이피와 포트번호를 적어준다
		Socket socket = new Socket("localhost", 8111);
		System.out.println("서버 연결 완료!");
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("서버로 전송할 메시지를 입력해주세요.");
		
		String msg = sc.nextLine();
		dos.writeUTF(msg);
		
		
		
//		InputStream in = socket.getInputStream();
//		DataInputStream dis = new DataInputStream(in);
	
		
		
		dos.close();
		sc.close();
		socket.close();
		
		System.out.println("클라이언트 종료!");
		
	}

}
