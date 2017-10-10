package com.aniakanl.hpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;

/**
 * HPACK is an implementation of rfc 7541 (HPACK: Header Compression for HTTP/2)
 */
public class HPACK {

	/**
	 * It decodes header byte array and returns a list of the
	 * extracted headers
	 * 
	 * @param  headerBuffer
	 *         the byte array that represents the headers
	 * @return a Queue of HTTP2Header objects if successful, otherwise it
	 *         returns null
	 * @throws HTTP2Exception if something unexpected happens during decoding
	 */
	public static ArrayList<HTTP2HeaderField> decode(byte[] headerBuffer) throws HTTP2Exception {
		ArrayList<HTTP2HeaderField> result = null;

		if (headerBuffer != null) {

			result = new ArrayList<HTTP2HeaderField>();
			Integer index = 0;

			HTTP2HeaderField tmpField = null;
			while (index < headerBuffer.length) {

				tmpField = new HTTP2HeaderField();
				if ((byte) (headerBuffer[index] & 0x80) != 0) {
					// if the first bit is 1, Indexed Header Field
					// Representation is used
					break;

				} else if ((headerBuffer[index] >> 6) == 1) {
					// if the value of the left two most bits is 01, Literal
					// Header Field with Incremental Indexing is used
					index = decodeFieldWithIncrementalIndexing(headerBuffer, index, tmpField);
					result.add(tmpField);

				} else if ((headerBuffer[index] >> 4) == 0) {
					// if the value of the left four most bits is 0000, Literal
					// Header Field without Indexing is used
					break;
				} else if ((headerBuffer[index] >> 4) == 1) {
					break;
				}
			}
		}

		return result;
	}

	public static int decodeFieldWithIncrementalIndexing(byte[] buffer, int index, HTTP2HeaderField result)
			throws HTTP2Exception {

		try {
			// reset the left most bit
			byte headerIndex = (byte) (buffer[index] & 0x3F);

			// TODO resolve field name from static table (rfc 7541)
			result.setName(String.valueOf(headerIndex));
			index += 1;

			// check the left most bit, if it is set, then huffman code is used
			boolean huffmanCode = (buffer[index] & 0x80) != 0;

			byte length = (byte) (buffer[index] & 0x7F);
			index += 1;

			byte[] headerValueArr = Arrays.copyOfRange(buffer, index, index + length);

			if (huffmanCode) {
				result.setValue(Huffman.decode(headerValueArr));
			} else {
				result.setValue(new String(headerValueArr));
			}

			index = index + length;
		} catch (FileNotFoundException exp) {
			throw new HTTP2Exception(HTTP2ErrorCode.INTERNAL_ERROR, exp.getMessage());
		} catch (IOException exp) {
			throw new HTTP2Exception(HTTP2ErrorCode.INTERNAL_ERROR, exp.getMessage());
		} catch (URISyntaxException exp) {
			throw new HTTP2Exception(HTTP2ErrorCode.INTERNAL_ERROR, exp.getMessage());
		}

		return index;
	}

}
