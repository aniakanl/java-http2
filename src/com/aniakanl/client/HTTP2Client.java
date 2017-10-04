package com.aniakanl.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

public class HTTP2Client {
	
	static String requestTmp = "GET / HTTP/1.1\r\n" + "Host: %s\r\n"
			+ "Connection: Upgrade, HTTP2-Settings\r\n" + "Upgrade: h2c\r\n"
			+ "HTTP2-Settings:AAMAAABkAARAAAAAAAIAAAAA\r\n\r\n";

	
	void downloadWebPage(URL target) throws UnknownHostException, IOException {
		
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
		byte[] result = bufferedInputStream.read("\r\n\r\n".getBytes(), true);
		
		if(result != null)
		{
			String msgResponse = new String(result, 0, result.length);
			
			if(msgResponse.startsWith("HTTP/1.1 101"))
			{
			
			}
			System.out.print(msgResponse);
		}
		
		

		clientSocket.close();

	}


}
