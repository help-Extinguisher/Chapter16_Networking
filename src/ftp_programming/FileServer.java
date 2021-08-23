package ftp_programming;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	public static final int PORT = 8520;
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("클라이언트 접속 대기중...");
		
		Socket client = server.accept();
		System.out.println(client.getInetAddress().getHostName() + "사용자 아이디"
				+ "님이 접속하셨습니다.");
		
		OutputStream out = client.getOutputStream();
		OutputStreamWriter outw = new OutputStreamWriter(out);
		PrintWriter pw = new PrintWriter(outw);
		
		FileInputStream fin = null;  // 읽음
		DataOutputStream dout = new DataOutputStream(out); // 뿌림
		
		
		/*
		 * 리소스 자동 배포
		 */
		
		File rescs = new File("resources\\");
		System.out.println(rescs.list().length + "개의 파일이 있습니다.");  // 파일의 갯수를 알수있다
		
		String filePath = null;
		for(String file : rescs.list()) {
			// 파일 정보(이름, 크기) 얻기
			filePath = rescs.getName()+"\\" + file; // 바이너리로 읽어들일때 사
			File sendFile = new File(filePath);
			System.out.println(sendFile.getName() + "송신중...");
			
			
			// 파일 송신
			pw.println(sendFile);
			pw.flush();
			pw.println(sendFile.length());
			pw.flush();
			
			fin = new FileInputStream(filePath);
			int cnt = 0;
			for(int i=0; i<sendFile.length(); i++) {
				// 파일 읽기
				int data = fin.read();
				// 파일 쓰기
				dout.write(data);
				
				if(cnt % 3000 == 0) {System.out.print("■");}
				cnt++;
				
			}
			System.out.println("완료 \n");
		
		}
		
		fin.close();
		dout.close();
		pw.close();
		client.close();
		server.close();
		
		System.out.println();
		System.out.println("파일 배포 서비스 프로그램 종료.");
	}	
	
}
