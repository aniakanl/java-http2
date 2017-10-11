package com.aniakanl.http2.frame.test;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import com.aniakanl.utils.Utils;

public class UtilsTest {

	@Test
	public void testDoubleToBinary() {
		
		double testDouble = 23;
		byte[] expectedResult = { 0, 0, 0, 0, 0, 0, 55, 64};

		assertArrayEquals(expectedResult, Utils.convertToBinary(testDouble));

	}

	@Test
	public void testStringToBinary() {
		
		String testString = "TestString";
		byte[] expectedResult = {84,101,115,116,83,116,114,105,110,103,0};
		
		assertArrayEquals(expectedResult, Utils.convertToBinary(testString));

	}
	
	public void testLongToBinary(){
		
	}

}
