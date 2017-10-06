package com.aniakanl.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils {

	public static int convertToInt(byte[] bytes, int off) throws Exception {
		return convertToInt(bytes, off, 4);
	}

	public static int convertToInt(byte[] bytes, int off, int length) throws Exception {
		int result = 0;
		if (off + length <= bytes.length) {

			int counter = 0;
			for (int i = off + length-1 ; i >=off ; i--) {
				result |= (bytes[i] & 0x00ff) << (8 * counter);
				counter++;
			}

		} else {
			throw new Exception(String.format("cannot read %s bytes from offset, goes beyond array boundries", length));
		}
		return result;

	}

	public static long convertToLong(byte[] bytes, int off) throws Exception {
		return convertToLong(bytes, off, 8);

	}

	public static long convertToLong(byte[] bytes, int off, int length) throws Exception {
		long result = 0;
		if (off + length <= bytes.length) {

			int counter = 0;
			for (int i = off + length-1 ; i >=off ; i--) {
				result |= (bytes[i] & 0x00ff) << (8 * counter);
				counter++;
			}

		} else {
			throw new Exception(String.format("cannot read %s bytes from offset, goes beyond array boundries", length));
		}
		return result;

	}

	public static double convertToDouble(byte[] bytes) {
		byte[] reverse = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			reverse[bytes.length - 1 - i] = bytes[i];
		}

		return ByteBuffer.wrap(reverse).getDouble();
	}

	public static String convertToString(byte[] buffer, int off) {
		String result = "";
		for (int i = off; buffer[i] != '\0' && i < buffer.length; i++) {
			result += (char) buffer[i];

		}
		return result;

	}

	public static byte[] convertToBinary(int input) {
		byte[] result = new byte[4];
		for (int i = 0; i < 4; i++) {
			result[i] = (byte) ((input >> 8 * i) & 255);

		}
		return result;
	}

	public static byte[] convertToBinary(long input) {

		byte[] result = new byte[8];
		for (int i = 0; i < 8; i++) {
			result[i] = (byte) ((input >> 8 * i) & 255);
		}
		return result;
	}

	public static byte[] convertToBinary(String input) {
		byte[] inputArr = input.getBytes();
		byte[] result = new byte[inputArr.length + 1];
		result[inputArr.length] = 0; // cstring must end with null byte ('\0')
		System.arraycopy(inputArr, 0, result, 0, inputArr.length);
		return result;
	}

	public static byte[] convertToBinary(double value) {
		byte[] bytes = new byte[8];
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		byteBuffer.putDouble(value);
		return bytes;
	}

	final protected static char[] hexArray = "0123456789abcdef".toCharArray();

	public static String convertByteArrToHexStr(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return "0x" + new String(hexChars);
	}

}