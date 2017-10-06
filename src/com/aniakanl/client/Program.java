package com.aniakanl.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Program {

	/**
	 * 
	 * @param args
	 */
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
		} catch (Exception e) {

		}
	}

}
