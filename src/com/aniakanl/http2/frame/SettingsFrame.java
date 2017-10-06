package com.aniakanl.http2.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsFrame extends BaseFrame{

	ArrayList<SettingParameter> params = new ArrayList<>();
	
	private SettingsFrame() {
		super();
	}
	
	public static SettingsFrame parse(byte[] frameBody, FrameHeader header) throws Exception
	{
		SettingsFrame result = null;
		
		if(frameBody != null && header.getLength() == frameBody.length)
		{
			result = new SettingsFrame();
			
			result.setHeader(header);
			
			int paramIndex=0;
			
			while(paramIndex < frameBody.length)
			{
				result.params.add(SettingParameter.parse(Arrays.copyOfRange(frameBody, paramIndex, paramIndex+6)));
				paramIndex += 6;
			}
			
			
		}
		return result;
	}

	@Override
	public byte[] convertToBinary() {
	   
		int settingBodySize = params.size() * SettingParameter.PARAMETER_SIZE;
		byte[] buffer = new byte[FrameHeader.HEADER_SIZE + settingBodySize];
		
		getHeader().convertToBinary(buffer, 0);
		
		for(int i= 0; i< params.size(); i++)
		{
			params.get(i).convertToBinary(buffer, FrameHeader.HEADER_SIZE + (i * SettingParameter.PARAMETER_SIZE));
		}
		
		return buffer;
	}
	
	

}
