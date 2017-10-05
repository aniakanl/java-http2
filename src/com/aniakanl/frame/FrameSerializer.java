package com.aniakanl.frame;

import java.io.IOException;

import com.aniakanl.client.ExBufferedInputStream;

public class FrameSerializer {

	public BaseFrame deSerialize(ExBufferedInputStream bufferedInputStream) {

		BaseFrame baseFrame = null;
		byte[] tmpBuffer = new byte[9];

		try {
			bufferedInputStream.read(tmpBuffer, 0, 9);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FrameHeader frameHeader = FrameHeader.Parse(tmpBuffer);

		switch (frameHeader.getType()) {
		case HEADERS: baseFrame = new HeaderFrame();
			break;
		case CONTINUATION: baseFrame = new ContinuationFrame();
			break;
		case DATA: baseFrame = new DataFrame();
			break;
		case GOAWAY: baseFrame = new GoawayFrame();
			break;
		case PING: baseFrame = new PingFrame();
			break;
		case PRIORITY: baseFrame = new PriorityFrame();
			break;
		case PUSH_PROMISE: baseFrame = new PushPromiseFrame();
			break;
		case RST_STREAM: baseFrame = new RSTStreamFrame();
			break;
		case SETTINGS: baseFrame = new SettingsFrame();
			break;
		case WINDOW_UPDATE: baseFrame = new WindowUpdateFrame();
			break;
		default:
			break;

		}

		return baseFrame;
	}

}
