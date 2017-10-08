package com.aniakanl.http2.frame;

import java.util.EnumSet;

import com.aniakanl.utils.Utils;

/**
 * Create a frame header object
 */
public class FrameHeader {
	
	static final int HEADER_SIZE = 9;

	private int length;
	private FrameType type;
	private EnumSet<FrameFlag> flags;
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
	 * @param flags
	 *            defined as an EnumSet<FrameFlag>, it identifies flags associated with a
	 *            particular frame
	 */
	public EnumSet<FrameFlag> getFlags() {
		return flags;
	}

	/**
	 * @param streamIdentifier
	 *            31-bit unsigned integer uniquely identifies a frame
	 */
	public int getStreamIdentifier() {
		return streamIdentifier;
	}

    FrameHeader(int length, FrameType type, EnumSet<FrameFlag> flags, int streamIdentifier) {
		this.length = length;
		this.type = type;
		this.flags = flags;
		this.streamIdentifier = streamIdentifier;
	}

	/**
	 * Parse the 9 bytes frame header to determine length, type, flags and the stream identifier
	 * @param tmpBuffer 9 bytes frame header
	 * @return
	 * @throws Exception 
	 */
    // TODO validate the frame size, frame number, and number of frames in session based on the SETTINGS frame
	public static FrameHeader Parse(byte[] tmpBuffer) throws Exception {
		FrameHeader frameHeader = null;

		FrameType type = null;
		EnumSet<FrameFlag> flag = null;
		int streamIdentifier = 0;
		int length = 0;
		int readIndex = 0;

		length = Utils.convertToInt(tmpBuffer, readIndex, 3);
		readIndex += 3;

		type =FrameType.getEnum(tmpBuffer[readIndex]); 
		readIndex++;

		flag = FrameFlag.getEnumSet( tmpBuffer[readIndex], type);
		readIndex++;

		streamIdentifier = Utils.convertToInt(tmpBuffer, readIndex);
		readIndex += 4;

		frameHeader = new FrameHeader(length, type, flag, streamIdentifier);

		return frameHeader;
	}
	
	public void convertToBinary(byte[] buffer, int off)
	{
		 Utils.convertToBinary( buffer, off, this.length, 3);
		 off += 3;
		 buffer[off] = this.getType().value;
		 off += 1;
		 buffer[off] = FrameFlag.getValue(this.getFlags());
		 off += 1;
		 Utils.convertToBinary( buffer, off, this.streamIdentifier);
			 
	}
	

}
