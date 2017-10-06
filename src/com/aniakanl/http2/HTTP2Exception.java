package com.aniakanl.http2;

public class HTTP2Exception extends Exception {
	
	private HTTP2ErrorCode errorCode;

	public HTTP2Exception(HTTP2ErrorCode errorCode) {
		this(errorCode,"");
	}

	public HTTP2Exception(HTTP2ErrorCode errorCod, String message) {
		super(message);
		
	}
	
	public HTTP2ErrorCode getErrorCode()
	{
		return errorCode;
	}
	
}
