package com.aniakanl.http2;

import com.aniakanl.http2.frame.BaseFrame;
import com.aniakanl.http2.frame.WindowUpdateFrame;

public class HTTP2Stream {

	private int streamIdentifier;
	
	private int windowSizeIncreament;


	public HTTP2Stream(int streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}



	public void processFrame(BaseFrame frame) {

		switch (frame.getHeader().getType()) {
		case CONTINUATION:
			break;
		case DATA:
			break;
		case GOAWAY:
			break;
		case HEADERS:
			break;
		case NOT_IMPLEMENTED:
			break;
		case PING:
			break;
		case PRIORITY:
			break;
		case PUSH_PROMISE:
			break;
		case RST_STREAM:
			break;
		case WINDOW_UPDATE:
			windowSizeIncreament = ((WindowUpdateFrame)frame).getWindowSizeIncreament();
			break;
		default:
			break;
		}
	}

}
