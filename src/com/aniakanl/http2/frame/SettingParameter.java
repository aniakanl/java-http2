package com.aniakanl.http2.frame;
import com.aniakanl.http2.HTTP2ErrorCode;
import com.aniakanl.http2.HTTP2Exception;
import com.aniakanl.utils.Utils;

public class SettingParameter {
	static final int PARAMETER_SIZE = 6;
	public SettingIdentifier identifier;
	public long value;
	
	public static SettingParameter parse(byte[] param) throws HTTP2Exception 
	{
		SettingParameter result = null;
		
		try
		{
			if(param.length == 6)
			{
				result = new SettingParameter();
				result.identifier = SettingIdentifier.getEnum( Utils.convertToInt(param,0, 2));
				result.value = Utils.convertToLong(param, 2, 4);	
			}
		}
		catch(Exception exp)
		{
			throw new HTTP2Exception(HTTP2ErrorCode.INTERNAL_ERROR);
		}
		return result;
	}
	
	public void convertToBinary(byte[] buffer, int off)
	{
		
	}
}
