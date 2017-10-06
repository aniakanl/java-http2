package com.aniakanl.http2.frame;

import java.io.IOException;
import java.io.InputStream;

import com.aniakanl.client.ExBufferedInputStream;

public class FrameSerializer {

	public static BaseFrame deserialize(ExBufferedInputStream bufferedInputStream) throws Exception {

		BaseFrame baseFrame = null;
		byte[] tmpBuffer = new byte[9];

		tmpBuffer = bufferedInputStream.readFully(9);

		FrameHeader frameHeader = FrameHeader.Parse(tmpBuffer);
		
		byte[] body = bufferedInputStream.readFully(frameHeader.getLength());

		switch (frameHeader.getType()) {
		case HEADERS:
			baseFrame = new HeaderFrame();
			break;
		case CONTINUATION:
			baseFrame = new ContinuationFrame();
			break;
		case DATA:
			baseFrame = new DataFrame();
			break;
		case GOAWAY:
			baseFrame = new GoawayFrame();
			break;
		case PING:
			baseFrame = new PingFrame();
			break;
		case PRIORITY:
			baseFrame = new PriorityFrame();
			break;
		case PUSH_PROMISE:
			baseFrame = new PushPromiseFrame();
			break;
		case RST_STREAM:
			baseFrame = new RSTStreamFrame();
			break;
		case SETTINGS:
			baseFrame = SettingsFrame.parse(body);
			break;
		case WINDOW_UPDATE:
			baseFrame = new WindowUpdateFrame();
			break;
		default:
			break;

		}

		return baseFrame;
	}

}
