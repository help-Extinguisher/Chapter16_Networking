package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FTPClient {
//	public static final String IP = "127.0.0.0"; // 뭐 이런 아이피 주소랑 똑같은거임
//	public static final String IP = "192.168.0.134";
	public static final String IP = "localhost";
	public static final int PORT = 8520;
	
	public static void main(String[] args) {
		
		// 입출력 스트림 - 통상적으로 이름을 in, out이라 함
		InputStream in = null; // 초기화햇음
		OutputStream out = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		
		FileOutputStream fos = null;
		Scanner scan = null;
		
		// 소켓 선언
		Socket socket =  null;	
		
		
		try {
			
			socket = new Socket(IP, PORT);
			scan = new Scanner(System.in);
			
			
			/*
			 * 수신과 송신 (FTPServer랑 구조는 같음)
			 */
			
			in = socket.getInputStream();
			din = new DataInputStream(in);
			
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			
			menuDisplay();
			String fileName = scan.nextLine();
			
			
			// 서버로 전송
			dout.writeUTF(fileName); // 문자니까 UTF이 붙음
			System.out.println(timeStamp() + "파일 서버에 요청.");
			System.out.println();
			
			// 파일 데이터 수신
			System.out.println("파일 수신중...");
			System.out.println();
			
			fos = new FileOutputStream("C:/Temp/" + fileName);
			
			
			int cnt = 0;
			while(true) {
				// 읽기
				int data = din.read();
				if(data == -1) break;
				
				// 쓰기
				fos.write(data);
				if (cnt % 2000 == 0) { // 로딩 표시? 하기 
					System.out.print("●");					
				}
				cnt++;
			}
			System.out.println();
			System.out.println(timeStamp() + "파일 수신을 완료하였습니다." +
												" (" + cnt + "byte)");
			
			System.out.println();
			System.out.println(timeStamp() + "파일 다운로드 완료.");
			System.out.println();			
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(din != null) {din.close();}
				if(dout != null) {dout.close();}
				if(fos != null) {fos.close();}
				if(socket != null) {socket.close();}
				
			} catch (IOException ex) { ex.printStackTrace(); }
		}
		
		System.out.println(timeStamp() + "클라이언트 파일 요청 프로그램을 종료합니다.");
		
	}

	private static void menuDisplay() {
		System.out.println();
		System.out.println("◈─────────────────────────────────────────────────◈");
		System.out.println();
		System.out.println("	 받고 싶은 파일의 이름을 입력하세요.");
		System.out.println("		 > (파일명, 확장자) 형식 ");
		System.out.println();
		System.out.println(	"	[1]aa.txt		[2]image.jpg"	);
		System.out.println("◈─────────────────────────────────────────────────◈");
		System.out.println();
		System.out.print("	선택? >	");
		
	}

	/*
	 * 현재 시간을 리턴해주는 메소드.
	 * 반환타입 : String
	 */
	private static String timeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
		return format.format(new Date()); // ???? 왜갑자기 이게나오지 이해필요
	}
	
}
