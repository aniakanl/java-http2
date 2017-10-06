package com.aniakanl.http2;

import java.io.IOException;
import java.util.Arrays;

import com.aniakanl.client.ExBufferedInputStream;
import com.aniakanl.http2.frame.BaseFrame;
import com.aniakanl.http2.frame.FrameSerializer;
import com.aniakanl.http2.frame.FrameType;

public class HTTP2Connection {

	ExBufferedInputStream inputStream;
	
	static final String PREFACE = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n";
	
	public HTTP2Connection(ExBufferedInputStream input) {
		
		inputStream = input;
	}
	
	public boolean hasProperPreface() throws IOException
	{
		boolean result = false;
	
		byte[] preface = inputStream.readFully(24);
		
		String prefaceStr = new String(preface, 0, preface.length);
		
		if(prefaceStr.equals(PREFACE))
		{
			result = true;
		}
		
		return result;
	}
	
	public void handle() throws Exception
	{
		BaseFrame frame = null;
		
		// main HTTP2
		while( (frame = FrameSerializer.deserialize(inputStream)) != null)
		{
			switch(frame.getHeader().getType())
			{
			case SETTINGS:
				// Save setting
				// Send back the settings with set ack flag
				break;
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
				break;
			default:
				break;
			}
		}
		
		
		
	}
	
	
	

	

}
