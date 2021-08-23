package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient2 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// 그사람쪽의 아이피와 포트번호를 적어준다
		Socket socket = new Socket("192.168.0.253", 8111);
		System.out.println("서버 연결 완료!");
		
		
		// 데이터 전송
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		// 데이터 수신
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
			System.out.print("> ");
			String sendmsg = sc.nextLine();
			
			dos.writeUTF(sendmsg);
			dos.flush();
			
			String readMsg = dis.readUTF();
			System.out.println("서버응답 :  " + readMsg);
			
			if(sendmsg.equalsIgnoreCase("exit")) {
				break;
			}
		}

		
		
		dos.close();
		sc.close();
		socket.close();
		
		System.out.println("클라이언트 종료!");
		
	}

}
