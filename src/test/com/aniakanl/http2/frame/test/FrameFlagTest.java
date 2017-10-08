package com.aniakanl.http2.frame.test;

import static org.junit.Assert.*;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;

import com.aniakanl.http2.HTTP2Exception;
import com.aniakanl.http2.frame.FrameFlag;
import com.aniakanl.http2.frame.FrameType;

public class FrameFlagTest {

	private FrameFlag testInstance;

	@Before
	public void initialize() {

	}

	@Test
	public void testGetEnumSet() throws HTTP2Exception {

		EnumSet<FrameFlag> flags = FrameFlag.getEnumSet((byte) 9, FrameType.DATA);

		assertTrue(flags.contains(FrameFlag.END_STREAM));

		assertTrue(flags.contains(FrameFlag.PADDED));
	}

	@Test
	public void testGetEnumSet1() throws HTTP2Exception {

		EnumSet<FrameFlag> flags = FrameFlag.getEnumSet((byte) 9, FrameType.SETTINGS);

		assertTrue(flags.contains(FrameFlag.ACK));

		assertTrue(flags.contains(FrameFlag.PADDED));
	}

	@Test(expected = HTTP2Exception.class)
	public void testPassingInvalidValueToGetEnumSet() throws HTTP2Exception {

		FrameFlag.getEnumSet((byte) 6, FrameType.DATA);

	}

	@Test
	public void testGetValueEnumSetOfFrameFlag() throws HTTP2Exception {

		EnumSet<FrameFlag> flags = FrameFlag.getEnumSet((byte) 0x29, FrameType.SETTINGS);

		assertSame((byte)  0x29, FrameFlag.getValue(flags));
	}

}
