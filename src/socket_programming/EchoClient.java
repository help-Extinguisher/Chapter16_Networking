package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// �׻������ �����ǿ� ��Ʈ��ȣ�� �����ش�
		Socket socket = new Socket("localhost", 8111);
		System.out.println("���� ���� �Ϸ�!");
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ������ �޽����� �Է����ּ���.");
		
		String msg = sc.nextLine();
		dos.writeUTF(msg);
		
		
		
//		InputStream in = socket.getInputStream();
//		DataInputStream dis = new DataInputStream(in);
	
		
		
		dos.close();
		sc.close();
		socket.close();
		
		System.out.println("Ŭ���̾�Ʈ ����!");
		
	}

}
