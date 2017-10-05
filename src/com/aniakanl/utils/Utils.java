package com.aniakanl.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils {
	public static int convertToInt(byte[] bytes, int off) throws Exception {
		int result = 0;
		if (off+3<bytes.length) {
			//result = (int) ((bytes[off] & 0x00FF) + (bytes[off+1]& 0x00FF) * (1<<8) + (bytes[off+2]& 0x00FF) * (1<<16)
			//		+ (bytes[off+3]& 0x00FF) * (1<<24));

			result = bytes[off]&0x00ff |
					((bytes[off+1]&0x00ff) <<8) |
					((bytes[off+2]&0x00ff) <<16)|
					((bytes[off+3]&0x00ff) <<24);

		} else {
			throw new Exception("cannot read 4 bytes from offset, goes beyond array boundries");
		}
		return result;

	}
	
	public static long convertToLong(byte[] bytes, int off) throws Exception {
		long result = 0;
		if (off+7<bytes.length) {
			result = (long) ((bytes[off] & 0x00FF) |
					((bytes[off+1]&0x00ff)<<8) |
					((bytes[off+2]&0x00ff)<<16)|
					((bytes[off+3]&0x00ff)<<24)|
					((bytes[off+4]&0x00ff)<<32)|
					((bytes[off+5]&0x00ff)<<40)|
					((bytes[off+6]&0x00ff)<<48)|
					((bytes[off+7]&0x00ff)<<56));

		} else {
			throw new Exception("cannot read 8 bytes from offset, goes beyond array boundries");
		}
		return result;

	}
	
	public static double convertToDouble(byte[] bytes) {
		byte[] reverse = new byte[bytes.length];
		for(int i=0;i<bytes.length;i++){
			reverse[bytes.length-1-i]=bytes[i];
		}
		
	    return ByteBuffer.wrap(reverse).getDouble();
	}
	
	public static String convertToString(byte[] buffer, int off){
		String result="";
		for(int i=off;buffer[i]!='\0'&& i<buffer.length;i++){
			result+=(char) buffer[i];
			
		}
		return result;
		
	}
	
	
	public static byte[] convertToBinary(int input){
		byte[] result= new byte[4];
		for(int i=0;i<4;i++){
			result[i]=(byte) ((input>>8*i)&255);
		
		}
		return result;
	}
	
	public static byte[] convertToBinary(long input){

		byte[] result=new byte[8];
		for(int i=0;i<8;i++){
			result[i]=(byte) ((input>>8*i)&255);
		}
		return result;
	}
	
	public static byte[] convertToBinary(String input)
	{
		byte[] inputArr = input.getBytes();
		byte[] result = new byte[inputArr.length +1];
		result[inputArr.length] = 0; // cstring must end with null byte ('\0')
		System.arraycopy(inputArr, 0, result, 0, inputArr.length);
		return result;
	}
	
	public static byte[] convertToBinary(double value) {
	    byte[] bytes = new byte[8];
	    ByteBuffer byteBuffer =ByteBuffer.wrap(bytes);
	    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	    byteBuffer.putDouble(value);
	    return bytes;
	}
	
	final protected static char[] hexArray = "0123456789abcdef".toCharArray();
	public static String convertByteArrToHexStr(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return "0x"+ new String(hexChars);
	}
	


}