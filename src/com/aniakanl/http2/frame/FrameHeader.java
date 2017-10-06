package com.aniakanl.http2.frame;

import com.aniakanl.utils.Utils;

/**
 * Create a frame header object
 * 
 * @param length
 *            24-bit unsigned integer value that specifies length of the frame
 * @param type
 *            defined as an enum FrameType, it identifies the type of the frame
 * @param flag
 *            defined as an enum Flag, it identifies flags associated with a
 *            particular frame
 * @param streamIdentifier
 *            31-bit unsigned integer uniquely identifies a frame
 */

public class FrameHeader {
	
	static final int HEADER_SIZE = 9;

	private int length;
	private FrameType type;
	private FrameFlag flag;
	private int streamIdentifier;

	/**
	 * @param length
	 *            24-bit unsigned integer value that specifies length of the
	 *            frame
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param type
	 *            defined as an enum FrameType, it identifies the type of the
	 *            frame
	 */
	public FrameType getType() {
		return type;
	}

	/**
	 * @param flag
	 *            defined as an enum Flag, it identifies flags associated with a
	 *            particular frame
	 */
	public FrameFlag getFlag() {
		return flag;
	}

	/**
	 * @param streamIdentifier
	 *            31-bit unsigned integer uniquely identifies a frame
	 */
	public int getStreamIdentifier() {
		return streamIdentifier;
	}

	private FrameHeader(int length, FrameType type, FrameFlag flag, int streamIdentifier) {
		this.length = length;
		this.type = type;
		this.flag = flag;
		this.streamIdentifier = streamIdentifier;
	}

	/**
	 * Parse the 9 bytes frame header to determine length, type, flags and the stream identifier
	 * @param tmpBuffer 9 bytes frame header
	 * @return
	 * @throws Exception 
	 */
	public static FrameHeader Parse(byte[] tmpBuffer) throws Exception {
		FrameHeader frameHeader = null;

		FrameType type = null;
		FrameFlag flag = null;
		int streamIdentifier = 0;
		int length = 0;
		int readIndex = 0;

		length = Utils.convertToInt(tmpBuffer, readIndex, 3);
		readIndex += 3;

		type =FrameType.getEnum(tmpBuffer[readIndex]); 
		readIndex++;

		flag = FrameFlag.getEnum( tmpBuffer[readIndex], type);
		readIndex++;

		streamIdentifier = Utils.convertToInt(tmpBuffer, readIndex);
		readIndex += 4;

		frameHeader = new FrameHeader(length, type, flag, streamIdentifier);

		return frameHeader;
	}
	
	public void convertToBinary(byte[] buffer, int off)
	{
		
	}
	

}
