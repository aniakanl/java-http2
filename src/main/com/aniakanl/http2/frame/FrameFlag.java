package com.aniakanl.http2.frame;

import java.util.EnumSet;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;

/**
 * An enumeration to define all the Flags that can be attached to a frame
 */
public enum FrameFlag {

	END_STREAM((byte)0x1), 
	ACK((byte)0x1), 
	END_HEADERS((byte)0x4), 
	PADDED((byte)0x8), 
	PRIORITY((byte)0x20);


	byte value;

	FrameFlag(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public static EnumSet<FrameFlag> getEnumSet(byte value, FrameType type) throws HTTP2Exception {

		// Empty EnumSet
		EnumSet<FrameFlag> result = EnumSet.noneOf(FrameFlag.class);

		// For each flag in FrameFlag
		for (FrameFlag flag : FrameFlag.values()) {
			// Check whether the flag bit is set
			if ((value & flag.value) == 1) {
				result.add(flag);
				
				// reset the flag bit
				value = (byte)(value & flag.value);
			}
		}
		
		if(value != 0)
			throw new HTTP2Exception(HTTP2ErrorCode.CONNECT_ERROR, "Unknown bit flag is set: " + value);

		return result;
	}
	
	public static byte getValue(EnumSet<FrameFlag> flags) {

		byte result = 0;

		for (FrameFlag flag : flags) {
			result = (byte) (result | flag.getValue());
		}

		return result;
	}

}
