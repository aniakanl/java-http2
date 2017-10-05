package com.aniakanl.frame;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.aniakanl.client.ExBufferedInputStream;

public class FrameSerializer {
	
	public BaseFrame deSerialize(ExBufferedInputStream bufferedInputStream){
		
		BaseFrame result = null;
		byte[] tmpBuffer = new byte[9];
		FrameType type = null;
		FrameFlag flag = null;
		int streamIdentifier = 0;
		int length = 0;
		
		try {
			tmpBuffer = IOUtils.toByteArray(bufferedInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FrameHeader header = FrameHeader.Parse(tmpBuffer);
		
		switch(header.Type)
		{
			case HEADERS:
				
				result = new HeaderFrame();
				break;
				
				
		}
		
		
		/*
		int readIndex = 0;
		
		while(readIndex != tmpBuffer.length){
			length = (tmpBuffer[2] & 0xFF) | ((tmpBuffer[1] & 0xFF) <<8) | ((tmpBuffer[0] & 0xFF) << 16);					
			readIndex = 3;
			
			
			int frame_type = tmpBuffer[readIndex];
			
			
			
			switch(frame_type){
			case 1: type = FrameType.HEADERS;
			case 2: type = FrameType.PRIORITY;
			case 3: type = FrameType.RST_STREAM;
			case 4: type = FrameType.SETTINGS;
			case 5: type = FrameType.PUSH_PROMISE;
			case 6: type = FrameType.PING;
			case 7: type = FrameType.GOAWAY;
			case 8: type = FrameType.WINDOW_UPDATE;
			case 9: type = FrameType.CONTINUATION;
			}
			
			readIndex++;
			
			int frame_flag = tmpBuffer[readIndex];			
				
			if(type == FrameType.SETTINGS || type == FrameType.PING){
				switch(frame_flag){
				case 1: flag = FrameFlag.ACK;
				}				
			}else{
				switch(frame_flag){
				case 1: flag = FrameFlag.END_STREAM;
				case 4: flag = FrameFlag.END_HEADERS;
				case 8: flag = FrameFlag.PADDED;
				case 32: flag = FrameFlag.PRIORITY;
				}
			}
			
			readIndex++;
			
			streamIdentifier = (tmpBuffer[readIndex+3] & 0xFF) | ((tmpBuffer[readIndex+2] & 0xFF) << 8) | 
					((tmpBuffer[readIndex+1] & 0xFF) << 16) | (((tmpBuffer[readIndex] & 01111111) & 0xFF) << 24);
			
		}*/
		
		//FrameHeader frameHeader = new FrameHeader(length, type, flag, streamIdentifier);
		
		return result;
	}

}
