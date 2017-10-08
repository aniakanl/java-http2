package com.aniakanl.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Program {

	public static void main(String args[]) throws Exception {
		
		try {
			if (args.length == 1) {
				HTTP2Client client = new HTTP2Client();
				client.downloadWebPage(new URL(args[0]));
			}
		} catch (MalformedURLException exp) {
			// TODO log appropriately
		} catch (IOException e) {
			// TODO log appropriately
		} 
	}

}
