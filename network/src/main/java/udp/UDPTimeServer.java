package udp;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {

	public static void main(String[] args) {
		//2. 데이터 수신
		DatagramPacket receivePacket = new DatagramPacket(new byte[0], 0);
		socket.receive(receivePacket); //blocking
		
		//3. 데이터 송신
		String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		
	}

}
