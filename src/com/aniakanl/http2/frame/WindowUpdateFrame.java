package com.aniakanl.http2.frame;

import java.util.Arrays;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;
import com.aniakanl.utils.Utils;

public class WindowUpdateFrame extends BaseFrame {
	
	int windowsSizeIncreament;
	

	public WindowUpdateFrame(FrameHeader header) {
		super(header);
	}
	
	public int getWindowSizeIncreament()
	{
		return windowsSizeIncreament;
	}


	public static WindowUpdateFrame parse(byte[] frameBody, FrameHeader header) throws HTTP2Exception {
		WindowUpdateFrame result = null;

		if (frameBody != null) {
			
			// rfc7540 (section 4.2) A frame size error in a frame that could alter the state of
			// the entire connection MUST be treated as a connection error (Section 5.4.1); 
			if (header.getLength() == frameBody.length && frameBody.length == 4) {
				try {
					result = new WindowUpdateFrame(header);
					result.windowsSizeIncreament = Utils.convertToInt(frameBody, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new HTTP2Exception(HTTP2ErrorCode.INTERNAL_ERROR);
				}
			}
			else
			{
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
