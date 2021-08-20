package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SimpleClient2 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// �׻������ �����ǿ� ��Ʈ��ȣ�� �����ش�
		Socket socket = new Socket("192.168.0.134", 8111);
		System.out.println("���� ���� �Ϸ�!");
		
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		String message = dis.readUTF();
		System.out.println("���� �޽��� : " + message);
		
		
		in.close();
		socket.close();
		
		System.out.println("Ŭ���̾�Ʈ ����!");
		
	}

}