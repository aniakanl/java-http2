package com.aniakanl.http2.frame;

import java.util.EnumSet;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;
import com.aniakanl.utils.Utils;

public class HeaderFrame extends BaseFrame {
	
	private int padLength;
	private int priority;
	private int streamIdentifier;
	private int weight;
	private byte[] headerBlock;
	private byte[] padding;
	
	public HeaderFrame() {
		this(new FrameHeader(0, FrameType.SETTINGS, EnumSet.noneOf(FrameFlag.class) , 0));
	}
	
	public HeaderFrame(FrameHeader header) {
		super(header);
		// TODO Auto-generated constructor stub
	}
		
	public int getPadLength() {
		return padLength;
	}

	public void setPadLength(int padLength) {
		this.padLength = padLength;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStreamIdentifier() {
		return streamIdentifier;
	}

	public void setStreamIdentifier(int streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public byte[] getHeaderBlock() {
		return headerBlock;
	}

	public void setHeaderBlock(byte[] headerBlock) {
		this.headerBlock = headerBlock;
	}

	public byte[] getPadding() {
		return padding;
	}

	public void setPadding(byte[] padding) {
		this.padding = padding;
	}

	public static HeaderFrame parse(byte[] frameBody, FrameHeader header) throws HTTP2Exception {
		HeaderFrame result = null;
				
		if (frameBody != null) {

			// rfc7540 (section 4.2) A frame size error in a frame that could
			// alter the state of
			// the entire connection MUST be treated as a connection error
			// (Section 5.4.1);
			if (header.getLength() == frameBody.length) {

				result = new HeaderFrame(header);

				int paramIndex = 0;
				
				HeaderFrame headerFrame = new HeaderFrame();
				
				if(header.getFlags().contains(FrameFlag.PADDED)){
					//headerFrame.setPadLength(Utils.convertToInt(frameBody, 0, 8));
				}
				//TODO implement the logic to parse the frame body
			} else {
				throw new HTTP2Exception(HTTP2ErrorCode.FRAME_SIZE_ERROR);
			}

		}
		return result;
	}

	@Override
	public byte[] convertToBinary() {
		// TODO Auto-generated method stub
		return null;
	}

}
