package com.aniakanl.frame;

import com.aniakanl.client.ExBufferedInputStream;

/**
 * Create a frame header object
 * @param length 24-bit unsigned integer value that specifies length of the frame
 * @param type defined as an enum FrameType, it identifies the type of the frame
 * @param flag defined as an enum Flag, it identifies flags associated with a particular frame
 * @param streamIdentifier 31-bit unsigned integer uniquely identifies a frame 
 */

public class FrameHeader {
	
	private int length;
	public FrameType Type;
	private FrameFlag flag;
	private int streamIdentifier;
	
	private FrameHeader(int length, FrameType type, FrameFlag flag, int streamIdentifier){
		this.length = length;
		this.Type = type;
		this.flag = flag;
		this.streamIdentifier = streamIdentifier;
	}
	
	public static FrameHeader Parse(byte[] headerStream)
	{
		FrameHeader result = null;
		
		//result = new FrameHeader()

		
		return result;
	}

}
