package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENTROOT = "./webapp";
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				// 브라우저가 연결을 끊으면,
				if(line == null) {
					break;
				}
				
				// Request Header만 읽음
				if("".equals(line)) {
					break;
				}
				
				// 첫 번째 라인만 처리
				if(request == null) {
					request = line;
					break;
				}
			}
			
			// 요청 처리
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				consoleLog("request:" + tokens[1]);
				responseStaticResource(os, tokens[1], tokens[2]);
			} else { 
				// methods: POST, PUT, DELETE, HEAD, CONNECT
				// Simple Http Server 에서는 무시
				response400Error(os, tokens[1], tokens[2]);
			}
		} catch(Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
			} catch( IOException ex ) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	private void responseStaticResource(
		OutputStream os,
		String url,
		String protocol) throws IOException {
		
		// welcome file set
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENTROOT + url);
		if(file.exists() == false) {
			response404Error(os, url, protocol);
			return;
		}
		
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes( "UTF-8" ));
		os.write("\r\n".getBytes() );
		os.write(body);
	}

	private void response400Error(
		OutputStream os,
		String url,
		String protocol) throws IOException {
		File file = new File(DOCUMENTROOT + "/error/400.html");
		if(file.exists() == false) {
			response404Error(os, url, protocol);
			return;
		}
		
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 400 Bad Request\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes( "UTF-8" ));
		os.write("\r\n".getBytes() );
		os.write(body);
	}
	
	private void response404Error(
		OutputStream os,
		String url,
		String protocol)  throws IOException {

		File file = new File(DOCUMENTROOT + "/error/404.html");
		if(file.exists() == false) {
			System.out.println("file not found:" + file.getAbsolutePath());
			return;
		}
		
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		os.write((protocol + " 404 File Not Found\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes( "UTF-8" ));
		os.write("\r\n".getBytes() );
		os.write(body);
	}

	public void consoleLog( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}