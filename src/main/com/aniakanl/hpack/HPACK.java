package com.aniakanl.hpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * HPACK is an implementation of rfc 7541 (HPACK: Header Compression for HTTP/2)
 */
public class HPACK {

	/**
	 * It decodes header byte stream and returns the ordered list of the
	 * extracted headers
	 * 
	 * @param headerBuffer
	 *            the byte array that represents the headers
	 * @return a Queue of HTTP2Header objects if successful, otherwise it
	 *         returns null
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 * @exception it
	 *                throws HTTP2Exception if something is not correct
	 *                accroding to rfc 7541
	 */
	public static ArrayList<HTTP2HeaderField> decode(byte[] headerBuffer) throws FileNotFoundException, IOException, URISyntaxException {
		ArrayList<HTTP2HeaderField> result = null;

		if (headerBuffer != null) {
			
			result = new ArrayList<HTTP2HeaderField>();
			int index = 0;

			while (index < headerBuffer.length) {

				
				if ((byte) (headerBuffer[index] & 0x80) != 0) {
					// if the first bit is 1, Indexed Header Field Representation is used
					

				} else if ((headerBuffer[index] >> 6) == 1) {
					// if the value of the left two most bits is 01, Literal
					// Header Field with Incremental Indexing is used
					
					byte headerIndex = (byte) (headerBuffer[index] & 0x3F);
					index += 1;
					boolean huffmanCode = (headerBuffer[index] & 0x80) != 0;
					byte length = (byte)(headerBuffer[index] & 0x7F);
					index += 1;
					
					byte[] headerValueArr = Arrays.copyOfRange(headerBuffer, index, index + length);
					
					String headerValue;
					
					
					if(huffmanCode)
					{
						headerValue = Huffman.decode(headerValueArr);
						
					}
					else
					{
						headerValue = new String(headerValueArr);
					}
					
					
					System.out.println(headerValue);
					
					

					
				} else if ((headerBuffer[index] >> 4) == 0) {
					// if the value of the left four most bits is 0000, Literal
					// Header Field without Indexing is used
				} else if ((headerBuffer[index] >> 4) == 1) {

				}
			}
		}

		return result;
	}

}
