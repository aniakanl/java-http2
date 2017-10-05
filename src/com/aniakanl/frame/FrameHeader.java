package com.aniakanl.frame;

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
	 */
	public static FrameHeader Parse(byte[] tmpBuffer) {
		FrameHeader frameHeader = null;

		FrameType type = null;
		FrameFlag flag = null;
		int streamIdentifier = 0;
		int length = 0;
		int readIndex = 0;

		length = (tmpBuffer[2] & 0xFF) | ((tmpBuffer[1] & 0xFF) << 8) | ((tmpBuffer[0] & 0xFF) << 16);

		readIndex = 3;

		int frame_type = tmpBuffer[readIndex];

		switch (frame_type) {
		case 1:
			type = FrameType.HEADERS;
			break;
		case 2:
			type = FrameType.PRIORITY;
			break;
		case 3:
			type = FrameType.RST_STREAM;
			break;
		case 4:
			type = FrameType.SETTINGS;
			break;
		case 5:
			type = FrameType.PUSH_PROMISE;
			break;
		case 6:
			type = FrameType.PING;
			break;
		case 7:
			type = FrameType.GOAWAY;
			break;
		case 8:
			type = FrameType.WINDOW_UPDATE;
			break;
		case 9:
			type = FrameType.CONTINUATION;
			break;
		}

		readIndex++;

		int frame_flag = tmpBuffer[readIndex];

		if (type == FrameType.SETTINGS || type == FrameType.PING) {
			switch (frame_flag) {
			case 1:
				flag = FrameFlag.ACK;
			}
		} else {
			switch (frame_flag) {
			case 1:
				flag = FrameFlag.END_STREAM;
				break;
			case 4:
				flag = FrameFlag.END_HEADERS;
				break;
			case 8:
				flag = FrameFlag.PADDED;
				break;
			case 32:
				flag = FrameFlag.PRIORITY;
				break;
			}
		}

		readIndex++;

		streamIdentifier = (tmpBuffer[readIndex + 3] & 0xFF) | ((tmpBuffer[readIndex + 2] & 0xFF) << 8)
				| ((tmpBuffer[readIndex + 1] & 0xFF) << 16) | (((tmpBuffer[readIndex] & 01111111) & 0xFF) << 24);

		frameHeader = new FrameHeader(length, type, flag, streamIdentifier);

		return frameHeader;
	}

}
