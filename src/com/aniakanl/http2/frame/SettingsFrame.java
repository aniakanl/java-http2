package com.aniakanl.http2.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsFrame extends BaseFrame{

	ArrayList<SettingParameter> params = new ArrayList<>();
	
	private SettingsFrame() {
		super();
	}
	
	public static SettingsFrame parse(byte[] frameBody) throws Exception
	{
		SettingsFrame result = null;
		
		if(frameBody != null)
		{
			result = new SettingsFrame();
			
			int paramIndex=0;
			
			while(paramIndex < frameBody.length)
			{
				result.params.add(SettingParameter.parse(Arrays.copyOfRange(frameBody, paramIndex, paramIndex+6)));
				paramIndex += 6;
			}
			
			
		}
		return result;
	}
	
	

}
