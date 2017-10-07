package com.aniakanl.http2.frame;

/**
 * An enumeration to define all the Flags that can be attached to a frame
 */
public enum FrameFlag {

	END_STREAM((byte)0x1), 
	ACK((byte)0x1), 
	END_HEADERS((byte)0x4), 
	PADDED((byte)0x8), 
	PRIORITY((byte)0x20), 
	NONE((byte)0x0);


	byte value;

	FrameFlag(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public static FrameFlag getEnum(byte value, FrameType type) {
		FrameFlag result = FrameFlag.NONE;

		if (type == FrameType.SETTINGS || type == FrameType.PING) {
			if (value == 1) {
				result = FrameFlag.ACK;
			}
		} 
		else {
			switch (value) {
			case 0x1:
				result = FrameFlag.END_STREAM;
				break;
			case 0x4:
				result = FrameFlag.END_HEADERS;
				break;
			case 0x8:
				result = FrameFlag.PADDED;
				break;
			case 0x20:
				result = FrameFlag.PRIORITY;
				break;
			}
		}
		return result;
	}

}
