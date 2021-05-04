package netwok.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 7000;
	
	public static void main(String[] args) {
	
		ServerSocket serverSocket = null; 
		
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(Binding)
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("starts... [port:" + PORT + "]");
			
			// 3. accept
			Socket socket = serverSocket.accept();
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			log("connected by client[" + remoteHostAddress + ":" + remoteHostPort +"]");
			
			try {
				// 4. IO Stream 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
				
				while(true) {
					// 4. 데이터 읽기
					String data = br.readLine();
					if(data == null) {
						log("closed by client");
						break;
					}
					
					log("received:" + data);
				
					// 5. 데이터 쓰기
					pw.println(data);
				}
			} catch(SocketException e) {
				log("suddenly closed by client");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void log(String log) {
		System.out.println("[EchoServer] " + log);
	}
}
