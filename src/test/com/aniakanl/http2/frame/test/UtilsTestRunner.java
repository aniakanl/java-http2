package com.aniakanl.http2.frame.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UtilsTestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UtilsTest.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}
