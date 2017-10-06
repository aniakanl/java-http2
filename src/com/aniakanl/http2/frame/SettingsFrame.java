package com.aniakanl.http2.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;

public class SettingsFrame extends BaseFrame {

	ArrayList<SettingParameter> params = new ArrayList<>();

	public SettingsFrame() {
		this(new FrameHeader(0, FrameType.SETTINGS, FrameFlag.NONE, 0));
	}

	public SettingsFrame(FrameHeader header) {
		super(header);
	}

	public ArrayList<SettingParameter> getSettingParameters() {
		return params;
	}

	public static SettingsFrame parse(byte[] frameBody, FrameHeader header) throws HTTP2Exception {
		SettingsFrame result = null;

		if (frameBody != null) {
			
			// rfc7540 (section 4.2) A frame size error in a frame that could alter the state of
			// the entire connection MUST be treated as a connection error (Section 5.4.1); 
			if (header.getLength() == frameBody.length) {
				
				result = new SettingsFrame(header);

				int paramIndex = 0;

				while (paramIndex < frameBody.length) {
					result.params
							.add(SettingParameter.parse(Arrays.copyOfRange(frameBody, paramIndex, paramIndex + 6)));
					paramIndex += 6;
				}
			}
			else
			{
				throw new HTTP2Exception(HTTP2ErrorCode.FRAME_SIZE_ERROR);
			}

		}
		return result;
	}

	@Override
	public byte[] convertToBinary() {

		int settingBodySize = params.size() * SettingParameter.PARAMETER_SIZE;
		byte[] buffer = new byte[FrameHeader.HEADER_SIZE + settingBodySize];

		getHeader().convertToBinary(buffer, 0);

		for (int i = 0; i < params.size(); i++) {
			params.get(i).convertToBinary(buffer, FrameHeader.HEADER_SIZE + (i * SettingParameter.PARAMETER_SIZE));
		}

		return buffer;
	}

}
