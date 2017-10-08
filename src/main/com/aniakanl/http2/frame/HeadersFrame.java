package com.aniakanl.http2.frame;

import java.util.Arrays;
import java.util.EnumSet;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;
import com.aniakanl.utils.Utils;

/**
 * [rfc7540 Section 6.2] The HEADERS frame (type=0x1) is used to open a stream
 * (Section 5.1), and additionally carries a header block fragment. HEADERS
 * frames can be sent on a stream in the "idle", "reserved (local)", "open", or
 * "half-closed (remote)" state.
 */
public class HeadersFrame extends BaseFrame {

	private int padLength;
	private boolean isExclusive;
	private long streamIdentifier;
	private int weight;
	private byte[] headerBlock;
	private byte[] padding;

	public HeadersFrame() {
		this(new FrameHeader(0, FrameType.SETTINGS, EnumSet.noneOf(FrameFlag.class), 0));
	}

	public HeadersFrame(FrameHeader header) {
		super(header);
		// TODO Auto-generated constructor stub
	}

	/**
	 * An 8-bit field containing the length of the frame padding in units of
	 * octets. This field is only present if the PADDED flag is set.
	 */
	public int getPadLength() {
		return padLength;
	}

	public void setPadLength(int padLength) {
		this.padLength = padLength;
	}

	/**
	 * A single-bit flag indicating that the stream dependency is exclusive (see
	 * Section 5.3). This field is only present if the PRIORITY flag is set.
	 */
	public boolean getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(boolean isExclusive) {
		this.isExclusive = isExclusive;
	}

	/**
	 * A 31-bit stream identifier for the stream that this stream depends on
	 * (see Section 5.3). This field is only present if the PRIORITY flag is
	 * set.
	 */
	public long getStreamIdentifier() {
		return streamIdentifier;
	}

	public void setStreamIdentifier(long streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}

	/**
	 * An unsigned 8-bit integer representing a priority weight for the stream
	 * (see Section 5.3). Add one to the value to obtain a weight between 1 and
	 * 256. This field is only present if the PRIORITY flag is set.
	 */
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * A header block fragment (Section 4.3).
	 */
	public byte[] getHeaderBlock() {
		return headerBlock;
	}

	public void setHeaderBlock(byte[] headerBlock) {
		this.headerBlock = headerBlock;
	}

	/**
	 * Padding octets.
	 */
	public byte[] getPadding() {
		return padding;
	}

	public void setPadding(byte[] padding) {
		this.padding = padding;
	}

	/**
	 * 
	 * @param frameBody
	 *            payload of the HeadersFrame after consuming the FrameHeader
	 * @param header
	 *            FrameHeader
	 * @return HeadersFrame object
	 * @throws HTTP2Exception
	 * @throws Exception
	 */
	public static HeadersFrame parse(byte[] frameBody, FrameHeader header) throws HTTP2Exception, Exception {
		HeadersFrame result = null;

		if (frameBody != null) {

			/**
			 * [rfc7540 Section 4.2] A frame size error in a frame that could
			 * alter the state of the entire connection MUST be treated as a
			 * connection error (Section 5.4.1);
			 */

			if (header.getLength() == frameBody.length) {

				result = new HeadersFrame(header);

				int paramIndex = 0;

				HeadersFrame headerFrame = new HeadersFrame();

				if (header.getFlags().contains(FrameFlag.PADDED)) {
					headerFrame.setPadLength(Utils.convertToInt(frameBody, paramIndex, 1));
					paramIndex += 1;
				}

				headerFrame.setStreamIdentifier(Utils.convertToLong(frameBody, paramIndex, 4));
				paramIndex += 4;

				if (header.getFlags().contains(FrameFlag.PRIORITY)) {
					if ((headerFrame.getStreamIdentifier() & 0x80000000L) != 0x0) {
						headerFrame.setIsExclusive(true);
					}
				}

				headerFrame.setStreamIdentifier(headerFrame.getStreamIdentifier() & 0x7FFFFFFFL);

				if (headerFrame.isExclusive == true) {
					headerFrame.setWeight(Utils.convertToInt(frameBody, paramIndex, 1) + 1);
					paramIndex += 1;
				}

				headerFrame.setHeaderBlock(
						Arrays.copyOfRange(frameBody, paramIndex, (header.getLength() - headerFrame.getPadLength())));
				paramIndex += (header.getLength() - headerFrame.getPadLength()) + 1;

				headerFrame.setPadding(Arrays.copyOfRange(frameBody, paramIndex, headerFrame.getPadLength()));

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
