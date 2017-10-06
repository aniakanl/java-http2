package com.aniakanl.client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import com.aniakanl.http2.HTTP2Connection;
import com.aniakanl.http2.frame.BaseFrame;
import com.aniakanl.http2.frame.FrameHeader;
import com.aniakanl.http2.frame.FrameSerializer;
import com.aniakanl.http2.frame.FrameType;
import com.aniakanl.http2.frame.HeaderFrame;

public class HTTP2Client {

	static String requestTmp = "GET / HTTP/1.1\r\n" + "Host: %s\r\n" + "Connection: Upgrade, HTTP2-Settings\r\n"
			+ "Upgrade: h2c\r\n" + "HTTP2-Settings:AAMAAABkAARAAAAAAAIAAAAA\r\n\r\n";

	void downloadWebPage(URL target) throws Exception {

		String request = String.format(requestTmp, target.getHost());

		byte[] reqArr = request.getBytes("UTF8");

		int port = target.getPort();
		
		if (port == -1)
			port = 80;
		
		Socket clientSocket = new Socket(target.getHost(), port);
		OutputStream output = clientSocket.getOutputStream();
		BufferedOutputStream bufferOS = new BufferedOutputStream(output);
		bufferOS.write(reqArr, 0, reqArr.length);
		bufferOS.flush();
		
		InputStream input = clientSocket.getInputStream();
		ExBufferedInputStream bufferedInputStream = new ExBufferedInputStream(input);
		byte[] result = bufferedInputStream.read("\r\n\r\n".getBytes(), true); // \r\n\r\n RFC standard as end of header

		if (result != null) {
			String msgResponse = new String(result, 0, result.length);

			if (msgResponse.startsWith("HTTP/1.1 101")) {
				
				HTTP2Connection connection = new HTTP2Connection(bufferedInputStream);
				connection.handle();
				
			}
			System.out.print(msgResponse);
		}

		clientSocket.close();

	}

}
