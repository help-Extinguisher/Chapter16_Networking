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
//	public static final String IP = "127.0.0.0"; // �� �̷� ������ �ּҶ� �Ȱ�������
//	public static final String IP = "192.168.0.134";
	public static final String IP = "localhost";
	public static final int PORT = 8520;
	
	public static void main(String[] args) {
		
		// ����� ��Ʈ�� - ��������� �̸��� in, out�̶� ��
		InputStream in = null; // �ʱ�ȭ����
		OutputStream out = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		
		FileOutputStream fos = null;
		Scanner scan = null;
		
		// ���� ����
		Socket socket =  null;	
		
		
		try {
			
			socket = new Socket(IP, PORT);
			scan = new Scanner(System.in);
			
			
			/*
			 * ���Ű� �۽� (FTPServer�� ������ ����)
			 */
			
			in = socket.getInputStream();
			din = new DataInputStream(in);
			
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			
			menuDisplay();
			String fileName = scan.nextLine();
			
			
			// ������ ����
			dout.writeUTF(fileName); // ���ڴϱ� UTF�� ����
			System.out.println(timeStamp() + "���� ������ ��û.");
			System.out.println();
			
			// ���� ������ ����
			System.out.println("���� ������...");
			System.out.println();
			
			fos = new FileOutputStream("C:/Temp/" + fileName);
			
			
			int cnt = 0;
			while(true) {
				// �б�
				int data = din.read();
				if(data == -1) break;
				
				// ����
				fos.write(data);
				if (cnt % 2000 == 0) { // �ε� ǥ��? �ϱ� 
					System.out.print("��");					
				}
				cnt++;
			}
			System.out.println();
			System.out.println(timeStamp() + "���� ������ �Ϸ��Ͽ����ϴ�." +
												" (" + cnt + "byte)");
			
			System.out.println();
			System.out.println(timeStamp() + "���� �ٿ�ε� �Ϸ�.");
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
		
		System.out.println(timeStamp() + "Ŭ���̾�Ʈ ���� ��û ���α׷��� �����մϴ�.");
		
	}

	private static void menuDisplay() {
		System.out.println();
		System.out.println("�¦���������������������������������������������������������������������������������������������������");
		System.out.println();
		System.out.println("	 �ް� ���� ������ �̸��� �Է��ϼ���.");
		System.out.println("		 > (���ϸ�, Ȯ����) ���� ");
		System.out.println();
		System.out.println(	"	[1]aa.txt		[2]image.jpg"	);
		System.out.println("�¦���������������������������������������������������������������������������������������������������");
		System.out.println();
		System.out.print("	����? >	");
		
	}

	/*
	 * ���� �ð��� �������ִ� �޼ҵ�.
	 * ��ȯŸ�� : String
	 */
	private static String timeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
		return format.format(new Date()); // ???? �ְ��ڱ� �̰Գ����� �����ʿ�
	}
	
}
