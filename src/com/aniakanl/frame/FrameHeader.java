package com.aniakanl.frame;

/**
 * Create a frame header object
 * @param length 24-bit unsigned integer value that specifies length of the frame
 * @param type defined as an enum FrameType, it identifies the type of the frame
 * @param flag defined as an enum Flag, it identifies flags associated with a particular frame
 * @param streamIdentifier 31-bit unsigned integer uniquely identifies a frame 
 */

public class FrameHeader {
	
	private int length;
	private FrameType type;
	private FrameFlag flag;
	private int streamIdentifier;
	
	public FrameHeader(int length, FrameType type, FrameFlag flag, int streamIdentifier){
		this.length = length;
		this.type = type;
		this.flag = flag;
		this.streamIdentifier = streamIdentifier;
	}

}
