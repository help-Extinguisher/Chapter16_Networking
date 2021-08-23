package chatting_programming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.framework.TcpApplication;

public class AppServer extends TcpApplication {

	/*
	 * ���ø����̼� �ʱ�ȭ
	 */
	
	@Override
	public void init() {
		super.init();
	}
	
	/*
	 * ���ø����̼� ����
	 */
	
	@Override
	public void start() {
		
		System.out.println(TcpApplication.timeStamp());
		System.out.println("TCP/IP - ���� ���α׷��� �����մϴ�.");
		System.out.println("SERVER START >");
		
		ServerSocket server = null;
		Socket cSocket = null;
		
		try {
			
			// 1. ���� ���� ����
			server = new ServerSocket(TcpApplication.PORT);
			
			// 2. Ŭ���̾�Ʈ�� ����� ���ϰ���
			while(true) {
				cSocket = server.accept();
				System.out.println(TcpApplication.timeStamp());
				System.out.println("Ŭ���̾�Ʈ ���� �����...");
				
				cSocket = server.accept();
				
				/*
				 * ������ Ŭ���̾�Ʈ �ۼ����� ����� �����带 �����Ͽ� ����(cSocket)�� ����
				 * ������ �� ��ŭ �����尡 �����ȴ�.
				 */
				
				Thread cThread = new Thread(new TcpServerHandler(cSocket));
				cThread.start();
				
				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
				
	}

}
