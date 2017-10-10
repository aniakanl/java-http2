package com.aniakanl.hpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;

public class Huffman {

	static HashMap<String, Byte> huffmanCodes = null;

	public static HashMap<String, Byte> getHuffmanCodes()
			throws FileNotFoundException, IOException, URISyntaxException {
		if (huffmanCodes == null) {
			huffmanCodes = new HashMap<>();

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("huffman_codes_rfc7541.txt");

			try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				String line;
				String code;
				byte value = 0;
				while ((line = br.readLine()) != null) {
					code = line.substring(11, line.indexOf(' ', 11));
					code = code.replace("|", "");
					huffmanCodes.put(code, value);
					value++;
				}
			}
		}

		return huffmanCodes;

	}

	public static String decode(byte[] value) throws FileNotFoundException, IOException, URISyntaxException {
		StringBuilder result = new StringBuilder();

		HashMap<String, Byte> codes = getHuffmanCodes();

		StringBuilder code = new StringBuilder();

		for (int i = 0; i < value.length; i++) {
			int unsignedByte = value[i] & 0xff;
			for (int j = 0; j < 8; j++) {
				if ((unsignedByte & 0x00000080) != 0) {
					code.append("1");
				} else {
					code.append("0");
				}

				unsignedByte = unsignedByte << 1;

				if (codes.containsKey(code.toString()) == true) {
					result.append((char) codes.get(code.toString()).byteValue());
					code = new StringBuilder();
				}
			}
		}

		return result.toString();
	}

}
