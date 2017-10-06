package com.aniakanl.http2.frame;
import com.aniakanl.utils.Utils;

public class SettingParameter {
	static final int PARAMETER_SIZE = 6;
	public SettingIdentifier identifier;
	public long value;
	
	public static SettingParameter parse(byte[] param) throws Exception
	{
		SettingParameter result = null;
		
		if(param.length == 6)
		{
			result = new SettingParameter();
			result.identifier = SettingIdentifier.getEnum( Utils.convertToInt(param,0, 2));
			result.value = Utils.convertToLong(param, 2, 4);	
		}
		return result;
	}
	
	public void convertToBinary(byte[] buffer, int off)
	{
		
	}
}
