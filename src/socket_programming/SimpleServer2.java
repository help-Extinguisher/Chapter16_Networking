package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
	public static void main(String[] args) throws IOException {
			
		// ���ڿ� �����ϱ�
		
		String str = "��¼����¼�� ���ڿ� �����ϱ�~~";
		
		
		// �� Ŭ���̾�Ʈ ��¼����
		ServerSocket server = new ServerSocket(8520);
		System.out.println("���� �غ� �Ϸ�");
		
		Socket socket = server.accept(); // �̰� ������ ������ΰ���
		System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�");
		System.out.println(socket.getInetAddress());
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		byte[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		
		dos.writeUTF(str);
		dos.flush();
		dos.close();
		
		socket.close();
		server.close();
		
		System.out.println("���� ����");
	}

}
