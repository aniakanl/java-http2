package com.aniakanl.http2.frame;

public abstract class BaseFrame {
	
	private FrameHeader header;
	
	
	
	public BaseFrame(FrameHeader header) {
		this.header = header;
	}

	public FrameHeader getHeader()
	{
		return header;
	}
	
	public void setHeader(FrameHeader header)
	{
		this.header = header;
	}
	
	public abstract byte[] convertToBinary();

}
