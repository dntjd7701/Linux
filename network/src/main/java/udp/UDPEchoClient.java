package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = UDPEchoServer.PORT;
	private static final int BUFFER_SIZE = UDPEchoServer.BUFFER_SIZE;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		Scanner scanner = null;
		
		try {
			//1. Scanner 생성
			scanner = new Scanner(System.in);
			
			//2. 소켓 생성
			socket = new DatagramSocket();
			
			while(true) {
				//3. 키보드 입력 받기
				System.out.print(">");
				String line = scanner.nextLine();
				
				if("exit".equals(line)) {
					break;
				}
				
				//4. 데이터 쓰기
				byte[] sendData = line.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(
					sendData,
					sendData.length,
					new InetSocketAddress(SERVER_IP, SERVER_PORT));
				
				socket.send(sendPacket);
				
				//7. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket); //blocking
				
				byte[] receiveData = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(receiveData, 0, length, "utf-8");

				
				//8. 콘솔 출력
				System.out.println("<" + message);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
			if(socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}
