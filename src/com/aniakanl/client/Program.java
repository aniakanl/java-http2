package com.aniakanl.client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;

public class Program {


	public static void main(String args[]) {



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
