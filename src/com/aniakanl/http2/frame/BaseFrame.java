package com.aniakanl.http2.frame;

public abstract class BaseFrame {
	
	private FrameHeader header;
	
	public FrameHeader getHeader()
	{
		return header;
	}

}
