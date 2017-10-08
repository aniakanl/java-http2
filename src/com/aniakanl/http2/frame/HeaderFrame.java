package com.aniakanl.http2.frame;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;

public class HeaderFrame extends BaseFrame {

	public HeaderFrame(FrameHeader header) {
		super(header);
		// TODO Auto-generated constructor stub
	}

	public static HeaderFrame parse(byte[] frameBody, FrameHeader header) throws HTTP2Exception {
		HeaderFrame result = null;

		if (frameBody != null) {

			// rfc7540 (section 4.2) A frame size error in a frame that could
			// alter the state of
			// the entire connection MUST be treated as a connection error
			// (Section 5.4.1);
			if (header.getLength() == frameBody.length) {

				result = new HeaderFrame(header);

				int paramIndex = 0;

				//TODO implement the logic to parse the frame body
			} else {
				throw new HTTP2Exception(HTTP2ErrorCode.FRAME_SIZE_ERROR);
			}

		}
		return result;
	}

	@Override
	public byte[] convertToBinary() {
		// TODO Auto-generated method stub
		return null;
	}

}
